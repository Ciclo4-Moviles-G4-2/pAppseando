package com.ciclo4_moviles_g4_2.pappseando.models;

/* Código Java para el manejo de los objetos tipo lugar que serán usados por el adaptador de lista
   Implementado por: Mauricio Moreno
*/

import java.io.Serializable;

public class PlaceVO implements Serializable {
    private String id;
    private String nombre;
    private String descripcion;
    private int foto;
    private String latitud;
    private String longitud;

    //Constructores

    public PlaceVO() {}

    public PlaceVO(String nombre, String descripcion, int foto) {
        this(null, nombre, descripcion, foto, null, null);
    }

    public PlaceVO(String id, String nombre, String descripcion, int foto, String latitud, String longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    //Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getFoto() {
        return foto;
    }
}
