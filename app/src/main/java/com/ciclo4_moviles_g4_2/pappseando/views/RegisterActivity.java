package com.ciclo4_moviles_g4_2.pappseando.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ciclo4_moviles_g4_2.pappseando.R;

public class RegisterActivity extends AppCompatActivity {
    // Validación de datos en Registro de Usuario //
    private EditText et_Name;
    private EditText et_mail;
    private EditText et_Password;
    private EditText et_CPassword;
    //        Fin Validación de Datos en Registro de Usuario //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        //        Inicio Validación de Datos en Registro de Usuario //
        et_Name=(EditText) findViewById(R.id.Name_Registration);
        et_mail=(EditText) findViewById(R.id.EmailAddress);
        et_Password=(EditText) findViewById(R.id.Password);
        et_CPassword=(EditText) findViewById(R.id.Confirm_Password);
        //        Fin Validación de Datos en Registro de Usuario //

    }


    //        Inicio Método Validadión de Datos en Registro de Usuario //
    public void siguiente ( View view) {
        String nombre= et_Name.getText().toString();
        String mail= et_mail.getText().toString();
        String password= et_Password.getText().toString();
        String cpassword= et_CPassword.getText().toString();

        if (nombre.length() != 0 && mail.length() != 0 && password.length() != 0 && cpassword.length() != 0) {

            Intent i= new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Registro Exitoso",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this, "Hay campos vacíos. Asegúrate de llenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }
    //        Fin Método Validadión de Datos en Registro de Usuario //


}

