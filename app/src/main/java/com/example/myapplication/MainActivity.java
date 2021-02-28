package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.collect.Range;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Button botoregistre;
    Button botologin;
    EditText usuari, contrasenya, correo;
    DataBase conexionBD = new DataBase();
    private AwesomeValidation validation;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         botoregistre = findViewById(R.id.BtnRegistre);
         //usuari =  findViewById(R.id.editUsuari);
         contrasenya = findViewById(R.id.editPass);
         botologin = findViewById(R.id.btnlogin);
         mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.editEmail);
        textInputLayout = findViewById(R.id.textInputLayout);
        database = FirebaseDatabase.getInstance("https://benku-4adaa-default-rtdb.firebaseio.com/");

        mostrarIcono();

        botologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  ComprobarUsuari();
                iniciarSesionFirebase();
            }
        });

         botoregistre.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, Registre.class);
                 startActivity(intent);
             }
         });
    }



    private void mostrarIcono(){
        contrasenya.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textInputLayout.setEndIconVisible(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    protected void iniciarSesionFirebase(){
        String email = correo.getText().toString();
        String password = contrasenya.getText().toString();
        if(validarFormulario()){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                autoActivity();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Credencials incorrectes", Toast.LENGTH_SHORT).show();
                                // ...
                            }
                        }
                    });
        }


    }




    protected boolean validarFormulario(){

        validation = new AwesomeValidation(ValidationStyle.BASIC);
         if(contrasenya.getText().toString().equals("")){
             validation.addValidation(this, R.id.editPass, RegexTemplate.NOT_EMPTY, R.string.empty_password);
              textInputLayout.setEndIconVisible(false);
         }

         if(correo.getText().toString().equals("")){
             validation.addValidation(this, R.id.editEmail, RegexTemplate.NOT_EMPTY, R.string.empty_email);
         }

         return validation.validate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            autoActivity();
        }
    }

    private void autoActivity(){
        Intent intent2 = new Intent(MainActivity.this, Menu.class);
        startActivity(intent2);
    }

    protected void ComprobarUsuari(){
        try{
            Statement st=conexionBD.conexionBD().createStatement();
            ResultSet rs= st.executeQuery("select* from Usuari where tipus_usuari='Final'");
            String nusuari = "";
            String npassword = "";
            while(rs.next()){
                nusuari = rs.getString("usuari");
                npassword = rs.getString("password");
                if(usuari.getText().toString().trim().equals(nusuari) && contrasenya.getText().toString().trim().equals(npassword)){

                    Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent2);
                }

            }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
    }
}