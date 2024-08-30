package com.arquitetura.senac.entity;

import com.arquitetura.senac.enuns.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class Livro extends BaseEntity {

    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String editora;

    private String genero;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "livro")
    @JsonIgnore
    private List<Emprestimo> emprestimos;

    @OneToMany(mappedBy = "livro")
    @JsonIgnore
    private List<Reserva> reservas;

}
