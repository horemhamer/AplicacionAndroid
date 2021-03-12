package com.example.myapplication.Activity;

import android.graphics.drawable.Drawable;

public class InfoServicio {
    String nombreuser;
    String servicio;
    String descripcion;
    Drawable imgperfil;
    Drawable imgserv;

    public InfoServicio() {
    }

    public InfoServicio(String nombreuser, String servicio, String descripcion, Drawable imgperfil, Drawable imgserv) {
        this.nombreuser = nombreuser;
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.imgperfil = imgperfil;
        this.imgserv = imgserv;
    }

    public String getNombreuser() {
        return nombreuser;
    }

    public void setNombreuser(String nombreuser) {
        this.nombreuser = nombreuser;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Drawable getImgperfil() {
        return imgperfil;
    }

    public void setImgperfil(Drawable imgperfil) {
        this.imgperfil = imgperfil;
    }

    public Drawable getImgserv() {
        return imgserv;
    }

    public void setImgserv(Drawable imgserv) {
        this.imgserv = imgserv;
    }
}
