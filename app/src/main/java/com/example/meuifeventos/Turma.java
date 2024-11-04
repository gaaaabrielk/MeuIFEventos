package com.example.meuifeventos;

import java.util.List;

public class Turma
{
    private String nome;
    private List<Aluno> alunos;
    private List<Evento> eventos;

    public Turma() {}

    public Turma(String nome, List<Aluno> alunos, List<Evento> eventos) {
        this.nome = nome;
        this.alunos = alunos;
        this.eventos = eventos;
    }

    public String getNome() {
        return nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public List<Evento> getEventos() {
        return eventos;
    }


}
