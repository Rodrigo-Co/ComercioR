package com.example.comercior;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class TelaCarrinho extends AppCompatActivity {
    ListView listaCarrinho;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_carrinho);
        listaCarrinho = findViewById(R.id.listaCarrinho);
        List<Produto> carrinho = getIntent().getParcelableArrayListExtra("carrinho");
        listarPCarrinho(carrinho);
    }

    public void listarPCarrinho(List<Produto> carrinho){
        CarrinhoAdaptador adapter = new CarrinhoAdaptador(this, carrinho);
        listaCarrinho.setAdapter(adapter);
    }
}