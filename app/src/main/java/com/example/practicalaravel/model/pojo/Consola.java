package com.example.practicalaravel.model.pojo;

import java.time.LocalDate;

public class Consola {
    long id;
    String nombre, urlpic, estado;
    double precio;
    String fechaventa;

    public Consola(Long id, String nombre, String urlpic, String estado, double precio, String fechaventa ) {
        this.id = id;
        this.urlpic = urlpic;
        this.estado = estado;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaventa = fechaventa;
    }

    public Consola(String nombre, String urlpic, String estado, double precio, String fechaventa) {
        this.nombre = nombre;
        this.urlpic = urlpic;
        this.estado = estado;
        this.precio = precio;
        this.fechaventa = fechaventa;
    }

    public Consola() {
        nombre ="";
        urlpic="";
        estado="";
        precio=0;
        fechaventa="";
    }

    @Override
    public String toString() {
        return "Consolas{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", urlpic='" + urlpic + '\'' +
                ", estado='" + estado + '\'' +
                ", precio=" + precio +
                ", fechaventa=" + fechaventa +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlpic() {
        return urlpic;
    }

    public void setUrlpic(String urlpic) {
        this.urlpic = urlpic;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(String fechaventa) {
        this.fechaventa = fechaventa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
}
