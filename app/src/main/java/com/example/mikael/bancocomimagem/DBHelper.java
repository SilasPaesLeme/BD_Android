package com.example.mikael.bancocomimagem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mikael on 22/01/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    static final String BANCO = "carro.db";
    static final int VERSAO = 1;
    static final String TABELA = "carros";
    static final String ID = "_id";
    static final String FOTO = "foto";
    static final String NOME = "nome";
    static final String DONO = "autor";

    public DBHelper(Context context){
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABELA
                + "(" + ID + " integer primary key autoincrement, "
                + NOME + " text, "
                + DONO + " text, "
                + FOTO + " blob " + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABELA);
        onCreate(db);
    }
}
