package com.arquitetura.senac.dto;

import com.arquitetura.senac.entity.Livro;
import com.arquitetura.senac.entity.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public record EmprestimoDto(
        @NotNull(message = "Data de empréstimo é obrigatório")
        LocalDate dtEmprestimo,

        LocalDate dtEntregaLivro,

        @NotNull(message = "Usuario ID é obrigatório")
        Long idUsuario,

        @NotNull(message = "Livro ID é obrigatório")
        Long idLivro,

        @NotNull(message = "Valor do Empréstimo é obrigatório")
        Double valorEmprestimo,

        @NotNull(message = "Taxa de juros do livro é obrigatório")
        Double taxaDeJuro
) {

}
