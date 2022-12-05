package com.example.valorantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class personagens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personagens);
    }

    //Declarando funções dos botões

    public void Back(View view){
        Intent voltar = new Intent(this, home.class);
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

    public void Jett(View view){
        Intent jett = new Intent(this, jett.class);
        startActivity(jett);
    }

    public void Omen(View view){
        Intent omen = new Intent(this, omen.class);
        startActivity(omen);
    }
    public void Brimstone(View view){
        Intent brim = new Intent(this, brimstone.class);
        startActivity(brim);
    }

    public void Phoenix(View view){
        Intent phoe = new Intent(this, phoenix.class);
        startActivity(phoe);
    }

    public void Sage(View view){
        Intent sage = new Intent(this, sage.class);
        startActivity(sage);
    }

    public void Viper(View view){
        Intent viper = new Intent(this, viper.class);
        startActivity(viper);
    }

}