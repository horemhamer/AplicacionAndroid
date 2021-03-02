
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

    EditText nombre, correo, edad, contraseña;
    Button confirmar;
    private DataBase conexionBD = new DataBase();
    private AwesomeValidation validation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiardatos);
        nombre = findViewById(R.id.editMNom);
        correo = findViewById(R.id.editMEmail);
        edad = findViewById(R.id.editMEdat);
        contraseña = findViewById(R.id.editMContra);
        confirmar = findViewById(R.id.btnMConfirmar);


        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             guardarCambios();
            }
        });

    }

    protected boolean validarFormulario(){

        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(this, R.id.editNomUsuari, RegexTemplate.NOT_EMPTY, R.string.invalid_name);


                validation.addValidation(this, R.id.editContra, RegexTemplate.NOT_EMPTY, R.string.empty_password);
                validation.addValidation(this, R.id.editCorreo, RegexTemplate.NOT_EMPTY, R.string.empty_email);
                validation.addValidation(this, R.id.editConfirmaContra, RegexTemplate.NOT_EMPTY, R.string.empty_confirmpass);
                validation.addValidation(this, R.id.editLocalizacion, RegexTemplate.NOT_EMPTY, R.string.empty_location);
                validation.addValidation(this, R.id.editEdad, RegexTemplate.NOT_EMPTY, R.string.empty_age);





        return validation.validate();
    }


    protected void guardarCambios(){

     /*   BitmapDrawable bitmapDrawable = (BitmapDrawable) ProfileImage.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

        String cnombre= nombre.getText().toString();

        byte[] bytes = baos.toByteArray();

        try {
            intedad = Integer.parseInt(edad.getText().toString());
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }

        try{

            Statement st=conexionBD.conexionBD().createStatement();
            String value = ()
            ResultSet rs = st.executeQuery("UPDATE Usuari SET usuari='?' where id="+);
             if(validarFormulario()){
                st.executeUpdate();
                Toast.makeText(getApplicationContext(),"Registro agregado correctamente",Toast.LENGTH_SHORT).show();
             /*   Intent intent = new Intent(Registre.this, CrearServicio.class);
                startActivity(intent);*/

            }


/*        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Mal",Toast.LENGTH_SHORT).show();
        }
    }
*/


}