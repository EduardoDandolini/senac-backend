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
    private List<Emprestimo> emprestimos;

    @OneToMany(mappedBy = "livro")
    private List<Reserva> reservas;

    public enum Status {
        DISPONIVEL(1L, "Disponível"),
        INDISPONIVEL(2L, "Indisponível"),
        VENCIDO(3L, "Vencido");

        private Long id;
        private String nome;

        Status(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }
    }

}
