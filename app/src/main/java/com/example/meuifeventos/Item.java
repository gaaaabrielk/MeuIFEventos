package com.example.meuifeventos;

public class Item {
    private String id;
    private String nome;
    private int quantidade;
    private boolean disponibilidade;


    public Item() {
    }


    public Item(String nome, int quantidade, boolean disponibilidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.disponibilidade = disponibilidade;
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
