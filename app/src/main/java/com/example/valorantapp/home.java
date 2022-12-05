package com.example.valorantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //Declarando funções dos botões

    public void Home(View view) {
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }

    public void Perfil(View view) {
        Intent perfil = new Intent(this, perfil.class);
        startActivity(perfil);
    }

    public void Personagens(View view) {
        Intent personagens = new Intent(this, personagens.class);
        startActivity(personagens);
    }
}