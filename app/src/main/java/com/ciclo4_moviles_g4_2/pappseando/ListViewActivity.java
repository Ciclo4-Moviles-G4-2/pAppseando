package com.ciclo4_moviles_g4_2.pappseando;

/* Código Java para el manejo de la vista de lista de lugares
   Implementado por: Mauricio Moreno
*/

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
    }

    //TODO: Navegación a vista de mapa y ficha de lugares pendiente

    // public void goToMap(View view) {
    //     Intent intent = new Intent(this, MapViewActivity.class);
    //     startActivity(intent);
    // }
    //
    // public void goToForm(View view) {
    //     Intent intent = new Intent(this, FormViewActivity.class);
    //     startActivity(intent);
    // }
}