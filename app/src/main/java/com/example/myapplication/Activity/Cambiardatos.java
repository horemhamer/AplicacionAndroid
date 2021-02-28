package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.DataBase;
import com.example.myapplication.R;

public class Cambiardatos extends AppCompatActivity {

    EditText nombre, correo, edad, contraseña;
    Button confirmar;
    private DataBase conexionBD = new DataBase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiardatos);
        nombre = findViewById(R.id.editMNom);
        correo = findViewById(R.id.editMEmail);
        edad = findViewById(R.id.editMEdat);
        contraseña = findViewById(R.id.editMContra);
        confirmar = findViewById(R.id.btnMConfirmar);


        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             guardarCambios();
            }
        });

    }


    protected void guardarCambios(){

    }



}