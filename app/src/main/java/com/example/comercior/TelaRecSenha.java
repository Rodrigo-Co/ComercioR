package com.example.comercior;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class TelaRecSenha extends AppCompatActivity {
    EditText emailrec;
    Usuario usuario;
    private FirebaseAuth authrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_rec_senha);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        emailrec = (EditText) findViewById(R.id.emailrec);
        authrec = FirebaseAuth.getInstance();
    }
    public void recuperar(View view){
        String emailRecuperar = emailrec.getText().toString();
        if (emailRecuperar.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Digite um email!", Toast.LENGTH_SHORT).show();
        } else {
            usuario = new Usuario();
            usuario.setEmail(emailRecuperar);
            authrec.sendPasswordResetEmail(usuario.getEmail()).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    Toast.makeText(TelaRecSenha.this, "Email enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(TelaRecSenha.this,MainActivity.class);
                    startActivity(in);
                    finish();
                }else {
                    String errorMessage = "Erro. Tente novamente.";
                    if (task.getException() != null) {
                        errorMessage = task.getException().getLocalizedMessage();
                    }
                    Toast.makeText(TelaRecSenha.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}