package com.example.appbitsware.ui.home_curso;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbitsware.Adaptadores.AdapterCurso;
import com.example.appbitsware.ComunicarFragments;
import com.example.appbitsware.Entidades.Curso;
import com.example.appbitsware.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    AdapterCurso adapterCurso;
    RecyclerView recyclerViewCurso;
    ArrayList <Curso> listaCurso;

    //referencias para comunicar fragment
    Activity actividad;
    ComunicarFragments interfaceComunicaFragments;
   // private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewCurso=view.findViewById(R.id.listaPrincipal);
        listaCurso=new ArrayList<>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarData();
        return view;
    }

    private void mostrarData() {
        recyclerViewCurso.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterCurso=new AdapterCurso(getContext(),listaCurso);
        recyclerViewCurso.setAdapter(adapterCurso);

        //evento click al tocar los cursos
        adapterCurso.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String nombre=listaCurso.get(recyclerViewCurso.getChildAdapterPosition(view)).getNombre();
                Toast.makeText(getContext(),"Click: "+nombre, Toast.LENGTH_SHORT).show();
                //ENVIANDO OBJETO 'CURSO'
                interfaceComunicaFragments.enviarCurso(listaCurso.get(recyclerViewCurso.getChildAdapterPosition(view)));
            }
        });
    }

    private void cargarLista() {
        listaCurso.add(new Curso("Curso profesional de Firebase", 8,R.drawable.firebase,"Juan Carlos Mendez", 15.0));
        listaCurso.add(new Curso("Aprende Flutter + Dart", 12,R.drawable.flutter,"Felix Pérez", 25.0));
        listaCurso.add(new Curso("Go desde cero", 10,R.drawable.go,"Roberto Jimenez", 12.0));
        listaCurso.add(new Curso("React.js nivel avanzado", 10,R.drawable.react, "Fernando Herrera", 25.0));
        listaCurso.add(new Curso("Aprende Java completo", 10,R.drawable.java,"Steve Gordon",30.0));
        listaCurso.add(new Curso("Frontend con Javascript", 10,R.drawable.js,"Rosa Maldonado", 28.0));
        listaCurso.add(new Curso("Backend con PHP", 10,R.drawable.php, "Rodolfo Fernandez", 27.0));
        listaCurso.add(new Curso("IA con Python", 10,R.drawable.python,"Gabriela Hernandez", 21.0));
        listaCurso.add(new Curso("Desarrollo web básico gratis", 0,R.drawable.desarrolloweb,"Luis Salas", 8.0));
    }

    //New métodos para enviar objeto a otro Fragment
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad=(Activity) context;
            interfaceComunicaFragments=(ComunicarFragments) this.actividad;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
