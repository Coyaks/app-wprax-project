package com.example.appbitsware.ModuloColaborador.vista;

import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseAppPersistencia extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
