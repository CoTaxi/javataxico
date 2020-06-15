/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.event;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mahdi
 */
public class fidelite_services {
        Connection conn = ConnexionBD.getInstance().getCnx();
    public void ajout_mail(String sender,String recipient){
            try {
                String req = "INSERT INTO invitation (email_sender,email_reciptient) VALUES ('"+sender+"','"+recipient+"')";
                PreparedStatement st = conn.prepareStatement(req);
                st.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(fidelite_services.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
    public int afficher_nbr_email()
    {
                int tot = 0;
            try {
                PreparedStatement pt = conn.prepareStatement("select COUNT(*) AS nbr from invitation ");
                ResultSet rs = pt.executeQuery();
                while(rs.next()){
                tot = rs.getInt("nbr");}
            } catch (SQLException ex) {
                Logger.getLogger(fidelite_services.class.getName()).log(Level.SEVERE, null, ex);
            }
            return tot;
    }
    
    
        public static String sendMail(String recep) throws Exception{
    Properties p= new Properties();
    
    p.put("mail.smtp.auth", "true");
    p.put("mail.smtp.starttls.enable", "true");
    p.put("mail.smtp.host", "smtp.gmail.com");
    p.put("mail.smtp.port", "587");
    
    String e_mail="mehdihrairi6@gmail.com"; 
    String pass = "lol06061997mhlol";

    Session session =Session.getInstance(p,new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
          return new PasswordAuthentication(e_mail, pass);
        }
    });
     
        Message message=prepareMessage(session,e_mail,recep);
        Transport.send(message);
          Notification.sendNotification("module fidelite", "email envoyé ",TrayIcon.MessageType.INFO);
       return e_mail;
    }
private static Message prepareMessage(Session session,String e_mail, String recipient) throws MessagingException{
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress(e_mail));
message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
message.setSubject("invitation letter :D ");
message.setText("Hello, i recommand this app it will make your life way easier. such a good app i will share it with you cause it's huge deal :p");

return message;

}
public void affecter_pt(int nbr)
{
        try {
            String req = "update user set point_fidelite=(?+point_fidelite) where id_u='1' ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setInt(1,nbr);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public int affichage_code()  {
    int code=0;
        try {
            PreparedStatement pt = conn.prepareStatement("select code from code_promo");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String c=rs.getString("code");
                code=Integer.parseInt(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }
public int affichage_balance()  {
    
            int balance=0;
        try {
            PreparedStatement pt = conn.prepareStatement("select balance from code_promo");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String b=rs.getString("balance");
                balance=Integer.parseInt(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return balance;
    }
public List<event> updateevent ()
 {        
    List<event> eve=new ArrayList<>();
      try {
    Connection conn = ConnexionBD.getInstance().getCnx();
            PreparedStatement pt = conn.prepareStatement("select * from evennement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                eve.add(new event(
                        rs.getString("nom_event"),
                        rs.getDate("date_event"),
                        rs.getInt("duree_event"),
                        rs.getInt("capacite"),
                        rs.getString("emplacement")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }

    return eve;
}
}
