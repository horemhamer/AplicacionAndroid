package com.example.myapplication;

import android.os.StrictMode;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
    public Connection conexionBD(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://oracle.ilerna.com;databaseName=DAM2_Alumnes_Enric;user=DAM2_48057117C;password=a48057117C");
        }catch(Exception e){
            Toast.makeText(null,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }


}
