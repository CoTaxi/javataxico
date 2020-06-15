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
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Garageentitie;
import entities.Rdventitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;
import java.io.File;
import java.net.MalformedURLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author walid
 */
public class garage {
    Connection c = ConnexionBD.getInstance().getCnx();
    public void create_garage(Garageentitie g)
    {
        try {
            Statement st =c.createStatement();
            String req="INSERT INTO garage VALUES("+g.getId_garage()+",'"+g.getName()+"','"+g.getAddress()+"','"+g.getId_service()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void edit_garage(Garageentitie g,String name)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update garage set name=? where id_garage=?");
            pt.setString(1, name);
            pt.setInt(2, g.getId_garage());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void display_garage()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from garage");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
                
            }   } catch (SQLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void remove_garage(Garageentitie g)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from garage where id_garage=?");
            pt.setInt(1,g.getId_garage());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void pdf()
    {
        try {
            String file_name ="C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\2SE\\java\\Garage.pdf";
            Document document = new Document();
            //file_name.setReadable(true,false);
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            PreparedStatement pt = c.prepareStatement("select * from garage");
            ResultSet rs = pt.executeQuery();
            
            while (rs.next()) { 
                Paragraph para=new Paragraph("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
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
            Notification.sendNotification("Module Maintenance", "Pdf has been Generated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }}
           
           

          public void display_garage (int id3)
    {
        try {
            PreparedStatement pt;
            pt = c.prepareStatement("select * from garage where id_service=?");
            pt.setInt(1, id3);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
          
          public ObservableList<Garageentitie> displayAll() {
               ObservableList<Garageentitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from garage AS g,service AS s where g.id_service=s.id_service ");
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Garageentitie(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(6)));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;
       
    }
           public void updateName(Garageentitie g)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update garage set name=? where id_garage =?");
            pt.setString(1,g.getName());
            pt.setInt(2, g.getId_garage());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           public void UpdateAddress(Garageentitie g)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update garage set address=? where id_garage =?");
            pt.setString(1,g.getAddress());
            pt.setInt(2, g.getId_garage());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            public void ListeGarageSelected(ComboBox c1,ComboBox c2,ComboBox c3){
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM rdv where id_service = ( select id_service from service where name =?) and id_garage= ( select id_garage from garage where name =?) and status=?");
            pt.setString(1,c1.getValue().toString());
            pt.setString(2,c2.getValue().toString());
            pt.setString(3,"disponible");
            ResultSet rs = pt.executeQuery();
            c3.getItems().clear();
            while (rs.next()) {            
                c3.getItems().add(rs.getDate(3));
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
