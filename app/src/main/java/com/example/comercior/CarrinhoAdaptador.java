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

    private Context context;
    private List<Produto> carrinho;
    private CarrinhoManager carrinhoManager;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_dropdown_cart, parent, false);
            holder = new ViewHolder();
            holder.nomeProdutoTextView = convertView.findViewById(android.R.id.text1);
            holder.descricaoProdutoTextView = convertView.findViewById(R.id.product_description);
            holder.iconeProdutoImageView = convertView.findViewById(R.id.icon);
            holder.quantidadeProdutoTextView = convertView.findViewById(R.id.product_quantity);
            holder.decrementButton = convertView.findViewById(R.id.decrement_button);
            holder.incrementButton = convertView.findViewById(R.id.increment_button);
            //holder.quantidadeTotalTextView = convertView.findViewById(R.id.qtdcart);
            holder.detailsLayout = convertView.findViewById(R.id.details);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Produto produto = carrinho.get(position);

        holder.nomeProdutoTextView.setText(produto.getNome());
        holder.descricaoProdutoTextView.setText(produto.getDescricao());
        holder.iconeProdutoImageView.setImageResource(produto.getIconeResource());
        holder.quantidadeProdutoTextView.setText("Quantidade: "+ carrinhoManager.getQuantidade(produto));
        //holder.quantidadeTotalTextView.setText(""+ carrinhoManager.getQuantidadeTotal());

        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrinhoManager.adicionarProduto(produto);
                holder.quantidadeProdutoTextView.setText("Quantidade: " + carrinhoManager.getQuantidade(produto));
                notifyDataSetChanged();
                if (context instanceof TelaCarrinho) {
                    ((TelaCarrinho) context).updateQuantidadeTotal();
                }
            }
        });

        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carrinhoManager.removerProduto(produto);
                holder.quantidadeProdutoTextView.setText("Quantidade: " + carrinhoManager.getQuantidade(produto));
                notifyDataSetChanged();
                if (context instanceof TelaCarrinho) {
                    ((TelaCarrinho) context).updateQuantidadeTotal();
                }
            }
        });

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

    static class ViewHolder {
        TextView nomeProdutoTextView;
        TextView descricaoProdutoTextView;
        ImageView iconeProdutoImageView;
        TextView quantidadeProdutoTextView;
        //TextView quantidadeTotalTextView;
        Button decrementButton;
        Button incrementButton;
        LinearLayout detailsLayout;
    }
}
