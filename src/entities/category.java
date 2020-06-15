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
public class category {
    int idcategorie;
    String categorie;

    public category(int idcategorie, String categorie) {
        this.idcategorie = idcategorie;
        this.categorie = categorie;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "category{" + "idcategorie=" + idcategorie + ", categorie=" + categorie + '}';
    }
    
}
