/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Rdventitie;
import entities.user;
import entities.vehicule;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import services.Rdv;
import services.service;
import services.servicesuser;
import services.servicesvehicule;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class NewinterfacechauffeurController implements Initializable {

   @FXML
    private ImageView accueil;

    @FXML
    private ImageView event;

    @FXML
    private ImageView fidelite;

    @FXML
    private ImageView reclamation;

    @FXML
    private ImageView user;
    @FXML
    private ImageView mainten;
    @FXML
    private ImageView backtaxi;
    @FXML
    private ImageView car;
    @FXML
    private ImageView imgconsult;
    @FXML
    private ImageView logout;
    
    @FXML
    private Pane pane_consulter_vehicule;
    @FXML
    private Pane pane_ajouter_vehicule;
   ObservableList<String> couleurlist = FXCollections.observableArrayList("Bleu","Blanc","Rouge","Vert","Noir","Gris","Jaune");
   ObservableList<String> typelist = FXCollections.observableArrayList("Covoiturage","Taxi");
    @FXML
    private TextField txtmat;

    @FXML
    private TextField txtcarte;

    @FXML
    private TextField txtmarque;

    @FXML
    private TextField txtmodele;

    @FXML
    private ComboBox couleur;

    @FXML
    private ComboBox type;

    @FXML
    private RadioButton coli=new RadioButton();
    private vehicule selectedUser;
    @FXML
    private TextField filterField;
    @FXML
    private TableView<vehicule> table;

    @FXML
    private TableColumn<vehicule, String> col_mat;

    @FXML
    private TableColumn<vehicule, String> col_marque;

    @FXML
    private TableColumn<vehicule, String> col_modele;

    @FXML
    private TableColumn<vehicule, String> col_carte;

    @FXML
    private TableColumn<vehicule, String> col_couleur;

    @FXML
    private TableColumn<vehicule, String> col_type;

    @FXML
    private TableColumn<vehicule, String> col_coli;
    private final ObservableList<vehicule> listve = FXCollections.observableArrayList();
    @FXML
    private Pane bgp;
    @FXML
    private Button profilbtn;
    @FXML
    private Label mailfieldc;
    private String nom,prenom,naissance,creation,image,typep,nom_compte;
    private int tel,nbr_course,cin,permis,rib_compte;
    servicesuser sru = new servicesuser();
    List <user> listechauffeur =new ArrayList<>();
    @FXML
    private Button ajouter_vehbtn;
    @FXML
    private Button consulter_vehbtn;
    @FXML
    private Button btn_supp;
    
    
    private Label labelid;
    @FXML
    private DatePicker picker_color;
    
    
    @FXML private ComboBox comboBox_garage ;
    @FXML private ComboBox comboBox_service;
    @FXML private TableView<Rdventitie> tableViewListReserved;
    @FXML private TableColumn<Rdventitie, Date>date_rdvColumnReserved;
    Connection c = ConnexionBD.getInstance().getCnx();
    private Image photo;
    @FXML
    private Pane pane_main;
    @FXML
    private Button btn_main;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        
        Rdv r = new Rdv();
//            labelid.setText("4");
            date_rdvColumnReserved.setCellValueFactory(new PropertyValueFactory<Rdventitie, Date>("date_rdv"));
            tableViewListReserved.setItems(r.displayAllReserved("4"));
            listService();
            picker_color.setDisable(true);
        
        
        
       // bgp.toFront();
        
     
        couleur.setItems(couleurlist);
        type.setItems(typelist);
        afficher_vehicule();
        FilteredList<vehicule> filteredData = new FilteredList<>(listve, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(vehicule -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(vehicule.getMatricule()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                }

                if (vehicule.getAccept_c().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                if (vehicule.getCartegrise().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                if (vehicule.getCouleur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                if (vehicule.getModele().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
                if (vehicule.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (vehicule.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1) {

                    return true; // Filter matches last name.
                } else if (String.valueOf(vehicule.getId_v()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });

            SortedList<vehicule> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(table.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            table.setItems(sortedData);
        });
        table.setEditable(true);
        col_carte.setCellFactory(TextFieldTableCell.forTableColumn());
        
        

    }
    void afficher_vehicule(){
    try {
            Connection c = ConnexionBD.getInstance().getCnx();
            PreparedStatement pt = c.prepareStatement("select * from vehicule");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                listve.add(new vehicule(rs.getString("matricule"), rs.getString("marque"), rs.getString("modele"), rs.getString("cartegrise"), rs.getString("couleur"), rs.getString("type"), rs.getString("accept_c")));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewinterfacechauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_mat.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        col_marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        col_carte.setCellValueFactory(new PropertyValueFactory<>("cartegrise"));
        col_couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_coli.setCellValueFactory(new PropertyValueFactory<>("accept_c"));
        table.setItems(listve);
    }
     @FXML
    public void supprimer_vehicule(ActionEvent event) throws AWTException, MalformedURLException {
        servicesvehicule srv = new servicesvehicule();
        try {
            vehicule selected = table.getSelectionModel().getSelectedItem();
            String matr = selected.getMatricule();
            Connection c = ConnexionBD.getInstance().getCnx();
            
            
            String req = "DELETE FROM `vehicule` WHERE `matricule`=" + matr + "";
            PreparedStatement pt = c.prepareStatement(req);
            pt.executeUpdate();
            table.getItems().clear();
            table.setItems(listve);
            //doryan.windowsnotificationapi.fr.Notification.sendNotification("Module Vehicule", "Status has been updated ", TrayIcon.MessageType.INFO);
        } catch (SQLException ex) {
            Logger.getLogger(NewinterfacechauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        afficher_vehicule();
        
        
        
        

    }

    
    

    @FXML
    void pane_ajouter_vehicule(ActionEvent event) {
        pane_ajouter_vehicule.toFront();
        pane_consulter_vehicule.toBack();
    //  pane_consulter_vehicule.setVisible(false);

    }
        @FXML
    void pane_consulter_vehicule(ActionEvent event) {
        pane_consulter_vehicule.toFront();
        table.getItems().clear();
        afficher_vehicule();
       // pane_consulter_vehicule.managedProperty().bind(pane_consulter_vehicule.visibleProperty());
      //  pane_ajouter_vehicule.setVisible(false);

    }

  @FXML
     void ajouter(ActionEvent event) {
        String matricule = txtmat.getText();
        String carte = txtcarte.getText();
        String marque = txtmarque.getText();
        String modele = txtmodele.getText();
        String couleurr = couleur.getSelectionModel().getSelectedItem().toString();
        String typee = type.getSelectionModel().getSelectedItem().toString();
        String colii;
        if(coli.isSelected())
        {colii="oui";
        }else {colii="non";}
        servicesvehicule srv  = new servicesvehicule();
        vehicule p = new vehicule(matricule,marque,modele,carte,couleurr,typee,0, "-", "-",colii,0);
        srv.ajouter_vehicule(p,7);
         JOptionPane.showMessageDialog(null, " Ajout avec succes ");
    }
    @FXML
         public void changeStatusCellEvent(TableColumn.CellEditEvent edditedCell) {
        vehicule statusSelected = table.getSelectionModel().getSelectedItem();
        statusSelected.setCartegrise(edditedCell.getNewValue().toString());
        vehicule r = new vehicule();
        servicesvehicule srv = new servicesvehicule();
        srv.modifier_vehiculee(statusSelected);
        }

   


  
      void setMail(String email) {
      this.mailfieldc.setText(email);
    }
    @FXML
    private void handleprofilchauf(ActionEvent event) {
                 getprofilchauffeur(mailfieldc.getText());
                 FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("profilchauffeur.fxml"));
            try {
                Parent root = loader.load();
                ProfilchauffeurController pcc = loader.getController();
                pcc.setMail(mailfieldc.getText());
                pcc.setPrenom(nom);
                pcc.setPrenom(prenom);
                pcc.setTel(tel);
                pcc.setNaissance(naissance);
                pcc.setCreation(creation);
                pcc.setNbr_course(nbr_course);
                pcc.setCin(cin);
                pcc.setPermis(permis);
                pcc.setNom_compte(nom_compte);
                pcc.setRib_compte(rib_compte);
               // apc.setpdp(image);
                mailfieldc.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }
     public void getprofilchauffeur (String mail)
    {
            listechauffeur = sru.afficherchauffeur(mail);
        for (int i=0 ; i<listechauffeur.size();i++)
        {
            nom =listechauffeur.get(i).getNom();
            prenom =listechauffeur.get(i).getPrenom();
            tel = listechauffeur.get(i).getTel();
            naissance=listechauffeur.get(i).getNaissance();
            creation=listechauffeur.get(i).getCreation();
            image = listechauffeur.get(i).getImage();
            nbr_course=listechauffeur.get(i).getNb_course();
            cin=listechauffeur.get(i).getCin();
            permis=listechauffeur.get(i).getPermis();
            nom_compte=listechauffeur.get(i).getNom_compte();
            rib_compte=listechauffeur.get(i).getRib_compte();
        }
    }
    //config Maintenance
     
     
        //Rdv r = new Rdv();
    public void DatePickerColor(){
        Rdv r = new Rdv();
        List<LocalDate> holidays = new ArrayList<>();
        List<LocalDate> holidays2 = new ArrayList<>();
        for (int i=0;i<r.displayAllReserved(comboBox_service, comboBox_garage).size();i++){
//            
holidays.add(LocalDate.of(r.displayAllReserved(comboBox_service, comboBox_garage).get(i).getDate_rdv().toLocalDate().getYear(), r.displayAllReserved(comboBox_service, comboBox_garage).get(i).getDate_rdv().toLocalDate().getMonthValue(), r.displayAllReserved(comboBox_service, comboBox_garage).get(i).getDate_rdv().toLocalDate().getDayOfMonth()));}
        for (int i=0;i<r.displayNotReserved(comboBox_service, comboBox_garage).size();i++){           
holidays2.add(LocalDate.of(r.displayNotReserved(comboBox_service, comboBox_garage).get(i).getDate_rdv().toLocalDate().getYear(), r.displayNotReserved(comboBox_service, comboBox_garage).get(i).getDate_rdv().toLocalDate().getMonthValue(), r.displayNotReserved(comboBox_service, comboBox_garage).get(i).getDate_rdv().toLocalDate().getDayOfMonth()));
        }
//holidays.add(r.displayAllReserved().get(i).getDate_rdv());
//holidays.add(LocalDate.of(2020, Month.MARCH, 3));
//holidays.add(LocalDate.of(2020, Month.APRIL, 2));
//holidays.add(LocalDate.of(2020, Month.MAY,10));
//holidays.add(LocalDate.of(2020, Month.MAY,21));
//holidays.add(LocalDate.of(2020, Month.OCTOBER,3));
//holidays.add(LocalDate.of(2020, Month.OCTOBER,31));
//holidays.add(LocalDate.of(2020, Month.NOVEMBER,21));
//holidays.add(LocalDate.of(2020, Month.DECEMBER,25));
//holidays.add(LocalDate.of(2020, Month.DECEMBER,26));


//DatePicker datePicker = new DatePicker();

picker_color.setDayCellFactory(new Callback<DatePicker, DateCell>() {
	@Override
	public DateCell call(DatePicker param) {
		return new DateCell(){
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				if (!empty && item != null) {
					if(holidays.contains(item)) {
						this.setStyle("-fx-background-color: green");
                                                
					}
                                        if(holidays2.contains(item)) {
						this.setStyle("-fx-background-color: red");
                                                this.setDisable(true);
					}
				}
			}
		};
	}
});
    }
    
    
    @FXML
     public void choiceBoxButtonPushed()
    {
         Rdv r=new Rdv();
         r.edit_rdv(Date.valueOf(picker_color.getValue()),Integer.valueOf(labelid.getText()));
         tableViewListReserved.getItems().clear();
         tableViewListReserved.setItems(r.displayAllReserved(labelid.getText()));
         SELECTEDcomboBoxGarage();
         SendMail_Maintenance.sendMail("benothmanwalid1@gmail.com","Rendez vous","Vous avez appris un rendez vous chez notre departement maintenance");
         
         
       
        try {
            Notification.sendNotification("Module Maintenance", "Rendez vous has been aafected ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(ChauffeurinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChauffeurinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
         
    }
    @FXML
  public void SELECTEDcomboBoxService()
    {
       service s = new service();
       s.ListeSelectedService(comboBox_service, comboBox_garage);
    }
    @FXML
    public void SELECTEDcomboBoxGarage()
    {
         picker_color.setDisable(false);
         DatePickerColor();
         
        
    }
   
    @FXML
    public void cancelRdv()
    {
            Rdv r = new Rdv();
            
            r.annule_rdv(tableViewListReserved.getSelectionModel().getSelectedItem().getDate_rdv().toString());
         
            tableViewListReserved.getItems().clear();
            tableViewListReserved.setItems(r.displayAllReserved(labelid.getText()));

            //SELECTEDcomboBoxGarage();
            
        
        try {
            Notification.sendNotification("Module Maintenance", "Rendez vous has been canceled ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(ChauffeurinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ChauffeurinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
          
    }
    
    
    
    
    public void listService(){
        service s = new service();
        s.ListeService(comboBox_service);
            }
    
    /**
     * Initializes the controller class.
     */
  


    private void mm(ActionEvent event) {
           try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Interface_back.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            //Interface_main.;
    } catch(Exception e) {
       e.printStackTrace();
      }
 }

    @FXML
    private void btn_event(ActionEvent event) {
    }

    @FXML
    private void btn_fidelite(ActionEvent event) {
    }

    @FXML
    private void btn_main(ActionEvent event) {
        pane_main.toFront();
    }
}
