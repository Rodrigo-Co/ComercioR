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
    ListView listaCarrinho; // ListView para exibir os itens do carrinho
    private TextView qtdcart; // TextView para exibir a quantidade total de itens no carrinho
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_carrinho);
        qtdcart = findViewById(R.id.qtdcart);
        listaCarrinho = findViewById(R.id.listaCarrinho);
        // Obtém a instância do CarrinhoManager e a lista de produtos no carrinho
        CarrinhoManager carrinhoManager = CarrinhoManager.getInstance();
        List<Produto> carrinho = carrinhoManager.getCarrinho();
        // Lista os produtos no carrinho
        listarPCarrinho(carrinho);
        // Atualiza a quantidade total de itens no carrinho
        updateQuantidadeTotal();
    }

    public void listarPCarrinho(List<Produto> carrinho){
        // Cria e define o adaptador para a ListView do carrinho
        CarrinhoAdaptador adapter = new CarrinhoAdaptador(this, carrinho);
        listaCarrinho.setAdapter(adapter);
    }
    public void TelaProdutos(View view){
        Intent in = new Intent(TelaCarrinho.this, TelaProdutos.class);
        startActivity(in);
    }
    public void addCupom(View view){
        // Método para simular a adição de um cupom (demonstração)
        Toast.makeText(TelaCarrinho.this, "Cupom adicionado!", Toast.LENGTH_SHORT).show();
    }
    public void comprar(View view){
        // Método para simular a ação de compra (demonstração)
        Toast.makeText(TelaCarrinho.this, "Compras serão realizadas após a atualização!", Toast.LENGTH_SHORT).show();
    }

    // Método para atualizar a quantidade total de itens no carrinho
    public void updateQuantidadeTotal() {
        // Obtém a instância do CarrinhoManager
        CarrinhoManager carrinhoManager = CarrinhoManager.getInstance();
        // Obtém a quantidade total de itens no carrinho
        int quantidadeTotal = carrinhoManager.getQuantidadeTotal();
        // Atualiza o TextView com a quantidade total
        qtdcart.setText("" + quantidadeTotal);
    }
}