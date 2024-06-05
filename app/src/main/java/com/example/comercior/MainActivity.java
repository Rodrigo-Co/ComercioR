package com.example.comercior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText login, senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        Intent intent = getIntent();
        auth = FirebaseAuth.getInstance();
    }

    public void logar(View view){
        String Login = login.getText().toString();
        String Senha = senha.getText().toString();
        if(Login.isEmpty() || Senha.isEmpty()){
            Toast.makeText(MainActivity.this, "Dados incompletos!", Toast.LENGTH_SHORT).show();
        }
        else{
            Usuario usuario = new Usuario();
            usuario.setEmail(Login); // Corrigido para usar a String Login
            usuario.setPass(Senha); // Corrigido para usar a String Senha
            auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getPass())
                    .addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Seja Bem Vindo!", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(MainActivity.this, TelaProdutos.class);
                    startActivity(in);
                    finish();
                }else{
                    String errorMessage = "Erro ao entrar. Cadastre-se ou tente novamente.";
                    if (task.getException() != null) {
                        // Verifica se a exceção é relacionada a credenciais
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            errorMessage = "Credenciais inválidas. Verifique seus dados ou cadastre-se.";
                        } else {
                            errorMessage = task.getException().getLocalizedMessage();
                        }
                    }
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void recuperarsenha(View view){
        Intent in = new Intent(MainActivity.this, TelaRecSenha.class);
        startActivity(in);
    }
    public void telaCadastro(View view){
        Intent in = new Intent(MainActivity.this, TelaCadastro.class);
        startActivity(in);
        finish();
    }
}