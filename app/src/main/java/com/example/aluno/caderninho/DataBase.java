package com.example.aluno.caderninho;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluno on 16/04/18.
 */

public class DataBase {

    Context contexto;

    private ArrayList<Anotacao> listaNotas;

    public DataBase(Context contexto) {

        this.contexto = contexto;


    }

    public void NovaAnotacao(Anotacao nota){
        getListaNotas();
        listaNotas.add(nota);
        SalvarAnotacao();

    }

    public void AlterarAnotacao(Anotacao nota, int posicao){
        getListaNotas();
        listaNotas.get(posicao).titulo = nota.titulo;
        listaNotas.get(posicao).conteudo = nota.conteudo;

        SalvarAnotacao();
    }


    private void SalvarAnotacao() {

        Gson gson = new Gson();

        Type textoLidoDaInterface = new TypeToken<List<Anotacao>>(){}.getType();
        String json = gson.toJson(listaNotas, textoLidoDaInterface);

        SharedPreferences sharedPref = contexto.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("lista",json);

        editor.commit();
    }


    public ArrayList<Anotacao> getListaNotas() {

        SharedPreferences sharedPref = contexto.getSharedPreferences("data", Context.MODE_PRIVATE);

        String json = sharedPref.getString("lista","");

        if (json.equals(""))
        {
            this.listaNotas = new ArrayList();
        }
        else
        {
            Type textoLidoDaInterface = new TypeToken<List<Anotacao>>() {
            }.getType();

            Gson gson = new Gson();

            listaNotas = gson.fromJson(json, textoLidoDaInterface);

        }

        return listaNotas;
    }

    public Anotacao getNota(int posicao) {
        getListaNotas();

        return listaNotas.get(posicao);
    }


    public int getNextId(){
        SharedPreferences sharedPref = contexto.getSharedPreferences("data", Context.MODE_PRIVATE);
        int ultimoId = sharedPref.getInt("UltimoId", 0);

        ultimoId = ultimoId + 1;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("UltimoId", ultimoId);
        editor.commit();

        return ultimoId;
    }

    public void ExcluirAnotacao(int posicao){
        getListaNotas();
        listaNotas.remove(posicao);

        SalvarAnotacao();
    }



}
