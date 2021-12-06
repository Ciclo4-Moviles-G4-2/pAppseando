package com.ciclo4_moviles_g4_2.pappseando_app.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ciclo4_moviles_g4_2.pappseando_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText email= findViewById(R.id.et_mail);
        EditText password= findViewById(R.id.et_password);
        Button Registro=findViewById(R.id.btn_Registro);
        EditText confirmacion= findViewById(R.id.c_password);

        firebaseAuth= FirebaseAuth.getInstance();

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textMail= email.getText().toString();
                String textPassword= password.getText().toString();
                String textconfirmacion= confirmacion.getText().toString();

                if (textMail.isEmpty()){email.setError("Requerido");

                } else if (textPassword.isEmpty()) {password.setError("Requerido");
                }
                else if (textconfirmacion.isEmpty()) {
                    confirmacion.setError("Requerido");

                }
                else if (!textconfirmacion.equals(textPassword)) {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(textMail, textPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "Hubo un error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });
    }
}

