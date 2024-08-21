package com.arquitetura.senac.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class Livro extends BaseEntity{

    private String nome;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String editora;
    private String genero;
    @OneToMany(mappedBy = "livro")
    private List<Emprestimo> emprestimos;

}
