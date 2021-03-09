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
    //AdaptadorServicios adaptador;
    private Context c;
    ArrayList<InfoServicio>listaObjeto = new ArrayList<>();
    DataBase dataBase = new DataBase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        lServicios = findViewById(R.id.lServicios);
        listaObjeto = new ArrayList<InfoServicio>();
        Lista();
      //  adaptador = new AdaptadorServicios(this, getListaObjeto());
      //  lServicios.setAdapter(adaptador);
    }


    public Context getC() {
        return c;
    }



    protected void Lista(){
        int i =0;
        try{
            Statement st=dataBase.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Usuari,Serveis where tipus_usuari='Final' AND id_usuari=id_servei");
            String nusuari = "";
            String npassword = "";
            while(rs.next()){
                i++;
                      /*  nusuari = rs.getString("usuari");
                npassword = rs.getString("password");*/
                try{
                  listaObjeto.add(new InfoServicio(rs.getString("usuari"),rs.getString("titol"),rs.getString("descipcio"),rs.getString("imatge"),rs.getString("imatge_servei")));

                    lServicios.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                  //  adaptador.list.get(i);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }catch (Exception e){

        }

    }


    public ArrayList<InfoServicio> getListaObjeto() {
        return listaObjeto;
    }

    public void setListaObjeto(ArrayList<InfoServicio> listaObjeto) {
        this.listaObjeto = listaObjeto;
    }
}