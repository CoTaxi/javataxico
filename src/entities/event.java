/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mahdi
 */
public class event {
    private String nom;
    private Date date;
    private int duree;
    private String emplacement;
    private String etat;
    private int capacite;
    

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public event(String nom, Date date, int duree, String emplacement, String etat, int capacite) {
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.emplacement = emplacement;
        this.etat = etat;
        this.capacite = capacite;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public int getCapacite() {
        return capacite;
    }

    public event(String nom, Date date, int duree, int capacite, String emplacement) {
        this.nom = nom;
        this.date = date;
        this.duree = duree;
        this.emplacement = emplacement;
        this.capacite = capacite;
    }

    public event(String nom, Date date, int duree) {
        this.nom = nom;
        this.date = date;
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public Date getDate() {
        return date;
    }

    public int getDuree() {
        return duree;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public event(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final event other = (event) obj;

        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
}
