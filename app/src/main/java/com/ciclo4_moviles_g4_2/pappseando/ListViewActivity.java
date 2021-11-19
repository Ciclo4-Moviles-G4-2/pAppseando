package com.ciclo4_moviles_g4_2.pappseando;

/* Código Java para el manejo de la vista de lista de lugares
   Implementado por: Mauricio Moreno
*/

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ciclo4_moviles_g4_2.pappseando.list_adapter.Place;
import com.ciclo4_moviles_g4_2.pappseando.list_adapter.PlacesAdapter;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        fillPlaces();
    }

    private void fillPlaces() {
        RecyclerView rvLugares = findViewById(R.id.rv_places);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));

        List<Place> lugares = new ArrayList<>();
        String[] nombreLugares = {"La Martina - Food Fast", "Parque del Café", "Mendihuacan Caribbean",
                "Jardín Plaza", "PANACA", "Ukumari", "Plaza de Bolívar", "Piedra del Peñol", "Minas de sal",
                "Caño Cristales", "Desierto de la Tatacoa", "Pantano de Vargas", "Puente de Boyacá"};
        String[] descLugares = {"Restaurante - Jamundí (Valle)", "Parque temático - Armenia (Quindío)",
                "Hotel - Santa Marta (Magdalena)", "Centro comercial - Cali (Valle)", "Parque temático - Quimbaya (Quindío)",
                "Bioparque - Pereira (Risaralda)", "Plaza mayor - Bogotá D.C.", "Sitio turístico - Guatapé (Antioquia)",
                "Parque natural - Nemocón (Cundinamarca)", "Parque natural - La Macarena (Meta)", "Parque natural - Natagaima (Huila)",
                "Sitio histórico - Paipa (Boyacá)", "Sitio histórico - Tunja (Boyacá)"};

        for (int i = 0; i < nombreLugares.length; i++) {
            lugares.add(new Place(nombreLugares[i], descLugares[i], R.drawable.place));
        }

        PlacesAdapter adaptadorLugares = new PlacesAdapter(lugares, this);
        rvLugares.setAdapter(adaptadorLugares);
    }

    //TODO: Navegación a vista de mapa y ficha de lugares pendiente

    public void goToMap(View view) {
        Toast.makeText(this, "Ha elegido ver el mapa", Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, MapViewActivity.class);
        //startActivity(intent);
    }

    public void goToForm(View view) {
        TextView nombre = findViewById(R.id.tv_place_name);
        Toast.makeText(this, "Ha elegido " + nombre.getText(), Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(this, FormViewActivity.class);
        //startActivity(intent);
    }
}