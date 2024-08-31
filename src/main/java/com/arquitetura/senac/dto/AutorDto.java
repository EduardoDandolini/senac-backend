package com.arquitetura.senac.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record AutorDto(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Data de nascimento é obrigatória")
        LocalDateTime dataNascimento,

        @Email(message = "Email deve ser válido")
        @NotBlank(message = "Email é obrigatório")
        String email
)
{}
