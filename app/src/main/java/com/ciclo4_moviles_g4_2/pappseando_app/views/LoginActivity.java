package com.ciclo4_moviles_g4_2.pappseando_app.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ciclo4_moviles_g4_2.pappseando_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.et_mail);
        EditText password = findViewById(R.id.et_password);
        Button ingreso = findViewById(R.id.btn_Ingreso);
        TextView etiqueta = findViewById(R.id.tv_registrarse);
        TextView recuperacion = findViewById(R.id.txt_Remember_Password);

        firebaseAuth = FirebaseAuth.getInstance();

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.getText().toString().isEmpty()) {
                    email.setError("Requerido");
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Requerido");
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, " Bienvenido", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MapsActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(LoginActivity.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                                //Intent i = new Intent(LoginActivity.this, MapsActivity.class);
                                //startActivity(i);
                            }
                        }
                    });
                }
            }
        });


        etiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }

        });

        recuperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RecoveryActivity.class);
                startActivity(i);
            }
        });
    }

}