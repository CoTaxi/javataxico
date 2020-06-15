/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Reclamation;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import utils.ConnexionBD;

/**
 *
 * @author Oussama_RMILI
 */
public class AdminCRUD {
    Connection c= ConnexionBD.getInstance().getCnx();
    int res ; 
    
   // Methode pour lister tous les reclamations : 
    public void afficherReclamationAdmin(ObservableList<Reclamation> oblist1){
        try {
            PreparedStatement pt1 = c.prepareStatement("SELECT id_r, message, etat, date_rec, reponse, prenom, nom FROM reclamation, user where idch=id_u and etat != 'Archivée'");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
                String fetch = rs.getString("prenom")+" "+rs.getString("nom");
                System.out.println(rs.getString("reponse"));
                oblist1.add(new Reclamation(rs.getInt("id_r"), rs.getString("message"), rs.getString("etat"), rs.getString("date_rec"), rs.getString("reponse"), fetch));
            }
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void afficherArchiveReclamationAdmin(ObservableList<Reclamation> oblist1){
        try {
            PreparedStatement pt1 = c.prepareStatement("SELECT id_r, message, etat, date_rec, reponse, prenom, nom FROM reclamation, user where idch=id_u and etat ='Archivée'");
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
                String fetch = rs.getString("prenom")+" "+rs.getString("nom");
                System.out.println(rs.getString("reponse"));
                oblist1.add(new Reclamation(rs.getInt("id_r"), rs.getString("message"), rs.getString("etat"), rs.getString("date_rec"), rs.getString("reponse"), fetch));
            }
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*public void rechercheReclamationAdmin(ObservableList<Reclamation> oblist1, String name){
        try {
            PreparedStatement pt1 = c.prepareStatement("SELECT * FROM reclamation where message ='"+name+"'");
            //pt1.setString(1, name);
            ResultSet rs = pt1.executeQuery();
            while (rs.next()) {
                //String fetch = rs.getString("message");
                System.out.println(rs.getString("reponse"));
                oblist1.add(new Reclamation(rs.getInt("id_r"), rs.getString("message"), rs.getString("etat"), rs.getString("date_rec"), rs.getString("reponse")));
            }
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void supprimerReclamationAdmin(int idRec){
        try {
            PreparedStatement pt2 = c.prepareStatement("delete from reclamation where id_r=?");
            pt2.setInt(1,idRec);
            pt2.executeUpdate();
            try {
                Notification.sendNotification("Notification", "La réclamation a été supprimée avec succée",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
               } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modifierEtatReclamation(String respond, String msg, int idRec){
        try {
            PreparedStatement pt3 = c.prepareStatement("update reclamation set reponse=?, etat=? where id_r=?");
            pt3.setString(1, respond);
            pt3.setString(2, msg);
            pt3.setInt(3, idRec);
            pt3.executeUpdate();
            try {
                Notification.sendNotification("Notification", "Votre réclamation a été modifée avec succée",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
             } catch (SQLException ex) {
            Logger.getLogger(ReclamationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void trieParDateASC(){
        try {
            PreparedStatement pt4 = c.prepareStatement("select * from reclamation order by date_rec asc");
            ResultSet rs = pt4.executeQuery();
            while (rs.next()) {
                System.out.println("Reclamations [ id Reclamation: " +rs.getInt(1) + " Votre message : " + rs.getString(2) + " Etat: " + rs.getString(3)+ " Date est le: " +rs.getString(4)+ "\t \n]");             
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void trieParDateDESC(){
        try {
            PreparedStatement pt4 = c.prepareStatement("select * from reclamation order by date_rec desc");
            ResultSet rs = pt4.executeQuery();
            while (rs.next()) {             
                System.out.println("Reclamations [ id Reclamation: " +rs.getInt(1) + " Votre message : " + rs.getString(2) + " Etat: " + rs.getString(3)+ " Date est le: " +rs.getString(4)+ "\t \n]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void convertirPDF(){
         
        try {
            String file_name ="C:\\Users\\ASUS\\Documents\\NetBeansProjects\\TaxiCo\\src\\Reclamation.pdf";
            Document document = new Document();
            try {
                //file_name.setReadable(true,false);
                PdfWriter.getInstance(document, new FileOutputStream(file_name));
            } catch (DocumentException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            document.open();
            PreparedStatement pt5;
            
                pt5 = c.prepareStatement("select * from reclamation");
                ResultSet rs;
                rs = pt5.executeQuery();
                while (rs.next()) { 
                Paragraph para=new Paragraph("Réclamation [ id_réclamation: " +rs.getInt(1) + " Message : " + rs.getString(2) + " Etat: " + rs.getBoolean(3)+" Date de réclamation: " + rs.getDate(4)+"]");
                //System.out.println("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
                document.add(para);
                document.add(new Paragraph(" "));
            }
            document.close();
            Notification.sendNotification("Notification", "Les réclamations sont prêtes à imprimer",TrayIcon.MessageType.INFO);
            } catch (SQLException ex) {
                Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
             } catch (AWTException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    public void chercherRec(int id_affiche){
        try {
            PreparedStatement pt6 = c.prepareStatement("select *from reclamation where id_r=?");
            pt6.setInt(1, id_affiche);
            ResultSet rs = pt6.executeQuery();
            while (rs.next()){
                System.out.println("Reclamations [ id Reclamation: " +rs.getInt(1) + " Votre message : " + rs.getString(2) + " Etat: " + rs.getString(3)+ " Date est le: " +rs.getString(4)+ "\t \n]");
                
            }   } catch (SQLException ex) {
            Logger.getLogger(AdminCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String sendMail(String recep) throws Exception{
    Properties p= new Properties();  
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "587");
   
    String e_mail="rmilissou@gmail.com";
    String pass = "Toyotaparado";

    Session session =Session.getInstance(p,new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
          return new PasswordAuthentication(e_mail, pass);
        }
    });
     
        Message message=prepareMessage(session,e_mail,recep);
        Transport.send(message);
          Notification.sendNotification("Notification TaxiCo", "Email envoyé ",TrayIcon.MessageType.INFO);
       return e_mail;
    }
private static Message prepareMessage(Session session,String e_mail, String recipient) throws MessagingException{
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(e_mail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
message.setSubject("Info TaxiCo");
message.setText("Bonjour monsieur,\n\n Votre réclamation a été traitée avec succès. \n ~~\n -------------------\n Equipe TaxiCo\n-------------------\n Hello, I recommand this app it will make your life way easier. such a good app i will share it with you cause it's huge deal 😀");

return message;

}
}
    
