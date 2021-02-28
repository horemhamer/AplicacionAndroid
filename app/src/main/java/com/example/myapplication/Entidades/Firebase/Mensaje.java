package com.example.myapplication.Entidades.Firebase;

import com.google.firebase.database.ServerValue;

public class Mensaje {
    String mensaje;
    String keyEmisor;
    String urlFoto;
    boolean contieneFoto;
    Object createdTimestamp;
    public Mensaje() {
         createdTimestamp = ServerValue.TIMESTAMP;
    }

    public String getKeyEmisor() {
        return keyEmisor;
    }

    public void setKeyEmisor(String keyEmisor) {
        this.keyEmisor = keyEmisor;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isContieneFoto() {
        return contieneFoto;
    }

    public void setContieneFoto(boolean contieneFoto) {
        this.contieneFoto = contieneFoto;
    }

    public Object getCreatedTimestamp() {
        return createdTimestamp;
    }
}
