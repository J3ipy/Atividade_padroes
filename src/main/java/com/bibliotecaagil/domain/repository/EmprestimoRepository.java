package com.bibliotecaagil.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaagil.domain.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    Optional<Emprestimo> findByLivroIdAndStatus(Long livroId, Emprestimo.StatusEmprestimo status);
}