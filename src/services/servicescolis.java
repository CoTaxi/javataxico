/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.colis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author ASUS
 */
public class servicescolis {
    Connection c= ConnexionBD.getInstance().getCnx();
    public void ajoutercolis (colis cl)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into colis(id_c,depart,destination,nom_expediteur,nom_destinataire,poids,etat,id_karhba,id_expediteur,tel_destinataire,mail_expediteur,mail_destinataire) values("+cl.getId_c()+",'"+cl.getDepart()+"','"+cl.getDestination()+"','"+cl.getNom_expediteur()+"','"+cl.getNom_destinataire()+"','"+cl.getPoids()+"','"+cl.getEtat()+"',0,'"+cl.getId_expediteur()+"','"+cl.getTel_destinataire()+"','"+cl.getMail_expediteur()+"','"+cl.getMail_destinataire()+"')";
            st.executeUpdate(req);
            PreparedStatement pt = c.prepareStatement("select * from colis ORDER BY id_c DESC LIMIT 0, 1");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                cl.setId_c(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public  List<colis> affichAllcolis()
    {  List<colis> arr=new ArrayList<>();
        try {
                        Statement st =c.createStatement();
          ResultSet rs=st.executeQuery("select * from colis ");
            while (rs.next()) {
                int id_c=rs.getInt(1);
                String depart=rs.getString(2);
                String destination=rs.getString(3);
                String nom_ex=rs.getString(4);
                String nom_dest=rs.getString(5);
                float poids=rs.getFloat(6);
                int etat=rs.getInt(7);
                int id_karhbe=rs.getInt(8);
                String mail_exp=rs.getString(11);
                String mail_dest=rs.getString(12);
                
                colis col=new colis(id_c,etat,id_karhbe,depart,destination,nom_ex, nom_dest,mail_exp,mail_dest, poids);
                arr.add(col);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("verifier votre id ! ce colis n'existe pas");
        }
        
      return arr;
    }
       public List<colis> getTrier(){
        
            List<colis> arr=new ArrayList<>();
        try {
            Statement st =c.createStatement();
            ResultSet rs=st.executeQuery("select * from colis ORDER BY etat DESC");
            while (rs.next()) {
                int id_c=rs.getInt(1);
                String depart=rs.getString(2);
                String destination=rs.getString(3);
                String nom_ex=rs.getString(4);
                String nom_dest=rs.getString(5);
                float poids=rs.getFloat(6);
                int etat=rs.getInt(7);
                int id_karhbe=rs.getInt(8);
                String mail_exp=rs.getString(11);
                String mail_dest=rs.getString(12);
                
                colis col=new colis(id_c,etat,id_karhbe,depart,destination,nom_ex, nom_dest,mail_exp,mail_dest, poids);
                arr.add(col);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
    }
         public List<colis> getTrierbyp(){
        
            List<colis> arr=new ArrayList<>();
        try {
            Statement st =c.createStatement();
            ResultSet rs=st.executeQuery("select * from colis ORDER BY poids DESC");
            while (rs.next()) {
                int id_c=rs.getInt(1);
                String depart=rs.getString(2);
                String destination=rs.getString(3);
                String nom_ex=rs.getString(4);
                String nom_dest=rs.getString(5);
                float poids=rs.getFloat(6);
                int etat=rs.getInt(7);
                int id_karhbe=rs.getInt(8);
                String mail_exp=rs.getString(11);
                String mail_dest=rs.getString(12);
                
                colis col=new colis(id_c,etat,id_karhbe,depart,destination,nom_ex, nom_dest,mail_exp,mail_dest, poids);
                arr.add(col);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
    }
            public  List<colis> affichcolisNAF(int etat)
    {  List<colis> arr=new ArrayList<>();
        try {
            Statement st =c.createStatement();
            ResultSet rs=st.executeQuery("select * from colis where etat='"+etat+"' ");
            while (rs.next()) {
                int id_c=rs.getInt(1);
                String depart=rs.getString(2);
                String destination=rs.getString(3);
                String nom_ex=rs.getString(4);
                String nom_dest=rs.getString(5);
                float poids=rs.getFloat(6);
                int id_karhbe=rs.getInt(8);
                String mail_exp=rs.getString(11);
                String mail_dest=rs.getString(12);
                
                colis col=new colis(id_c,etat,id_karhbe,depart,destination,nom_ex, nom_dest,mail_exp,mail_dest, poids);
                arr.add(col);
            }
        }
          catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return arr;
    }
              public List<colis> affichercolis(int id)
     {  List<colis> arr=new ArrayList<>();
        try {
            PreparedStatement pt = c.prepareStatement("select * from colis where id_c='"+id+"'");
            ResultSet rs = pt.executeQuery();
             while (rs.next()) {
                 int id_c=rs.getInt(1);
               String depart=rs.getString(2);
                String destination=rs.getString(3);
                String nom_ex=rs.getString(4);
                String nom_dest=rs.getString(5);
                float poids=rs.getFloat(6);
                int etat=rs.getInt(7);
                int id_karhbe=rs.getInt(8);
                String mail_exp=rs.getString(11);
                String mail_dest=rs.getString(12);
                
                colis col=new colis(id,etat,id_karhbe,depart,destination,nom_ex, nom_dest,mail_exp,mail_dest, poids);
                arr.add(col);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("verifier votre id ! ce colis n'existe pas");
        }
        
      return arr;
    }
               public void affecter (int idc,int idv)
    { 
     try {
            PreparedStatement pt = c.prepareStatement("update colis set id_karhba=? where id_c=?");
            pt.setInt(1,idv);
            pt.setInt(2,idc);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifieretat (int etat, int idc)
    {
         try {
            PreparedStatement pt = c.prepareStatement("update colis set etat=? where id_c=?");
            pt.setInt(1,etat);
            pt.setInt(2,idc);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicescolis.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
