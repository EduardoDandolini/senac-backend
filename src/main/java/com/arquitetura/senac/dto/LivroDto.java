package com.arquitetura.senac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record LivroDto(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Autor ID é obrigatório")
        Long autorId,

        @NotBlank(message = "Editora é obrigatória")
        String editora,

        @NotBlank(message = "Gênero é obrigatório")
        String genero,

        @NotNull(message = "Status é obrigatório")
        Status status,

        List<Long> emprestimosIds,

        List<Long> reservasIds
) {

    public enum Status {
        DISPONIVEL,
        INDISPONIVEL,
        VENCIDO
    }
}
