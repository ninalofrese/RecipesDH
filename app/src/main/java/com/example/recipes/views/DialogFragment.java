package com.example.recipes.views;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.example.recipes.R;
import com.example.recipes.interfaces.CardOnClick;
import com.example.recipes.interfaces.DialogBack;
import com.example.recipes.models.CreditCard;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends androidx.fragment.app.DialogFragment implements TextView.OnEditorActionListener {
    public static final String CARTAO_NOVO = "cartao";
    public static final String DIALOG_KEY = "dialog";
    private TextInputLayout cardName;
    private TextInputLayout cardNumber;
    private TextInputLayout cardCode;
    private Button buttonCancel;
    private Button buttonOK;
    private DialogBack listenerOK;

    CreditCard cartaoCredito;

    public DialogFragment() {
        // Required empty public constructor
    }

    public static DialogFragment newInstance(
            CreditCard cartao) {

        Bundle bundle = new Bundle();
        bundle.putParcelable(CARTAO_NOVO, cartao);

        DialogFragment dialog = new DialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof DialogBack) {
            listenerOK = (DialogBack) context;
        } else {
            throw new ClassCastException(context.toString() +
                    "must implement interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        initViews(view);

        if (getArguments() != null) {
            cartaoCredito = getArguments().getParcelable(CARTAO_NOVO);

            cardName.getEditText().setText(cartaoCredito.getName());
            cardNumber.getEditText().setText(String.format(Locale.US, "%d", cartaoCredito.getNumber()));
            cardCode.getEditText().setText(String.format(Locale.US, "%d", cartaoCredito.getSafeCode()));

            buttonOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nome = cardName.getEditText().getText().toString();
                    String number = cardNumber.getEditText().getText().toString();
                    String code = cardCode.getEditText().getText().toString();

                    cartaoCredito.setName(nome);
                    cartaoCredito.setNumber(Long.parseLong(number));
                    cartaoCredito.setSafeCode(Integer.parseInt(code));

                    if (listenerOK != null) {
                        listenerOK.onClickBack(cartaoCredito);
                        dismiss();
                    }

                }
            });
        } else{
            buttonOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String nome = cardName.getEditText().getText().toString();
                    String number = cardNumber.getEditText().getText().toString();
                    String code = cardCode.getEditText().getText().toString();

                    CreditCard novoCartao = new CreditCard(nome, Long.parseLong(number), Integer.parseInt(code));

                    if (listenerOK != null) {
                        listenerOK.onNewItem(novoCartao);
                        dismiss();
                    }

                }
            });
        }

        // Exibe o teclado virtual ao exibir o Dialog
//        getDialog().getWindow().setSoftInputMode(
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//
//        getDialog().setTitle("Register Credit Card");
//
//        cardName.requestFocus();
        // Listener para quando clicarmos
        // em 'Done' no teclado
        //cardCode.setOnEditorActionListener(this);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }


    public void initViews(View view) {
        cardName = view.findViewById(R.id.dialog_card_name);
        cardNumber = view.findViewById(R.id.dialog_card_number);
        cardCode = view.findViewById(R.id.dialog_card_safecode);
        buttonCancel = view.findViewById(R.id.dialog_button_cancel);
        buttonOK = view.findViewById(R.id.dialog_button_ok);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        // Se clicou em 'Done'
        if (EditorInfo.IME_ACTION_DONE == i) {
            // Notifique a Activity
            CardOnClick activity =
                    (CardOnClick) getActivity();
            activity.onCardClick(cartaoCredito);
            // Feche o dialog
            dismiss();
            return true;
        }
        return false;
    }

    public void openDialog(FragmentManager fm) {
        if (fm.findFragmentByTag(DIALOG_KEY) == null) {
            show(fm, DIALOG_KEY);
        }
    }

}