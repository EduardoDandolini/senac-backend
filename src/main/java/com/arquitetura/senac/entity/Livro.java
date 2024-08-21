package com.arquitetura.senac.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class Livro extends BaseEntity {

    private String nome;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    private String editora;
    private String genero;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        DISPONIVEL(1L, "Disponível"),
        INDISPONIVEL(2L, "Indisponível");

        private Long id;
        private String nome;

        Status(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }
    }

}
