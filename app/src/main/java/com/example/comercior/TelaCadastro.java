package com.example.comercior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class TelaCadastro extends AppCompatActivity {
    Usuario usuario; // Objeto Usuario que armazena as informações do usuário
    FirebaseAuth autenticacao; // Objeto FirebaseAuth para autenticação com Firebase
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
    public void cadastro(View view) {
        String Nome = nome.getText().toString();
        String Email = email.getText().toString();
        String Telefone = telefone.getText().toString();
        String Senhacadastro = senhacadastro.getText().toString();

        if (Nome.isEmpty() || Email.isEmpty() || Telefone.isEmpty() || Senhacadastro.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Dados incompletos!", Toast.LENGTH_SHORT).show();
        } else {
            usuario = new Usuario(); // Cria um novo objeto Usuario
            //usuario.setNome(Nome); // Define o nome do usuário
            usuario.setEmail(Email); // Define o email do usuário
            //usuario.setTel(Telefone); // Define o telefone do usuario
            usuario.setPass(Senhacadastro); // Define a senha do usuário

            autenticacao = ConfiguraBd.Fireautenticacao(); // Obtém instância de autenticação do Firebase

            autenticacao.createUserWithEmailAndPassword(
                    usuario.getEmail(), usuario.getPass()
            ).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent in = new Intent(TelaCadastro.this, MainActivity.class);
                    Toast.makeText(getApplicationContext(), "Dados Cadastrados com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao cadastrar. Verifique o email ou telefone.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}