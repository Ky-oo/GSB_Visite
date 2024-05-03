package com.example.gsb_visite;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Echantillon implements Serializable {
    @SerializedName("_id")
    private String id;
    private String nom_commercial;
    @SerializedName("date_peremption")
    private Date date_peremption;
    @SerializedName("date_fabrication")
    private Date date_fabrication;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom_commercial() {
        return nom_commercial;
    }

    public void setNom_commercial(String nom_commercial) {
        this.nom_commercial = nom_commercial;
    }

    public Date getDate_peremption() {
        return date_peremption;
    }

    public void setDate_peremption(Date date_peremption) {
        this.date_peremption = date_peremption;
    }

    public Date getDate_fabrication() {
        return date_fabrication;
    }

    public void setDate_fabrication(Date date_fabrication) {
        this.date_fabrication = date_fabrication;
    }

    @Override
    public String toString() {
        return nom_commercial + ' ' ;
    }
}
