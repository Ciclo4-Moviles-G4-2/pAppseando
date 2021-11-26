package com.ciclo4_moviles_g4_2.pappseando.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.ciclo4_moviles_g4_2.pappseando.R;

public class FormPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_place);
        Bundle bundle = getIntent().getExtras();
        TextView nombre = findViewById(R.id.tv_nombreLugar);
        TextView descripcion = findViewById(R.id.tv_descLugar);
        nombre.setText(bundle.getString("nombre"));
        descripcion.setText(bundle.getString("descripcion"));
    }
}