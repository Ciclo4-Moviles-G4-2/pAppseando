package com.ciclo4_moviles_g4_2.pappseando;

/* Código Java para el manejo de la vista de lista de lugares
   Implementado por: Mauricio Moreno
*/

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ciclo4_moviles_g4_2.pappseando.adapter.Places;
import com.ciclo4_moviles_g4_2.pappseando.adapter.PlacesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private RecyclerView rvLugares;
    private PlacesAdapter adaptadorLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initPlaces();
    }

    private void initPlaces() {
        rvLugares = findViewById(R.id.rv_places);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));

        List<Places> lugares = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            lugares.add(new Places(i, "Lugar " + (i + 1), "Descripción " + (i + 1)));
        }

        adaptadorLugares = new PlacesAdapter(lugares, this);
        rvLugares.setAdapter(adaptadorLugares);
    }

    //TODO: Navegación a vista de mapa y ficha de lugares pendiente

     public void goToMap(View view) {
         Toast.makeText(this,"Ha elegido ver el mapa", Toast.LENGTH_LONG).show();
         //Intent intent = new Intent(this, MapViewActivity.class);
         //startActivity(intent);
     }

     public void goToForm(View view) {
         Toast.makeText(this,"Ha elegido ver un lugar", Toast.LENGTH_LONG).show();
         //Intent intent = new Intent(this, FormViewActivity.class);
         //startActivity(intent);
     }
}