package com.bibliotecaagil.application.service;

import com.bibliotecaagil.application.dto.EmprestimoResponseDTO;
import com.bibliotecaagil.application.dto.RealizarEmprestimoRequestDTO;

public interface EmprestimoService {
    EmprestimoResponseDTO realizarEmprestimo(RealizarEmprestimoRequestDTO requestDTO);
    EmprestimoResponseDTO realizarDevolucao(Long emprestimoId);
}
