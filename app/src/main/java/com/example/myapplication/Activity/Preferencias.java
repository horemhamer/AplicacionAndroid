package com.example.myapplication.Activity;

import android.content.Context;
import android.content.SharedPreferences;



public class Preferencias {

    boolean logueado;
    String correo, cambiarcorreo;

    public Preferencias() {
    }

    protected void cargarPreferencias(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        boolean login = sharedPreferences.getBoolean("login", getLogueado());
        String e = sharedPreferences.getString("email",getCorreo());
        String e2 =sharedPreferences.getString("email2",getCambiarcorreo());
        setCorreo(e);
        setCambiarcorreo(e2);
        setLogueado(login);
    }
    protected void guardarPreferencia(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email",getCorreo());
        editor.putBoolean("login",getLogueado());
        editor.putString("email2",getCorreo());
        setLogueado(getLogueado());
        setCorreo(getCorreo());
        setCambiarcorreo(getCambiarcorreo());
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

    public String getCambiarcorreo() {
        return cambiarcorreo;
    }

    public void setCambiarcorreo(String cambiarcorreo) {
        this.cambiarcorreo = cambiarcorreo;
    }
}
