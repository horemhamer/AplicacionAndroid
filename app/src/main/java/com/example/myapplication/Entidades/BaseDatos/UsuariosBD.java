package com.example.myapplication.Entidades.BaseDatos;

import android.widget.Toast;

import com.example.myapplication.Entidades.Logica.Usuarios;

import java.sql.ResultSet;
import java.sql.Statement;

public class UsuariosBD extends DataBase {
    Usuarios usuarios;



    public UsuariosBD() {
        usuarios = new Usuarios();
    }



    public String getNombreUsuario() {
        String rnombre = "a";
        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select*  from Usuari where tipus_usuari='Final' and usuari ='" + usuarios.getNombre()+ "'");

            if (rs.next()) {
                rnombre = rs.getString("usuari");
            }

        } catch (Exception e) {
         //   Toast.makeText(, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return rnombre;
    }

    public String getEmail() {
        String rcorreo = "";
        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select*  from Usuari where tipus_usuari='Final' and correu ='" +usuarios.getCorreo()+ "'");

            if (rs.next()) {
               rcorreo = rs.getString("correu");
            }

        } catch (Exception e) {
         //   Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return rcorreo;
    }

}
