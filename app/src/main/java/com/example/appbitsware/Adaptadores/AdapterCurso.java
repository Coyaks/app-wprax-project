package com.example.appbitsware.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbitsware.Entidades.Curso;
import com.example.appbitsware.R;

import java.util.ArrayList;

public class AdapterCurso extends RecyclerView.Adapter<AdapterCurso.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<Curso> model;
    private View.OnClickListener listener;

    //constructor
    public AdapterCurso(Context context, ArrayList<Curso> model){
        this.inflater=LayoutInflater.from(context);
        this.model=model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.lista_cursos, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre=model.get(position).getNombre();
        double precio=model.get(position).getPrecio();
        String pre=String.valueOf(precio);
        int imagen=model.get(position).getImagenId();

        holder.nombre.setText(nombre);
        holder.precio.setText("Precio: $ "+pre);
        holder.imagen.setImageResource(imagen);
    }
    @Override
    public int getItemCount() {
        return model.size();
    }

    //METODO onClick
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //IMPRIMIR ATRIBUTOS EN LISTA DE CURSOS | HOME_FRAGMENT
        TextView nombre, precio;
        ImageView imagen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombre_curso);
            precio=itemView.findViewById(R.id.precio_curso);
            imagen=itemView.findViewById(R.id.img_curso);
        }
    }
}
