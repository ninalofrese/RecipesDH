package com.example.recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Receita implements Parcelable {
    private String nomePrato;
    private int imagemPrato;
    private String detalhesPrato;

    public Receita() {
    }

    public Receita(String nomePrato, int imagemPrato, String detalhesPrato) {
        this.nomePrato = nomePrato;
        this.imagemPrato = imagemPrato;
        this.detalhesPrato = detalhesPrato;
    }

    protected Receita(Parcel in) {
        nomePrato = in.readString();
        imagemPrato = in.readInt();
        detalhesPrato = in.readString();
    }

    public static final Creator<Receita> CREATOR = new Creator<Receita>() {
        @Override
        public Receita createFromParcel(Parcel in) {
            return new Receita(in);
        }

        @Override
        public Receita[] newArray(int size) {
            return new Receita[size];
        }
    };

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public int getImagemPrato() {
        return imagemPrato;
    }

    public void setImagemPrato(int imagemPrato) {
        this.imagemPrato = imagemPrato;
    }

    public String getDetalhesPrato() {
        return detalhesPrato;
    }

    public void setDetalhesPrato(String detalhesPrato) {
        this.detalhesPrato = detalhesPrato;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nomePrato);
        parcel.writeInt(imagemPrato);
        parcel.writeString(detalhesPrato);
    }
}
