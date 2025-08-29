package com.bibliotecaagil.application.dto;

import lombok.Data;

@Data
public class RealizarEmprestimoRequestDTO {
    private Long membroId;
    private Long livroId;
}