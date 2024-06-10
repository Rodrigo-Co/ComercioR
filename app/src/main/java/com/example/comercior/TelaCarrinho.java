package com.example.comercior;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TelaCarrinho extends AppCompatActivity {
    ListView listaCarrinho;
    private TextView qtdcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carrinho);
        qtdcart = findViewById(R.id.qtdcart);
        listaCarrinho = findViewById(R.id.listaCarrinho);
        CarrinhoManager carrinhoManager = CarrinhoManager.getInstance();
        List<Produto> carrinho = carrinhoManager.getCarrinho();
        listarPCarrinho(carrinho);

        updateQuantidadeTotal();
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
    public void comprar(View view){
        Toast.makeText(TelaCarrinho.this, "Compras serão realizadas após a atualização!", Toast.LENGTH_SHORT).show();
    }
    public void updateQuantidadeTotal() {
        CarrinhoManager carrinhoManager = CarrinhoManager.getInstance();
        int quantidadeTotal = carrinhoManager.getQuantidadeTotal();
        qtdcart.setText("" + quantidadeTotal);
    }
}