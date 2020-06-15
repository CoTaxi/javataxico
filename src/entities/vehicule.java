/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author ASUS
 */
public class vehicule {
    private int id_v,dispo,places,etat,archive,id_ch;
    private String matricule,marque,modele,cartegrise,couleur,type,position,destination,dateco,zone,accept_c;

    public vehicule() {
    }
    
    
    public vehicule(int id_ch, int id_v, String matricule, String marque, String modele, String carte_grise, String couleur, String type, int dispo, String position, String destination, String accept_c, int etat) {
        this.id_ch = id_ch;
        this.id_v = id_v;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.cartegrise = carte_grise;
        this.couleur = couleur;
        this.type = type;
        this.dispo = dispo;
        this.position = position;
        this.destination = destination;
        this.accept_c = accept_c;
        this.etat = etat;
    }

    public void setAccept_c(String accept_c) {
        this.accept_c = accept_c;
    }

    public String getAccept_c() {
        return accept_c;
    }

    public vehicule(int id_v, String matricule, String marque, String modele, String carte_grise, String couleur, String type, int dispo, String position, String destination, String accept_c, int etat) {
        this.id_v = id_v;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.cartegrise = carte_grise;
        this.couleur = couleur;
        this.type = type;
        this.dispo = dispo;
        this.position = position;
        this.destination = destination;
        this.accept_c = accept_c;
        this.etat = etat;
    }
      public vehicule(String matricule, String marque, String modele, String carte_grise, String couleur, String type, int dispo, String position, String destination, String accept_c, int etat) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.cartegrise = carte_grise;
        this.couleur = couleur;
        this.type = type;
        this.dispo = dispo;
        this.position = position;
        this.destination = destination;
        this.accept_c = accept_c;
        this.etat = etat;
    }
        public vehicule( String matricule, String marque, String modele, String carte_grise, String couleur, String type, String accept_c) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.cartegrise = carte_grise;
        this.couleur = couleur;
        this.type = type;
        this.accept_c = accept_c;
    }
    public vehicule(int id_v,String matricule, String marque, String modele, String couleur, String position, String destination,int places,String dateco) {
        this.id_v=id_v;
        this.places = places;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.position = position;
        this.destination = destination;
        this.dateco = dateco;
    }

    public int getPlaces() {
        return places;
    }

    public String getDateco() {
        return dateco;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public void setDateco(String dateco) {
        this.dateco = dateco;
    }

    public vehicule(int id_v, int dispo, String matricule, String marque, String modele, String cartegrise, String couleur, String type, String position) {
        this.id_v = id_v;
        this.dispo = dispo;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.cartegrise = cartegrise;
        this.couleur = couleur;
        this.type = type;
        this.position = position;
    }

    public vehicule(String matricule, String marque, String modele, String couleur, String position,String zone) {
      
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.position = position;
        this.zone=zone;
    }

    public vehicule(int id_v,String matricule, String position, String destination) {
        this.id_v=id_v;
        this.matricule = matricule;
        this.position = position;
        this.destination = destination;
    }
  

    
   public vehicule(int id_v,String matricule, String marque, String modele, String couleur, String position,String zone) {
        this.id_v=id_v;
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.couleur = couleur;
        this.position = position;
        this.zone=zone;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "vehicule{" + "id_v=" + id_v + ", dispo=" + dispo + ", matricule=" + matricule + ", marque=" + marque + ", modele=" + modele + ", cartegrise=" + cartegrise + ", couleur=" + couleur + ", type=" + type + ", position=" + position + ", destination=" + destination + '}';
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getEtat() {
        return etat;
    }

   

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public void setId_v(int id_v) {
        this.id_v = id_v;
    }

    public void setDispo(int dispo) {
        this.dispo = dispo;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setCartegrise(String cartegrise) {
        this.cartegrise = cartegrise;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId_v() {
        return id_v;
    }

    public int getDispo() {
        return dispo;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public String getCartegrise() {
        return cartegrise;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getType() {
        return type;
    }
    
    
}
