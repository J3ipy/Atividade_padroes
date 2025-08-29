package com.bibliotecaagil.application.dto;

import java.time.LocalDate;

import com.bibliotecaagil.domain.model.Emprestimo;

import lombok.Data;

@Data
public class EmprestimoResponseDTO {
    private Long id;
    private String nomeMembro;
    private String tituloLivro;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private String status;

    public static EmprestimoResponseDTO fromEntity(Emprestimo emprestimo) {
        EmprestimoResponseDTO dto = new EmprestimoResponseDTO();
        dto.setId(emprestimo.getId());
        dto.setNomeMembro(emprestimo.getMembro().getNome());
        dto.setTituloLivro(emprestimo.getLivro().getTitulo());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataPrevistaDevolucao(emprestimo.getDataPrevistaDevolucao());
        dto.setStatus(emprestimo.getStatus().name());
        return dto;
    }
}

