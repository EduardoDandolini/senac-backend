package com.arquitetura.senac.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class Livro extends BaseEntity{

    private String nome;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    private String editora;
    private String genero;
}
