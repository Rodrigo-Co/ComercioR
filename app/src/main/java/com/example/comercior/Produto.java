package com.example.comercior;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Produto implements Parcelable {
    private String nome;
    private int iconeResource;
    private String descricao;


    public Produto(String nome, int iconeResource, String descricao) {
        this.nome = nome;
        this.iconeResource = iconeResource;
        this.descricao = descricao;
    }

    // Construtor usado pelo Parcelable para recriar um objeto Produto a partir de um Parcel
    protected Produto(Parcel in) {
        nome = in.readString(); // Lê o nome do Parcel
        iconeResource = in.readInt(); // Lê o identificador do recurso de ícone do Parcel
        descricao = in.readString(); // Lê a descrição do Parcel
    }

    // Implementação da interface Parcelable
    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        } // Cria um novo objeto Produto a partir do Parcel

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        } // Cria um novo array de objetos Produto
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
    }// Método da interface Parcelable (não utilizado, mas necessário)

    // Método da interface Parcelable para escrever os dados do Produto em um Parcel
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nome); // Escreve o nome no Parcel
        dest.writeInt(iconeResource); // Escreve o identificador do recurso de ícone no Parcel
        dest.writeString(descricao); // Escreve a descrição no Parcel
    }
}
