package com.ciclo4_moviles_g4_2.pappseando_app.models;

/* Código Java para el manejo de los objetos tipo lugar que serán usados por el adaptador de lista
   Implementado por: Mauricio Moreno
*/

import android.net.Uri;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;

public class PlaceVO implements Serializable {

    public static final FirebaseStorage storage = FirebaseStorage.getInstance();
    private String id;
    private String nombre;
    private String descripcion;
    private String url;
    private float latitud;
    private float longitud;

    //Constructores

    public PlaceVO() {
    }

    public PlaceVO(String nombre, String descripcion) {
        this(null, nombre, descripcion, 0, 0);
    }

    public PlaceVO(String id, String nombre, String descripcion) {
        this(id, nombre, descripcion, 0, 0);
    }

    public PlaceVO(String id, String nombre, String descripcion, float latitud, float longitud) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    //Métodos propios

    public String getLocation() {
        return Math.abs(latitud) + "° " + (latitud >= 0 ? "N" : "S") + ", " + Math.abs(longitud) + "° " + (longitud <= 0 ? "W" : "E");
    }

    public StorageReference obtainImgRef() {
        if (id != null)
            return storage.getReferenceFromUrl("gs://" + storage.getReference().getBucket() + "/imgTest/IMG" + id);
        return null;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
