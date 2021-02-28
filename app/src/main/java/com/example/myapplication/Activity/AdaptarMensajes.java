package com.example.myapplication.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Entidades.Logica.LMensaje;
import com.example.myapplication.Entidades.Logica.LUsuario;
import com.example.myapplication.HolderMensaje;
import com.example.myapplication.Persistencia.UsuarioDAO;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptarMensajes extends RecyclerView.Adapter<HolderMensaje> {

    List<LMensaje> listMensaje = new ArrayList<>();
    Context c;

    public AdaptarMensajes(Context c) {
        this.c = c;

    }

   protected int añadirMensaje(LMensaje lMensaje){
        listMensaje.add(lMensaje);
       int posicion = listMensaje.size()-1;
       notifyItemInserted(listMensaje.size());
       return posicion;
    }

    @Override
    public HolderMensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType==1){
            view = LayoutInflater.from(c).inflate(R.layout.card_view_emisor,parent,false);
        }else{
            view = LayoutInflater.from(c).inflate(R.layout.card_view_receptor,parent,false);
        }

        return new HolderMensaje(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensaje holder, int position) {
        LMensaje lMensaje = listMensaje.get(position);
        LUsuario lUsuario = lMensaje.getlUsuario();

        if (lUsuario!=null){
            holder.getNombre().setText(lUsuario.getUsuario().getNombre());
            Glide.with(c).load(lUsuario.getUsuario().getFotoPerfilURL()).into(holder.getFotoMensaje());
        }

        holder.getNombre().setText(lMensaje.getlUsuario().getUsuario().getNombre());
        holder.getMensaje().setText(lMensaje.getMensaje().getMensaje());

        if(lMensaje.getMensaje().isContieneFoto()){
             holder.getFotoMensajeEnviar().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(lMensaje.getMensaje().getUrlFoto());
        }else{
            holder.getFotoMensajeEnviar().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }


        holder.getHora().setText(lMensaje.fechaCreacionMensaje());
    }

    @Override
    public int getItemCount() {
       return listMensaje.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (listMensaje.get(position).getlUsuario()!=null){
            if (listMensaje.get(position).getlUsuario().getKey().equals(UsuarioDAO.getInstance().getKeyUsuario())){
                return 1;
            }else {
                return -1;
            }
        }else{
            return -1;
        }

        //return super.getItemViewType(position);
    }

    protected void actualizarMensaje(int posicion, LMensaje lMensaje){
        listMensaje.set(posicion,lMensaje);
        notifyItemChanged(posicion);
    }
}
