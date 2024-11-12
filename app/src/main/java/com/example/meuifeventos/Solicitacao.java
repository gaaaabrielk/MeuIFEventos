package com.example.meuifeventos;

public class Solicitacao {
    private String alunoId;
    private String itemId;
    private String status;

    public Solicitacao() {

    }

    public Solicitacao(String alunoId, String itemId, String status) {
        this.alunoId = alunoId;
        this.itemId = itemId;
        this.status = status;
    }

    public String getAlunoId() { return alunoId; }
    public void setAlunoId(String alunoId) { this.alunoId = alunoId; }

    public String getItemId() { return itemId; }
    public void setItemId(String itemId) { this.itemId = itemId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
