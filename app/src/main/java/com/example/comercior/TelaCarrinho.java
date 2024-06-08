package com.example.comercior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class TelaCarrinho extends AppCompatActivity {
    ListView listaCarrinho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carrinho);
        listaCarrinho = findViewById(R.id.listaCarrinho);
        CarrinhoManager carrinhoManager = CarrinhoManager.getInstance();
        List<Produto> carrinho = carrinhoManager.getCarrinho();
        listarPCarrinho(carrinho);
    }

    public void listarPCarrinho(List<Produto> carrinho){
        CarrinhoAdaptador adapter = new CarrinhoAdaptador(this, carrinho);
        listaCarrinho.setAdapter(adapter);
    }
    public void TelaProdutos(View view){
        Intent in = new Intent(TelaCarrinho.this, TelaProdutos.class);
        startActivity(in);
    }
    public void addCupom(View view){
        Toast.makeText(TelaCarrinho.this, "Cupom adicionado!", Toast.LENGTH_SHORT).show();
    }
}