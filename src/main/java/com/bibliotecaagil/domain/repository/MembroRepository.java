package com.bibliotecaagil.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaagil.domain.model.Membro;
public interface MembroRepository extends JpaRepository<Membro, Long> {}
