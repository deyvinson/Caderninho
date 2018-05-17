package com.example.aluno.caderninho;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    DataBase data;

    private AdapterNotas adapterNotas;

    @Override
    protected void onResume() {

        data.getListaNotas();
        adapterNotas = new AdapterNotas(data.getListaNotas(),this);
        listNotas.setAdapter(adapterNotas);

        super.onResume();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNotas = findViewById(R.id.listNotas);
        btnNew = findViewById(R.id.btnNew);
        data = new DataBase(this);

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

                Toast.makeText(MainActivity.this, "Anotação excluída", Toast.LENGTH_LONG).show();
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
