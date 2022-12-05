package com.example.valorantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cadastro extends AppCompatActivity {

    EditText txt_nome, txt_email ,txt_senha;
    Button btn_cadastrar;
    databasehelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txt_nome = findViewById(R.id.txt_nome);
        txt_email = findViewById(R.id.txt_email);
        txt_senha = findViewById(R.id.txt_senha);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);
        myDb = new databasehelper(this);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DatabaseHelper myDb = new DatabaseHelper(CadastroActivity.this);
//                myDb.Cadastrar(txt_nome.getText().toString().trim(),
//                        txt_email.getText().toString().trim(),
//                        txt_senha.getText().toString().trim());

                String nome = txt_nome.getText().toString();
                String email = txt_email.getText().toString();
                String senha = txt_senha.getText().toString();

                if (nome.equals("") || email.equals("") || senha.equals("")){
                    Toast.makeText(cadastro.this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    if(senha != "") {
                        Boolean checkemail = myDb.checkemail(email);
                        if(checkemail == false) {
                            Boolean insert = myDb.Cadastrar(nome, email, senha);
                            txt_nome.setText("");
                            txt_email.setText("");
                            txt_senha.setText("");

                            if(insert==true) {
                                Toast.makeText(cadastro.this, "Usuário cadastrado.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(cadastro.this, "Falha ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(cadastro.this, "E-mail já cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(cadastro.this, "Senha incorreta.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void Back(View view){
        Intent voltar = new Intent(this, login.class);
        startActivity(voltar);
    }
}