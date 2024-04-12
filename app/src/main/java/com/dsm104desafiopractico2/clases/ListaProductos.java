package com.dsm104desafiopractico2.clases;

public class ListaProductos {
    String nombre;
    String imagen;
    double precio;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ListaProductos(String nombre, String imagen, double precio) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
    }

    public ListaProductos() {
    }
}
