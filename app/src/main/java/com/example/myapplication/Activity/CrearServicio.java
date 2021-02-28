package com.example.myapplication.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrearServicio extends AppCompatActivity {
    private DataBase conexionBD = new DataBase();
    ImageView imagenservei;
    private Uri imageUri;
    private static final int PICK_IMAGE = 1;
    EditText especialidad,desc;
    private AwesomeValidation validation;
    Button btnservei;
    protected boolean imageselect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_servicio);
        imagenservei = findViewById(R.id.ImagenServicio);
        especialidad = findViewById(R.id.editServei);
        desc = findViewById(R.id.editDescripcion);
        btnservei = findViewById(R.id.btnServicio);
        imagenservei.setOnClickListener(new View.OnClickListener() {
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

        btnservei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           guardarServicio();
              //  validarFormulario();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            setImagenUri(imageUri);
            imagenservei.setImageURI(imageUri);
            setImageselect(true);
        }
    }
    protected boolean validarFormulario(){

        validation = new AwesomeValidation(ValidationStyle.BASIC);
        String arraydatos[] = {especialidad.getText().toString(), desc.getText().toString()};
        for(int i=0; i<arraydatos.length;i++){
            if(arraydatos[i].equals("")){
                validation.addValidation(this, R.id.editServei, RegexTemplate.NOT_EMPTY, R.string.empty_service);
                validation.addValidation(this, R.id.editDescripcion, RegexTemplate.NOT_EMPTY, R.string.empty_desc);
            }
        }
        if(!getImageselect()){
            Toast.makeText(getApplicationContext(), "Afegeix una imatge", Toast.LENGTH_SHORT).show();
        }

        return validation.validate();
    }

    protected void guardarServicio(){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imagenservei.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("INSERT INTO Serveis VALUES(?,?,?)");
            st.setString(2, especialidad.getText().toString());
            st.setString(3, desc.getText().toString());
            st.setBytes(4,bytes);

             if(validarFormulario()) {
                 st.executeUpdate();
                 Toast.makeText(getApplicationContext(), "Servicio guardado correctamente", Toast.LENGTH_SHORT).show();
             }


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"No se ha guardado el servicio",Toast.LENGTH_SHORT).show();
        }
    }

    protected void crearServicio(){
        String nombreuser = "";
        String nservicio = "";
        String ndesc = "";
        String imageString = "";

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imagenservei.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        try{
            Statement st=conexionBD.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Usuari,Serveis where tipus_usuari='Final' AND id_usuari=id_servei");

            while(rs.next()){
                nombreuser = rs.getString("usuari");
                nservicio = rs.getString("titol");
                ndesc = rs.getString("descripcio");
                bytes= rs.getBytes("imatge");

            }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }

    }

    public boolean getImageselect() {
        return imageselect;
    }

    public void setImageselect(boolean imageselect) {
        this.imageselect = imageselect;
    }

    protected void setImagenUri(Uri imgUri){
        this.imageUri = imgUri;
    }

    protected Uri getImageUri(){
        return imageUri;
    }



}