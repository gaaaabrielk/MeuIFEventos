package com.example.meuifeventos;

import java.util.List;

import java.util.List;

public class Turma {
    private String id;       // ID único da turma
    private String nome;     // Nome da turma
    private List<String> alunos; // Lista de alunos da turma

    // Construtor
    public Turma(String id, String nome, List<String> alunos) {
        this.id = id;
        this.nome = nome;
        this.alunos = alunos;
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

    public List<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<String> alunos) {
        this.alunos = alunos;
    }
}
