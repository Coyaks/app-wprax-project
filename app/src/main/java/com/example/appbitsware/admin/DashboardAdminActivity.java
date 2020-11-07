package com.example.appbitsware.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appbitsware.ModuloColaborador.vista.ColaboradorActivity;
import com.example.appbitsware.R;

public class DashboardAdminActivity extends AppCompatActivity {

    ImageButton btnColaborador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        btnColaborador=(ImageButton)findViewById(R.id.imgBtnColaborador);

        //Asigno el evento onClick
        btnColaborador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //aquí está el metodo onClick al presionar imgBtnColaborador
                Toast.makeText(DashboardAdminActivity.this, "Gestión de colaboradores!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DashboardAdminActivity.this, ColaboradorActivity.class));
            }
        });

    }

    public void gestionUsuarios(View v){
        Toast.makeText(this, "Gestión de usuarios!", Toast.LENGTH_SHORT).show();
    }

    public void gestionCursos(View v){
        Toast.makeText(this, "Gestión de Cursos!", Toast.LENGTH_SHORT).show();
    }

    public void gestionPresencial(View v){
        Toast.makeText(this, "Gestión de servicio presencial!", Toast.LENGTH_SHORT).show();
    }

    public void gestionVentas(View v){
        Toast.makeText(this, "Gestión de ventas!", Toast.LENGTH_SHORT).show();
    }

    public void gestionPagos(View v){
        Toast.makeText(this, "Gestión de pagos!", Toast.LENGTH_SHORT).show();
    }
}
