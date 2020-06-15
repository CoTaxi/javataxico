/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import taxico.ClientinterfaceController;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.util.Properties;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Oussama_RMILI
 */
public class ReclamationCRUD {
    Connection c= ConnexionBD.getInstance().getCnx();
   //ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
    int res ;
    int res1;
    //Methode pour ajouter une reclamation : 
    public void ajouterReclamation(String msg, Reclamation r, String item_selName, String item_selPrename){
        try {           
            PreparedStatement pt2 = c.prepareStatement("select id_u from user where prenom='"+item_selPrename+"' and nom='"+item_selName+"' and type='chauffeur'");
            ResultSet rs = pt2.executeQuery();
            while(rs.next()){
                 res =rs.getInt(1);
            }
            String Req_Add="INSERT INTO `reclamation`(`id_r`,`message`,`etat`,`date_rec`,`reponse`,`idch`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pt = c.prepareStatement(Req_Add);
            pt.setInt(1, r.getId_reclamation());
            pt.setString(2, msg);
            pt.setString(3, "Non traitée");
            pt.setString(4, r.getDate_rec());
            pt.setString(5, "");
            pt.setInt(6, res);
            pt.executeUpdate();
            try {
                Notification.sendNotification("Notification", "Votre réclamation a été envoyée avec succées",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void afficherPrenomChauffeur(ComboBox combo){
        try {
            PreparedStatement pt = c.prepareStatement("select prenom from user where type='chauffeur'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
             //System.out.println(rs.getString(1) + " " +rs.getString(2));
                
             combo.getItems().add(rs.getString(1));
               //System.out.println(rs.getString(1)+" "+rs.getString(2));         
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherNomChauffeur(String Prenom, ComboBox comboNom){
        try {
            PreparedStatement pt = c.prepareStatement("select nom from user where prenom='"+Prenom+"' and type='chauffeur'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
            // System.out.println(rs.getString(1));
             comboNom.getItems().add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   public void afficherReclamation(ObservableList<Reclamation> oblist){
        try {
           // oblist = FXCollections.observableArrayList();
            PreparedStatement pt1 = c.prepareStatement("select id_r, message, etat, date_rec, reponse from reclamation where etat!='Archivée'");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
          //System.out.println("Reclamations [ Votre message : " +rs.getString(1)+ " Etat: " +rs.getString(2)+ " Date est le: " +rs.getString(3)+ "Rep est "+rs.getString(4)+ "\t \n]");             
            oblist.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                
            }
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
 public void deleteRec(int selectedId ){
        try {    
            PreparedStatement pt2 = c.prepareStatement("delete from reclamation where id_r='"+selectedId+"'");
            pt2.executeUpdate();
            try {
                Notification.sendNotification("Notification", "La réclamation a été supprimée avec succée",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public void modifierReclamation(Reclamation r, int IDSel, String msgEdit, String Prenom, String Nom){
        try {
            PreparedStatement pt1 = c.prepareStatement("select id_u from user where prenom='"+Prenom+"' and nom='"+Nom+"'");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
             res1=rs.getInt(1);      
            }
            PreparedStatement pt3 = c.prepareStatement("update reclamation, user set message=?, date_rec=?, idch="+res1+" where id_r=?");
            pt3.setString(1, msgEdit);
            pt3.setString(2, r.getDate_rec());
            pt3.setInt(3, IDSel);
            pt3.executeUpdate();
            try {
                Notification.sendNotification("Notification", "Votre réclamation a été modifiée avec succée",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
          }  catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

}

