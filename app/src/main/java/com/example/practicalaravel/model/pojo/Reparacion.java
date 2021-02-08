package com.example.practicalaravel.model.pojo;

public class Reparacion {

    private long id;
    private long idconsola;
    private String descripcion;
    private String fecha;
    private double precio;

    public Reparacion(long id, long idconsola, String descripcion, String fecha, double precio) {
        this.id = id;
        this.idconsola = idconsola;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.precio = precio;
    }

    public Reparacion(long idconsola, String descripcion, String fecha, double precio) {
        this.idconsola = idconsola;
        this.descripcion = descripcion;
        this.fecha= fecha;
        this.precio=precio;
    }

    @Override
    public String toString() {
        return "Reparacion{" +
                "id=" + id +
                ", idConsola=" + idconsola +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", precio=" + precio +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdconsola() {
        return idconsola;
    }

    public void setIdconsola(long idconsola) {
        this.idconsola = idconsola;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
