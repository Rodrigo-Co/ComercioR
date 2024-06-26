package com.example.comercior;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TelaProdutos extends AppCompatActivity {

    ListView listaprodutos; // ListView para exibir a lista de produtos
    EditText etSearch; // EditText para o campo de busca
    List<Produto> carrinho = new ArrayList<>(); // Lista para armazenar os produtos adicionados ao carrinho
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produtos);
        listaprodutos = findViewById(R.id.listaprodutos);
        etSearch = findViewById(R.id.etSearch);
        listarProdutos(); // Chama o método para listar os produtos
    }
    public void listarProdutos(){
        List<Produto> produtos = new ArrayList<>();
        // Cria uma lista de produtos e adiciona itens a ela
        produtos.add(new Produto("WHEY PROTEIN CONCENTRADO (1KG)", R.drawable.iconewhey, "WHEY PROTEIN CONCENTRADO funciona como um complemento proteico na alimentação e auxilia em diversos benefícios, sendo o principal o ganho de massa muscular e ajuda na recuperação das micro lesões musculares que são ocasionadas durante o treino, fortalecendo a musculatura para o próximo treino."));
        produtos.add(new Produto("CREATINA MONOHIDRATADA (250G)", R.drawable.iconecreatina,"CREATINA MONOHIDRATADA é uma fonte de produção de energia para as células musculares, de modo que melhora a força e o tônus muscular."));
        produtos.add(new Produto("BARRA DE PROTÉINA RECHEADA (12UNID)", R.drawable.iconebarrinha,"BARRA DE PROTEÍNA serve não só para aumentar a quantidade de proteínas diárias ingeridas, mas também, como uma forte aliada em dietas restritivas. Sendo uma opção de doce saudável e um lanche prático que pode ser levado e consumido em qualquer lugar."));
        produtos.add(new Produto("COMBO: WHEY CONCENTRADO (1KG) + COQUETELEIRA (300mL) + CREATINA MONOHIDRATADA (100G)", R.drawable.iconecombo,"COQUETELEIRA também conhecida como shakeira, é um recipiente prático para misturar seu WHEY PROTEIN CONCENTRADO e sua CREATINA MONOHIDRATADA sem desperdício."));
        produtos.add(new Produto("MULTIVITAMINICOS (120CAPS)", R.drawable.iconemultv,"MULTIVITAMINICO é um suplemento alimentar composto por diversas vitaminas e minerais, que podem fornecer a nutrição diária exigida pelo corpo humano."));

        int iconeaddcart = R.drawable.iconeaddcart;// Ícone para adicionar ao carrinho
        // Cria um adaptador personalizado (AdaptadorImg) para o ListView
        AdaptadorImg adapter = new AdaptadorImg(this, produtos, iconeaddcart, carrinho);
        listaprodutos.setAdapter(adapter); // Define o adaptador para o ListView

        // Adiciona um listener para o campo de busca
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString()); // Filtra a lista de produtos com base na entrada de texto
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    public void TelaCarrinho(View view){
        // Cria um Intent para TelaCarrinho e logo inicializa ao clicar no botao
        Intent in = new Intent(TelaProdutos.this, TelaCarrinho.class);
        in.putParcelableArrayListExtra("carrinho", new ArrayList<>(carrinho));
        startActivity(in);

    }
}