package com.arquitetura.senac.entity;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Usuario extends BaseEntity {

    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataDeNascimento;
    private String endereco;
    private String telefone;
}
