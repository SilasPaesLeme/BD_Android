package com.example.mikael.bancocomimagem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Inicial extends Activity {

    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Button save = (Button)findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller dao = new Controller(getBaseContext());
                EditText nome = (EditText)findViewById(R.id.editTextTitulo);
                EditText dono = (EditText)findViewById(R.id.editTextAutor);
                ImageView foto = (ImageView)findViewById(R.id.imageCapa);
                String result;
                Bitmap bitmap = ((BitmapDrawable)foto.getDrawable()).getBitmap();
                ByteArrayOutputStream saida = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
                byte[] img = saida.toByteArray();
                result = dao.insereDado(nome.getText().toString(),dono.getText().toString(),img);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                setContentView(R.layout.layout_carros);


            }
        });

        Button btn = (Button) findViewById(R.id.buttonImage);
        btn.setOnClickListener(mOnClickListener);
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Selecione a capa do livro"), SELECT_PICTURE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImage = data.getData();
                ImageView imagemCapa = (ImageView) findViewById(R.id.imageCapa);
                imagemCapa.setImageURI(selectedImage);
            }
        }
    }
}
