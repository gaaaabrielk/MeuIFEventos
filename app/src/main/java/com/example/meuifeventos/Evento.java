package com.example.meuifeventos;

public class Evento {
    private String titulo;
    private String descricao;
    private String data;
    private String local;

    public Evento() {

    }

    public Evento(String titulo, String descricao, String data, String local) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getData() { return data; }
    public String getLocal() { return local; }
}