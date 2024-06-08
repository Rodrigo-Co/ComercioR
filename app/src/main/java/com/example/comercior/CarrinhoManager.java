package com.example.comercior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrinhoManager {

    private static CarrinhoManager instance;
    private Map<Produto, Integer> quantidadeMap;

    private CarrinhoManager() {
        quantidadeMap = new HashMap<>();
    }

    public static CarrinhoManager getInstance() {
        if (instance == null) {
            instance = new CarrinhoManager();
        }
        return instance;
    }

    public void adicionarProduto(Produto produto) {
        if (quantidadeMap.containsKey(produto)) {
            quantidadeMap.put(produto, quantidadeMap.get(produto) + 1);
        } else {
            quantidadeMap.put(produto, 1);
        }
    }

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
        return quantidadeMap.getOrDefault(produto, 0);
    }

    public List<Produto> getCarrinho() {
        return new ArrayList<>(quantidadeMap.keySet());
    }
}
