/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

public class contrat {
   
 private int duree,id_c,cin;
 private String type_c;
 private String nom,prenom;

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getCin() {
        return cin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public contrat() {
    }
    public int getId_c() {
        return id_c;
    }


    public contrat(int id_c,int duree, String type_c) {
        this.id_c = id_c;
        this.duree = duree;
        this.type_c = type_c;

        
    }
    public contrat(String nom, String prenom, int cin, String type_c,int duree) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.type_c = type_c;
        this.duree = duree;

    }
        public contrat(int id_c) {
        this.id_c = id_c;
        
    }
    public contrat( String type_c,int duree) {

        this.type_c = type_c;
        this.duree = duree;
    }


    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setType_c(String type_c) {
        this.type_c = type_c;
    }

    public int getDuree() {
        return duree;
    }

    public String getType_c() {
        return type_c;
    }
 
    
    
}
