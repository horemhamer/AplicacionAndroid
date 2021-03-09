package com.example.myapplication.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.HolderServicio;
import com.example.myapplication.R;


import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/*public class AdaptadorServicios  extends BaseAdapter{

    public ArrayList<InfoServicio>list;
    private Context c;

    public AdaptadorServicios(Context context, ArrayList<InfoServicio>list){
        this.c = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public InfoServicio getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row;
        final HolderServicio holderServicio;
        if (convertView==null){
           LayoutInflater layoutInflater =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.servicios_view,parent,false);
            holderServicio = new HolderServicio();
            holderServicio.nombreuser = row.findViewById(R.id.nombre);
            holderServicio.servicio = row.findViewById(R.id.servicio);
            holderServicio.descripcion =row.findViewById(R.id.desc);
            holderServicio.imgservicio =row.findViewById(R.id.imgServ);
            holderServicio.imgperfil = row.findViewById(R.id.imgPerfilServ);
           holderServicio.btnsolicitar =row.findViewById(R.id.btnSolicitar);
            row.setTag(holderServicio);
        }else{
            row=convertView;
            holderServicio=(HolderServicio)row.getTag();
        }

        final InfoServicio infoServicio= getItem(position);

        holderServicio.nombreuser.setText(infoServicio.nombreuser);
        holderServicio.servicio.setText(infoServicio.servicio);
        holderServicio.descripcion.setText(infoServicio.descripcion);
        holderServicio.imgservicio.setImageDrawable(null);
        holderServicio.imgperfil.setImageResource(0);
        holderServicio.btnsolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, " "+position, Toast.LENGTH_SHORT).show();
            }
        });

        return null;
    }
}
*/

/*
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
        holder.getBtnsolicitar().set
        Glide.with(c).load(listServicio.get(position).getImgperfil());

    }

    @Override
    public int getItemCount() {
        return listServicio.size();
    }
}*/
