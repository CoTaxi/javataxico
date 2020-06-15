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
public class colis {
    int id_c,etat,id_karhba,id_expediteur,tel_destinataire;
    String depart,destination,nom_expediteur,nom_destinataire,mail_expediteur,mail_destinataire;
    float poids;
    int nomcategorie;
    public colis(int id_c,int etat, int id_karhba, int id_expediteur, int tel_destinataire, String depart, String destination, String nom_expediteur, String nom_destinataire, String mail_expediteur, String mail_destinataire, float poids) {
        this.id_c=id_c;
        this.etat = etat;
        this.id_karhba = id_karhba;
        this.id_expediteur = id_expediteur;
        this.tel_destinataire = tel_destinataire;
        this.depart = depart;
        this.destination = destination;
        this.nom_expediteur = nom_expediteur;
        this.nom_destinataire = nom_destinataire;
        this.mail_expediteur = mail_expediteur;
        this.mail_destinataire = mail_destinataire;
        this.poids = poids;
    }

    public colis(int id_c,int etat, int id_karhba, String depart, String destination, String nom_expediteur, String nom_destinataire, String mail_expediteur, String mail_destinataire, float poids) {
        this.id_c=id_c;
        this.etat = etat;
        this.id_karhba = id_karhba;
        this.depart = depart;
        this.destination = destination;
        this.nom_expediteur = nom_expediteur;
        this.nom_destinataire = nom_destinataire;
        this.mail_expediteur = mail_expediteur;
        this.mail_destinataire = mail_destinataire;
        this.poids = poids;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setId_karhba(int id_karhba) {
        this.id_karhba = id_karhba;
    }

    public void setId_expediteur(int id_expediteur) {
        this.id_expediteur = id_expediteur;
    }

    public void setTel_destinataire(int tel_destinataire) {
        this.tel_destinataire = tel_destinataire;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setNom_expediteur(String nom_expediteur) {
        this.nom_expediteur = nom_expediteur;
    }

    public void setNom_destinataire(String nom_destinataire) {
        this.nom_destinataire = nom_destinataire;
    }

    public void setMail_expediteur(String mail_expediteur) {
        this.mail_expediteur = mail_expediteur;
    }

    public void setMail_destinataire(String mail_destinataire) {
        this.mail_destinataire = mail_destinataire;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public int getId_c() {
        return id_c;
    }

    public int getEtat() {
        return etat;
    }

    public int getId_karhba() {
        return id_karhba;
    }

    public int getId_expediteur() {
        return id_expediteur;
    }

    public int getTel_destinataire() {
        return tel_destinataire;
    }

    public String getDepart() {
        return depart;
    }

    public String getDestination() {
        return destination;
    }

    public String getNom_expediteur() {
        return nom_expediteur;
    }

    public String getNom_destinataire() {
        return nom_destinataire;
    }

    public String getMail_expediteur() {
        return mail_expediteur;
    }

    public String getMail_destinataire() {
        return mail_destinataire;
    }

    public float getPoids() {
        return poids;
    }

    public int getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(int nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    @Override
    public String toString() {
        return "colis{" + "id_c=" + id_c + ", etat=" + etat + ", id_karhba=" + id_karhba + ", id_expediteur=" + id_expediteur + ", tel_destinataire=" + tel_destinataire + ", depart=" + depart + ", destination=" + destination + ", nom_expediteur=" + nom_expediteur + ", nom_destinataire=" + nom_destinataire + ", mail_expediteur=" + mail_expediteur + ", mail_destinataire=" + mail_destinataire + ", poids=" + poids + ", nomcategorie=" + nomcategorie + '}';
    }
    
}
