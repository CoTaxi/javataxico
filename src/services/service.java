/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Garageentitie;
import entities.Serviceentitie;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import utils.ConnexionBD;

/**
 *
 * @author walid
 */
public class service {
    Connection c = ConnexionBD.getInstance().getCnx();
    public void create_service(Serviceentitie s)
    {
        try {
            Statement st =c.createStatement();
            String req="INSERT INTO service VALUES("+s.getId_service()+",'"+s.getName()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void display_service ()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from service");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Service [ id_service: " +rs.getInt(1) + " name : " + rs.getString(2) + "]");
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    public void remove_service(Serviceentitie s)
    {
        try {
            PreparedStatement pt = c.prepareStatement("delete from service where id_service=?");
            pt.setInt(1,s.getId_service());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void display_service (int id)
    {
        try {
            PreparedStatement pt = c.prepareStatement("select from service where id=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println("Service [ id_service: " +rs.getInt(1) + " name : " + rs.getString(2) + "]");
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
        public void display_service_name ()
    {
        try {
            PreparedStatement pt = c.prepareStatement("select * from");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                System.out.println(" name : " + rs.getString(2) + "]");
            }   } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
       public ObservableList<Serviceentitie> displayAll() {
               ObservableList<Serviceentitie> list=FXCollections.observableArrayList();

        
        try {
            PreparedStatement pt = c.prepareStatement("select * from service ");
            ResultSet rs = pt.executeQuery();
            
     while(rs.next())
     {
         list.add(new Serviceentitie(rs.getInt(1),rs.getString(2)));
        
     }
     
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;
       
    }
       public void edit_rdvs(Serviceentitie s)
    {
        try {
            PreparedStatement pt = c.prepareStatement("update service set name=? where id_service =?");
            pt.setString(1,s.getName());
            pt.setInt(2, s.getId_service());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Rdv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
       
       
       public void pdf() throws FileNotFoundException, DocumentException
    {
//        try {
            String file_name ="C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\2SE\\java\\Service.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
//            PreparedStatement pt = c.prepareStatement("select * from garage");
//            ResultSet rs = pt.executeQuery();
            
            PdfPTable table = new PdfPTable(1);
            PdfPCell c1 = new PdfPCell (new Phrase("Name"));
            table.addCell(c1);
            table.setHeaderRows(1);
            
//            while (rs.next()) { 
//                Paragraph para=new Paragraph("Service [ Id_Service: " +rs.getInt(1) + " Name : " + rs.getString(2) + " ]");
//                System.out.println("garage [ id_garage: " +rs.getInt(1) + " name : " + rs.getString(2) + " Address: " + rs.getString(3)+" id_service: " + rs.getInt(4)+"]");
//                document.add(para);
//                document.add(new Paragraph(" "));
                  table.addCell("walid");
//            }
            document.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (DocumentException ex) {
//            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(garage.class.getName()).log(Level.SEVERE, null, ex);
//        }{
//            try {
//                Notification.sendNotification("Module Maintenance", "Pdf has been Generated ",TrayIcon.MessageType.INFO);
//            } catch (AWTException ex) {
//                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(Menu_Garage.class.getName()).log(Level.SEVERE, null, ex);
//            }
}
       public void ListeSelectedService(ComboBox c1,ComboBox c2){
        try {
            PreparedStatement pt = c.prepareStatement("SELECT * FROM garage where id_service = ( select id_service from service where name =?)");
            pt.setString(1,c1.getValue().toString());
            ResultSet rs = pt.executeQuery();
            c2.getItems().clear();
            while (rs.next()) {            
                c2.getItems().add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
       public void ListeService(ComboBox c1){
        try {
            PreparedStatement pt = c.prepareStatement("select * from service");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                c1.getItems().add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        }
}
       
    
}


