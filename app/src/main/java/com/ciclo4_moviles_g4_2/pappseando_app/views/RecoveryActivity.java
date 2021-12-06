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
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        EditText RecoveryEmail= findViewById(R.id.edit_Text_Recovery_Mail);
        Button Recovery=findViewById(R.id.btn_Recovery);
        firebaseAuth= FirebaseAuth.getInstance();
        Recovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RecoveryEmail.getText().toString().isEmpty()){RecoveryEmail.setError("Requerido");
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(RecoveryEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RecoveryActivity.this, "Revisa tu correo", Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(RecoveryActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(RecoveryActivity.this, "El correo ingresado no está registrado", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });
    }
}
