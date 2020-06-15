/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.contrat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import utils.ConnexionBD;

/**
 *
 * @author achref
 */
public class service_contrat {

    Connection c = ConnexionBD.getInstance().getCnx();

   int a; 
    
    public int return_id(int cinn) {

        try {
            PreparedStatement pt = c.prepareStatement("select * from user where cin=?");
            pt.setInt(1,cinn);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                a=    rs.getInt(1);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(service_contrat.class.getName()).log(Level.SEVERE, null, ex);
        }
return a;}


    public void ajoutercontrat(contrat c1,int cinn) {
        
        try {
            //Statement st = c.createStatement();
            String req = "INSERT INTO `contrat` (`id_contrat`,`type_c`, `duree`) VALUES (?,?,?)";
            PreparedStatement pstm = c.prepareStatement(req);
    pstm.setInt(1, c1.getId_c());
           // pstm.setInt(1,return_id(cinn));          
            pstm.setString(2, c1.getType_c());
            pstm.setInt(3, c1.getDuree());

            

//String  req = "insert into Vehicule values ("+vl.getMatricule()+",'"+vl.getMarque()+"','"+vl.getModele()+"','"+vl.getCarte_grise()+"','"+vl.getCouleur()+"','"+vl.getType()+"',+'"+vl.getType()+"'+)" ;
            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(service_contrat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficher_contrat() {
        try {
            PreparedStatement pt = c.prepareStatement("select * from contrat");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("contrat: identifiant du chauffeur :" + rs.getInt(1) + "\n type de contrat: " + rs.getString(2) + "\n duree de contrat: " + rs.getInt(3) + "mois \n\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(service_contrat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int verif(int id) throws SQLException {
     String stat = ("select * from chauffeur where chauffeur.id_chauff='"+id+"'");
     int r=0;
     PreparedStatement pt=c.prepareStatement(stat);
     ResultSet rs = pt.executeQuery();
                 if (rs.next()) {
                     r=r+1;
                    return r;
            }
            else        
            {
                System.out.println("l'identifiant que vous avez saisi est invalide!!!");
                
            }
        return r;
    }
        public int verifier(int cinn) throws SQLException {
     String stat = ("select * from chauffeur where chauffeur.cin='"+cinn+"'");
     int r=0;
     PreparedStatement pt=c.prepareStatement(stat);
     ResultSet rs = pt.executeQuery();
                 if (rs.next()) {
                     r=r+1;
                    return r;
            }
            else        
            {
                System.out.println("error"); 
            }
        return r;
    }
    
              public void supprimer_contrat (contrat co)
    {
        try {
            String req = "DELETE FROM `contrat` WHERE `id_contrat`=?";
           PreparedStatement pt = c.prepareStatement(req);
            pt.setInt(1,co.getId_c());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(service_contrat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void modifier_contrat(int id, String type)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update contrat set type_c=? where id_contrat=?");
            pt.setString(1,type);
            pt.setInt(2,id);
            pt.setInt(3,id);
            
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(service_contrat.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
                public void modifier_contratt(contrat co)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update contrat set type_c=?");
            pt.setString(1,co.getType_c());
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(service_contrat.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
        public contrat chercher_contrat(String type)
        {
        contrat c1 = null;
        String req = "select * from contrat where type=?";
        try {
            PreparedStatement pstm = c.prepareStatement(req);
            pstm.setString(2, type);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                c1 = new contrat(rs.getInt(1),rs.getInt(2),rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c1;

    }
        public ArrayList<contrat> getAllcontratctsBytype(String type) {
        ArrayList<contrat> contrats= new ArrayList<>();
        String req="SELECT * FROM contrat WHERE type_c='"+type+"'";
        try {
            PreparedStatement pstm = c.prepareStatement(req);            
            ResultSet rs = pstm.executeQuery(req);
            while(rs.next()){
                contrat r = new contrat();
                r =new contrat(rs.getInt(1),rs.getInt(2),rs.getString(3));
                contrats.add(r);
            }
        } catch (SQLException ex) {
            
        }
        return contrats;
        
    }  
        
    }

       
