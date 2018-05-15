package com.example.aluno.caderninho;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by aluno on 18/04/18.
 */

public class AdapterNotas extends BaseAdapter{

    private ArrayList<Anotacao> lista;
    private Context contexto;

    public AdapterNotas(ArrayList<Anotacao> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        view = inflater.inflate(R.layout.row_anotacao, null);

        TextView title = view.findViewById(R.id.title);
        TextView subTitle =  view.findViewById(R.id.subTitle);

        title.setText(lista.get(i).titulo);
        subTitle.setText(lista.get(i).getDataHora());

        return view;


    }


}
