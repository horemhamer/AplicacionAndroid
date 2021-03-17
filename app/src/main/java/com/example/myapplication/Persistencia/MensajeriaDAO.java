package com.example.myapplication.Persistencia;

import com.example.myapplication.Constantes.Constantes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MensajeriaDAO {
    private static MensajeriaDAO mensajeriaDAO;
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    DatabaseReference referenceMensajeria;
    StorageReference referenceFotoPerfil;
    public static MensajeriaDAO getInstance(){
        if (mensajeriaDAO==null)mensajeriaDAO = new MensajeriaDAO();
        return mensajeriaDAO;
    }

    public MensajeriaDAO() {
        database = FirebaseDatabase.getInstance();
        //firebaseStorage = FirebaseStorage.getInstance();
        referenceMensajeria = database.getReference(Constantes.NODO_MENSAJES);
      //  referenceFotoPerfil = firebaseStorage.getReference("fotos"+getKeyUsuario());
    }



}
