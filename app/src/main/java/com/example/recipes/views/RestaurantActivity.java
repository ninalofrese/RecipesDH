package com.example.recipes.views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.recipes.adapters.RecipesAdapter;
import com.example.recipes.interfaces.RecipeOnClick;
import com.example.recipes.models.Receita;
import com.example.recipes.models.Restaurante;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;

import com.example.recipes.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.recipes.views.MainActivity.RESTAURANT;

public class RestaurantActivity extends AppCompatActivity implements RecipeOnClick {
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView imageRestaurant;
    private RecyclerView recyclerRecipes;
    private RecipesAdapter recipesAdapter;
    private List<Receita> recipesList = new ArrayList<>();
    private Restaurante restaurante;

    public static final String RECIPE = "receita";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = findViewById(R.id.toolbar_layout);
        imageRestaurant = findViewById(R.id.image_restaurante);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().getExtras() != null) {
            restaurante = getIntent().getExtras().getParcelable(RESTAURANT);
            Drawable drawable = getResources().getDrawable(restaurante.getImagem());

            toolbarLayout.setTitle(restaurante.getNome());
            imageRestaurant.setImageDrawable(drawable);
            recipesList = restaurante.getListaReceita();
        }

        recyclerRecipes = findViewById(R.id.recipes_recyclerView);
        recipesAdapter = new RecipesAdapter(recipesList, this);
        recyclerRecipes.setAdapter(recipesAdapter);
        ViewCompat.setNestedScrollingEnabled(recyclerRecipes, false);
        recyclerRecipes.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    @Override
    public void onClick(Receita recipe) {
        Intent intent = new Intent(RestaurantActivity.this, RecipeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE, recipe);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
