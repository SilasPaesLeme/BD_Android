package com.example.mikael.bancocomimagem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mikael on 22/01/17.
 */

public class Controller {

    private SQLiteDatabase db;
    private DBHelper banco;

    public Controller(Context context){
        banco = new DBHelper(context);
    }

    public String insereDado(String nome, String dono, byte[] foto){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(banco.NOME,nome);
        valores.put(banco.DONO,dono);
        valores.put(banco.FOTO,foto);
        resultado = db.insert(banco.TABELA,null,valores);
        db.close();

        if(resultado==-1){
            return "ERRO";
        }else{
            return "Inserido com Sucesso";
        }
    }
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.NOME,banco.DONO,banco.FOTO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    /*
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID,banco.TITULO,banco.AUTOR,banco.EDITORA};
        String where = DBHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(DBHelper.TABELA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistro(int id, String titulo, String autor, String editora){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = DBHelper.ID + "=" + id;

        valores = new ContentValues();
        valores.put(DBHelper.TITULO, titulo);
        valores.put(DBHelper.AUTOR, autor);
        valores.put(DBHelper.EDITORA, editora);

        db.update(DBHelper.TABELA,valores,where,null);
        db.close();
    }

    public void deletaRegistro(int id){
        String where = DBHelper.ID + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(DBHelper.TABELA,where,null);
        db.close();
    }*/
}
