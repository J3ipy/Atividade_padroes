package com.bibliotecaagil.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecaagil.application.dto.EmprestimoResponseDTO;
import com.bibliotecaagil.application.dto.RealizarEmprestimoRequestDTO;
import com.bibliotecaagil.application.service.EmprestimoService;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponseDTO> realizarEmprestimo(@RequestBody RealizarEmprestimoRequestDTO requestDTO) {
        EmprestimoResponseDTO response = emprestimoService.realizarEmprestimo(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<EmprestimoResponseDTO> realizarDevolucao(@PathVariable Long id) {
        EmprestimoResponseDTO response = emprestimoService.realizarDevolucao(id);
        return ResponseEntity.ok(response);
    }
}
