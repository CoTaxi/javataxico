/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Oussama_RMILI
 */
public class Reclamation {
    private int id_reclamation ; 
    private String message ; 
    private String etat;
    private String reponse  ;
    private int id_vh ; 
    private String prename; 
    
    //private String date_rec ;
   java.util.Date dt = new java.util.Date();
   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String date_rec = sdf.format(dt);

    public Reclamation() {
    }
  
    public Reclamation(String message) {
        
        this.message = message;
    }

    public Reclamation(int id_reclamation, String message, String etat, String date_rec, String reponse) {
        this.id_reclamation=id_reclamation; 
        this.message = message;
        this.etat = etat;
        this.date_rec=date_rec;
        this.reponse=reponse;
    }

    public Reclamation(int id_reclamation, String message, String etat, String date_rec, String reponse, String prename) {
        this.id_reclamation = id_reclamation;
        this.message = message;
        this.etat = etat;
        this.date_rec=date_rec;
        this.reponse=reponse;
        this.prename=prename;
    }

    public int getId_vh() {
        return id_vh;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getMessage() {
        return message;
    }

    public String getEtat() {
        return etat;
    }
    
    public String getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(String date_rec) {
        this.date_rec = date_rec;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
      public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setId_vh(int id_vh) {
        this.id_vh = id_vh;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getPrename() {
        return prename;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", message=" + message + ", etat=" + etat + '}';
    }
    
    
    
}
