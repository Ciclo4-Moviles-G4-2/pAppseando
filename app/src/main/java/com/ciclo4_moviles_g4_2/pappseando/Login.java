package com.ciclo4_moviles_g4_2.pappseando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ciclo4_moviles_g4_2.pappseando.list_adapter.PlacesAdapter;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button inicia_sesion = findViewById(R.id.btn_inicio);
        inicia_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,MapsActivity.class);
                startActivity(i);
            }
        });
    }

    public void goToRegistro(View v) {
        Intent i = new Intent(Login.this,Registro.class);
        startActivity(i);
    }
}