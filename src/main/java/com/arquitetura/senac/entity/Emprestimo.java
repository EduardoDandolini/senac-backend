package com.arquitetura.senac.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Emprestimo extends BaseEntity {
    private Date dtEmprestimo;
    private Date dtEntregaLivro;
    @ManyToOne
    @JoinColumn(name= "livro_id")
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Double valorEmprestimo;
    private Double taxaDeJuro;





}
