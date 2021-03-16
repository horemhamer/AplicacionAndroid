package com.example.myapplication.Entidades.BaseDatos;

import android.widget.Toast;

import com.example.myapplication.Entidades.Logica.Usuarios;

import java.sql.ResultSet;
import java.sql.Statement;

public class UsuariosBD extends DataBase {
    Usuarios usuarios;

    String nombre;
    String correo;
    String rnombre;
    String rcorreo;
    public UsuariosBD() {
        usuarios = new Usuarios();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreUsuario() {

        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select*  from Usuari where tipus_usuari='Final' and usuari ='" + getNombre()+"'");

            if (rs.next()) {
                this.rnombre = rs.getString("usuari");
            }

        } catch (Exception e) {
         //   Toast.makeText(, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return this.rnombre;
    }

    public String getEmail() {

        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select*  from Usuari where tipus_usuari='Final' and correu ='"+getCorreo()+"'");

            if (rs.next()) {
               this.rcorreo = rs.getString("correu");
            }

        } catch (Exception e) {
         //   Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return this.rcorreo;
    }

}
