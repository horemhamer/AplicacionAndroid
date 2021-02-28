package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdaptadorServicios extends RecyclerView.Adapter<HolderServicio> {

    List<InfoServicio> listServicio = new ArrayList<>();
    Context c;

    public AdaptadorServicios(Context c) {
        this.c = c;

    }

    protected void añadirServicio(InfoServicio i){
        listServicio.add(i);
        notifyItemInserted(listServicio.size());
    }

    @Override
    public HolderServicio onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.servicios_view,parent,false);
        return new HolderServicio(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderServicio holder, int position) {
        holder.getNombreuser().setText(listServicio.get(position).getNombreuser());
        holder.getServicio().setText(listServicio.get(position).getServicio());
        holder.getDescripcion().setText(listServicio.get(position).getDescripcion());
        Glide.with(c).load(listServicio.get(position).getImgperfil());

    }

    @Override
    public int getItemCount() {
        return listServicio.size();
    }
}
