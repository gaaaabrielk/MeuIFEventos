package com.example.meuifeventos;

public class Solicitacao {

    private String id;        // ID do documento no Firebase
    private String alunoId;   // ID do aluno que fez a solicitação
    private String itemId;    // ID do item solicitado (se necessário)
    private String status;    // Status da solicitação

    // Construtor vazio (necessário para o Firebase)
    public Solicitacao() {
    }

    // Construtor sem itemId (usado para simplificar)
    public Solicitacao(String alunoId, String status) {
        this.alunoId = alunoId;
        this.status = status;
    }

    // Construtor completo com todos os campos
    public Solicitacao(String id, String alunoId, String itemId, String status) {
        this.id = id;
        this.alunoId = alunoId;
        this.itemId = itemId;
        this.status = status;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
