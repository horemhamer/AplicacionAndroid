package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.R;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reportar extends AppCompatActivity {

    DataBase conexionBD;
    Button reportar;
    Array columna;
    List<String> list = new ArrayList<>();
    List<Integer> listreports = new ArrayList<Integer>();
    String nusuarioreportado;
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
                String s = String.valueOf(getNReportes());
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();


            }
        });

    }



    protected void insertarReporte(){

            try{

                PreparedStatement st=conexionBD.conexionBD().prepareStatement("INSERT INTO Reports VALUES(?,?,?,?)");
                st.setString(1, editusuario.getText().toString());
                st.setString(2, editusuarioreportado.getText().toString());
                st.setString(3, editmensaje.getText().toString());
                st.setInt(4, contreport++);
                if(Validar()){
                    st.executeUpdate();
                    Toast.makeText(getApplicationContext(),"El report s'ha enviat correctament",Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){

                Toast.makeText(getApplicationContext(),"Mal",Toast.LENGTH_SHORT).show();
            }


    }

    protected boolean Validar(){
        if(editusuario.getText().toString().trim().equals(getNombreUsuario().toString())&&editusuarioreportado.getText().toString().trim().equals(getNombreUsuario().toString())){
            validar=true;

        }else{
            if(getVacio()){
                Toast.makeText(getApplicationContext(),"Omple tots els camps",Toast.LENGTH_SHORT).show();
            }else if(!getNombreUsuario().contains(editusuario.getText().toString())&&!getNombreUsuario().contains(editusuarioreportado.getText().toString())){
                Toast.makeText(getApplicationContext(), "El teu nom o el del usuari reportat no son correctes.", Toast.LENGTH_SHORT).show();
            }else if(!getNombreUsuario().contains(editusuario.getText().toString())){
                Toast.makeText(getApplicationContext(),"El teu nom d'usuari no es correcte.",Toast.LENGTH_SHORT).show();
            }else if(!getNombreUsuario().contains(editusuarioreportado.getText().toString())){
                Toast.makeText(getApplicationContext(),"El usuari a reportar no existeix.",Toast.LENGTH_SHORT).show();
            }

        }

        return validar;

    }

    protected boolean getVacio(){

        String arraydatos[] = {editusuario.getText().toString(),editusuarioreportado.getText().toString(),editmensaje.getText().toString()};

        for(int i=0; i<arraydatos.length;i++){
            if(arraydatos[i].equals("")){
                vacio = true;
            }else{
                vacio = false;
            }
        }
        return vacio;
    }


    protected List<String> getNombreUsuario(){
            try{
                Statement st=conexionBD.conexionBD().createStatement();
                ResultSet rs= st.executeQuery("select*  from Usuari where tipus_usuari='Final'");

                while(rs.next()){
                    list.add(rs.getString("usuari"));
                }

            }catch(Exception e){
                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
            }

        return list;
    }


    protected int getNReportes(){
        int i= 0;
        try{
            Statement st=conexionBD.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select*  from Reports");

            while(rs.next()){
                i++;
                listreports.add(rs.getInt("usuari"));
            }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        return i;
    }







}