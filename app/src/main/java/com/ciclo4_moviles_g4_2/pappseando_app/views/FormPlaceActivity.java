package com.ciclo4_moviles_g4_2.pappseando_app.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ciclo4_moviles_g4_2.pappseando_app.R;
import com.ciclo4_moviles_g4_2.pappseando_app.models.PlaceVO;

public class FormPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_place);
        TextView nombre = findViewById(R.id.tv_nombreLugar);
        TextView descripcion = findViewById(R.id.tv_descLugar);
        PlaceVO lugarElegido = (PlaceVO) getIntent().getSerializableExtra("lugar");

        nombre.setText(lugarElegido.getNombre());
        descripcion.setText(lugarElegido.getDescripcion());
    }
}