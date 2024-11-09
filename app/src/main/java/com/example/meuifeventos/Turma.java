package com.example.meuifeventos;

public class Turma {
    private String nome;

    public Turma() {
        // Construtor vazio necessário para Firestore
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}


