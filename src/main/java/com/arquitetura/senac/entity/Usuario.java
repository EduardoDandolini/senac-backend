package com.arquitetura.senac.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Usuario extends BaseEntity {

    private String nome;
    private String email;
    private String senha;
    private String cpf;

}
