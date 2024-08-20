package com.arquitetura.senac.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
public class Autor extends BaseEntity{

    private String nome;
    private LocalDateTime dataNascimento;
    private String email;
    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

}
