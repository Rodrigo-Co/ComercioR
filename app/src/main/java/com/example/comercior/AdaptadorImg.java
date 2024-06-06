package com.example.comercior;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdaptadorImg extends ArrayAdapter<Produto> {
    private final Context context;
    private final List<Produto> produtos;

    public AdaptadorImg(Context context, List<Produto> produtos) {
        super(context, R.layout.custom_dropdown_item, produtos);
        this.context = context;
        this.produtos = produtos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_dropdown_item, parent, false);

        TextView textView = rowView.findViewById(android.R.id.text1);
        ImageView iconView = rowView.findViewById(R.id.icon);
        Produto produto = produtos.get(position);

        textView.setText(produto.getNome());
        iconView.setImageResource(produto.getIconeResource());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ItemClicked", "Item clicado na posição: " + position);
                LinearLayout detailsLayout = rowView.findViewById(R.id.details);
                
                if (detailsLayout.getVisibility() == View.VISIBLE) {
                    detailsLayout.setVisibility(View.GONE); // Oculta os detalhes
                } else {
                    detailsLayout.setVisibility(View.VISIBLE); // Exibe os detalhes
                }
            }
        });

        return rowView;
    }
}
