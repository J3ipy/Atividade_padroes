package com.bibliotecaagil.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaagil.domain.model.Livro;
public interface LivroRepository extends JpaRepository<Livro, Long> {}

