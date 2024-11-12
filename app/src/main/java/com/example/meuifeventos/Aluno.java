package com.example.meuifeventos;

public class Aluno {
    private String Nome;
    private String Matricula;
    private String Turma;


    public Aluno() {

    }

    // Getters e Setters
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getTurma() {
        return Turma;
    }

    public void setTurma(String Turma) {
        this.Turma = Turma;
    }
}

