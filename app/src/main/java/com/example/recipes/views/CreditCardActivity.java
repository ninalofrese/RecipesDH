package com.example.recipes.views;

import android.os.Bundle;

import com.example.recipes.adapters.CreditCardsAdapter;
import com.example.recipes.interfaces.CardOnClick;
import com.example.recipes.interfaces.DialogBack;
import com.example.recipes.models.CreditCard;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.recipes.R;

import java.util.ArrayList;
import java.util.List;

public class CreditCardActivity extends AppCompatActivity implements CardOnClick, DialogBack {
    private RecyclerView recyclerCartao;
    private CreditCardsAdapter adapter;
    private List<CreditCard> listaCartao = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.add_credit_card);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DialogFragment novoCartao = new DialogFragment();
                novoCartao.show(manager, "fragment_dialog");
            }
        });

        recyclerCartao = findViewById(R.id.recycler_creditcards);
        adapter = new CreditCardsAdapter(retornarCartoes(), this);
        recyclerCartao.setAdapter(adapter);
        recyclerCartao.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<CreditCard> retornarCartoes() {
        listaCartao.add(new CreditCard("Mastercard Nubank", 1234567891011L, 321));
        listaCartao.add(new CreditCard("Visa Nubank", 1234567891011L, 321));
        listaCartao.add(new CreditCard("Mastercard Nubank", 1234567891011L, 321));
        listaCartao.add(new CreditCard("Visa Nubank", 1234567891011L, 321));

        return listaCartao;
    }

    //Clique no CardView que abre o dialog
    @Override
    public void onCardClick(CreditCard cartao) {
        FragmentManager manager = getSupportFragmentManager();
        DialogFragment editNameDialogFragment = DialogFragment.newInstance(cartao);
        editNameDialogFragment.show(manager, "fragment_dialog");
    }

    //Clique no botao de OK dentro do Dialog
    @Override
    public void onClickBack(CreditCard cartao) {
        adapter.updateItem(listaCartao.indexOf(cartao), cartao);
    }

    @Override
    public void onNewItem(CreditCard cartao) {
        adapter.insertItem(cartao);
    }

}
