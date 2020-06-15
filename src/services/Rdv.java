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
import entities.Rdventitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import utils.ConnexionBD;

/**
 *
 * @author walid
 */
public class Rdv {
    Connection c = ConnexionBD.getInstance().getCnx();
    public void create_rdv(Rdventitie r){
        try {
            Statement st =c.createStatement();
            String req="INSERT INTO rdv VALUES("+r.getId_rdv()+",'"+r.getId_chauffeur()+"','"+r.getDate_rdv()+"','"+r.getId_garage()+"','"+r.getId_service()+"','"+r.getStatus()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void edit_rdv(Rdventitie r,Date date_rdv)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set date_rdv=? where id_rdv=?");
            pt.setDate(1, date_rdv);
            pt.setInt(2, r.getId_rdv());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void display_rdv()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Rendez vous [ id_rdv: " +rs.getInt(1) + " id_chauffeur: " + rs.getInt(2) + " Date_Rdv: " + rs.getDate(3)+" id_garage: " + rs.getInt(4)+" id_service: " + rs.getInt(5)+" status: " + rs.getString(6)+"]");
            }   } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void remove_rdv(Rdventitie r)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from rdv where id_rdv=?");
            pt.setInt(1,r.getId_rdv());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void get_trie()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv ORDER BY date_rdv ASC");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Rendez vous [ id_rdv: " +rs.getInt(1) + " id_chauffeur: " + rs.getInt(2) + " Date_Rdv: " + rs.getDate(3)+" id_garage: " + rs.getInt(4)+" id_service: " + rs.getInt(5)+" status: " + rs.getString(6)+"]");
            }   } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void display_rdv(int id,int id2)
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv where id_service=? and id_garage=? and status=?");
            pt.setInt(1,id);
            pt.setInt(2,id2);
            pt.setString(3, "disponible");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Rendez vous [ id_rdv: " +rs.getInt(1) + " Date_Rdv: " + rs.getDate(3)+"]");
            }   } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }

        
}
    public void edit_rdv(Date date,int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set status=?,id_chauffeur=? where date_rdv=?");
            pt.setString(1, "nondisponible");
            pt.setInt(2, id);
            pt.setDate(3, date);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void annule_rdv(String date)
     {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set status=? where date_rdv=?");
            pt.setString(1, "disponible");
            pt.setString(2, date);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ObservableList<Rdventitie> displayAll() {
               ObservableList<Rdventitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv AS r, garage AS g,service AS s where r.id_garage = g.id_garage and r.id_service=s.id_service ");
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Rdventitie(rs.getInt("id_rdv"),rs.getInt("id_chauffeur"),rs.getDate("date_rdv"),rs.getString(8),rs.getString(12),rs.getString("status")));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;
       
    }
   
    
    
    public void edit_rdvs(Rdventitie r)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update rdv set status=? where id_rdv =?");
            pt.setString(1,r.getStatus());
            pt.setInt(2, r.getId_rdv());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void pdf()
    {
        try {
            String file_name ="C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\2SE\\java\\Rdv.pdf";
            Document document = new Document();
            //file_name.setReadable(true,false);
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            PreparedStatement pt = c.prepareStatement("select * from rdv");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) { 
                Paragraph para=new Paragraph("Rdv [ Id_Rdv: " +rs.getInt(1) + " Id_Chauffeur : " + rs.getInt(2) + " Date_Rdv " + rs.getDate(3)+" Id_garage: " + rs.getInt(4)+" Id_Service: " + rs.getInt(5)+" Status: " + rs.getString(4)+"]");
                //System.out.println("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
                document.add(para);
                document.add(new Paragraph(" "));
            }
            document.close();
        } catch (SQLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        try {
            Notification.sendNotification("Module Maintenance", "Pdf has been generated has been aafected ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    
}
    int idG;
    public int ReturnIdGarage(String name) {
     
        
        try {
            PreparedStatement pt = c.prepareStatement("select * from garage where name =? ");
            pt.setString(1,name);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                idG=rs.getInt(1);
            }
//            list2.add(rs.getInt(1));
//            idG = list2.get(1);

        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }return idG;
            
    }
    int idS;
    public int ReturnIdService(String name) {
        try {
            PreparedStatement pt = c.prepareStatement("select * from service where name =? ");
            pt.setString(1,name);
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                idS=rs.getInt(1);
            }
//            list3.add(rs.getInt(1));
//            idS = list3.get(1);
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }return idS;
        } 
    
     public void listService(ComboBox s1){
            try {
                PreparedStatement pt = c.prepareStatement("select * from service");
                ResultSet rs = pt.executeQuery();
                while (rs.next()) {
                    s1.getItems().add(rs.getString(2));
                    
                }   } catch (SQLException ex) {
                    Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
                
                }}
     @FXML
     public void SELECTEDcomboBoxService(ComboBox s2,String name)
    {
         
        
        
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM garage where id_service = ( select id_service from service where name =?)");
            pt.setString(1,name);
            ResultSet rs = pt.executeQuery();
            s2.getItems().clear();
            while (rs.next()) {            
                s2.getItems().add(rs.getString(2));
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
     public ObservableList<Rdventitie> displayAllReserved(String id2) {
               ObservableList<Rdventitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv where status=? and id_chauffeur=?");
            pt.setString(1, "nondisponible");
            pt.setString(2, id2);
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Rdventitie(rs.getDate("date_rdv")));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;
       
    }
     public ObservableList<Rdventitie> displayAllReserved(ComboBox c1,ComboBox c2){
                  ObservableList<Rdventitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv where id_service = ( select id_service from service where name =?) and id_garage= ( select id_garage from garage where name =?) and status=? ");
            pt.setString(1,c1.getValue().toString());
            pt.setString(2,c2.getValue().toString());
            pt.setString(3,"disponible");
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Rdventitie(rs.getDate("date_rdv")));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;

     }
     public ObservableList<Rdventitie> displayNotReserved(ComboBox c1,ComboBox c2){
                  ObservableList<Rdventitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from rdv where id_service = ( select id_service from service where name =?) and id_garage= ( select id_garage from garage where name =?) and status=? ");
            pt.setString(1,c1.getValue().toString());
            pt.setString(2,c2.getValue().toString());
            pt.setString(3,"nondisponible");
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Rdventitie(rs.getDate("date_rdv")));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;

     }
        
    }



