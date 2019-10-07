package com.example.recipes.interfaces;

import com.example.recipes.models.CreditCard;

public interface DialogBack {
    void onClickBack(CreditCard cartao);
    void onNewItem(CreditCard cartao);
}
