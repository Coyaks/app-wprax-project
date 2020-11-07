package com.example.appbitsware;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.appbitsware.Entidades.Curso;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity implements ComunicarFragments{
    private TextView et_nombre, et_email;
    private DatabaseReference mDatabase;

    private FirebaseAuth mAuth;
    private AppBarConfiguration mAppBarConfiguration;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    //New Enviar datos de un fragment a otro
    DetalleCursoFragment detalleCursoFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

        et_nombre=(TextView)findViewById(R.id.txtNombre);
        et_email=(TextView)findViewById(R.id.txtEmail1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Reemplace con su propia acción", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //OJO: Para al hacerle click a los iconos de la barra lateral me redireccione al fragment
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.progreso, R.id.herramientas,
                R.id.perfil, R.id.cerrarSesion)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //invocacion de metodo
        //getUserInfo();

        //cargar fragment principal en la actividad
        //fragmentManager = getSupportFragmentManager();
        //fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.add(R.id.container_fragment,new MainFragment());
       //fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //getUserInfo();
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    //metodo propio
    private void getUserInfo(){
        String id=mAuth.getCurrentUser().getUid();
        mDatabase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String nombre=dataSnapshot.child("Usuario").getValue().toString();
                    String email=dataSnapshot.child("Email").getValue().toString();
                    et_nombre.setText(nombre);
                    et_email.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Lógica pra hacer el envio
    @Override
    public void enviarCurso(Curso curso) {
        detalleCursoFragment=new DetalleCursoFragment();
        //objeto bundle para transportar la info
        Bundle bundleEnvio=new Bundle();
        //enviar objeto
        bundleEnvio.putSerializable("objeto", curso);
        detalleCursoFragment.setArguments(bundleEnvio);
        //abrir fragment
        //NEWWW: CARGAR EL FRAGMENT EN EL ACTIVITY

      /*  fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,detalleCursoFragment);
        //Para volver atrás despues de abrir un fragment
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();*/

      getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,detalleCursoFragment)
              .addToBackStack(null).commit();
    }
}
