package com.example.appbitsware.ModuloColaborador.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appbitsware.ModuloColaborador.model.Colaborador;
import com.example.appbitsware.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ColaboradorActivity extends AppCompatActivity {

    //OJO: listPerson
    private List<Colaborador> listColaborador=new ArrayList<Colaborador>();
    ArrayAdapter<Colaborador> arrayAdapterColaborador;

    EditText etNombre, etApellido, etCorreo, password, etCurso, etFecha;
    ListView lvPersonas;

    //Fecha
    private int dia,mes,anio;
    Button btn_Fecha;
    //OJO: CONEXION CON FIREBASE | ANDROID STUDIO
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Colaborador personaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaborador);

        etNombre=findViewById(R.id.txtNombre);
        etApellido=findViewById(R.id.txtApellidos);
        etCorreo=findViewById(R.id.txtCorreo);
        password=findViewById(R.id.txtPass);

        etCurso=findViewById(R.id.txtCurso);
        etFecha=findViewById(R.id.txtFecha);

        //btnFecha
        btn_Fecha=(Button)findViewById(R.id.btnFecha);
        lvPersonas=findViewById(R.id.lv_datosColaboradores);

        //Inicializar firebase
        inicializarFirebase();
        listarDatos();

        lvPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSeleccionada=(Colaborador) parent.getItemAtPosition(position);
                etNombre.setText(personaSeleccionada.getNombre());
                etApellido.setText(personaSeleccionada.getApellidos());
                etCurso.setText(personaSeleccionada.getCurso());
                etFecha.setText(personaSeleccionada.getFecha());
                etCorreo.setText(personaSeleccionada.getCorreo());
                password.setText(personaSeleccionada.getPassword());
            }
        });

        btn_Fecha.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mostrarFechaColaborador();
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Colaboradores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listColaborador.clear(); //persistencia de datos
                for(DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Colaborador p=objSnaptshot.getValue(Colaborador.class);
                    listColaborador.add(p);

                    arrayAdapterColaborador=new ArrayAdapter<Colaborador>(ColaboradorActivity.this, android.R.layout.simple_list_item_1, listColaborador);
                    lvPersonas.setAdapter(arrayAdapterColaborador);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    //incluir nuestro menu en el activity colaborador

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_colaborador, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String nombre=etNombre.getText().toString();
        String apellidos=etApellido.getText().toString();
        String correo=etCorreo.getText().toString();
        String pass=password.getText().toString();

        String curso=etCurso.getText().toString();
        String fecha=etFecha.getText().toString();
        switch (item.getItemId()){
            case R.id.icon_add:
                //validacion de campos
                if(nombre.equals("") || apellidos.equals("") || correo.equals("") || pass.equals("") || curso.equals("") || fecha.equals("")){
                    validacion();
                }else{
                    Colaborador p=new Colaborador();
                    p.setId(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setApellidos(apellidos);
                    p.setCorreo(correo);
                    p.setPassword(pass);

                    p.setCurso(curso);
                    p.setFecha(fecha);

                    databaseReference.child("Colaboradores").child(p.getId()).setValue(p);
                    Toast.makeText(this, "Colaborador Agregado!", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;
            case R.id.icon_save:
                Colaborador p=new Colaborador();
                p.setId(personaSeleccionada.getId());
                p.setNombre(etNombre.getText().toString().trim());
                p.setApellidos(etApellido.getText().toString().trim());
                p.setCurso(etCurso.getText().toString().trim());
                p.setFecha(etFecha.getText().toString().trim());
                p.setCorreo(etCorreo.getText().toString().trim());
                p.setPassword(password.getText().toString().trim());
                //Actualizar nodo por "id"
                databaseReference.child("Colaboradores").child(p.getId()).setValue(p);

                Toast.makeText(this, "Actualizado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            case R.id.icon_delete:
                Colaborador p2=new Colaborador();
                p2.setId(personaSeleccionada.getId());
                //Eliminar nodo por "id"
                databaseReference.child("Colaboradores").child(p2.getId()).removeValue();
                Toast.makeText(this, "Eliminado", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
            default:
                break;
        }
        return true;
    }

    private void limpiarCajas() {
        etNombre.setText("");
        etApellido.setText("");
        etCorreo.setText("");
        password.setText("");
        etCurso.setText("");
        etFecha.setText("");
    }

    private void validacion(){
        String nombre=etNombre.getText().toString();
        String apellidos=etApellido.getText().toString();
        String correo=etCorreo.getText().toString();
        String pass=password.getText().toString();

        String curso=etCurso.getText().toString();
        String fecha=etFecha.getText().toString();

        if(nombre.equals("")){
            etNombre.setError("Required nombre");
        }else if(apellidos.equals("")){
            etApellido.setError("Required apellidos");
        }else if(correo.equals("")){
            etCorreo.setError("Required correo");
        }else if(pass.equals("")){
            password.setError("Required password");
        }else if(curso.equals("")){
            password.setError("Required curso");
        }else if(fecha.equals("")){
            password.setError("Required fecha");
        }
    }

    //Metodo para mostrar fecha
    public void mostrarFechaColaborador (){
        final Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                etFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
            }
        },anio,mes,dia);
        datePickerDialog.show();
    }
}
