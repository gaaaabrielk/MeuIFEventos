package com.example.meuifeventos;

public class Solicitacao {
    private String itemId;
    private String alunoId;
    private String status;

    // Construtor
    public Solicitacao(String itemId, String alunoId, String status) {
        this.itemId = itemId;
        this.alunoId = alunoId;
        this.status = status;
    }

    // Getters e setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
