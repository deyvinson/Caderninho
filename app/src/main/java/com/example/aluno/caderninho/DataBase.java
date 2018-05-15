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


    private ArrayList<Anotacao> listaNotas;

    public DataBase(Context contexto) {

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
    }

    public void NovaAnotacao(Context contexto, Anotacao nota){

        listaNotas.add(nota);
        SalvarAnotacao(contexto);

    }

    public void AlterarAnotacao(Context contexto, Anotacao nota, int posicao){

        listaNotas.get(posicao).titulo = nota.titulo;
        listaNotas.get(posicao).conteudo = nota.conteudo;

        SalvarAnotacao(contexto);
    }


    private void SalvarAnotacao(Context contexto) {

        Gson gson = new Gson();

        Type textoLidoDaInterface = new TypeToken<List<Anotacao>>(){}.getType();
        String json = gson.toJson(listaNotas, textoLidoDaInterface);

        SharedPreferences sharedPref = contexto.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("lista",json);

        editor.commit();
    }


    public ArrayList<Anotacao> getListaNotas() {
        return listaNotas;
    }

    public Anotacao getNota(int posicao) {
        return listaNotas.get(posicao);
    }


    public int getNextId(Context contexto){
        SharedPreferences sharedPref = contexto.getSharedPreferences("data", Context.MODE_PRIVATE);
        int ultimoId = sharedPref.getInt("UltimoId", 0);

        ultimoId = ultimoId + 1;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("UltimoId", ultimoId);
        editor.commit();

        return ultimoId;
    }

    public void ExcluirAnotacao(Context contexto, int id){


    }



}
