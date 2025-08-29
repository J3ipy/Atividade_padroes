package com.bibliotecaagil.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String isbn;

    @Enumerated(EnumType.STRING)
    private StatusLivro status;

    public enum StatusLivro {
        DISPONIVEL,
        EMPRESTADO
    }

    public boolean estaDisponivel() {
        return this.status == StatusLivro.DISPONIVEL;
    }
}