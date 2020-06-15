/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class user {
  private int id_u,tel,active,cin,permis,rib_compte,experience,nb_course,point_fidelite;
  private String nom,prenom,mail,mdp,naissance,creation,image,type,nom_compte,nom_event;
  
    public user(String nom, String prenom,int tel,  String mail, String mdp, String naissance, String creation,int active,String image,String type,int nb_course) {
       
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.naissance = naissance;
        this.creation = creation;
        this.active =active;
        this.image =image;
        this.type=type;
        this.nb_course=nb_course;
    }

    public user( String nom,int point_fidelite) {
        this.point_fidelite = point_fidelite;
        this.nom = nom;
    }

    public user(int tel, int active, int cin, int permis, int rib_compte, int experience, int nb_course, String nom, String prenom, String mail, String mdp, String naissance, String creation, String image, String type, String nom_compte) {
 
        this.tel = tel;
        this.active = active;
        this.cin = cin;
        this.permis = permis;
        this.rib_compte = rib_compte;
        this.experience = experience;
        this.nb_course = nb_course;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.naissance = naissance;
        this.creation = creation;
        this.image = image;
        this.type = type;
        this.nom_compte = nom_compte;
    }

    public user(int id_u, int tel, String nom, String prenom, String mail) {
        this.id_u = id_u;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "user{" + "id_u=" + id_u + ", tel=" + tel + ", active=" + active + ", cin=" + cin + ", permis=" + permis + ", rib_compte=" + rib_compte + ", experience=" + experience + ", nb_course=" + nb_course + ", point_fidelite=" + point_fidelite + ", nom_event=" + nom_event + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mdp=" + mdp + ", naissance=" + naissance + ", creation=" + creation + ", image=" + image + ", type=" + type + ", nom_compte=" + nom_compte + '}';
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setPermis(int permis) {
        this.permis = permis;
    }

    public void setRib_compte(int rib_compte) {
        this.rib_compte = rib_compte;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setNb_course(int nb_course) {
        this.nb_course = nb_course;
    }

    public void setPoint_fidelite(int point_fidelite) {
        this.point_fidelite = point_fidelite;
    }

    public void setNom_event(String nom_event) 
    {
        this.nom_event=nom_event;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNaissance(String naissance) {
        this.naissance = naissance;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNom_compte(String nom_compte) {
        this.nom_compte = nom_compte;
    }

    public int getId_u() {
        return id_u;
    }

    public int getTel() {
        return tel;
    }

    public int getActive() {
        return active;
    }

    public int getCin() {
        return cin;
    }

    public int getPermis() {
        return permis;
    }

    public int getRib_compte() {
        return rib_compte;
    }

    public int getExperience() {
        return experience;
    }

    public int getNb_course() {
        return nb_course;
    }

    public int getPoint_fidelite() {
        return point_fidelite;
    }

    public String getNom_event() {
        return nom_event;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNaissance() {
        return naissance;
    }

    public String getCreation() {
        return creation;
    }

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getNom_compte() {
        return nom_compte;
    }


}
