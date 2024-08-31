package com.arquitetura.senac.dto;

import com.arquitetura.senac.enuns.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDto(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Autor ID é obrigatório")
        Long autorId,

        @NotBlank(message = "Editora é obrigatória")
        String editora,

        @NotBlank(message = "Gênero é obrigatório")
        String genero,

        Status status
) {
}
