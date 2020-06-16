/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Garageentitie;
import entities.Rdventitie;
import entities.Serviceentitie;
import entities.user;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import services.Rdv;
import services.garage;
import services.service;
import services.servicesuser;
import utils.ConnexionBD;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminuserController implements Initializable {

    @FXML
    private Button userbtn;
    @FXML
    private Pane userp;
    @FXML
    private Pane indexp;
    @FXML
    private TitledPane listecl;
    @FXML
    private TitledPane listech;
    @FXML
    private TableView<user> tablech;
     servicesuser src =new servicesuser();
     List<user> arr=new ArrayList<>();    
     List<user> listetrie=new ArrayList<>();
     List<String> comb=new ArrayList<>();
    @FXML
    private TableColumn<user, String> nom, prenom,mail,naissance,creation,nomc;
    @FXML
    private TableColumn<user, Integer> tel,active,cin,ribc,experience,nbrcourse;
    @FXML
    private ComboBox<String> tribox;
    @FXML
    private TextField searchfield;
    @FXML
    private CheckBox checkifactive;
    @FXML
    private CheckBox checkifarchive;
    int i=0;
    int j=0;
    String usertype="";
    @FXML
    private TableColumn<user, String> secondnom,secondprenom;
    @FXML
    private TableColumn<user, Integer> secondtel;
    @FXML
    private TableColumn<user, String> secondmail,secondnaissance,secondcreation;
    @FXML
    private TableColumn<user, Integer> secondactive,secondcourse;
    @FXML
    private TableView<user> tablecl;
    @FXML
    private Button acceuilbtn;
    @FXML
    private Button MaintenanceBtn;
    
    @FXML
    private Pane pane_Maintenance;
     /**
     * Initializes the controller class.
     */
    //Configure table view
    //Configure Rendez vous
    @FXML private TableView<Rdventitie> tableView;
    @FXML private TableColumn<Rdventitie, Integer>id_rdvColumn;
    @FXML private TableColumn<Rdventitie, Integer>id_chauffeurColumn;
    @FXML private TableColumn<Rdventitie, Date>date_rdvColumn;
    @FXML private TableColumn<Rdventitie, String>name_garageColumn;
    @FXML private TableColumn<Rdventitie, String>name_serviceColumn;
    @FXML private TableColumn<Rdventitie, String>statusColumn;
    Connection c = ConnexionBD.getInstance().getCnx();
    
    //
    
    @FXML private DatePicker date_rdvTextField;
   
  
    
    
    //Configure Garage
    @FXML private TableView<Garageentitie> tableViewGarage;
    @FXML private TableColumn<Garageentitie, Integer>id_garageColumnG;
    @FXML private TableColumn<Garageentitie, String>name_serviceColumnG;
    @FXML private TableColumn<Garageentitie, String>nameColumnG;
    @FXML private TableColumn<Garageentitie, String>addressColumn;
    @FXML private ComboBox GarageCombo;
    
    //Les Button Remove
    @FXML private Button RemoveBtnRdv;
    @FXML private Button RemoveBtnService;
    @FXML private Button RemoveBtnGarage;

    
    //
    @FXML private TextField nameTextFieldG;
    @FXML private TextField addressTextField;
    
    //Configure Service
    @FXML private TableView<Serviceentitie> tableViewService;
    @FXML private TableColumn<Serviceentitie, Integer>id_serviceColumnS;
    @FXML private TableColumn<Serviceentitie, String>nameColumnS;
    @FXML private ComboBox ServiceCombo;
    @FXML private ComboBox ServiceComboG;
    
    //
    @FXML private TextField nameTextFieldS;
    private Object com;
    public void SetBtnRdv(){
        this.RemoveBtnRdv.setDisable(false);
    }
   // public static int idS;

    /**
     *
     */
   
    
    
    
   @FXML public void SelectedComboBoxService()
    {
        Rdv r = new Rdv();
        r.SELECTEDcomboBoxService(GarageCombo, ServiceCombo.getValue().toString());
        
             
    }
    
    //Update status For Table Rendez vous
    
    
    public void changeStatusCellEvent (TableColumn.CellEditEvent edditedCell)
    {
        Rdventitie statusSelected = tableView.getSelectionModel().getSelectedItem();
        statusSelected.setStatus(edditedCell.getNewValue().toString());
        Rdv r = new Rdv();
        r.edit_rdvs(statusSelected);
        
        try {
            Notification.sendNotification("Module Maintenance", "Status has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }
        
    }
    
   
//Update Name And Address For Table Garage
    
    
    public void changeNameCellEventG (TableColumn.CellEditEvent edditedCell)
    {
        Garageentitie nameSelected =  tableViewGarage.getSelectionModel().getSelectedItem();
        nameSelected.setName(edditedCell.getNewValue().toString());
        garage g = new garage();
        g.updateName(nameSelected);
      
        try {
            Notification.sendNotification("Module Maintenance", "Name has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }
         
    }
    public void changeAddressCellEvent (TableColumn.CellEditEvent edditedCell)
    {
        Garageentitie nameSelected =  tableViewGarage.getSelectionModel().getSelectedItem();
        nameSelected.setAddress(edditedCell.getNewValue().toString());
        garage g = new garage();
        g.UpdateAddress(nameSelected);
      
        try {
            Notification.sendNotification("Module Maintenance", "Address has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }
          
    }
    
//Update Name For Table Service
    
    
    public void changeNameCellEventS (TableColumn.CellEditEvent edditedCell)
    {
        Serviceentitie nameSelected = tableViewService.getSelectionModel().getSelectedItem();
        nameSelected.setName(edditedCell.getNewValue().toString());
        service s = new service();
        s.edit_rdvs(nameSelected);
       
        try {
            Notification.sendNotification("Module Maintenance", "Name has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }
        
    }
    
  
    
//Configure Button For Table Rendez vous
    
    
    public void addButton() throws SQLException{
 
 Rdv r = new Rdv();
       
//        Integer.parseInt(id_chauffeurTextField.getText())

        Rdventitie newRdv = new Rdventitie(Date.valueOf(date_rdvTextField.getValue()),
                                           r.ReturnIdGarage(GarageCombo.getValue().toString()),
                                           r.ReturnIdService(ServiceCombo.getValue().toString()),
                                           "disponible");
        
        //Rdv r = new Rdv();
        r.create_rdv(newRdv);
        //tableView.getItems().add(newRdv);
        tableView.getItems().clear();
        tableView.setItems(r.displayAll());
        
        
        
        try {
            Notification.sendNotification("Module Maintenance", "Rendez vous has been aafected ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }
      
    }
    
    public void RemoveButton()
    {
      
        ObservableList <Rdventitie> selectedRows,allPeople;
        allPeople = tableView.getItems();
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        for (Rdventitie rdv : selectedRows)
        {
            
            Rdventitie selected = tableView.getSelectionModel().getSelectedItem();
            int id = selected.getId_rdv();
            Rdventitie re = new Rdventitie(id);
            Rdv r = new Rdv();
            r.remove_rdv(re);
            allPeople.remove(rdv);
        }
       
        try {
            Notification.sendNotification("Module Maintenance", "Rendez vous has been aafected ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }
        
}
    
//Configure Button For Table Garage
    
    
    public void addButtonGarage()
    {
        String a =nameTextFieldG.getText();
        if (a.isEmpty()){
          JOptionPane.showMessageDialog(null, " Missing Information ");
        }else{
        Rdv r = new Rdv();
        Garageentitie newgarage = new Garageentitie( nameTextFieldG.getText(),
                                           addressTextField.getText(),
                                           r.ReturnIdService(ServiceComboG.getValue().toString()));
        
        garage g = new garage();
        g.create_garage(newgarage);
        //tableViewGarage.getItems().add(newgarage);
        tableViewGarage.getItems().clear();
        tableViewGarage.setItems(g.displayAll());
      
        try {
            Notification.sendNotification("Module Maintenance", "Garage has been aafected ",TrayIcon.MessageType.INFO);
            
            //  JavaMailUtil.sendMail("benothmanwalid@gmail.com", "MyFirstApp", "Look My First App");
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }}
    }
    
    public void RemoveButtonGarage()
    {
      
        ObservableList <Garageentitie> selectedRows,allPeople;
        allPeople = tableViewGarage.getItems();
        selectedRows = tableViewGarage.getSelectionModel().getSelectedItems();
        for (Garageentitie garage : selectedRows)
        {
            Garageentitie selected = tableViewGarage.getSelectionModel().getSelectedItem();
            int id = selected.getId_garage();
            Garageentitie gr = new Garageentitie(id);
            garage g = new garage();
            g.remove_garage(gr);
            allPeople.remove(garage);
        }
      
        try {
            Notification.sendNotification("Module Maintenance", "Garage has been Removed ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }

    }
    
    
//Configure Button For Service
    
    
    public void addButtonService()
    {
        String a =nameTextFieldG.getText();
        if (a.isEmpty()){
          JOptionPane.showMessageDialog(null, " Missing Information ");
        }else{
        Serviceentitie newservice = new Serviceentitie(nameTextFieldS.getText());
                                           
                                           
        
        service s = new service();
        s.create_service(newservice);
        //tableViewService.getItems().add(newservice);
        tableViewService.getItems().clear();
        tableViewService.setItems(s.displayAll());
       
        try {
            Notification.sendNotification("Module Maintenance", "Service has been aafected ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }}

        
    }
    
    public void RemoveButtonService()
    {
      
        ObservableList <Serviceentitie> selectedRows,allPeople;
        allPeople = tableViewService.getItems();
        selectedRows = tableViewService.getSelectionModel().getSelectedItems();
        for (Serviceentitie service : selectedRows)
        {
            Serviceentitie selected = tableViewService.getSelectionModel().getSelectedItem();
            int id = selected.getId_service();
            Serviceentitie se = new Serviceentitie(id);
            service s = new service();
            s.remove_service(se);
            allPeople.remove(service);
        }
      
        try {
            Notification.sendNotification("Module Maintenance", "Service has been Removed",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
        } catch (MalformedURLException ex) {
        }

    }
    public void ImprimeRdv(){
        Rdv r = new Rdv();
        r.pdf();
    }
    public void ImprimeGarage(){
        garage g = new garage();
        g.pdf();
    }
    public void ImprimeService(){
        service s = new service ();
       // s.pdf();
    }
     public void SetBtnService(){
        this.RemoveBtnService.setDisable(false);
    }
    public void SetBtnGarage(){
        this.RemoveBtnGarage.setDisable(false);
    }
    private Serviceentitie selectedUser;
       @FXML
    private void createPDF() {
        try {
            System.out.println("walid------------------------------------->"+selectedUser);
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\2SE\\java\\Service.pdf"));
            } catch (FileNotFoundException ex) {
            }
            document.open();
            com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(1);
            com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("details vehicule"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GREEN);
            table.addCell(cell);
            
            table.addCell("Name:");
            for (int i=0;i<tableViewService.getItems().size();i++){
            table.addCell(tableViewService.getItems().get(i).getName());}
            //  table.addCell("Prenom");
            //    table.addCell(selectedUser.getPrix());
//                table.addCell("Modele");
//               table.addCell(selectedUser.getModele()+"");

//      table.addCell(selectedUser.getQuantiteStock());

//     table.addCell("email");

//table.addCell(selectedUser.getNom());
//System.out.println("*******************"+selectedUser.getNom().toString());

            try {
                document.add(Image.getInstance("C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\logo.png"));
            } catch (BadElementException ex) {
            } catch (IOException ex) {
            }
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
document.add(table);
document.close();
JOptionPane.showMessageDialog(null, " Pdf has been generated ");
        } catch (DocumentException ex) {
        }
            
    }
    
    @FXML
    private void createPDFGarage() {
        try {
            System.out.println("walid------------------------------------->"+selectedUser);
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\2SE\\java\\Garage.pdf"));
            } catch (FileNotFoundException ex) {
            }
            document.open();
            com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
            com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Garage details"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLUE);
            table.addCell(cell);
            com.itextpdf.text.pdf.PdfPCell cell2 = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Name :"));
            //cell2.setColspan(2);
            //cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.YELLOW);
            table.addCell(cell2);
            com.itextpdf.text.pdf.PdfPCell cell3 = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Address :"));
            //cell2.setColspan(2);
            //cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.YELLOW);
            table.addCell(cell3);
//            table.addCell("Name:");
            for (int i=0;i<tableViewGarage.getItems().size();i++){
            PdfPCell cell4;   
            cell4 = new PdfPCell(new Paragraph(tableViewGarage.getItems().get(i).getName()));
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell4);
            PdfPCell cell5;   
            cell5 = new PdfPCell(new Paragraph(tableViewGarage.getItems().get(i).getAddress()));
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell5);
//            table.addCell(tableViewGarage.getItems().get(i).getName());
//            table.addCell(tableViewGarage.getItems().get(i).getAddress());
            }
//            table.addCell("Adress");
//            for (int i=0;i<tableViewGarage.getItems().size();i++){
//            table.addCell(tableViewGarage.getItems().get(i).getAddress());}
            //    table.addCell(selectedUser.getPrix());
//                table.addCell("Modele");
//               table.addCell(selectedUser.getModele()+"");

//      table.addCell(selectedUser.getQuantiteStock());

//     table.addCell("email");

//table.addCell(selectedUser.getNom());
//System.out.println("*******************"+selectedUser.getNom().toString());

            try {
                document.add(Image.getInstance("C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\logo.png"));
            } catch (BadElementException ex) {
            } catch (IOException ex) {
            }
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
document.add(table);
document.close();
JOptionPane.showMessageDialog(null, " Pdf has been generated ");
        } catch (DocumentException ex) {
        }
            
    }
    
    
    @FXML
    private void createPDFRdv() {
        try {
            System.out.println("walid------------------------------------->"+selectedUser);
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\2SE\\java\\Rdv.pdf"));
            } catch (FileNotFoundException ex) {
            }
            document.open();
            com.itextpdf.text.pdf.PdfPTable table = new com.itextpdf.text.pdf.PdfPTable(2);
            com.itextpdf.text.pdf.PdfPCell cell = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Garage details"));
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.BLUE);
            table.addCell(cell);
            com.itextpdf.text.pdf.PdfPCell cell2 = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Date Rdv :"));
            //cell2.setColspan(2);
            //cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(BaseColor.YELLOW);
            table.addCell(cell2);
            com.itextpdf.text.pdf.PdfPCell cell3 = new com.itextpdf.text.pdf.PdfPCell(new Paragraph("Status :"));
            //cell2.setColspan(2);
            //cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(BaseColor.YELLOW);
            table.addCell(cell3);
//            table.addCell("Name:");
            for (int i=0;i<tableView.getItems().size();i++){
            PdfPCell cell4;   
            cell4 = new PdfPCell(new Paragraph(tableView.getItems().get(i).getDate_rdv().toString()));
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell4);
            PdfPCell cell5;   
            cell5 = new PdfPCell(new Paragraph(tableView.getItems().get(i).getStatus()));
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell5);
//            table.addCell(tableViewGarage.getItems().get(i).getName());
//            table.addCell(tableViewGarage.getItems().get(i).getAddress());
            }
//            table.addCell("Adress");
//            for (int i=0;i<tableViewGarage.getItems().size();i++){
//            table.addCell(tableViewGarage.getItems().get(i).getAddress());}
            //    table.addCell(selectedUser.getPrix());
//                table.addCell("Modele");
//               table.addCell(selectedUser.getModele()+"");

//      table.addCell(selectedUser.getQuantiteStock());

//     table.addCell("email");

//table.addCell(selectedUser.getNom());
//System.out.println("*******************"+selectedUser.getNom().toString());

            try {
                document.add(Image.getInstance("C:\\Users\\walid\\OneDrive\\Bureau\\Esprit-3A3\\logo.png"));
            } catch (BadElementException ex) {
            } catch (IOException ex) {
            }
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
document.add(table);
document.close();
JOptionPane.showMessageDialog(null, " Pdf has been generated ");
        } catch (DocumentException ex) {
        }
            
    }
   // private final ObservableList<user> dataList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comb.add("experience");        
        comb.add("nombre de course");
        comb.add("nom");
        ObservableList<String> trib= FXCollections.observableArrayList(comb);
        tribox.setItems(trib);
        Rdv r = new Rdv();
        garage g = new garage();
        r.listService(ServiceCombo);
        g.listService(ServiceComboG);
        this.RemoveBtnGarage.setDisable(true);
        this.RemoveBtnService.setDisable(true);
        this.RemoveBtnRdv.setDisable(true);
//Configure Column For Table Rendez vous
        
        
        id_rdvColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Integer>("id_rdv"));
        id_chauffeurColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Integer>("id_chauffeur"));
        date_rdvColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, Date>("date_rdv"));
        name_garageColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, String>("name_garage"));
        name_serviceColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, String>("name_service"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, String>("status"));
        //Rdv r = new Rdv();
        tableView.setItems(r.displayAll());
        tableView.setEditable(true);
        statusColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//Configure Garage
        
        
        id_garageColumnG.setCellValueFactory(new PropertyValueFactory<Garageentitie, Integer>("id_garage"));
        name_serviceColumnG.setCellValueFactory(new PropertyValueFactory<Garageentitie, String>("name_service"));
        nameColumnG.setCellValueFactory(new PropertyValueFactory<Garageentitie, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Garageentitie, String>("address"));
        //garage g = new garage();
        tableViewGarage.setItems(g.displayAll());
        tableViewGarage.setEditable(true);
        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumnG.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewGarage.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
//Configure Service
        
        
        id_serviceColumnS.setCellValueFactory(new PropertyValueFactory<Serviceentitie, Integer>("id_service"));
        nameColumnS.setCellValueFactory(new PropertyValueFactory<Serviceentitie, String>("name"));
        service s = new service();
        tableViewService.setItems(s.displayAll());
        tableViewService.setEditable(true);
        nameColumnS.setCellFactory(TextFieldTableCell.forTableColumn());
        tableViewService.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    

    @FXML
    private void handleuseradmin(ActionEvent event) {
        userp.toFront();
    }
    servicesuser sru = new servicesuser();
String role = sru.getrole().get(0).getRole();
    @FXML
    private void handleaffichch(MouseEvent event) {
            usertype="chauffeur";
            tablechauff();
            arr= src.afficherAllchauffeur(role);
            ObservableList<user> dataList= FXCollections.observableArrayList(arr);
            tablech.setItems(dataList);
             FilteredList<user> filteredData = new FilteredList<>(dataList, b -> true);
		searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(user.getCin()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
                SortedList<user> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablech.comparatorProperty());
		tablech.setItems(sortedData);
    }

    @FXML
    private void handlefiltreactive(ActionEvent event) {
        i++;
        if(usertype.equals("chauffeur"))
        tablechauff();
        else if(usertype.equals("client"))
        tableclient();
        if(i%2!=0 && j%2==0)
        {
            arr= src.filtrer(usertype,1);
            ObservableList<user> chwafra= FXCollections.observableArrayList(arr);
            tablech.setItems(chwafra);
        }
        else
        {
            arr= src.afficherAllchauffeur(role);
            ObservableList<user> chwafra= FXCollections.observableArrayList(arr);
            tablech.setItems(chwafra);
        }
    }

    @FXML
    private void handlefiltrearchive(ActionEvent event) {
        if(usertype.equals("chauffeur"))
        tablechauff();
        else if(usertype.equals("client"))
        tableclient();
       j++;
        if(j%2!=0 && i%2==0)
        {
            arr= src.filtrer(usertype,0);
            ObservableList<user> chwafra= FXCollections.observableArrayList(arr);
            tablech.setItems(chwafra);
        }
        else
        {
            arr= src.afficherAllchauffeur(usertype);
            ObservableList<user> chwafra= FXCollections.observableArrayList(arr);
            tablech.setItems(chwafra);
        }
    }

    @FXML
    private void handletrierchauff(ActionEvent event) {
        if(usertype.equals("chauffeur"))
        tablechauff();
        else if(usertype.equals("client"))
        tableclient();
        servicesuser sru = new servicesuser();
        String role = sru.getrole().get(0).getRole();
        String choix=tribox.getValue().toString();
        if(choix.equals("experience"))
        {
            listetrie= src.getTrier(role);
            ObservableList<user> obstrie= FXCollections.observableArrayList(listetrie);
            tablech.setItems(obstrie);
        }
        else if(choix.equals("nombre de course"))
        {
            listetrie= src.getTrierByCourse(role);
            ObservableList<user> obstrie= FXCollections.observableArrayList(listetrie);
            tablech.setItems(obstrie);
        }
        else if(choix.equals("nom"))
        {
            listetrie= src.getTrierByNom(usertype);
            ObservableList<user> obstrie= FXCollections.observableArrayList(listetrie);
            tablech.setItems(obstrie);
        }
    }

    @FXML
    private void handlechercherchauff(ActionEvent event) {
    }
    public void tablechauff ()
    {
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            naissance.setCellValueFactory(new PropertyValueFactory<>("naissance"));
            creation.setCellValueFactory(new PropertyValueFactory<>("creation"));
            active.setCellValueFactory(new PropertyValueFactory<>("active"));
            cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            nomc.setCellValueFactory(new PropertyValueFactory<>("nom_compte"));
            ribc.setCellValueFactory(new PropertyValueFactory<>("rib_compte"));
            experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
            nbrcourse.setCellValueFactory(new PropertyValueFactory<>("nb_course"));
    }
     public void tableclient()
    {
            secondnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            secondprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            secondtel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            secondmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
            secondnaissance.setCellValueFactory(new PropertyValueFactory<>("naissance"));
            secondcreation.setCellValueFactory(new PropertyValueFactory<>("creation"));
            secondactive.setCellValueFactory(new PropertyValueFactory<>("active"));
            secondcourse.setCellValueFactory(new PropertyValueFactory<>("nb_course"));
    }

    @FXML
    private void handleaffichcl(MouseEvent event) {
        usertype="client";
            tableclient();
            arr= src.afficherAllchauffeur(role);
            ObservableList<user> dataList= FXCollections.observableArrayList(arr);
            tablech.setItems(dataList);
             FilteredList<user> filteredData = new FilteredList<>(dataList, b -> true);
		searchfield.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(user.getMail()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false;
			});
		});
                SortedList<user> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablecl.comparatorProperty());
		tablecl.setItems(sortedData);
    }

    @FXML
    private void handleindex(ActionEvent event) {
        indexp.toFront();
    }
    @FXML
    private void MaintenanceBtn(ActionEvent event) {
        pane_Maintenance.toFront();
    }
}
