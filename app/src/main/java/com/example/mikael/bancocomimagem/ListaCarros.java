package com.example.mikael.bancocomimagem;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikael on 22/01/17.
 */

public class ListaCarros extends Activity {

    private CarrosAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_carros);
        List<Carro> lista;
        ListView controle;
        Controller dao = new Controller(getBaseContext());
        Cursor cursor = dao.carregaDados();
        lista = cursor2List(cursor);
        adaptador = new CarrosAdapter(getApplication(),R.layout.layout_carros, lista);
        controle = (ListView)findViewById(R.id.listView);
        controle.setAdapter(adaptador);
    }

    protected List<Carro> cursor2List(Cursor cursor){
        List<Carro> carros = new ArrayList<Carro>();
        cursor.moveToFirst();
        do{
            Carro carro = new Carro();

            carro.setNome(cursor.getString(cursor.getColumnIndex(DBHelper.NOME)));
            carro.setDono(cursor.getString(cursor.getColumnIndex(DBHelper.DONO)));
            carro.setFoto(cursor.getBlob(cursor.getColumnIndex(DBHelper.FOTO)));

            carros.add(carro);
        }while(cursor.moveToNext());
        return carros;
    }
}
