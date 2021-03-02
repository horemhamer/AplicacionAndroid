package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
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

public class Servicios extends AppCompatActivity {

    ListView lServicios;
    AdaptadorServicios adaptador;
    private Context c;
    ArrayList<InfoServicio>listaObjeto = new ArrayList<>();
    DataBase dataBase = new DataBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        lServicios = findViewById(R.id.lServicios);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaObjeto = new ArrayList<InfoServicio>();
        adaptador = new AdaptadorServicios(this, listaObjeto);

    }


    public Context getC() {
        return c;
    }



    protected void Lista(){

        int i = 0;


        try{
            Statement st=dataBase.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Serveis");
            String nusuari = "";
            String npassword = "";
            while(rs.next()){
                i++;
              /*  nusuari = rs.getString("usuari");
                npassword = rs.getString("password");*/
                try{
                  listaObjeto.add(new InfoServicio(rs.getString("a"),rs.getString("s"),rs.getString("s"),rs.getString(""),rs.getString("")));
                    lServicios.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lServicios.setAdapter(adaptador);
                    //adaptador.list.get(i);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }catch (Exception e){

        }

    }



}