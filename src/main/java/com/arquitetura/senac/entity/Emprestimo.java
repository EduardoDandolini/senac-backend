package com.arquitetura.senac.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Emprestimo extends BaseEntity {

    private LocalDate dtEmprestimo;

    private LocalDate dtEntregaLivro;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name= "livro_id")
    private Livro livro;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Double valorEmprestimo;

    private Double taxaDeJuro;

}
