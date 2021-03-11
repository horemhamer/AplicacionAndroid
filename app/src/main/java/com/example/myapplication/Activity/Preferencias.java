package com.example.myapplication.Activity;

import android.content.Context;
import android.content.SharedPreferences;



public class Preferencias {

    boolean logueado;
    String correo;

    public Preferencias() {
    }

    protected void cargarPreferencias(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        boolean login = sharedPreferences.getBoolean("login", getLogueado());
        String e = sharedPreferences.getString("email",getCorreo());
        setCorreo(e);
        setLogueado(login);
    }
    protected void guardarPreferencia(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",getCorreo());
        editor.putBoolean("login",getLogueado());
        setLogueado(getLogueado());
        setCorreo(getCorreo());
        editor.commit();
    }

    public boolean getLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
