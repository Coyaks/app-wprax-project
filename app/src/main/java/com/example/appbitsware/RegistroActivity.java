package com.example.appbitsware;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    private EditText et_usuario, et_email, et_password;
    private Button btnRegistrar;
    private String usuario="";
    private String email="";
    private String password="";
    //Conexion con Firebase
    FirebaseAuth mAuth;
    DatabaseReference mDatabase; //DB realTime
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance().getReference();//nodo padre

        et_usuario=(EditText)findViewById(R.id.txtUsuario);
        et_email=(EditText)findViewById(R.id.txtEmail1);
        et_password=(EditText)findViewById(R.id.txtPassword);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrarUsuario);

        //Evento click al boton
        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                usuario=et_usuario.getText().toString();
                email=et_email.getText().toString();
                password=et_password.getText().toString();

                //validación de campos
                if(!usuario.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    //validacion de pass debe tener al menos 6 caracteres
                    if(password.length()>=6){
                        registrarUsuario();
                    }else{
                        Toast.makeText(RegistroActivity.this, "El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    validacion();
                    //Toast.makeText(RegistroActivity.this, "Todos los campos son obligatorios!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void registrarUsuario(){
        //Important
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Almacenar datos en Firebase

                    Map<String, Object> map=new HashMap<>();
                    map.put("Usuario", usuario);
                    map.put("Email", email);
                    map.put("Password", password);

                    String id=mAuth.getCurrentUser().getUid();
                    mDatabase.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                Toast.makeText(RegistroActivity.this, "Datos correctos!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistroActivity.this, MainActivity.class));
                                finish();//evitar volver al activity
                            }else{
                                Toast.makeText(RegistroActivity.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegistroActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void irLogin(View v){
        startActivity(new Intent(RegistroActivity.this, loginActivity.class));
    }

    private void validacion(){
        usuario=et_usuario.getText().toString();
        email=et_email.getText().toString();
        password=et_password.getText().toString();

        if(usuario.equals("")){
            et_usuario.setError("usuario requerido");
        }else if(email.equals("")){
            et_email.setError("email requerido");
        }else if(password.equals("")){
            et_password.setError("password requerido");
        }
    }
}
