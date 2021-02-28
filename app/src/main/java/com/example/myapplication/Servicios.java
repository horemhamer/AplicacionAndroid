package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

public class Servicios extends AppCompatActivity {

    RecyclerView rServicios;
    AdaptadorServicios adaptador;
    private Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        rServicios = findViewById(R.id.rServicios);
        adaptador = new AdaptadorServicios(getC());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rServicios.setLayoutManager(linearLayoutManager);
        rServicios.setAdapter(adaptador);
    }


    public Context getC() {
        return c;
    }
}