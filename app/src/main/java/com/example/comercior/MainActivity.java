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
    private FirebaseAuth auth; // Declaração da instância do FirebaseAuth
    EditText login, senha; // Declaração dos campos de entrada para login e senha


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        Intent intent = getIntent(); // Obtém o Intent que iniciou a atividade
        auth = FirebaseAuth.getInstance(); // Inicializa a instância do FirebaseAuth
    }

    public void logar(View view){
        String Login = login.getText().toString();
        String Senha = senha.getText().toString();
        if(Login.isEmpty() || Senha.isEmpty()){
            // Verifica se os campos de login ou senha estão vazios e exibe uma mensagem de erro se estiverem
            Toast.makeText(MainActivity.this, "Dados incompletos!", Toast.LENGTH_SHORT).show();
        }
        else{
            // Cria uma instância do usuário e define o email e senha
            Usuario usuario = new Usuario();
            usuario.setEmail(Login); // Define o email do usuário
            usuario.setPass(Senha); // Define a senha do usuário

            // Tenta fazer login com email e senha usando FirebaseAuth
            auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getPass())
                    .addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Seja Bem Vindo!", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(MainActivity.this, TelaProdutos.class);// Cria um Intent para TelaProdutos e logo inicializa
                    startActivity(in);
                    finish();
                }else{
                    // Se o login falhar, exibe uma mensagem de erro
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
        // Cria um Intent para TelaRecSenha e logo inicializa ao clicar no botao
        Intent in = new Intent(MainActivity.this, TelaRecSenha.class);
        startActivity(in);
    }
    public void telaCadastro(View view){
        // Cria um Intent para TelaCadastro e logo inicializa ao clicar no botao
        Intent in = new Intent(MainActivity.this, TelaCadastro.class);
        startActivity(in);
        finish();
    }
}