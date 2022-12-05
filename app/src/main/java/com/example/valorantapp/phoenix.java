package com.example.valorantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class phoenix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoenix);
    }

    public void Back(View view){
        Intent voltar = new Intent(this, personagens.class);
        startActivity(voltar);
    }

    public void Home (View view){
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    public void Perfil(View view){
        Intent perfil = new Intent(this, perfil.class);
        startActivity(perfil);
    }
}