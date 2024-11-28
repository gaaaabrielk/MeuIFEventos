package com.example.meuifeventos;

public class Aluno {
    private String nome;
    private String matricula;

    public Aluno() {} // Construtor vazio é obrigatório para o Firestore

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}

