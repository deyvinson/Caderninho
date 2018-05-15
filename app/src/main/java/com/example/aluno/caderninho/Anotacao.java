package com.example.aluno.caderninho;

import java.text.DateFormat;

/**
 * Created by aluno on 16/04/18.
 */

public class Anotacao {

    int id;
    String titulo;
    String conteudo;
    String dataHora;

    public Anotacao(int id, String titulo, String conteudo, String dataHora) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.dataHora = dataHora;
    }

    public Anotacao() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
