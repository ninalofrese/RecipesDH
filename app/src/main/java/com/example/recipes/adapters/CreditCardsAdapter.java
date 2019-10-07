package com.example.recipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipes.R;
import com.example.recipes.interfaces.CardOnClick;
import com.example.recipes.models.CreditCard;

import java.util.List;

public class CreditCardsAdapter extends RecyclerView.Adapter<CreditCardsAdapter.ViewHolder> {
    private List<CreditCard> creditCardList;
    private CardOnClick listener;

    public CreditCardsAdapter(List<CreditCard> creditCardList, CardOnClick listener) {
        this.creditCardList = creditCardList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_credit_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CreditCard cartao = creditCardList.get(position);
        holder.onBind(cartao);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCardClick(cartao);
            }
        });
    }

    @Override
    public int getItemCount() {
        return creditCardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cardName;
        private TextView cardNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardName = itemView.findViewById(R.id.item_nomeCartao);
            cardNumber = itemView.findViewById(R.id.item_numeroCartao);
        }

        public void onBind(CreditCard cartao) {

            String numeroCartao = String.valueOf(cartao.getNumber());
            cardName.setText(cartao.getName());
            cardNumber.setText(maskCardNumber(numeroCartao, "#### **** **** ####"));
        }
    }

    public static String maskCardNumber(String cardNumber, String mask) {

        // format the number
        int index = 0;
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == 'x') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        // return the masked number
        return maskedNumber.toString();
    }

    public void updateItem(int position, CreditCard novoCartao) {
        CreditCard velhoCartao = creditCardList.get(position);
        velhoCartao.setName(novoCartao.getName());
        velhoCartao.setNumber(novoCartao.getNumber());
        velhoCartao.setSafeCode(novoCartao.getSafeCode());
        notifyItemChanged(position, velhoCartao);
    }

    public void updateList(CreditCard cartao) {
        insertItem(cartao);
    }

    private void insertItem(CreditCard cartao) {
        creditCardList.add(cartao);
        notifyItemInserted(getItemCount());
    }

}
