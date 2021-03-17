package com.example.myapplication.Persistencia;

import androidx.annotation.NonNull;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UsuarioDAO {

    public interface IDevolverUsuario{
        public void devolverError(String error);
    }

  public interface IDevolverUrlFoto{
      public void devolverUrlStirng(String url);
  }

  protected static UsuarioDAO usuarioDAO;
  FirebaseDatabase database;
  FirebaseStorage firebaseStorage;
  DatabaseReference referenceUsuarios;
  StorageReference referenceFotoPerfil;


    public static UsuarioDAO getInstance(){
        if (usuarioDAO==null)usuarioDAO = new UsuarioDAO();
        return usuarioDAO;
    }

    public UsuarioDAO() {
        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        referenceUsuarios = database.getReference();
        referenceFotoPerfil = firebaseStorage.getReference("fotos"+getKeyUsuario());
    }

    public  String getKeyUsuario(){
        return FirebaseAuth.getInstance().getUid();
    }

    public long fechaDeCreacionLong(){
        return FirebaseAuth.getInstance().getCurrentUser().getMetadata().getCreationTimestamp();
    }
    public long fechaUltimaVezLoginLong(){
        return FirebaseAuth.getInstance().getCurrentUser().getMetadata().getLastSignInTimestamp();
    }

    public void getInfoUsuarioPorLlave(final String key, final IDevolverUsuario iDevolverUsuario){
        referenceUsuarios.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iDevolverUsuario.devolverError(databaseError.getMessage());
            }
        });
    }
}
