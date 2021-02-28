package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Constantes.Constantes;
import com.example.myapplication.Entidades.Firebase.Mensaje;
import com.example.myapplication.Entidades.Firebase.Usuario;
import com.example.myapplication.Entidades.Logica.LMensaje;
import com.example.myapplication.Entidades.Logica.LUsuario;
import com.example.myapplication.Persistencia.MensajeriaDAO;
import com.example.myapplication.Persistencia.UsuarioDAO;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {

    CircleImageView fotoPerfil;
    TextView nombre;
    RecyclerView rMensajes;
    EditText txtMensaje;
    Button btnEnviar;
    FirebaseAuth mAuth;
    AdaptarMensajes adaptador;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    ImageButton btnEnviarFoto;
    String NOMBRE_USUARIO;
    private final int PHOTO_SEND=1;
    String KEY_RECEPTOR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            KEY_RECEPTOR = bundle.getString("key_receptor");
        }else{finish();}

        fotoPerfil = findViewById(R.id.fotoPerfilChat);
        nombre = findViewById(R.id.nombreUser);
        rMensajes = findViewById(R.id.rMensajes);
        txtMensaje = findViewById(R.id.editMensaje);
        btnEnviar = findViewById(R.id.btnEnviarMensaje);
        btnEnviarFoto = findViewById(R.id.btnEnviarFoto);
        adaptador = new AdaptarMensajes(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAuth = FirebaseAuth.getInstance();

       firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        rMensajes.setLayoutManager(linearLayoutManager);
        rMensajes.setAdapter(adaptador);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mensajeEnviar = txtMensaje.getText().toString();
                if(!mensajeEnviar.isEmpty()){
                    Mensaje mensaje = new Mensaje();
                    mensaje.setContieneFoto(false);
                    mensaje.setMensaje(mensajeEnviar);
                    mensaje.setKeyEmisor(UsuarioDAO.getInstance().getKeyUsuario());
                    MensajeriaDAO.getInstance().nuevoMensaje(UsuarioDAO.getInstance().getKeyUsuario(),KEY_RECEPTOR,mensaje);
                    txtMensaje.setText("");
                }

            }
        });
        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Pujar una imatge"),PHOTO_SEND);



            }
        });

        adaptador.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();;
            }
        });
        FirebaseDatabase.
                getInstance().
                getReference(Constantes.NODO_MENSAJES).
                child(UsuarioDAO.getInstance().getKeyUsuario()).
                child(KEY_RECEPTOR).addChildEventListener(new ChildEventListener() {
            Map<String, LUsuario> mapUsuariosTemporales = new HashMap<>();
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

               final Mensaje m = dataSnapshot.getValue(Mensaje.class);
               final LMensaje lMensaje = new LMensaje(dataSnapshot.getKey(),m);
               final int posicion =  adaptador.añadirMensaje(lMensaje);

                if (mapUsuariosTemporales.get(m.getKeyEmisor())!=null){
                    lMensaje.setlUsuario(mapUsuariosTemporales.get(m.getKeyEmisor()));
                    adaptador.actualizarMensaje(posicion,lMensaje);

                }else{
                    UsuarioDAO.getInstance().getInfoUsuarioPorLlave(m.getKeyEmisor(), new UsuarioDAO.IDevolverUsuario() {
                        @Override
                        public void devolverUsuario(LUsuario lUsuario) {
                            mapUsuariosTemporales.put(m.getKeyEmisor(),lUsuario);
                            lMensaje.setlUsuario(lUsuario);
                            adaptador.actualizarMensaje(posicion,lMensaje);
                        }

                        @Override
                        public void devolverError(String error) {
                            Toast.makeText(Chat.this,"Error: "+error,Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    protected void setScrollbar(){
        rMensajes.scrollToPosition(adaptador.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PHOTO_SEND && requestCode==RESULT_OK){
            Toast.makeText(getApplicationContext(),"isadasd",Toast.LENGTH_SHORT).show();
            Uri uri = data.getData();
            storageReference = firebaseStorage.getReference("imagenes_chat");
            final StorageReference fotoreferencia = storageReference.child(uri.getLastPathSegment());

            fotoreferencia.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

             //    Task<Uri> uri1 = taskSnapshot.getStorage().getDownloadUrl();
                    Mensaje mensaje = new Mensaje();
                    mensaje.setMensaje("Ha enviado una foto");
                    mensaje.setUrlFoto(uri.toString());
                    mensaje.setContieneFoto(true);
                    mensaje.setKeyEmisor(UsuarioDAO.getInstance().getKeyUsuario());
                    MensajeriaDAO.getInstance().nuevoMensaje(UsuarioDAO.getInstance().getKeyUsuario(),KEY_RECEPTOR,mensaje);
                }
            });

        }
    }



    @Override
    protected void onResume() {
        super.onResume();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            btnEnviar.setEnabled(false);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Usuaris/"+currentUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    NOMBRE_USUARIO = usuario.getNombre();
                    nombre.setText(NOMBRE_USUARIO);
                    btnEnviar.setEnabled(true);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}