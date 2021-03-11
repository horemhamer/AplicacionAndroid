package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.Entidades.Logica.LMensaje;
import com.example.myapplication.HolderServicio;
import com.example.myapplication.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Servicios extends AppCompatActivity {

    ListView lServicios;
   AdaptadorServicios adaptador;
    List <InfoServicio>listaObjeto = new ArrayList<>();
    DataBase dataBase = new DataBase();
    Drawable image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        lServicios = findViewById(R.id.lServicios);
       adaptador = new AdaptadorServicios(this, R.layout.servicios_view,Lista());
      lServicios.setAdapter(adaptador);
        Toast.makeText(getApplicationContext(), listaObjeto.toString(), Toast.LENGTH_SHORT).show();
  //   setContentView(lServicios);
    }





    protected List<InfoServicio> Lista(){
        byte[] bytes;
        try{
            Statement st=dataBase.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Serveis  ");
            String nusuari = "";
            String npassword = "";
            while(rs.next()){
                bytes = rs.getBytes("imatge_servei");
               image = new BitmapDrawable(getResources(),BitmapFactory.decodeByteArray(bytes,0,bytes.length));
                try{
              listaObjeto.add(new InfoServicio("",rs.getString("titol"),rs.getString("descripcio"),"", image));
                    //listaObjeto.add(new InfoServicio("a","e","a","e","e"));

                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }catch (Exception e){

        }

        return listaObjeto;
    }




}