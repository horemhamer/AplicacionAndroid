package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderServicio extends RecyclerView.ViewHolder {
    TextView nombreuser;
    TextView servicio;
    TextView descripcion;
    CircleImageView imgperfil;

    public HolderServicio(@NonNull View itemView) {
        super(itemView);
        nombreuser = (TextView) itemView.findViewById(R.id.nombre);
        servicio = (TextView) itemView.findViewById(R.id.servicio);
        descripcion = (TextView) itemView.findViewById(R.id.desc);
       imgperfil = (CircleImageView) itemView.findViewById(R.id.servicioimg);
    }

    public TextView getNombreuser() {
        return nombreuser;
    }

    public void setNombreuser(TextView nombreuser) {
        this.nombreuser = nombreuser;
    }

    public TextView getServicio() {
        return servicio;
    }

    public void setServicio(TextView servicio) {
        this.servicio = servicio;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextView descripcion) {
        this.descripcion = descripcion;
    }

    public CircleImageView getImgperfil() {
        return imgperfil;
    }

    public void setImgperfil(CircleImageView imgperfil) {
        this.imgperfil = imgperfil;
    }
}
