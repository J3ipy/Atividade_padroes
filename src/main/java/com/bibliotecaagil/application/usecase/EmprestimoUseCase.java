package com.bibliotecaagil.application.usecase;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.bibliotecaagil.application.dto.EmprestimoResponseDTO;
import com.bibliotecaagil.application.dto.RealizarEmprestimoRequestDTO;
import com.bibliotecaagil.application.service.EmprestimoService;
import com.bibliotecaagil.domain.model.Emprestimo;
import com.bibliotecaagil.domain.model.Livro;
import com.bibliotecaagil.domain.model.Membro;
import com.bibliotecaagil.domain.repository.EmprestimoRepository;
import com.bibliotecaagil.domain.repository.LivroRepository;
import com.bibliotecaagil.domain.repository.MembroRepository;
import com.bibliotecaagil.infra.exception.BusinessException;

import jakarta.transaction.Transactional;

@Service
public class EmprestimoUseCase implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final MembroRepository membroRepository;
    private final LivroRepository livroRepository;

    public EmprestimoUseCase(EmprestimoRepository e, MembroRepository m, LivroRepository l) {
        this.emprestimoRepository = e;
        this.membroRepository = m;
        this.livroRepository = l;
    }

    @Override
    @Transactional
    public EmprestimoResponseDTO realizarEmprestimo(RealizarEmprestimoRequestDTO dto) {
        Membro membro = membroRepository.findById(dto.getMembroId())
                .orElseThrow(() -> new BusinessException("Membro não encontrado."));

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new BusinessException("Livro não encontrado."));

        // Regras de negócio da aplicação
        if (!membro.podeRealizarEmprestimo()) {
            throw new BusinessException("Membro com pendências não pode realizar empréstimo.");
        }
        if (!livro.estaDisponivel()) {
            throw new BusinessException("Livro não está disponível para empréstimo.");
        }

        // Altera o status do livro
        livro.setStatus(Livro.StatusLivro.EMPRESTADO);
        livroRepository.save(livro);

        // Cria o registro do empréstimo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setMembro(membro);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataPrevistaDevolucao(LocalDate.now().plusDays(14));
        emprestimo.setStatus(Emprestimo.StatusEmprestimo.ATIVO);
        
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        return EmprestimoResponseDTO.fromEntity(emprestimoSalvo);
    }

    @Override
    @Transactional
    public EmprestimoResponseDTO realizarDevolucao(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
            .orElseThrow(() -> new BusinessException("Empréstimo não encontrado."));

        // Altera o status do empréstimo para concluído
        emprestimo.setStatus(Emprestimo.StatusEmprestimo.CONCLUIDO);
        emprestimo.setDataDevolucaoReal(LocalDate.now());

        Livro livro = emprestimo.getLivro();
        // Altera o status do livro para disponível
        livro.setStatus(Livro.StatusLivro.DISPONIVEL);
        livroRepository.save(livro);

        // A lógica de verificação de atraso e criação de multa seria implementada aqui.

        Emprestimo emprestimoDevolvido = emprestimoRepository.save(emprestimo);
        return EmprestimoResponseDTO.fromEntity(emprestimoDevolvido);
    }
}