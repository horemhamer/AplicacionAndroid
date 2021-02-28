package com.example.myapplication.Entidades.Firebase;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

public class Usuario {
    private String nombre,correo, fotoPerfilURL;


    public Usuario(){

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFotoPerfilURL() {
        return fotoPerfilURL;
    }

    public void setFotoPerfilURL(String fotoPerfilURL) {
        this.fotoPerfilURL = fotoPerfilURL;
    }


}
