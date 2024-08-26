package com.arquitetura.senac.dto;

import java.time.LocalDate;

public record UsuarioRequest(
    String nome,
    String email,
    String senha,
    String cpf,
    LocalDate dataDeNascimento,
    String endereco,
    String telefone
) {
}
