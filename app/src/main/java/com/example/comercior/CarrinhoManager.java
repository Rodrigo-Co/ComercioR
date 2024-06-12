package com.example.comercior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrinhoManager {

    private static CarrinhoManager instance; // Instância única do gerenciador de carrinho (singleton)
    private Map<Produto, Integer> quantidadeMap; // Mapa para armazenar os produtos e suas quantidades

    private CarrinhoManager() {
        quantidadeMap = new HashMap<>(); // Construtor privado para implementar o padrão singleton
    }

    // Método estático para obter a instância única do gerenciador de carrinho
    public static CarrinhoManager getInstance() {
        if (instance == null) {
            instance = new CarrinhoManager(); // Cria uma nova instância se ainda não existir
        }
        return instance;
    }

    // Método para adicionar um produto ao carrinho
    public void adicionarProduto(Produto produto) {
        if (quantidadeMap.containsKey(produto)) {
            quantidadeMap.put(produto, quantidadeMap.get(produto) + 1);
        } else {
            quantidadeMap.put(produto, 1);
        }
    }

    // Método para remover um produto do carrinho
    public void removerProduto(Produto produto) {
        if (quantidadeMap.containsKey(produto)) {
            int quantidadeAtual = quantidadeMap.get(produto);
            if (quantidadeAtual > 1) {
                quantidadeMap.put(produto, quantidadeAtual - 1);
            } else {
                quantidadeMap.remove(produto);
            }
        }
    }

    public int getQuantidade(Produto produto) {
        return quantidadeMap.getOrDefault(produto, 0); // Método para obter a quantidade de um produto específico no carrinho
    }

    // Método para obter a lista de produtos no carrinho
    public List<Produto> getCarrinho() {
        return new ArrayList<>(quantidadeMap.keySet());
    }
    // Método para obter a quantidade total de produtos no carrinho
    public int getQuantidadeTotal() {
        int quantidadeTotal = 0;
        // Itera sobre as quantidades dos produtos e soma para obter a quantidade total
        for (int quantidade : quantidadeMap.values()) {
            quantidadeTotal += quantidade;
        }
        return quantidadeTotal;
    }
}
