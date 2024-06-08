package com.example.comercior;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Produto implements Parcelable {
    private String nome;
    private int iconeResource;
    private String descricao;
    // Outros detalhes do produto (preço, descrição, etc.)


    public Produto(String nome, int iconeResource, String descricao) {
        this.nome = nome;
        this.iconeResource = iconeResource;
        this.descricao = descricao;
    }

    protected Produto(Parcel in) {
        nome = in.readString();
        iconeResource = in.readInt();
        descricao = in.readString();
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public int getIconeResource() {
        return iconeResource;
    }
    public String getDescricao(){
        return descricao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(iconeResource);
        dest.writeString(descricao);
    }
}
