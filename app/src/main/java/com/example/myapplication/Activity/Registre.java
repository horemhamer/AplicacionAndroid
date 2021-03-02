package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.myapplication.Entidades.BaseDatos.DataBase;
import com.example.myapplication.Entidades.Firebase.Usuario;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Range;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.sql.PreparedStatement;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registre extends AppCompatActivity {
    private DataBase conexionBD = new DataBase();
    private static final int PICK_IMAGE = 1;
    private  Uri imageUri;
    private  CircleImageView ProfileImage;
    private Button confirmar;
    private AwesomeValidation validation;
    private EditText nusuari,contra,confirmcontra,correo, edad, especialidad, localizacion, desc;
    private int intedad = 0;
    ImageView imagenservei;
    private FirebaseAuth mAuth;
    DatabaseReference imgref;
    StorageReference storageReference;
    FirebaseDatabase database;
    AdaptadorServicios adaptadorServicios;
    Servicios servicios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registre);
        imagenservei = findViewById(R.id.ImagenServicio);
        confirmar = (Button) findViewById(R.id.btnConfirmar);
        ProfileImage = (CircleImageView) findViewById(R.id.profile_image);
        nusuari = findViewById(R.id.editNomUsuari);
        contra = findViewById(R.id.editContra);
        correo = findViewById(R.id.editCorreo);
        edad = findViewById(R.id.editEdad);
        confirmcontra = findViewById(R.id.editConfirmaContra);
        localizacion = findViewById(R.id.editLocalizacion);
        especialidad = findViewById(R.id.editServei);
        desc = findViewById(R.id.editDescripcion);
        storageReference = FirebaseStorage.getInstance().getReference();
        servicios = new Servicios();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance("https://benku-4adaa-default-rtdb.firebaseio.com/");





           confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             guardarUsuarios();
                Intent intent = new Intent(Registre.this, CrearServicio.class);
                startActivity(intent);
           //  usuarioFirebase();

            }
        });

        ProfileImage.setOnClickListener(new View.OnClickListener() {
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





    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            setImagenUri(imageUri);
            ProfileImage.setImageURI(imageUri);

        }
    }

    protected boolean validarFormulario(){

        validation = new AwesomeValidation(ValidationStyle.BASIC);
        validation.addValidation(this, R.id.editNomUsuari, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        String arraydatos[] = {contra.getText().toString(),correo.getText().toString(),confirmcontra.getText().toString(), localizacion.getText().toString(), confirmcontra.getText().toString(), edad.getText().toString()};

        for(int i=0; i<arraydatos.length;i++){
            if(arraydatos[i].equals("")){
                validation.addValidation(this, R.id.editContra, RegexTemplate.NOT_EMPTY, R.string.empty_password);
                validation.addValidation(this, R.id.editCorreo, RegexTemplate.NOT_EMPTY, R.string.empty_email);
                validation.addValidation(this, R.id.editConfirmaContra, RegexTemplate.NOT_EMPTY, R.string.empty_confirmpass);
                validation.addValidation(this, R.id.editLocalizacion, RegexTemplate.NOT_EMPTY, R.string.empty_location);
                validation.addValidation(this, R.id.editEdad, RegexTemplate.NOT_EMPTY, R.string.empty_age);
            }else{
                validation.addValidation(this, R.id.editContra, ".{4,}", R.string.invalid_password);
                validation.addValidation(this,R.id.editCorreo, Patterns.EMAIL_ADDRESS, R.string.invalid_email);
                validation.addValidation(this,R.id.editLocalizacion, "(?:Lleida|Girona|Tarragona|Barcelona)",R.string.invalidlocation);
                validation.addValidation(this, R.id.editEdad, Range.closed(18, 60), R.string.invalid_edad);
            }
        }
        if(!contra.getText().toString().trim().equals(confirmcontra.getText().toString().trim())&&!contra.getText().toString().equals("")&&!confirmcontra.getText().toString().equals("")){
            validation.addValidation(this,R.id.editConfirmaContra, R.id.editContra,R.string.invalid_confirmpassword);
        }


        return validation.validate();
    }



    protected void usuarioFirebase(){
        String email = correo.getText().toString();
        String password = contra.getText().toString();
        if(validarFormulario()){
            subirImagenFirebase();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(),"Se guardo en firebase",Toast.LENGTH_SHORT).show();
                                Usuario usuario = new Usuario();
                                usuario.setCorreo(correo.getText().toString());
                                usuario.setNombre(nusuari.getText().toString());
                                usuario.setFotoPerfilURL(getImageUri().toString());
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                DatabaseReference reference = database.getReference("Usuaris/"+currentUser.getUid());
                                reference.setValue(usuario);
                            } else {

                            }

                        }
                    });
        }

    }


    protected void subirImagenFirebase(){
        StorageReference filePath = storageReference.child("fotos").child(getImageUri().getPathSegments().toString());
        filePath.putFile(getImageUri()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"imagen subida",Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void setImagenUri(Uri imgUri){
     this.imageUri = imgUri;
    }

    protected Uri getImageUri(){
        return imageUri;
    }




    protected void guardarUsuarios(){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ProfileImage.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        try {
            intedad = Integer.parseInt(edad.getText().toString());
        } catch(NumberFormatException nfe) {
            // Handle parse error.
        }

        try{

            PreparedStatement st=conexionBD.conexionBD().prepareStatement("INSERT INTO Usuari VALUES(?,?,?,?,?,?,?)");
            st.setString(1, nusuari.getText().toString());
            st.setString(2, contra.getText().toString());
            st.setString(3, "Final");
            st.setString(4, correo.getText().toString());
            st.setInt(5, intedad);
            st.setString(6, localizacion.getText().toString());
            st.setBytes(7, bytes);
            if(validarFormulario()){
                st.executeUpdate();
                Toast.makeText(getApplicationContext(),"Registro agregado correctamente",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registre.this, CrearServicio.class);
                startActivity(intent);

            }


        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Mal",Toast.LENGTH_SHORT).show();
        }
    }


}