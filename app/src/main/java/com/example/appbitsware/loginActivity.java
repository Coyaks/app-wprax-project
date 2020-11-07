package com.example.appbitsware;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbitsware.admin.DashboardAdminActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    private EditText et_email, et_password;
    String email="";
    String password="";
    boolean bandera;
    //conexion con Firebase
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();
        et_email=(EditText)findViewById(R.id.txtEmail1);
        et_password=(EditText)findViewById(R.id.txtPassword);

    }
    public void iniciarSesion(View v){
        email=et_email.getText().toString();
        password=et_password.getText().toString();
        //validacion
        if(!email.isEmpty() && !password.isEmpty()){
            loginAdmin();
            if(bandera!=true){
                loginUser();
            }

        }else{
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginAdmin() {
        bandera=false;
        if(email.equals("admin") && password.equals("sistemas")){
            Toast.makeText(this, "Bienvenido Admistrador! ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(loginActivity.this, DashboardAdminActivity.class));
            bandera=true;
            limpiarLogin();
        }
    }

    private void limpiarLogin() {
        et_email.setText("");
        et_password.setText("");
    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(loginActivity.this, "Bienvenido nuevamente!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(loginActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(loginActivity.this, "Datos incorrectos, compruebe los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void irRegistrar(View v){
        startActivity(new Intent(loginActivity.this, RegistroActivity.class));
    }
    //mantener la sesión
    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null){ //usuario ya inicio sesión
            startActivity(new Intent(loginActivity.this, MainActivity.class));
            finish();
        }
    }
}
