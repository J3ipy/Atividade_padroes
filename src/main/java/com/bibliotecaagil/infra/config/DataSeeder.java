package com.bibliotecaagil.infra.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bibliotecaagil.domain.model.Livro;
import com.bibliotecaagil.domain.model.Membro;
import com.bibliotecaagil.domain.repository.LivroRepository;
import com.bibliotecaagil.domain.repository.MembroRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final MembroRepository membroRepository;
    private final LivroRepository livroRepository;

    public DataSeeder(MembroRepository membroRepository, LivroRepository livroRepository) {
        this.membroRepository = membroRepository;
        this.livroRepository = livroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Populando o banco de dados com dados de exemplo...");

        // --- Criando Membros de Exemplo ---
        Membro membro1 = new Membro(null, "João Pedro Santana", "jp@email.com", Membro.StatusMembro.REGULAR);
        Membro membro2 = new Membro(null, "Maria Oliveira", "maria@email.com", Membro.StatusMembro.COM_PENDENCIA);
        Membro membro3 = new Membro(null, "Carlos Souza", "carlos@email.com", Membro.StatusMembro.REGULAR);
        
        membroRepository.saveAll(Arrays.asList(membro1, membro2, membro3));
        System.out.println("Membros criados com IDs: 1, 2, 3");

        // --- Criando Livros de Exemplo ---
        Livro livro1 = new Livro(null, "Arquitetura Limpa", "Robert C. Martin", "978-8576089488", Livro.StatusLivro.DISPONIVEL);
        Livro livro2 = new Livro(null, "Padrões de Projeto", "Erich Gamma", "978-8573076103", Livro.StatusLivro.DISPONIVEL);
        Livro livro3 = new Livro(null, "O Programador Pragmático", "Andrew Hunt", "978-8573077377", Livro.StatusLivro.DISPONIVEL);
        Livro livro4 = new Livro(null, "Refatoração", "Martin Fowler", "978-8575227244", Livro.StatusLivro.EMPRESTADO);

        livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4));
        System.out.println("Livros criados com IDs: 1, 2, 3, 4");
        
        System.out.println("Banco de dados populado com sucesso!");
    }
}