package com.ciclo4_moviles_g4_2.pappseando.views;

/* Código Java para el manejo de la vista de lista de lugares
   Implementado por: Mauricio Moreno
*/

import android.app.AlertDialog;
import android.content.Context;
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
import java.util.concurrent.atomic.AtomicInteger;

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
        deletePlacesListener(this);
    }

    private void loadPlacesOnRecyclerView() {
        //Carga/llenado de lugares en el Recycler View

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

    private void deletePlacesListener(Context context) {
        //Borrado de lugares de la lista mediante gesto swipe simple (deslizar a izquierda o derecha)

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AtomicInteger position = new AtomicInteger(viewHolder.getAdapterPosition());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                PlaceVO lugarTemp = lugares.get(position.get());
                String nombreLugar = lugarTemp.getNombre();

                builder.setMessage("\n¿Deseas eliminar el lugar '" + nombreLugar + "'?")
                        .setTitle("Borrar lugar")
                        .setPositiveButton("Sí", (dialog, which) ->
                                Toast.makeText(context, "Se ha borrado el lugar '" + nombreLugar + "' satisfactoriamente", Toast.LENGTH_SHORT).show())
                        .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel())
                        .setOnCancelListener(dialog -> {
                            adaptadorLugares.addPlace(lugarTemp, position.get());
                            position.getAndIncrement();
                        })
                        .setOnDismissListener(dialog -> adaptadorLugares.removePlace(position.get()));

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvLugares);
    }

    public void goToMap(View view) {
        //Toast.makeText(this, "Ha elegido ver el mapa", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void goToForm(String nombreLugar, String descLugar) {
        //Toast.makeText(getApplicationContext(), "Ha elegido: " + nombreLugar, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, FormPlaceActivity.class);
        intent.putExtra("nombre", nombreLugar);
        intent.putExtra("descripcion", descLugar);
        startActivity(intent);
    }
}