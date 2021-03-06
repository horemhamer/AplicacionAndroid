
package com.example.myapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class Cambiardatos extends AppCompatActivity {

    EditText nombre, correo, edad, contraseña, localizacion;
    Button confirmar;
    private DataBase conexionBD = new DataBase();
    private AwesomeValidation validation;
    private int intedad = 0;
    private String cnombre, ccorreo, cpass, cedad, cprovincia;
    CircleImageView imagenperfil;
    private  Uri imageUri;
    Context c;
    private static final int PICK_IMAGE = 1;
    boolean validarimagen;
    String correoguardado = "";
    int idusuario;
    boolean click;
    Preferencias preferencias = new Preferencias();
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
        imagenperfil = findViewById(R.id.Mprofile_image);
        c = this;

        preferencias.cargarPreferencias(c);





        validation = new AwesomeValidation(ValidationStyle.BASIC);

        imagenperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Link guardar imatge base de dades:
                https://stackoverflow.com/questions/25821141/image-storing-in-sql-server-database-using-java*/
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Selecciona una imatge"), PICK_IMAGE);
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             guardarCambios();
                preferencias.setCambiarcorreo(getEmail2());
                preferencias.guardarPreferencia(c);
                if(correo.getText().toString().equals("")){
                    correoguardado = getEmail2();
                }else{
                    correoguardado = correo.getText().toString();
                }

                Toast.makeText(getApplicationContext(),correoguardado,Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            setImagenUri(imageUri);
            imagenperfil.setImageURI(imageUri);
            setValidarimagen(true);

        }
    }





    protected void guardarCambios(){

    boolean mensaje[] = new boolean[6];
        boolean mensaje2[] = new boolean[6];

        if(!getNombre().equals("")){
            guardarNombre();
            mensaje[0] = true;
        }



        if(!getPass().equals("")){
            guardarPass();
            mensaje[1] = true;
        }


        if(!getEmail().equals("")){
            guardarEmail();
            mensaje[2] = true;
        }


        if(!getEdad().equals("")){
            guardarEdad();
            mensaje[3] = true;
        }


        if(!getProvincia().equals("")){
            guardarLocation();
            mensaje[4] = true;
        }

           if(getValidarimagen()){
            mensaje[5] = true;
        }




        if( mensaje[0] == true|| mensaje[1] == true|| mensaje[2] == true|| mensaje[3] ==true|| mensaje[4] == true ||  mensaje[5] == true){
            Toast.makeText(getApplicationContext(),"Els canvis s'han efectuat correctament",Toast.LENGTH_SHORT).show();
        }else{
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

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET localitzacio=? where tipus_usuari='Final'and correu ='"+correoguardado+"'");
            st.setString(1,getProvincia());
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }



    protected void guardarEmail(){
        try{
       ;

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET correu=? where tipus_usuari='Final' and correu ='"+correoguardado+"' and correu !=' "+preferencias.getCambiarcorreo()+"'");
            st.setString(1,getEmail());
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    protected void guardarPass(){
        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET contrasenya=? where tipus_usuari='Final'and correu ='"+correoguardado+"'");
            st.setString(1,getPass());
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    protected int getIDUsuario(){

        try{
            Statement st=conexionBD.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Usuari where tipus_usuari='Final' and correu=correu");

            if(rs.next()){
                idusuario = rs.getInt("id_usuari");
            }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }

        return idusuario;
    }

    protected void guardarImagen(){
          BitmapDrawable bitmapDrawable = (BitmapDrawable) imagenperfil.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
     byte[] bytes = baos.toByteArray();
        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET imatge=? where tipus_usuari='Final'and correu ='"+correoguardado+"'");
            st.setBytes(1,bytes);
            st.executeUpdate();
            st.close();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    protected void guardarNombre(){
        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET usuari=? where tipus_usuari='Final'and correu ='"+correoguardado+"'");
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

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("UPDATE Usuari SET edat=? where tipus_usuari='Final'and correu ='"+correoguardado+"'");
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


    protected void setImagenUri(Uri imgUri){
        this.imageUri = imgUri;
    }

    protected Uri getImageUri(){
        return imageUri;
    }

    public boolean getValidarimagen() {
        return validarimagen;
    }

    public void setValidarimagen(boolean validarimagen) {
        this.validarimagen = validarimagen;
    }

    protected String getEmail2() {
        String a = "";
        try {
            Statement st = conexionBD.conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select*  from Usuari where tipus_usuari='Final' and correu ='" + correoguardado + "'");

            if (rs.next()) {
                a = rs.getString("correu");
            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return a;
    }

}