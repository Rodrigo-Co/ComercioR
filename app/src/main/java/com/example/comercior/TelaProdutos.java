package com.example.comercior;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TelaProdutos extends AppCompatActivity {

    ListView listaprodutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_produtos);
        listaprodutos = (ListView) findViewById(R.id.listaprodutos);
        listarProdutos();
    }
    public void listarProdutos(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("WHEW PROTEIN CONCENTRADO (1KG)", R.drawable.iconewhey, "WHEY PROTEIN CONCENTRADO funciona como um complemento proteico na alimentação e auxilia em diversos benefícios, sendo o principal o ganho de massa muscular e ajuda na recuperação das micro lesões musculares que são ocasionadas durante o treino, fortalecendo a musculatura para o próximo treino."));
        produtos.add(new Produto("CREATINA MONOHIDRATADA (250G)", R.drawable.iconecreatina,"CREATINA MONOHIDRATADA é uma fonte de produção de energia para as células musculares, de modo que melhora a força e o tônus muscular."));
        produtos.add(new Produto("BARRA DE PROTÉINA RECHEADA (12UNID)", R.drawable.iconebarrinha,"CREATINA MONOHIDRATADA é uma fonte de produção de energia para as células musculares, de modo que melhora a força e o tônus muscular."));
        produtos.add(new Produto("COMBO: WHEY CONCENTRADO (1KG) + COQUETELEIRA (300mL) + CREATINA MONOHIDRATADA (100G)", R.drawable.iconecombo,"CREATINA MONOHIDRATADA é uma fonte de produção de energia para as células musculares, de modo que melhora a força e o tônus muscular."));
        produtos.add(new Produto("MULTIVITAMINICOS (120CAPS)", R.drawable.iconemultv,"CREATINA MONOHIDRATADA é uma fonte de produção de energia para as células musculares, de modo que melhora a força e o tônus muscular."));

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, produtos);
        AdaptadorImg adapter = new AdaptadorImg(this, produtos);
        listaprodutos.setAdapter(adapter);
    }
}