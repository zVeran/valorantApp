package com.example.valorantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class perfil extends AppCompatActivity {

    TextView nome ,senha;
    public static TextView email;
    ImageView imgview;
    Button btnsair;
    databasehelper myDb;
    private static int RESULT_LOAD_IMG = 1;
    private String PREFS_NAME = "image";
    private String SharedDados = "dados";
    private Context mContext;
    String nomedb;
    String emaildb;
    String senhadb;
    String user_email;
    String imgDecodableString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        nome = findViewById(R.id.txtview_nome);
        email = findViewById(R.id.txtview_email);
        senha = findViewById(R.id.txtview_senha);
        btnsair = findViewById(R.id.btn_sair);
        myDb = new databasehelper(this);


        //Botão para sair do login
        btnsair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("ckeckbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                Intent voltar = new Intent(perfil.this, login.class);
                startActivity(voltar);
            }
        });

        //Peguei o email
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            user_email = extras.getString("importedEmail");
            Cursor cursor = myDb.getdata(user_email);
            armazenar(extras.getString("importedEmail"));
            if (cursor.moveToFirst()) {
                do {
                    nomedb = cursor.getString(1);
                    emaildb = cursor.getString(2);
                    senhadb = cursor.getString(3);
                    nome.setText(nomedb);
                    email.setText(emaildb);
                    senha.setText(senhadb);
                } while (cursor.moveToNext());
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences saveEmailPreference = getSharedPreferences(SharedDados , 0);
        user_email = saveEmailPreference.getString("email", null);

        nome = findViewById(R.id.txtview_nome);
        email = findViewById(R.id.txtview_email);
        senha = findViewById(R.id.txtview_senha);


        Cursor cursor = myDb.getdata(user_email);
        if (cursor.moveToFirst()) {
            do {
                nomedb = cursor.getString(1);
                emaildb = cursor.getString(2);
                senhadb = cursor.getString(3);
                nome.setText(nomedb);
                email.setText(emaildb);
                senha.setText(senhadb);
            } while (cursor.moveToNext());
        }

    }

    private void armazenar(String user_email)
    {
        SharedPreferences saveEmailPreference = getSharedPreferences(SharedDados , 0);
        SharedPreferences.Editor editor = saveEmailPreference.edit();
        editor.putString("email", user_email);

        editor.commit();
    }



    public void loadImagefromGallery(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    public void Back(View view){
        Intent voltar = new Intent(this, home.class);
        startActivity(voltar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // Setando a imagem na imageView
                imgview.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "Você não escolheu uma imagem.", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Oops! Houve um erro.", Toast.LENGTH_LONG).show();
        }

    }

}