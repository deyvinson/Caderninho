package com.example.aluno.caderninho;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listNotas;
    private FloatingActionButton btnNew;
    private TextView empty;

    DataBase data;

    private AdapterNotas adapterNotas;

    @Override
    protected void onResume() {

        data.getListaNotas();

        if (data.getListaNotas().size() > 0) {
            adapterNotas = new AdapterNotas(data.getListaNotas(), this);
            listNotas.setAdapter(adapterNotas);
            empty.setVisibility(View.GONE);
        }

        else{
            empty.setVisibility(View.VISIBLE);
        }

        super.onResume();
    }

    /*private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNotas = findViewById(R.id.listNotas);
        btnNew = findViewById(R.id.btnNew);
        data = new DataBase(this);
        empty = findViewById(R.id.lblEmpty);

        //refresh na lista
        data.getListaNotas();
        adapterNotas = new AdapterNotas(data.getListaNotas(), this);
        listNotas.setAdapter(adapterNotas);


        //botão nova nota
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NotaActivity.class);
                i.putExtra("novaNota", true);
                startActivity(i);

               /* ActivityOptions.makeSceneTransitionAnimation(

                        (
                        this,
                        new Pair<View, String>(circleImage,
                                ViewCompat.getTransitionName(circleImage)))
                        .toBundle());*/
                overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);

            }
        });


        //clique no item da lista
        listNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {

                Intent i = new Intent(MainActivity.this, NotaActivity.class);
                i.putExtra("posicao", x);
                i.putExtra("novaNota", false);
                startActivity(i);
                overridePendingTransition(R.anim.from_right_in, R.anim.from_left_out);
            }
        });

        listNotas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                callQuestionAlert("Deseja excluir?", i);

                return false;
            }
        });

    }


    private void callQuestionAlert(String msg, final int i) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);
        TextView pergunta = dialog.findViewById(R.id.lblPergunta);
        Button btnCancelar = dialog.findViewById(R.id.btnCancelar);
        Button btnOk = dialog.findViewById(R.id.btnOk);

        pergunta.setText(msg);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data.ExcluirAnotacao(i);
                adapterNotas = new AdapterNotas(data.getListaNotas(),MainActivity.this);
                listNotas.setAdapter(adapterNotas);

                Toast.makeText(MainActivity.this, "Anotação excluída", Toast.LENGTH_SHORT).show();

                if (data.getListaNotas().size() < 1){
                    empty.setVisibility(View.VISIBLE);
                }

                dialog.dismiss();
            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
