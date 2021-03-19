package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Servicios extends AppCompatActivity {
    private DataBase conexionBD = new DataBase();
    ListView lServicios;
    AdaptadorServicios adaptador;
    List<InfoServicio> listaObjeto = new ArrayList<>();

    DataBase dataBase = new DataBase();
    Drawable image, imagenperfil;
    String correo;
    int idusuario;
    boolean comprobaremail;
    int i;
    Preferencias preferencias = new Preferencias();
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        preferencias.cargarPreferencias(this);
        correo = preferencias.getCorreo();
        lServicios = findViewById(R.id.lServicios);
        adaptador = new AdaptadorServicios(this, R.layout.servicios_view, Lista());
        lServicios.setAdapter(adaptador);

        //   setContentView(lServicios);
        Toast.makeText(getApplicationContext(), getNombre(), Toast.LENGTH_SHORT).show();

    }


    protected List<InfoServicio> Lista() {
        byte[] bytes;
     //  if (getEmail()) {
            try {

                Statement st = dataBase.conexionBD().createStatement();

                ResultSet rs = st.executeQuery(" select *  from Serveis where id_usuari != " +getIDUsuario()+ "");


                while (rs.next()) {

                    bytes = rs.getBytes("imatge_servei");
                    image = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                    try {
                        listaObjeto.add(new InfoServicio(getNombre(), rs.getString("titol"), rs.getString("descripcio"), getImagenPerfil(), image));
                       // listaObjeto.add(new InfoServicio("","","",null,null));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {

            }
       // }


        return listaObjeto;
    }



    protected Drawable getImagenPerfil() {
        byte[] bytes;
        try {
            Statement st = conexionBD.conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select * from Usuari where tipus_usuari = 'Final' and correu !='" + correo.trim() + "'");

            while (rs.next()) {
                bytes = rs.getBytes("imatge");
                imagenperfil = new BitmapDrawable(getResources(), BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return imagenperfil;
    }

    protected String getNombre() {

        try {
            Statement st = conexionBD.conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select * from Usuari where tipus_usuari = 'Final'  and id_usuari !='" + getIDUsuario() + "'");

            while (rs.next()) {
                s = rs.getString("usuari");
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return s;
    }


    protected boolean getEmail() {

        try {
            Statement st = conexionBD.conexionBD().createStatement();

            ResultSet rs = st.executeQuery("select * from Usuari where tipus_usuari = 'Final'  and id_usuari ='" + getIDUsuario() + "'");
            if (rs.next()) {

                comprobaremail = true;
            } else {
                comprobaremail = false;
            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return comprobaremail;
    }


    protected int  getIDUsuario() {

        try {
            Statement st = conexionBD.conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select* from Usuari where tipus_usuari='Final' and correu='" + correo.trim() + "'");

            while (rs.next()) {

                idusuario = rs.getInt("id_usuari");
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        return idusuario;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}