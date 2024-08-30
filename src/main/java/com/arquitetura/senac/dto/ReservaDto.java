package com.arquitetura.senac.dto;

import jakarta.validation.constraints.NotNull;

public record ReservaDto(
        @NotNull(message = "Livro ID é obrigatório")
        Long livroId,

        @NotNull(message = "Usuário é obrigatório")
        String nome
) {
}
