package com.curso_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequestDTO(
        @NotBlank(message = "Nome do curso é obrigatorio")
        String nome,
        @NotBlank(message = "Descrição do curso é obrigatorio")
        String descricao,
        @NotNull(message = "Carga Horaria minima é 1 hora")
        Integer cargaHoraria,
        @NotNull(message = "Instrutor é obrigatorio")
        Long instrutorId
) {
}
