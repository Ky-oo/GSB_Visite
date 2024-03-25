package com.example.gsb_visite;

import com.google.gson.annotations.SerializedName;

public class Motif {
    @SerializedName("_id")
    private int id;
    @SerializedName("libelle")
    private String libelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}