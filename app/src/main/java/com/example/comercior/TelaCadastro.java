package com.example.comercior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelaCadastro extends AppCompatActivity {
    EditText nome, email, telefone, senhacadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        telefone = (EditText) findViewById(R.id.telefone);
        senhacadastro = (EditText) findViewById(R.id.senhacadastro);
    }
    public void cadastro(View view){
        String Nome = nome.getText().toString();
        String Email = email.getText().toString();
        String Telefone = telefone.getText().toString();
        String Senhacadastro = senhacadastro.getText().toString();

        if(Nome.isEmpty() || Email.isEmpty() || Telefone.isEmpty() || Senhacadastro.isEmpty()){
            Toast.makeText(getApplicationContext(), "Dados incompletos!", Toast.LENGTH_SHORT).show();
        }else{

            Intent in = new Intent(TelaCadastro.this, MainActivity.class);
            in.putExtra("Email", Email);
            in.putExtra("Senhacadastro", Senhacadastro);
            Toast.makeText(getApplicationContext(), "Dados Cadastrados com sucesso!", Toast.LENGTH_SHORT).show();
            startActivity(in);
        }
    }

}