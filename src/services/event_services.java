/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author mahdi
 */
import utils.ConnexionBD;
import entities.event;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import entities.user;
//import com.module_event.interfaces.eventServices;
/**
 *
 * @author mahdi
 */
public class event_services {
    Connection conn = ConnexionBD.getInstance().getCnx();
    Statement stmt;
    event e;
    int cap;
    static public final String CharactersNumbers = "[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890èéòàùì ]";
    static public final String Characters = "[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùì ]";
    static public final String Numbers = "[1234567890 ]";
    static public final String NumbersPoint = "[1234567890. ]";

    public void create(String nom,Date date,int duree,String emplacement,int capacite) {
        try {
            String on="on";
            String req = "INSERT INTO evennement (nom_event,date_event,duree_event,capacite,emplacement,etat) VALUES ('"+nom+"','"+date+"','"+duree+"','"+capacite+"','"+emplacement+"','"+on+"')";

            PreparedStatement st = conn.prepareStatement(req);
            //st.setString(1, e.getNom());
            //st.setObject(2, e.getDate());
            //st.setObject(3, e.getDuree());
            st.executeUpdate();
            try {
                Notification.sendNotification("module evennement", "evennement ajouté ",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
     public void update(String nomp,String nom,Date Date_debut,int duree,int capacite,String emplacement) {
        try {
            String req = "UPDATE evennement set nom_event='"+nom+"', date_event ='"+Date_debut+"' , duree_event ='"+duree+"', emplacement ='"+emplacement+"', capacite ='"+capacite+"'  where nom_event ='"+nomp+"'";
            PreparedStatement st = conn.prepareStatement(req);

            

            st.executeUpdate();
            System.out.println("event updated ");
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void delete(String nom) {
        try {
            String req = "DELETE FROM `evennement` WHERE nom_event = ? ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,nom);
            //  st.setString(2, e.getNomAnnonce());

            st.executeUpdate();
        } catch (SQLException ex) {

            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


public void supprimer_event (String nom)
    {
        
        try{
            PreparedStatement pt = conn.prepareStatement("delete from evennement where nom_event=?");
            pt.setString(1,nom);
            pt.executeUpdate();
            } catch (SQLException ex) {

            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
}
public void modifie_event(int duree,Timestamp date_debut,String nom)
    {
        try {
            PreparedStatement pt = conn.prepareStatement("update evennement set  duree_event= ?, date_event= ?, empacement= ?, capacite= ? where nom_event= ? ");
            
            pt.setInt(1,duree);
            pt.setTimestamp(2,date_debut);
            pt.setString(3,nom);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

public List<event> tri(){
            List<event> list1=new ArrayList<>();
            try {
                
            PreparedStatement pt = conn.prepareStatement("select * from evennement ORDER BY duree_event DESC");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                String nom= rs.getString("nom_event");
                Date date=rs.getDate("date_event");
                int duree=rs.getInt("duree_event");
                int capacite= rs.getInt("capacite");
                String emplacement=rs.getString("emplacement");
                event ev = new event(nom,date,duree,capacite,emplacement);
                list1.add(ev);
            }}
            catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
           }
            return list1;
}
public int capacite(){
                    int nbr = 0;
            try {
                PreparedStatement pt = conn.prepareStatement("select count(*) as capacite from user where nom_event !='' ");
                ResultSet rs = pt.executeQuery();
                while(rs.next()){
                nbr=rs.getInt("capacite");}
            } catch (SQLException ex) {
                Logger.getLogger(fidelite_services.class.getName()).log(Level.SEVERE, null, ex);
            }
            return nbr;
}
public int capacite_final(String nom){
    int capt=0;
     try {
            String pt = ("select capacite from evennement where nom_event='"+nom+"'");
            PreparedStatement st = conn.prepareStatement(pt);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
            capt=rs.getInt("capacite");}
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
     return capt;
}
public void affecter(String nom)
{
    int capacite=capacite_final(nom);
        try {
            String req = "update user set  nom_event=? where id_u=1 ";
            
            if(capacite!=0){
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,nom);
            st.executeUpdate();}
            else{
                System.out.println("pas d'espace dispo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void quit()
{
        try {
            String req = "update user set  nom_event=? where id_u=1 ";
            PreparedStatement st = conn.prepareStatement(req);
            st.setString(1,"");
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public List<String> affichagecombo()  {
               List<String> arr=new ArrayList<>();
        try {
            
            
            
            PreparedStatement pt = conn.prepareStatement("select nom_event from evennement");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String nom=rs.getString("nom_event");
                
                arr.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
public List<String> affichagecombo_fidelite()  {
               List<String> liste=new ArrayList<>();
        try {
            
            
            
            PreparedStatement pt = conn.prepareStatement("select nom from user");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String nom=rs.getString("nom");
                
                liste.add(nom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
public List<String> recherche_avancee(String nom)  {
                List<String> liste2=new ArrayList<>();

        try {
            
            PreparedStatement pt = conn.prepareStatement("select * from evennement where nom_event like '%"+nom+"%' ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String nom1=rs.getString("nom_event");
                liste2.add(nom);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return liste2;
}
public List<user> getTrier()
              {
            List<user> arr=new ArrayList<>();
        try {
            Connection conn = ConnexionBD.getInstance().getCnx();
            PreparedStatement pt = conn.prepareStatement("select nom,point_fidelite from user");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                arr.add(new user(
                        rs.getString("nom"),
                        rs.getInt("point_fidelite")
                        
                ) );
            }  
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
            }
public List<event> modifierevent(String nomp)
{
            List<event> arr=new ArrayList<>();
        try {
            
            Connection conn = ConnexionBD.getInstance().getCnx();
            PreparedStatement pt = conn.prepareStatement("select * from evennement where nom_event=?");
            pt.setString(1,nomp);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) { 
                String nom=rs.getString("nom_event");
                Date date=rs.getDate("date_event");
                int duree=rs.getInt("duree_event");
                int capacite=rs.getInt("capacite");
                String c=Integer.toString(capacite);
                String emplacement=rs.getString("emplacement");
                event ev =new event(nom, date, duree, emplacement, c, capacite);
                arr.add(ev);
            }
            
             
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
         return arr; 
}
public List<String> affichernomevent()
{
            List<String> arr=new ArrayList<>();
        try {
            
            Connection conn = ConnexionBD.getInstance().getCnx();
            PreparedStatement pt = conn.prepareStatement("select nom_event from evennement ");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) { 
                String nom=rs.getString("nom_event");
                arr.add(nom);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(event_services.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;   
}
public List<event> chercherevent (String nom)
 {        
    List<event> eve=new ArrayList<>();
      try {
    Connection conn = ConnexionBD.getInstance().getCnx();
    PreparedStatement pt = conn.prepareStatement("select * from evennement where nom_event like '%"+nom+"%'");
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

