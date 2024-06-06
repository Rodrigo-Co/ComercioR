package com.example.comercior;

public class Produto {
    private String nome;
    private int iconeResource;
    private String descricao;
    // Outros detalhes do produto (preço, descrição, etc.)

    public Produto(String nome, int iconeResource, String descricao) {
        this.nome = nome;
        this.iconeResource = iconeResource;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getIconeResource() {
        return iconeResource;
    }
    public String getDescricao(){
        return descricao;
    }
}
