/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import entities.vehicule;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class servicesvehicule {
    
    Connection c= ConnexionBD.getInstance().getCnx();
   

    public void ajoutervehicule(vehicule vl) {

        try {
            //Statement st = c.createStatement();
            String req = "INSERT INTO `vehicule`(`matricule`, `marque`, `modele`, `carte_grise`, `couleur`, `type`,`dispo`,`position`,`destination`,`accept_c`, `etat`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = c.prepareStatement(req);
            pstm.setString(1, vl.getMatricule());
            pstm.setString(2, vl.getMarque());          
            pstm.setString(3, vl.getModele());
            pstm.setString(4, vl.getCartegrise());
            pstm.setString(5, vl.getCouleur());
            pstm.setString(6, vl.getType());
            pstm.setInt(7, vl.getDispo());
            pstm.setString(8, vl.getPosition());
            pstm.setString(9, vl.getDestination());
            pstm.setString(10, vl.getAccept_c());
            pstm.setInt(11, vl.getEtat());
//String  req = "insert into Vehicule values ("+vl.getMatricule()+",'"+vl.getMarque()+"','"+vl.getModele()+"','"+vl.getCarte_grise()+"','"+vl.getCouleur()+"','"+vl.getType()+"',+'"+vl.getType()+"'+)" ;

            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void ajouter_vehicule(vehicule vl,int id_ch) {

        try {
            //Statement st = c.createStatement();
            String req = "INSERT INTO `vehicule`(`id_v`,`matricule`, `marque`, `modele`, `cartegrise`, `couleur`, `type`,`dispo`,`position`,`destination`,`etat`,`accept_c`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = c.prepareStatement(req);
            pstm.setInt(1, id_ch);
            pstm.setString(2, vl.getMatricule());
            pstm.setString(3, vl.getMarque());          
            pstm.setString(4, vl.getModele());
            pstm.setString(5, vl.getCartegrise());
            pstm.setString(6, vl.getCouleur());
            pstm.setString(7, vl.getType());
            pstm.setInt(8, vl.getDispo());
            pstm.setString(9, vl.getPosition());
            pstm.setString(10, vl.getDestination());
             pstm.setInt(11, vl.getEtat());
            pstm.setString(12, vl.getAccept_c());
           
//String  req = "insert into Vehicule values ("+vl.getMatricule()+",'"+vl.getMarque()+"','"+vl.getModele()+"','"+vl.getCarte_grise()+"','"+vl.getCouleur()+"','"+vl.getType()+"',+'"+vl.getType()+"'+)" ;

            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afficher_vehicule() {
        try {
            PreparedStatement pt = c.prepareStatement("select * from vehicule");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("vehicule: identifiant vehicule :"+rs.getInt(1)+ "\n matricule: " + rs.getInt(2) + "\nmarque : " + rs.getString(3) + "\nmodele: " + rs.getString(4) + "\ncarte_grise:" + rs.getString(5) + "\ncouleur: " + rs.getString(6) + "\ntype: " + rs.getString(7) + "\n disponibilité : " + rs.getInt(8) +"\n position : "+rs.getString(9)+ "\ndestination: "+rs.getString(10)+ "\n importation des colis" +rs.getString(11)+ "\nEtat"+rs.getInt(12)+ " \n\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void afficher_vehiculeParid(int id) {
            //vehicule v = null;
        try {
            PreparedStatement pt = c.prepareStatement("select * from vehicule where id_v='"+id+"'");
            //pt.setInt(1,id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
             System.out.println("vehicule: identifiant vehicule :"+rs.getInt(1)+ "\n matricule: " + rs.getString(2) + "\nmarque : " + rs.getString(3) + "\nmodele: " + rs.getString(4) + "\ncarte_grise:" + rs.getString(5) + "\ncouleur: " + rs.getString(6) + "\ntype: " + rs.getString(7) + "\n disponibilité : " + rs.getString(8) +"\n position : "+rs.getString(9)+ "\ndestination: "+rs.getString(10)+ "\n importation des colis" +rs.getString(11)+ "\nEtat"+rs.getBoolean(12)+ " \n\n");
           }
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void modifier_vehicule(String carte)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update vehicule set carte_grise=?");
            pt.setString(1,carte);
            

            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
                public void modifier_vehiculee(vehicule v)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update vehicule set carte_grise=? where id_v=?");

            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

        
          public void supprimer_vehicule (vehicule v,int id_ve)
    {
        try {
            String req = "DELETE FROM `vehicule` WHERE `id_v`= ?";
            PreparedStatement pt = c.prepareStatement(req);
            pt.setInt(1,id_ve);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            public int verif(int id) throws SQLException {
     String stat = ("select * from vehicule where vehicule.id_v='"+id+"'");
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
    public List<vehicule> afficherTaxiZone(String position,String zone)
    {  List<vehicule> arr=new ArrayList<>();
        try {
            PreparedStatement pt1 = c.prepareStatement("select matricule,marque,modele,couleur,zone,position,id_v from vehicule where zone='"+zone+"' and type='Taxi' and dispo=1  ");
            ResultSet rs1 = pt1.executeQuery(); 
            if (rs1.next())
            {
                String matricule=rs1.getString(1);
                String marque=rs1.getString(2);
                String modele=rs1.getString(3);
                String couleur=rs1.getString(4);
                String zo=rs1.getString(5);
                String pos=rs1.getString(6);
                int id_v=rs1.getInt(7);
                vehicule v= new vehicule(id_v,matricule,marque,modele,couleur,pos,zo);
                arr.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    
    
      public List<vehicule> afficherTaxi (String position,String zone)
    {    List<vehicule> arr=new ArrayList<>();
        try {
            PreparedStatement pt = c.prepareStatement("select matricule,marque,modele,couleur,zone,id_v from vehicule where position='"+position+"' and type='Taxi' and dispo=1  ");
            ResultSet rs = pt.executeQuery();           
            if (rs.next()) 
            {
                String matricule=rs.getString(1);
                String marque=rs.getString(2);
                String modele=rs.getString(3);
                String couleur=rs.getString(4);
                String zonet=rs.getString(5);
                int id_v=rs.getInt(6);
                vehicule v= new vehicule(id_v,matricule,marque,modele,couleur,position,zonet);
                arr.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
    
     
        public List<vehicule> afficherco (String position,String destination,String dateres,int nbrp)
    {    List<vehicule> arr=new ArrayList<>();
         String type="co-voiturage";
        try {
            PreparedStatement pt = c.prepareStatement("select matricule,marque,modele,couleur,places,dateco,id_v from vehicule where places>'"+nbrp+"' and position='"+position+"' and destination='"+destination+"' and dispo=1 and type='"+type+"'  ");
            ResultSet rs = pt.executeQuery();           
            while (rs.next()) 
            {
                String matricule=rs.getString(1);
                String marque=rs.getString(2);
                String modele=rs.getString(3);
                String couleur=rs.getString(4);
                int places=rs.getInt(5);
                String dateco=rs.getString(6);
                int id_v=rs.getInt(7);
                vehicule v= new vehicule(id_v,matricule,marque,modele,couleur,position,destination,places,dateco);
                arr.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
           public List<vehicule> afficherlisteco ()
    {    List<vehicule> arr=new ArrayList<>();
         String type="co-voiturage";
        try {
            PreparedStatement pt = c.prepareStatement("select matricule,marque,modele,couleur,places,dateco,id_v,position,destination from vehicule where dispo=1 and type='"+type+"'  ");
            ResultSet rs = pt.executeQuery();           
            while (rs.next()) 
            {
                String matricule=rs.getString(1);
                String marque=rs.getString(2);
                String modele=rs.getString(3);
                String couleur=rs.getString(4);
                int places=rs.getInt(5);
                String dateco=rs.getString(6);
                int id_v=rs.getInt(7);
                String position=rs.getString(8);
                String destination =rs.getString(9);
                vehicule v= new vehicule(id_v,matricule,marque,modele,couleur,position,destination,places,dateco);
                arr.add(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
      public void vehiculeAFC(int idc)
    {
        int dispo =1;
        String depart="";
        String destination="";
        try {
            String stat = ("select * from colis where id_c='"+idc+"'");
            PreparedStatement pt1=c.prepareStatement(stat);
            ResultSet rs1 = pt1.executeQuery();            
            if (rs1.next()) {
                
                depart = rs1.getString(2);
                destination= rs1.getString(3);
            }
            PreparedStatement pt = c.prepareStatement("select * from vehicule where dispo ='"+dispo+"' and position='"+depart+"' and destination='"+destination+"'  ");
            ResultSet rs = pt.executeQuery();
             System.out.println("Liste des vehicule partant de "+depart);
             System.out.println("vers "+destination);
            while (rs.next()) 
                {  
                System.out.println("vehicule [ id_v: " +rs.getInt(1) + "  marque : " + rs.getString(3) + "  modele: " + rs.getString(4)+ "  couleur:" +rs.getString(6)+ "  position: " +rs.getString(9) + "  destination: " +rs.getString(10) + "]");
                
                }
             } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String findvehiculebyid(int idv)
    {
        List<String> arr=new ArrayList<>();
        String m="";
         String type="co-voiturage";
        try {
            PreparedStatement pt = c.prepareStatement("select matricule from vehicule where dispo=1 and type='"+type+"' and id_v='"+idv+"' ");
            ResultSet rs = pt.executeQuery();           
            while (rs.next()) 
            {
                String matricule=rs.getString(1);
                m=matricule;
                return matricule;
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
      public List<vehicule> vehcolis (String position,String destination)
    {    List<vehicule> arr=new ArrayList<>();
        try {
            PreparedStatement pt = c.prepareStatement("select matricule,marque,modele,couleur,zone,id_v,destination from vehicule where position='"+position+"' and destination='"+destination+"' and dispo=1  ");
            ResultSet rs = pt.executeQuery();           
            if (rs.next()) 
            {
                String matricule=rs.getString(1);
                String marque=rs.getString(2);
                String modele=rs.getString(3);
                String couleur=rs.getString(4);
                String zonet=rs.getString(5);
                int id_v=rs.getInt(6);
                vehicule v= new vehicule(id_v,matricule,position,destination);
                arr.add(v);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(servicesvehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
    
}
