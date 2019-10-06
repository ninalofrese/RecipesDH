package com.example.recipes.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipes.R;
import com.example.recipes.interfaces.RestaurantOnClick;
import com.example.recipes.models.Restaurante;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {
    private List<Restaurante> restaurantList;
    private RestaurantOnClick listener;

    public RestaurantsAdapter(List<Restaurante> restaurantList, RestaurantOnClick listener) {
        this.restaurantList = restaurantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Restaurante restaurant = restaurantList.get(position);
        holder.onBind(restaurant);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(restaurant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageRestaurant;
        private TextView nameRestaurant;
        private TextView addressRestaurant;
        private TextView hoursRestaurant;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageRestaurant = itemView.findViewById(R.id.item_imagemRestaurante);
            nameRestaurant = itemView.findViewById(R.id.item_nomeRestaurante);
            addressRestaurant = itemView.findViewById(R.id.item_enderecoRestaurante);
            hoursRestaurant = itemView.findViewById(R.id.item_horarioRestaurante);
        }

        public void onBind(Restaurante restaurant) {
            Drawable drawable = itemView.getResources().getDrawable(restaurant.getImagem());

            imageRestaurant.setImageDrawable(drawable);
            nameRestaurant.setText(restaurant.getNome());
            addressRestaurant.setText(restaurant.getEndereco());
            hoursRestaurant.setText(restaurant.getHorario());
        }
    }
}
