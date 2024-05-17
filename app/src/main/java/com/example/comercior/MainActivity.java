package com.example.comercior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText login, senha;
    String Emailcadastrado, Senhacadastrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        Intent intent = getIntent();
        Emailcadastrado = intent.getStringExtra("Email"); // Corrigido
        Senhacadastrada = intent.getStringExtra("Senhacadastro"); // Corrigido
    }

    public void logar(View view){
        String Login = login.getText().toString();
        String Senha = senha.getText().toString();
        if(Login.isEmpty() || Senha.isEmpty()){
            Toast.makeText(MainActivity.this, "Dados incompletos!", Toast.LENGTH_SHORT).show(); // Corrigido
        }else if(Login.equals(Emailcadastrado) && Senha.equals(Senhacadastrada)){
            Toast.makeText(MainActivity.this, "Seja Bem Vindo!", Toast.LENGTH_SHORT).show(); // Corrigido
            Intent in = new Intent(MainActivity.this, TelaProdutos.class);
            startActivity(in);
            finish();
        }else{
            Toast.makeText(MainActivity.this, "Dados incorretos\nCadastre-se ou Recupere a Senha", Toast.LENGTH_SHORT).show(); // Corrigido
        }
    }

    public void recuperarsenha(View view){
        String Login = login.getText().toString();
        if(Login.equals(Emailcadastrado)){
            Toast.makeText(MainActivity.this, "Senha desse email digitado: "+ Senhacadastrada, Toast.LENGTH_LONG).show(); // Corrigido
        }else{
            Toast.makeText(MainActivity.this, "Email não cadastrado", Toast.LENGTH_SHORT).show(); // Corrigido
        }
    }
    public void telaCadastro(View view){
        Intent in = new Intent(MainActivity.this, TelaCadastro.class);
        startActivity(in);
    }
}