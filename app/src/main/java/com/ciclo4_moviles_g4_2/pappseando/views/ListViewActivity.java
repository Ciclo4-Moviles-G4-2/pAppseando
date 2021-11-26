package com.ciclo4_moviles_g4_2.pappseando.views;

/* Código Java para el manejo de la vista de lista de lugares
   Implementado por: Mauricio Moreno
*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ciclo4_moviles_g4_2.pappseando.R;
import com.ciclo4_moviles_g4_2.pappseando.adapters.PlaceVO;
import com.ciclo4_moviles_g4_2.pappseando.adapters.PlacesAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private RecyclerView rvLugares;
    private List<PlaceVO> lugares;
    private PlacesAdapter adaptadorLugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        rvLugares = findViewById(R.id.rv_places);
        rvLugares.setLayoutManager(new LinearLayoutManager(this));
        loadPlacesOnRecyclerView();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                String nombreLugar = lugares.get(position).getNombre();

                lugares.remove(position);
                adaptadorLugares.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Se ha borrado el lugar '" + nombreLugar + "' satisfactoriamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                return super.getSwipeDirs(recyclerView, viewHolder);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvLugares);
    }

    private void loadPlacesOnRecyclerView() {
        String[] nombreLugares = {"La Martina - Food Fast", "Parque del Café", "Mendihuacan Caribbean",
                "Jardín Plaza", "PANACA", "Ukumari", "Plaza de Bolívar", "Piedra del Peñol", "Minas de sal",
                "Caño Cristales", "Desierto de la Tatacoa", "Pantano de Vargas", "Puente de Boyacá"};
        String[] descLugares = {"Restaurante - Jamundí (Valle)", "Parque temático - Armenia (Quindío)",
                "Hotel - Santa Marta (Magdalena)", "Centro comercial - Cali (Valle)", "Parque temático - Quimbaya (Quindío)",
                "Bioparque - Pereira (Risaralda)", "Plaza mayor - Bogotá D.C.", "Sitio turístico - Guatapé (Antioquia)",
                "Parque natural - Nemocón (Cundinamarca)", "Parque natural - La Macarena (Meta)", "Parque natural - Natagaima (Huila)",
                "Sitio histórico - Paipa (Boyacá)", "Sitio histórico - Tunja (Boyacá)"};

        lugares = new ArrayList<>();

        for (int i = 0; i < nombreLugares.length; i++) {
            lugares.add(new PlaceVO(nombreLugares[i], descLugares[i], R.drawable.place));
        }

        adaptadorLugares = new PlacesAdapter(lugares, this);
        adaptadorLugares.setOnClickListener(v -> {
            String nombreLugar = lugares.get(rvLugares.getChildAdapterPosition(v)).getNombre();
            String descLugar = lugares.get(rvLugares.getChildAdapterPosition(v)).getDescripcion();
            goToForm(nombreLugar, descLugar);
        });

        rvLugares.setAdapter(adaptadorLugares);
    }

    public void goToMap(View view) {
        //Toast.makeText(this, "Ha elegido ver el mapa", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


    public void goToForm(String nombreLugar, String descLugar) {
        Toast.makeText(getApplicationContext(), "Ha elegido: " + nombreLugar, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FormPlaceActivity.class);
        intent.putExtra("nombre", nombreLugar);
        intent.putExtra("descripcion", descLugar);
        startActivity(intent);
    }
}