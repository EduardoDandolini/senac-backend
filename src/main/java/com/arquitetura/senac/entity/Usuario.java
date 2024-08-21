package com.arquitetura.senac.entity;

import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.boot.model.source.spi.EmbeddableMapping;

import java.time.LocalDate;
import java.util.List;

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
    @OneToMany(mappedBy = "emprestimo")
    private List<Emprestimo> emprestimos;







}
