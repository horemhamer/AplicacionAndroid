package com.example.myapplication.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import de.hdodenhof.circleimageview.CircleImageView;


public class AdaptadorServicios  extends ArrayAdapter<InfoServicio>{

    private  List<InfoServicio> mlist;
    private  Context c;
    private  int resourceLayout;
    public AdaptadorServicios(@NonNull Context context, int resource, List<InfoServicio> objects) {
        super(context, resource, objects);
        this.mlist = objects;
        this.c= context;
        resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null)
            view = LayoutInflater.from(c).inflate(resourceLayout,null);

            InfoServicio infoServicio = mlist.get(position);
            TextView textView = view.findViewById(R.id.nombre);
            textView.setText(infoServicio.getNombreuser());
            TextView textView2 = view.findViewById(R.id.servicio);
            textView2.setText(infoServicio.getServicio());
            TextView textView3 =  view.findViewById(R.id.desc);
            textView3.setText(infoServicio.getDescripcion());
        CircleImageView circleImageView = view.findViewById(R.id.imgPerfilServ);
        circleImageView.setImageResource(0);
        ImageView imageView =  view.findViewById(R.id.imgServ);
        imageView.setImageDrawable(infoServicio.getImgserv());



            return view;

    }
}

