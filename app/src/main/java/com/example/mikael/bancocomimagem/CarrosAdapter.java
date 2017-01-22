package com.example.mikael.bancocomimagem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mikael on 22/01/17.
 */

public class CarrosAdapter extends ArrayAdapter<Carro> {

    Context contexto;
    int id;
    List<Carro> lista;

    public CarrosAdapter(Context contexto, int id, List<Carro> lista){
        super(contexto,id,lista);
        this.contexto = contexto;
        this.lista = lista;
        this.id = id;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        Carro carro;
        ImageView foto;
        TextView nome;
        TextView dono;
        Bitmap raw;
        byte[] fotoArray;

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(id, parent, false);
        }

        nome = (TextView)view.findViewById(R.id.textView);
        dono = (TextView)view.findViewById(R.id.textView2);
        foto = (ImageView)view.findViewById(R.id.imageView);

        carro = lista.get(position);

        nome.setText(carro.getNome());
        dono.setText(carro.getDono());
        fotoArray = carro.getFoto();

        if(fotoArray!=null){
            raw  = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
            foto.setImageBitmap(raw);
        }

        return view;
    }
}
