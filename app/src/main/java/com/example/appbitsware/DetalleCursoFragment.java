package com.example.appbitsware;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.appbitsware.Entidades.Curso;

import java.util.ArrayList;
import java.util.Currency;

public class DetalleCursoFragment extends Fragment {
    TextView nombreDetalle, precio_detalle, tutor_detalle, duracion_detalle;
    ImageView imagenDetalle;

    private Button bntCarrito;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.detalle_curso_fragment, container, false);

        nombreDetalle = view.findViewById(R.id.nombre_detalle);
        imagenDetalle = view.findViewById(R.id.imagen_detalle);
        precio_detalle=view.findViewById(R.id.precio_detalle);
        tutor_detalle=view.findViewById(R.id.tutor_detalle);
        duracion_detalle=view.findViewById(R.id.duracion_detalle);

        bntCarrito=(Button)view.findViewById(R.id.btnCarrito);
        bntCarrito.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                double precio=Double.parseDouble(precio_detalle.getText().toString());
                Toast.makeText(getContext(), "$ "+precio+" agregado al carrito de compras!", Toast.LENGTH_SHORT).show();
            }
        });


        //crear objeto bundler
       // Bundle objetoCurso=new Bundle();
        Bundle objetoCurso=getArguments();
        Curso curso = null;
        //validar existencia de argumentos
        if(objetoCurso!= null){
            curso=(Curso)objetoCurso.getSerializable("objeto");
            //etablecer los datos en la vistas
            nombreDetalle.setText(curso.getNombre());
            imagenDetalle.setImageResource(curso.getImagenId());
            //precio_detalle.setText("Precio: $ "+String.valueOf(curso.getPrecio()));
            precio_detalle.setText(String.valueOf(curso.getPrecio()));

            tutor_detalle.setText("Tutor: "+curso.getTutor());
            duracion_detalle.setText("Duración: "+String.valueOf(curso.getDuracion())+" h");
        }
        return view;
    }
}
