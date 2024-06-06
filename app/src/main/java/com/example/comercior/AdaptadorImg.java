package com.example.comercior;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorImg extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> produtos;

    public AdaptadorImg(Context context, ArrayList<String> produtos) {
        super(context, android.R.layout.simple_dropdown_item_1line, produtos);
        this.context = context;
        this.produtos = produtos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_produto, parent, false);

        TextView textView = rowView.findViewById(android.R.id.text1);
        textView.setText(produtos.get(position));

        return rowView;
    }
}
