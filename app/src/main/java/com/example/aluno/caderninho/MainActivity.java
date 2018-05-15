package com.example.aluno.caderninho;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listNotas;
    private Button btnNew;

    DataBase data;

    private AdapterNotas adapterNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNotas = findViewById(R.id.listNotas);
        btnNew = findViewById(R.id.btnNew);
        data = new DataBase(this);



        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,NotaActivity.class);
                i.putExtra("novaNota", true);
                startActivity(i);
            }
        });


        listNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int x, long l) {


                    Intent i = new Intent(MainActivity.this,NotaActivity.class);
                    i.putExtra("posicao", x);
                    i.putExtra("novaNota", false);
                    startActivity(i);
            }
        });


        data.getListaNotas();

        adapterNotas = new AdapterNotas(data.getListaNotas(),this);
        listNotas.setAdapter(adapterNotas);

    }

}
