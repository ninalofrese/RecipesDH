package com.example.recipes.views;

import android.content.Intent;
import android.os.Bundle;

import com.example.recipes.R;
import com.example.recipes.adapters.RestaurantsAdapter;
import com.example.recipes.interfaces.RestaurantOnClick;
import com.example.recipes.models.Restaurante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantOnClick {
    private RecyclerView recyclerRestaurant;
    private RestaurantsAdapter adapterRestaurant;
    private List<Restaurante> listaRestaurantes = new ArrayList<>();

    public static final String RESTAURANT = "restaurante";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerRestaurant = findViewById(R.id.recycler_main);
        adapterRestaurant = new RestaurantsAdapter(listarRestaurantes(), this);
        recyclerRestaurant.setAdapter(adapterRestaurant);
        recyclerRestaurant.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Restaurante> listarRestaurantes() {
        listaRestaurantes.add(new Restaurante("Tony's Roma", "Av. Lavandisca, 717 - Indianópolis, São Paulo", "Fecha às 22h", R.drawable.restaurante1));
        listaRestaurantes.add(new Restaurante("Aoyama Moema", "Av. Lavandisca, 717 - Indianópolis, São Paulo", "Fecha às 22h", R.drawable.restaurante2));
        listaRestaurantes.add(new Restaurante("Outback Moema", "Av. Lavandisca, 717 - Indianópolis, São Paulo", "Fecha às 22h", R.drawable.restaurante3));
        listaRestaurantes.add(new Restaurante("Si Señor Moema", "Av. Lavandisca, 717 - Indianópolis, São Paulo", "Fecha às 22h", R.drawable.restaurante4));

        return listaRestaurantes;
    }

    @Override
    public void onClick(Restaurante restaurante) {
        Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(RESTAURANT, restaurante);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}