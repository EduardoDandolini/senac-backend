package com.arquitetura.senac.enuns;

public enum Roles {

    BASIC(1L, "Basic"),
    ADMIN(2L, "Admin");

    private Long id;
    private String nome;

    Roles(Long id, String nome) {
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
