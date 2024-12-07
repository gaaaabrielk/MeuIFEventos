package com.example.meuifeventos;

import com.google.firebase.Timestamp;

public class Items {
    private String id;
    private String alunoID;
    private Timestamp dataAchado;
    private String dataEncontrado;
    private String descricao;
    private String localEncontrado;
    private String nome;
    private boolean status;
    private Timestamp dataEmprestimo; // Para o campo de data de empréstimo

    // Construtores, getters e setters

    public String getAlunoID() {
        return alunoID;
    }

    public void setAlunoID(String alunoID) {
        this.alunoID = alunoID;
    }

    public Timestamp getDataAchado() {
        return dataAchado;
    }

    public void setDataAchado(Timestamp dataAchado) {
        this.dataAchado = dataAchado;
    }

    public String getDataEncontrado() {
        return dataEncontrado;
    }

    public void setDataEncontrado(String dataEncontrado) {
        this.dataEncontrado = dataEncontrado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalEncontrado() {
        return localEncontrado;
    }

    public void setLocalEncontrado(String localEncontrado) {
        this.localEncontrado = localEncontrado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getStatus() {
        return status; // Método getStatus adicionado
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Timestamp dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo; // Setter para dataEmprestimo
    }

    // Método para transformar Timestamp em Date
    public java.util.Date getDataAchadoToDate() {
        return dataAchado != null ? dataAchado.toDate() : null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
