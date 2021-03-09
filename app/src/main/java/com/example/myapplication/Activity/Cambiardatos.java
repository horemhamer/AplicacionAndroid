
package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.R;
import com.google.common.collect.Range;

import java.io.ByteArrayOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cambiardatos extends AppCompatActivity {

    EditText nombre, correo, edad, contraseña, localizacion;
    Button confirmar;
    private DataBase conexionBD = new DataBase();
    private AwesomeValidation validation;
    private int intedad = 0;
    private String cnombre, ccorreo, cpass, cedad, cprovincia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiardatos);
        nombre = findViewById(R.id.editMNom);
        correo = findViewById(R.id.editMEmail);
        edad = findViewById(R.id.editMEdat);
        contraseña = findViewById(R.id.editMContra);
        confirmar = findViewById(R.id.btnMConfirmar);
        localizacion = findViewById(R.id.editMLocation);
        validation = new AwesomeValidation(ValidationStyle.BASIC);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             guardarCambios();
            }
        });

    }





    protected void guardarCambios(){

    boolean mensaje = false;
    boolean mensaje2=  false;

  /* BitmapDrawable bitmapDrawable = (BitmapDrawable) ProfileImage.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);*/

      //  byte[] bytes = baos.toByteArray();


        if(!getNombre().equals("")&&validarNombre()){
            guardarNombre();
            mensaje = true;
        }

        if(!getNombre().equals("")){
            mensaje2 = true;
        }

        if(!getPass().equals("")&& validarPass()){
            guardarPass();
            mensaje = true;
        }
        if(!getPass().equals("")){
            mensaje2 = true;
        }

        if(!getEmail().equals("")&&validarEmail()){
            guardarEmail();
            mensaje = true;
        }

        if(!validarEmail()){
            mensaje2 = true;
        }

        if(!getEdad().equals("")&&validarEdad()){
            guardarEdad();
            mensaje = true;
        }
        if(!getEdad().equals("")){
            mensaje2 = true;
        }

        if(!getProvincia().equals("")&&validarLocaltion()){
            guardarLocation();
            mensaje = true;
        }
        if(!getProvincia().equals("")){
            mensaje2 = true;
        }



        if(mensaje == true){
            Toast.makeText(getApplicationContext(),"Els canvis s'han efectuat correctament",Toast.LENGTH_SHORT).show();
        }else if(mensaje2==true){

        }
        else{
            Toast.makeText(getApplicationContext(),"Omple un camp minim",Toast.LENGTH_SHORT).show();
        }

    }


    protected boolean validarNombre(){
        validation.addValidation(this,R.id.editMNom,"/^[a-z ,.'-]+$/i", R.string.invalid_nom);
        return  validation.validate();
    }

    protected boolean validarPass(){
        validation.addValidation(this,R.id.editMContra, ".{4,}", R.string.invalid_password);
        return  validation.validate();
    }

    protected boolean validarEmail(){
        validation.addValidation(this,R.id.editMEmail, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
        return  validation.validate();
    }

    protected boolean validarEdad(){
        validation.addValidation(this,R.id.editMEdat, Range.closed(18, 60), R.string.invalid_edad);
        return  validation.validate();
    }

    protected boolean validarLocaltion(){
        validation.addValidation(this,R.id.editMLocation, "(?:Lleida|Girona|Tarragona|Barcelona)", R.string.invalidlocation);
        return  validation.validate();
    }

    protected void guardarLocation(){
        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET localitzacio=? where tipus_usuari='Final'");
            st.setString(1,getProvincia());
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    protected void guardarEmail(){
        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET correu=? where tipus_usuari='Final'");
            st.setString(1,getEmail());
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    protected void guardarPass(){
        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET contrasenya=? where tipus_usuari='Final'");
            st.setString(1,getPass());
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    protected void guardarNombre(){
        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET usuari=? where tipus_usuari='Final'");
            st.setString(1,getNombre());
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    protected void guardarEdad(){

        try {
            intedad = Integer.parseInt(getEdad());
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }

        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET edat=? where tipus_usuari='Final'");
            st.setInt(1, intedad);
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    protected String getNombre(){
         cnombre= nombre.getText().toString();
        return cnombre;
    }

    protected String getEmail(){
        ccorreo = correo.getText().toString();
        return ccorreo;
    }

    public String getPass() {
        return cpass = contraseña.getText().toString();
    }

    public String getProvincia() {
        return cprovincia = localizacion.getText().toString();
    }

    public String getEdad() {
        return cedad = edad.getText().toString();
    }
}