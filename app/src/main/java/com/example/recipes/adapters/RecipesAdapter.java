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
import com.example.recipes.interfaces.RecipeOnClick;
import com.example.recipes.models.Receita;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    private List<Receita> listRecipes;
    private RecipeOnClick listener;

    public RecipesAdapter(List<Receita> listRecipes, RecipeOnClick listener) {
        this.listRecipes = listRecipes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Receita receita = listRecipes.get(position);

        holder.onBind(receita);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(receita);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRecipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageRecipe;
        private TextView nameRecipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageRecipe = itemView.findViewById(R.id.item_imagemReceita);
            nameRecipe = itemView.findViewById(R.id.item_nomeReceita);
        }

        public void onBind(Receita receita) {
            Drawable drawable = itemView.getResources().getDrawable(receita.getImagemPrato());

            imageRecipe.setImageDrawable(drawable);
            nameRecipe.setText(receita.getNomePrato());
        }
    }
}
