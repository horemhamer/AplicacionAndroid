package com.example.myapplication;

public class InfoServicio {
    String nombreuser;
    String servicio;
    String descripcion;
    String imgperfil;
    public InfoServicio() {
    }

    public InfoServicio(String nombreuser, String servicio, String descripcion, String imgperfil) {
        this.nombreuser = nombreuser;
        this.servicio = servicio;
        this.descripcion = descripcion;
        this.imgperfil = imgperfil;
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

    public String getImgperfil() {
        return imgperfil;
    }

    public void setImgperfil(String imgperfil) {
        this.imgperfil = imgperfil;
    }
}
