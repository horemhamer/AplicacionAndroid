package com.example.myapplication.Activity;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.Entidades.Usuario.Usuario;

public class Preferencias {

    boolean logueado;
    Usuario usuario = new Usuario();

    public Preferencias() {
    }

    protected void cargarPreferencias(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        boolean login = sharedPreferences.getBoolean("login", getLogueado());
        setLogueado(login);
    }
    protected void guardarPreferencia(Context c){
        SharedPreferences sharedPreferences = c.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login",getLogueado());
        setLogueado(getLogueado());

        editor.commit();
    }

    public boolean getLogueado() {
        return logueado;
    }

    public void setLogueado(boolean logueado) {
        this.logueado = logueado;
    }
}
