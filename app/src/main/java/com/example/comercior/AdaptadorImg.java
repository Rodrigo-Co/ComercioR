package com.example.comercior;

import static java.lang.reflect.Array.get;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class AdaptadorImg extends ArrayAdapter<Produto> implements Filterable {
    private final Context context;
    private final List<Produto> produtos;
    private List<Produto> produtosFiltrados;
    public AdaptadorImg(Context context, List<Produto> produtos) {
        super(context, R.layout.custom_dropdown_item, produtos);
        this.context = context;
        this.produtos = new ArrayList<>(produtos);
        this.produtosFiltrados = new ArrayList<>(produtos);

    }
    @Override
    public int getCount() {
        return produtosFiltrados.size();
    }

    @Override
    public Produto getItem(int position) {
        return produtosFiltrados.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_dropdown_item, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(android.R.id.text1);
            holder.iconView = convertView.findViewById(R.id.icon);
            holder.textView1 = convertView.findViewById(R.id.product_description);
            holder.detailsLayout = convertView.findViewById(R.id.details);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Produto produto = getItem(position);

        holder.textView.setText(produto.getNome());
        holder.iconView.setImageResource(produto.getIconeResource());
        holder.textView1.setText(produto.getDescricao());
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
                    filteredList.addAll(produtos);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Produto item : produtos) {
                        if (item.getNome().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                produtosFiltrados.clear();
                produtosFiltrados.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };
    }

    static class ViewHolder {
        TextView textView;
        ImageView iconView;
        TextView textView1;
        LinearLayout detailsLayout;
    }

}

