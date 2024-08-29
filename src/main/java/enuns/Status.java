package enuns;

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
