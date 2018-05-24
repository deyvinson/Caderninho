package com.example.aluno.caderninho;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.transition.Fade;
import android.transition.Slide;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

public class NotaActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText conteudo;
    private TextView dataHora;
    private boolean novaNota = false;
    Anotacao nota = new Anotacao();
    private int posicao;

    DataBase data;

    /*private void setupWindowAnimations() {

        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }*/

    @Override
    protected void onResume() {

        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(titulo, InputMethodManager.SHOW_IMPLICIT);
        conteudo.requestFocus();
        titulo.requestFocus();

        super.onResume();
    }

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
            int id = data.getNextId();

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date(System.currentTimeMillis());
            String dataFormatada = df.format(date);

            nota = new Anotacao(id, "", "", dataFormatada);
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

       if (titulo.getText().toString().isEmpty() && conteudo.getText().toString().isEmpty()){
           Toast.makeText(this, "Anotação em branco. Nada foi alterado.", Toast.LENGTH_SHORT).show();
           NotaActivity.this.finish();
           overridePendingTransition(R.anim.from_left_in,R.anim.from_right_out);
       }
       else
       {
           if (titulo.getText().toString().isEmpty()) {
               nota.titulo = "Nova anotação";
           } else {
               nota.titulo = titulo.getText().toString();
           }

           nota.conteudo = conteudo.getText().toString();

           if (novaNota == true) {
               data.NovaAnotacao(nota);
           } else {
               data.AlterarAnotacao(nota, posicao);
           }

           Toast.makeText(this, "Anotação salva!", Toast.LENGTH_SHORT).show();
           NotaActivity.this.finish();
           overridePendingTransition(R.anim.from_left_in,R.anim.from_right_out);
       }
    }
}
