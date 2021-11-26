package com.ciclo4_moviles_g4_2.pappseando.adapters;

/* Código Java para el manejo de los objetos tipo lugar que serán usados por el adaptador de lista
   Implementado por: Mauricio Moreno
*/

public class PlaceVO {
    private String nombre;
    private String descripcion;
    private int foto;

    public PlaceVO(String nombreLugar, String descripcionLugar, int fotoLugar) {
        this.nombre = nombreLugar;
        this.descripcion = descripcionLugar;
        this.foto = fotoLugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
