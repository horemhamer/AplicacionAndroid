package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.R;
import com.google.common.collect.Range;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Reportar extends AppCompatActivity {

    DataBase conexionBD;
    Button reportar;
    String nusuario, nusuarioreportado;
    AwesomeValidation validation;
    EditText editusuario, editusuarioreportado, editmensaje;
    int contreport;
    boolean vacio, validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportar);
        reportar = findViewById(R.id.btnReportar);
        conexionBD =  new DataBase();
        editmensaje= findViewById(R.id.editRDescripcion);
        editusuario = findViewById(R.id.editNom);
        editusuarioreportado = findViewById(R.id.editUsuariReport);
        reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportarUsuario();
            }
        });

    }



    protected void reportarUsuario(){

            try{

                PreparedStatement st=conexionBD.conexionBD().prepareStatement("INSERT INTO Reports VALUES(?,?,?,?,?,?,?)");
                st.setString(1, editusuario.getText().toString());
                st.setString(2, editusuarioreportado.getText().toString());
                st.setString(3, editmensaje.getText().toString());
                if(Validar()){
                    st.executeUpdate();
                    Toast.makeText(getApplicationContext(),"El report s'ha enviat correctament",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){

                Toast.makeText(getApplicationContext(),"Mal",Toast.LENGTH_SHORT).show();
            }


    }

    protected boolean Validar(){
        if(editusuario.getText().toString().trim().equals(getNombreUsuario())&&editusuarioreportado.getText().toString().trim().equals(getNombreUsarioReportado())){
            validar=true;
        }else{
            if(getVacio()){
                Toast.makeText(getApplicationContext(),"Omple tots els camps",Toast.LENGTH_SHORT).show();
            }else if(!editusuario.getText().toString().trim().equals(getNombreUsuario())){
                Toast.makeText(getApplicationContext(),"El teu nom d'usuari no es correcte",Toast.LENGTH_SHORT).show();
            }else if(!editusuarioreportado.getText().toString().trim().equals(getNombreUsarioReportado())){
                Toast.makeText(getApplicationContext(),"El usuari a reportar no existeix",Toast.LENGTH_SHORT).show();
            }
        }

        return validar;

    }

    protected boolean getVacio(){

        String arraydatos[] = {editusuario.getText().toString(),editmensaje.getText().toString()};

        for(int i=0; i<arraydatos.length;i++){
            if(arraydatos[i].equals("")){
                vacio = true;
            }else{
                vacio = false;
            }
        }
        return vacio;
    }


    protected String getNombreUsuario(){
        try{
            Statement st=conexionBD.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Usuari where tipus_usuari='Final'");

            while(rs.next()){
                nusuario = rs.getString("usuari");
            }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }

        return nusuario;
    }

    protected String getNombreUsarioReportado(){
        try{
            Statement st=conexionBD.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Usuari where tipus_usuari='Final'");

            while(rs.next()){
                nusuarioreportado = rs.getString("usuari");
            }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }

        return nusuarioreportado;
    }



}