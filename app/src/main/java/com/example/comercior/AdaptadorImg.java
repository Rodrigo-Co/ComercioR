package com.example.comercior;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorImg extends ArrayAdapter<Produto> implements Filterable {
    private final Context context; // Contexto da aplicação
    private final List<Produto> produtos; // Lista completa de produtos
    private List<Produto> produtosFiltrados; // Lista filtrada de produtos
    private int iconeaddcart; // Ícone de adicionar ao carrinho
    private List<Produto> carrinho; // Lista de produtos no carrinho
    public AdaptadorImg(Context context, List<Produto> produtos, int iconeaddcart, List<Produto> carrinho) {
        super(context, R.layout.custom_dropdown_item, produtos);
        this.context = context;
        this.produtos = new ArrayList<>(produtos);
        this.produtosFiltrados = new ArrayList<>(produtos);
        this.iconeaddcart = iconeaddcart;
        this.carrinho = carrinho;

    }
    @Override
    public int getCount() {
        return produtosFiltrados.size();// Retorna o número de itens filtrados
    }

    @Override
    public Produto getItem(int position) {
        return produtosFiltrados.get(position); // Retorna o produto na posição especificada
    }
    @Override
    public long getItemId(int position) {
        return position; // Retorna a posição como ID do item
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
        // Infla o layout personalizado e inicializa o ViewHolder
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_dropdown_item, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(android.R.id.text1);
            holder.iconView = convertView.findViewById(R.id.icon);
            holder.textView1 = convertView.findViewById(R.id.product_description);
            holder.iconView1 = convertView.findViewById(R.id.adcCart);
            holder.detailsLayout = convertView.findViewById(R.id.details);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag(); // Reutiliza o ViewHolder
        }


        final Produto produto = getItem(position); // Obtém o produto na posição especificada

        // Define os dados do produto nas views
        holder.textView.setText(produto.getNome());
        holder.iconView.setImageResource(produto.getIconeResource());
        holder.textView1.setText(produto.getDescricao());
        holder.iconView1.setImageResource(iconeaddcart);

        // Define o comportamento do botão de adicionar ao carrinho
        holder.iconView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarrinhoManager.getInstance().adicionarProduto(produto);
                Toast.makeText(context, produto.getNome() + " adicionado ao carrinho", Toast.LENGTH_SHORT).show();
            }
        });

        // Define o comportamento do item quando clicado
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
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Produto> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(produtos); // Se não houver filtro, adiciona todos os produtos
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    // Filtra os produtos com base no nome
                    for (Produto item : produtos) {
                        if (item.getNome().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results; // Retorna os resultados do filtro
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                produtosFiltrados.clear(); // Limpa a lista filtrada
                produtosFiltrados.addAll((List) results.values); // Adiciona os resultados filtrados
                notifyDataSetChanged(); // Notifica o adaptador para atualizar a exibição
            }
        };
    }

    // ViewHolder para armazenar as views do item
    static class ViewHolder {
        ImageView iconView1;
        TextView textView;
        ImageView iconView;
        TextView textView1;
        LinearLayout detailsLayout;
    }

}

