package com.example.appbitsware.Entidades;

import java.io.Serializable;

public class Curso implements Serializable {
    private String nombre;
    private double precio;
    private int imagenId;
    private String tutor;
    private double duracion;

    public Curso(){

    }

    public Curso(String nombre, double precio, int imagenId, String tutor, double duracion) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagenId = imagenId;
        this.tutor = tutor;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
}
