package com.example.comercior;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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
        ArrayList<String> produtos = new ArrayList<String>();
        produtos.add("WHEY CONCENTRADO (1KG)");
        produtos.add("CREATINA MONOHIDRATADA (250G)");
        produtos.add("BARRA DE PROTEÍNA RECHEADA (12UNID)");
        produtos.add("COMBO: WHEY CONCENTRADO (1KG) + COQUETELEIRA (300mL) + CREATINA MONOHIDRATADA (100G)");
        produtos.add("MULTIVITAMINICOS (120 CAPS)");
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, produtos);
        AdaptadorImg adapter = new AdaptadorImg(this, produtos);
        listaprodutos.setAdapter(adapter);
    }
}