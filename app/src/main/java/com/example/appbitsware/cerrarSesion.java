package com.example.appbitsware;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class cerrarSesion extends Fragment {
    //crear la referencia
    private View vista;
    private Button bntnCerrar;

    private FirebaseAuth mAuth;

    public cerrarSesion() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Tod proceso aquí
        mAuth=FirebaseAuth.getInstance();

        vista=inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
        bntnCerrar=(Button)vista.findViewById(R.id.btnRegistrarUsuario);
        bntnCerrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Contenido del metodo cerrarSesion
                mAuth.signOut();
                //redireccionar pantalla de login
                startActivity(new Intent(getContext(), loginActivity.class));
            }
        });

        return vista;
    }

    /*public void test(View v){
        Toast.makeText(getContext(), "Holaaaaaaaaa", Toast.LENGTH_SHORT).show();
    }*/

}
