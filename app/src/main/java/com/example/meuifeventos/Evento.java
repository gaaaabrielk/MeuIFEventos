package com.example.meuifeventos;

public class Evento {
        private String titulo;
        private String data;
        private String descricao;
        private String local;

        public Evento() {}

        public Evento(String titulo, String data, String descricao, String local) {
            this.titulo = titulo;
            this.data = data;
            this.descricao = descricao;
            this.local = local;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getData() {
            return data;
        }

        public String getDescricao() {
            return descricao;
        }

        public String getLocal() {
            return local;
        }
    }


