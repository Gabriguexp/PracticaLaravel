package com.example.practicalaravel.model.pojo;

public class Admin {
    private long id;
    private String usuario, contraseña;


    public Admin(long id, String usuario, String contraseña) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Admin(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
