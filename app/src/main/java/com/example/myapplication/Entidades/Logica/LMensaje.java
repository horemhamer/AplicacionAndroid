package com.example.myapplication.Entidades.Logica;

import com.example.myapplication.Entidades.Firebase.Mensaje;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LMensaje {
    Mensaje mensaje;
    String key;
    LUsuario lUsuario;

    public LMensaje(String key, Mensaje mensaje) {
        this.mensaje = mensaje;
        this.key = key;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public LUsuario getlUsuario() {
        return lUsuario;
    }

    public void setlUsuario(LUsuario lUsuario) {
        this.lUsuario = lUsuario;
    }

    public long getCreatedTimestampLong(){
        return (long) mensaje.getCreatedTimestamp();
    }

    public String fechaCreacionMensaje(){

      /*  Date d = new Date(getCreatedTimestampLong());
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(d);*/
        Date date = new Date(getCreatedTimestampLong());
        PrettyTime prettyTime = new PrettyTime(new Date(),Locale.getDefault());
        return prettyTime.format(date);

    }
}
