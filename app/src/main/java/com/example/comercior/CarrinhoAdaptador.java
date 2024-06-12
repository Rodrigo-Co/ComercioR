package com.example.comercior;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarrinhoAdaptador extends ArrayAdapter<Produto> {

    private Context context; // Contexto da aplicação
    private List<Produto> carrinho; // Lista de produtos no carrinho
    private CarrinhoManager carrinhoManager; // Gerenciador do carrinho

    public CarrinhoAdaptador(Context context, List<Produto> carrinho) {
        super(context, R.layout.custom_dropdown_cart, carrinho);
        this.context = context;
        this.carrinho = carrinho;
        this.carrinhoManager = CarrinhoManager.getInstance();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Verifica se a view está sendo reutilizada, caso contrário infla uma nova view
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_dropdown_cart, parent, false);
            holder = new ViewHolder();
            holder.nomeProdutoTextView = convertView.findViewById(android.R.id.text1);
            holder.descricaoProdutoTextView = convertView.findViewById(R.id.product_description);
            holder.iconeProdutoImageView = convertView.findViewById(R.id.icon);
            holder.quantidadeProdutoTextView = convertView.findViewById(R.id.product_quantity);
            holder.decrementButton = convertView.findViewById(R.id.decrement_button);
            holder.incrementButton = convertView.findViewById(R.id.increment_button);
            holder.detailsLayout = convertView.findViewById(R.id.details);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Produto produto = carrinho.get(position); // Obtém o produto da posição atual

        // Define os valores dos componentes de interface com os dados do produto
        holder.nomeProdutoTextView.setText(produto.getNome());
        holder.descricaoProdutoTextView.setText(produto.getDescricao());
        holder.iconeProdutoImageView.setImageResource(produto.getIconeResource());
        holder.quantidadeProdutoTextView.setText("Quantidade: "+ carrinhoManager.getQuantidade(produto));

        // Define o comportamento do botão de incrementar quantidade
        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrinhoManager.adicionarProduto(produto); // Adiciona um produto ao carrinho
                holder.quantidadeProdutoTextView.setText("Quantidade: " + carrinhoManager.getQuantidade(produto)); // Atualiza a quantidade exibida
                notifyDataSetChanged(); // Notifica o adaptador sobre a mudança de dados
                if (context instanceof TelaCarrinho) {
                    ((TelaCarrinho) context).updateQuantidadeTotal(); // Atualiza a quantidade total no TextView correspondente
                }
            }
        });

        // Define o comportamento do botão de diminuir quantidade
        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrinhoManager.removerProduto(produto); // Remove um produto do carrinho
                holder.quantidadeProdutoTextView.setText("Quantidade: " + carrinhoManager.getQuantidade(produto)); // Atualiza a quantidade exibida
                notifyDataSetChanged(); // Notifica o adaptador sobre a mudança de dados
                if (context instanceof TelaCarrinho) {
                    ((TelaCarrinho) context).updateQuantidadeTotal(); // Atualiza a quantidade total no TextView correspondente
                }
            }
        });

        // Define o comportamento de exibir ou ocultar os detalhes do produto ao clicar na view
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ItemClicked", "Item clicado na posição: " + position);

                if (holder.detailsLayout.getVisibility() == View.VISIBLE) {
                    holder.detailsLayout.setVisibility(View.GONE); // Oculta os detalhes
                } else {
                    holder.detailsLayout.setVisibility(View.VISIBLE); // Exibe os detalhes
                }
            }
        });

        return convertView;
    }

    // Classe estática para armazenar as referências dos componentes de interface
    static class ViewHolder {
        TextView nomeProdutoTextView;
        TextView descricaoProdutoTextView;
        ImageView iconeProdutoImageView;
        TextView quantidadeProdutoTextView;
        Button decrementButton;
        Button incrementButton;
        LinearLayout detailsLayout;
    }
}
