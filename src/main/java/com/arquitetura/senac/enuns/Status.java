package com.arquitetura.senac.enuns;

import lombok.Data;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
