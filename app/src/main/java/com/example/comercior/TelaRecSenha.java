package com.example.comercior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class TelaRecSenha extends AppCompatActivity {
    EditText emailrec; // Campo de entrada para o email de recuperação
    Usuario usuario; // Campo de entrada para o email de recuperação
    private FirebaseAuth authrec; // Instância do FirebaseAuth para recuperação de senha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_rec_senha);
        emailrec = (EditText) findViewById(R.id.emailrec);
        authrec = FirebaseAuth.getInstance(); // Inicializa a instância do FirebaseAuth
    }
    public void recuperar(View view){
        String emailRecuperar = emailrec.getText().toString();
        if (emailRecuperar.isEmpty()) {
            // Verifica se o campo de email está vazio e exibe uma mensagem de erro se estiver
            Toast.makeText(getApplicationContext(), "Digite um email!", Toast.LENGTH_SHORT).show();
        } else {
            // Cria um novo objeto Usuario e define o email
            usuario = new Usuario();
            usuario.setEmail(emailRecuperar);

            // Envia um email de redefinição de senha usando FirebaseAuth
            authrec.sendPasswordResetEmail(usuario.getEmail()).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    // Se o envio for bem-sucedido, exibe uma mensagem de sucesso e inicializa a intent MainActivity
                    Toast.makeText(TelaRecSenha.this, "Email enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(TelaRecSenha.this,MainActivity.class);
                    startActivity(in);
                    finish();
                }else {
                    // Se o envio falhar, exibe uma mensagem de erro
                    String errorMessage = "Erro. Tente novamente.";
                    if (task.getException() != null) {
                        errorMessage = task.getException().getLocalizedMessage();
                    }
                    Toast.makeText(TelaRecSenha.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void login(View view){
        // Cria um Intent para MainActivity(Tela Principal) e inicializa ao clicar no botao
        Intent in = new Intent(TelaRecSenha.this,MainActivity.class);
        startActivity(in);
        finish();
    }
}