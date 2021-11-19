package com.ciclo4_moviles_g4_2.pappseando.adapter;

import android.net.Uri;

public class Places {
    private int idLugar;
    private String nombreLugar;
    private String descripcionLugar;
    private Uri fotoLugar;

    public Places(int idLugar) {
        this.idLugar = idLugar;
    }

    public Places(int idLugar, String nombreLugar, String descripcionLugar) {
        this.idLugar = idLugar;
        this.nombreLugar = nombreLugar;
        this.descripcionLugar = descripcionLugar;
    }

    public Places(int idLugar, String nombreLugar, String descripcionLugar, Uri fotoLugar) {
        this.idLugar = idLugar;
        this.nombreLugar = nombreLugar;
        this.descripcionLugar = descripcionLugar;
        this.fotoLugar = fotoLugar;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getDescripcionLugar() {
        return descripcionLugar;
    }

    public void setDescripcionLugar(String descripcionLugar) {
        this.descripcionLugar = descripcionLugar;
    }

    public Uri getFotoLugar() {
        return fotoLugar;
    }

    public void setFotoLugar(Uri fotoLugar) {
        this.fotoLugar = fotoLugar;
    }
}
