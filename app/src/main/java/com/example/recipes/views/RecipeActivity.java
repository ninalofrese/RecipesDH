package com.example.recipes.views;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.recipes.models.Receita;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipes.R;

import static com.example.recipes.views.RestaurantActivity.RECIPE;

public class RecipeActivity extends AppCompatActivity {
    private CollapsingToolbarLayout toolbarLayout;
    private ImageView imageRecipe;
    private TextView detailRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = findViewById(R.id.toolbar_layout);
        imageRecipe = findViewById(R.id.image_recipe);
        detailRecipe = findViewById(R.id.content_recipe);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null && getIntent().getExtras() != null) {
            Receita recipe = getIntent().getExtras().getParcelable(RECIPE);
            Drawable drawable = getResources().getDrawable(recipe.getImagemPrato());

            toolbarLayout.setTitle(recipe.getNomePrato());
            imageRecipe.setImageDrawable(drawable);
            detailRecipe.setText(recipe.getDetalhesPrato());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
