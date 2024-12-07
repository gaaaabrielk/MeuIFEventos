package com.example.meuifeventos;

import com.google.firebase.firestore.DocumentId;

public class Evento {

    private String titulo;
    private String descricao;
    private String data;
    private String local;
    @DocumentId
    private String id;


    public Evento() {
    }

    public Evento(String titulo, String descricao, String data, String local) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.local = local;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


}
