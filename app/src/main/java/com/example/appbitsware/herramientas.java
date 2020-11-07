package com.example.appbitsware;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.appbitsware.AgregarCarrito.AgregarCarritoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class herramientas extends Fragment {

    private View vista;
    private Button bntCarrito;

    public herramientas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_herramientas, container, false);

        bntCarrito=(Button)vista.findViewById(R.id.btnComprarCursos);
        bntCarrito.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //redireccionar pantalla de login
                startActivity(new Intent(getContext(), AgregarCarritoActivity.class));
            }
        });

        return vista;
    }


}
