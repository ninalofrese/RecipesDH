package com.example.recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurante implements Parcelable {
    private String nome;
    private String endereco;
    private String horario;
    private int imagem;

    public Restaurante(String nome, String endereco, String horario, int imagem) {
        this.nome = nome;
        this.endereco = endereco;
        this.horario = horario;
        this.imagem = imagem;
    }

    public Restaurante() {
    }

    protected Restaurante(Parcel in) {
        nome = in.readString();
        endereco = in.readString();
        horario = in.readString();
        imagem = in.readInt();
    }

    public static final Creator<Restaurante> CREATOR = new Creator<Restaurante>() {
        @Override
        public Restaurante createFromParcel(Parcel in) {
            return new Restaurante(in);
        }

        @Override
        public Restaurante[] newArray(int size) {
            return new Restaurante[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nome);
        parcel.writeString(endereco);
        parcel.writeString(horario);
        parcel.writeInt(imagem);
    }
}
