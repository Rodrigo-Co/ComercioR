package com.example.comercior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CarrinhoAdaptador extends ArrayAdapter<Produto> {

    private Context context;
    private List<Produto> carrinho;

    public CarrinhoAdaptador(Context context, List<Produto> carrinho) {
        super(context, R.layout.custom_dropdown_cart, carrinho);
        this.context = context;
        this.carrinho = carrinho;
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Produto produto = carrinho.get(position);

        holder.nomeProdutoTextView.setText(produto.getNome());
        holder.descricaoProdutoTextView.setText(produto.getDescricao());
        holder.iconeProdutoImageView.setImageResource(produto.getIconeResource());

        return convertView;
    }

    static class ViewHolder {
        TextView nomeProdutoTextView;
        TextView descricaoProdutoTextView;
        ImageView iconeProdutoImageView;
    }
}
