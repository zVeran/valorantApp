package com.example.valorantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class login extends AppCompatActivity {

    EditText txt_email;
    EditText txt_senha;
    Button btn_login, btn_cadastrar;
    CheckBox remember;
    databasehelper myDb;
    ArrayList<String> user_name, user_email, user_senha;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static String email;
    public static String senha;
    public static Boolean rememberme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        remember = findViewById(R.id.remember_btn);

        txt_email = findViewById(R.id.txt_email);
        txt_senha = findViewById(R.id.txt_senha);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        btn_login = findViewById(R.id.btn_login);
        myDb = new databasehelper(this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = txt_email.getText().toString();
                senha = txt_senha.getText().toString();
                rememberme = remember.isChecked();


                if(email.equals("") || senha.equals("")) {
                    Toast.makeText(login.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkpassword = myDb.checkpassword(email, senha);
                    if(checkpassword == true) {
                        Toast.makeText(login.this, "Login concluído.", Toast.LENGTH_SHORT).show();
                        if (rememberme == true) {
                            SharedPreferences lembrardemim = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor editor = lembrardemim.edit();

                            editor.putString("email", email);
                            editor.putString("senha", senha);
                            editor.putBoolean("checkbox", rememberme);
                            editor.commit();
                        } else if (rememberme == false) {
                            SharedPreferences lembrardemim = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                            SharedPreferences.Editor editor = lembrardemim.edit();

                            editor.putString("email", "");
                            editor.putString("senha", "");
                            editor.putBoolean("checkbox", false);
                            editor.commit();
                        }
                        Intent home = new Intent(login.this, home.class);
                        Intent i = new Intent(getApplicationContext(), perfil.class);
                        i.putExtra("importedEmail", email);
                        startActivity(i);
                    } else {
                        Toast.makeText(login.this, "Informações inválidas.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loadData();
        updateView();

    }

    public void loadData() {
        SharedPreferences lembrardemim = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        email = lembrardemim.getString("email", null);
        senha = lembrardemim.getString("senha", null);
        rememberme = lembrardemim.getBoolean("checkbox", false);
    }

    public void updateView() {
        txt_email.setText(email);
        txt_senha.setText(senha);
        remember.setChecked(rememberme);
    }

    public void Cadastro(View view){
        Intent cadastrar = new Intent(this, cadastro.class);
        startActivity(cadastrar);
    }

    public void Home (View view){
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }
}