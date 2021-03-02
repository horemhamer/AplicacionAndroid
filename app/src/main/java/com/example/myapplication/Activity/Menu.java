package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class Menu extends AppCompatActivity {
    DataBase conexionBD = new DataBase();
    CircleImageView imagemuestra;
    Button logout, bustia, verservicio, cambiar;
    Preferencias preferencias = new Preferencias();
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        logout = findViewById(R.id.btnLogout);
        bustia = findViewById(R.id.btnBustia);
        verservicio = findViewById(R.id.btnVerServicios);
        cambiar = findViewById(R.id.btnCambiar);
        c = this;
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              /* mainActivity.correo.setText("");
                mainActivity.contrasenya.setText("");*/
                preferencias.setLogueado(false);
                preferencias.guardarPreferencia(c);

                Intent intent = new Intent(Menu.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        bustia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Chat.class);
                startActivity(intent);
            }
        });

        verservicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Servicios.class);
                startActivity(intent);
            }
        });

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Cambiardatos.class);
                startActivity(intent);
            }
        });


        //cargarImagenPerfil();
    }
}

   /*protected void cargarImagenPerfil(){

    }*/
