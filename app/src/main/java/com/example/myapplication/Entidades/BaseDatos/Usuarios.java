package com.example.myapplication.Entidades.BaseDatos;

import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;

public class Usuarios extends DataBase {

    String rcorreo;
    String rnombre;


    public Usuarios() {
    }

    protected String getNombreUsuario(String nombre) {

        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select*  from Usuari where tipus_usuari='Final' and usuari ='" +nombre + "'");

            if (rs.next()) {
                rnombre = rs.getString("usuari");
            }

        } catch (Exception e) {
         //   Toast.makeText(, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return rnombre;
    }

    protected String getEmail(String correo) {
        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select*  from Usuari where tipus_usuari='Final' and correu ='" + correo + "'");

            if (rs.next()) {
               rcorreo = rs.getString("correu");
            }

        } catch (Exception e) {
         //   Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return rcorreo;
    }

}
