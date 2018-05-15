package com.example.aluno.caderninho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class NotaActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText conteudo;
    private TextView dataHora;
    private boolean novaNota = false;
    Anotacao nota = new Anotacao();
    private int posicao;

    DataBase data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        novaNota =  getIntent().getExtras().getBoolean("novaNota");

        titulo = findViewById(R.id.txtTitulo);
        conteudo = findViewById(R.id.txtConteudo);
        dataHora = findViewById(R.id.lblDataHora);

        data = new DataBase(this);


        if(novaNota == true)
        {
            int id = data.getNextId(NotaActivity.this);
            Date date = new Date(System.currentTimeMillis());
            nota = new Anotacao(id, "Nova Anotação", "", date.toString());

            titulo.setText(nota.titulo);
            dataHora.setText(nota.dataHora);
        }
        else
        {
            posicao = getIntent().getExtras().getInt("posicao");

            Anotacao n = data.getNota(posicao);

            titulo.setText(n.titulo);
            dataHora.setText(n.dataHora);
            conteudo.setText(n.conteudo);
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        nota.titulo = titulo.getText().toString();
        nota.conteudo = conteudo.getText().toString();

        if (novaNota == true)
        {
            data.NovaAnotacao(this, nota);

            Toast.makeText(this, "Anotação salva!", Toast.LENGTH_SHORT);
            Intent i = new Intent(NotaActivity.this, MainActivity.class);
            startActivity(i);
        }
        else
        {
            data.AlterarAnotacao(this, nota, posicao);

            Toast.makeText(this, "Anotação alterada!", Toast.LENGTH_SHORT);
            Intent i = new Intent(NotaActivity.this, MainActivity.class);
            startActivity(i);
        }



    }
}
