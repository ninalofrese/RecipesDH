package com.example.recipes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CreditCard implements Parcelable {
    private String name;
    private long number;
    private int safeCode;

    public CreditCard(String name, long number, int safeCode) {
        this.name = name;
        this.number = number;
        this.safeCode = safeCode;
    }

    public CreditCard() {
    }

    protected CreditCard(Parcel in) {
        name = in.readString();
        safeCode = in.readInt();
    }

    public static final Creator<CreditCard> CREATOR = new Creator<CreditCard>() {
        @Override
        public CreditCard createFromParcel(Parcel in) {
            return new CreditCard(in);
        }

        @Override
        public CreditCard[] newArray(int size) {
            return new CreditCard[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public int getSafeCode() {
        return safeCode;
    }

    public void setSafeCode(int safeCode) {
        this.safeCode = safeCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(safeCode);
    }
}
