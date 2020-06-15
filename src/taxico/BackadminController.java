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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Garageentitie;
import entities.Rdventitie;
import entities.Reclamation;
import entities.Serviceentitie;
import entities.colis;
import entities.contrat;
import entities.event;
import entities.fidelite;
import entities.user;
import entities.vehicule;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.AdminCRUD;
import services.Rdv;
import services.event_services;
import services.fidelite_services;
import services.garage;
import services.service;
import services.service_contrat;
import services.servicescolis;
import services.servicesuser;
import services.servicesvehicule;
import static taxico.AdminuserController.Characters;
import static taxico.AdminuserController.Numbers;
import utils.ConnexionBD;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BackadminController implements Initializable {
    servicesuser src =new servicesuser();
     servicescolis sc =new servicescolis();
     servicesvehicule sv =new servicesvehicule();
     List<user> arr=new ArrayList<>();    
     List<user> listetrie=new ArrayList<>();
     List<String> comb=new ArrayList<>();
     List<String> combcol=new ArrayList<>();
     List<colis> listecol=new ArrayList<>();
     List<String> Matriculet=new ArrayList<>();
     List<vehicule> vehcol=new ArrayList<>();
     event_services ev =new event_services();
    List<String> arrd= ev.affichagecombo();
    List<String> liste= ev.affichagecombo_fidelite();
    List<user> liste1= new ArrayList<>();   
    List<event> listeevent= new ArrayList<>();
    List<event> listetri= new ArrayList<>();    
    List<String> elcinet= new ArrayList<>();
    ObservableList<contrat> listc = FXCollections.observableArrayList();
    List <contrat> listcc=new ArrayList<>();
    ObservableList<user> fid =FXCollections.observableArrayList();
    ObservableList<String> cobo = FXCollections.observableArrayList(arrd);
    ObservableList<String> combo_fidelite = FXCollections.observableArrayList(liste);
    String depchoix,destchoix;
    static public final String CharactersNumbers = "[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890èéòàùì ]";
    static public final String Characters = "[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùì ]";
    static public final String Numbers = "[1234567890]";
    int i=0;
    int j=0;
    int k=0;
    int h=0;
    String usertype="";
    int idveh,idcoliselect,idvselect;
    @FXML
    private Pane pnl_stat;
    String nom1,emplacement;
    @FXML
    TextField duree;
    int capacite;
    Date date;
    event_services sre = new event_services();
    
     List<String> combov= new ArrayList<>();
    @FXML
     private TableColumn<Reclamation, String> mat;
     ObservableList<String> stateEtat = FXCollections.observableArrayList("Non traitée","En cours de traitement","Traitée","Archivée");
    ObservableList<Reclamation> oblist1 = FXCollections.observableArrayList();
    ObservableList<Reclamation> oblist2 = FXCollections.observableArrayList();
    ObservableList<Reclamation> oblist3 = FXCollections.observableArrayList();   
    ObservableList<String> typeclist = FXCollections.observableArrayList("CDD","CDI");

    AdminCRUD acr = new AdminCRUD();
    Reclamation r = new Reclamation();
    @FXML
    private Pane consulter_contrat_pane;
    @FXML
    private TableView<contrat> tablec;
    @FXML
    private TableColumn<contrat, String> col_nom, col_prenom, col_cin,col_type,col_duree;
    @FXML
    private Button supprimer;
    @FXML
    private ImageView background;
    @FXML
    private ImageView contrat;
    @FXML
    private ImageView maintenance;
    @FXML
    private ImageView reclamation;
    @FXML
    private ImageView eventback;
    @FXML
    private ImageView admin;
    @FXML
    private Button btncontrat;
    @FXML
    private ImageView logout;
    @FXML
    private Pane ajouter_contrat_pane;
    private ComboBox<String> combo1;
    @FXML
    private Button btn_val;
    @FXML
    private ComboBox<String> type_c;
    private TextField duree1;
    @FXML
    private ImageView userback;
    @FXML
    private ImageView commande;
    @FXML
    private ImageView fidelite_back;
    @FXML
    private Label lbl_etat;
    @FXML
    private ComboBox comboEtat;
    @FXML
    private TextArea txt_repAdmin;
    @FXML
    private Button DelAdmin_btn;
    @FXML
    private Button editAdmin_btn;
    @FXML
    private Button saveAdmin_btn;
    @FXML
    private Button mail_btn;
    @FXML
    private Button btn_archive;
    @FXML
    private Button imp_btn;
    @FXML
    private TableView<Reclamation> tab_admin;
    @FXML
    private TableColumn<Reclamation, Integer> id_admin;
    @FXML
    private TableColumn<Reclamation, String> msg_admin,etat_admin,date_admin,rep_admin;
    @FXML
    private Pane archivep;
    @FXML
    private TextField txt_arch;
    @FXML
    private TableView<Reclamation> tab_arch;
    @FXML
    private TableColumn<Reclamation, Integer> id_arch;
    @FXML
    private TableColumn<Reclamation, String> msg_arch,etat_arch,date_arch,rep_arch,pre_arch;
    @FXML
    private Button btn_arch;
    @FXML
    private Button stat_event1;
    @FXML
    private Pane pnl_fidelite;
    @FXML
    private TableView<user> tab_fidelite;
    @FXML
    private TableColumn<user, String> nom_client;
    @FXML
    private TableColumn<user, Integer> point_fidelite;
    @FXML
    private TextField txt_points;
    @FXML
    private Button btn_points;
    @FXML
    private Label nbr_inv;
    @FXML
    private Pane pnl_event;
    @FXML
    private TextField txt_nom;
    @FXML
    private DatePicker txt_date;
    @FXML
    private TextField dureejdida;
    @FXML
    private Button btn_add;
    @FXML
    private TableView<event> table;
    @FXML
    private TableColumn<event,String> col_nomev;
    @FXML
    private TableColumn<event,Date> col_date;
    @FXML
    private TableColumn<event,Integer> col_dureeev;
    @FXML
    private TableColumn<event,String> col_emplacement;
    @FXML
    private TableColumn<event,Integer> col_capacite;
    @FXML
    private ComboBox<String> combo_modif;
    @FXML
    private Button event_delete;
    @FXML
    private Button event_modifier;
    @FXML
    private JFXButton btn_modif;
    @FXML
    private TextField txt_emplacement;
    @FXML
    private TextField txt_capacite;
    @FXML
    private TextField txt_recherche;
    @FXML
    private CheckBox tri_chk;
    @FXML
    private Pane colisadminp;
    @FXML
    private TitledPane listecl1;
   @FXML
    private TableView<colis> tablecnaf;
    @FXML
    private TableColumn<colis, String> nafdepart,nafdestination;
    @FXML
    private TableColumn<colis, Float> nafpoids;
    @FXML
    private TableView<vehicule> tablevaf;
    @FXML
    private TableColumn<vehicule, String> nafmatricule, vnafdep,vnafdest;
    @FXML
    private TitledPane listecolis;
    @FXML
    private TableView<colis> tablecol;
    @FXML
    private TableColumn<colis, String> col1;
    @FXML
    private TableColumn<colis, String> col2;
    @FXML
    private TableColumn<colis, String> col3;
    @FXML
    private TableColumn<colis, String> col4;
    @FXML
    private TableColumn<colis, String> col5;
    @FXML
    private TableColumn<colis, String> col6;
    @FXML
    private TableColumn<colis, Float> col7;
    @FXML
    private TableColumn<colis, Integer> col8;
    @FXML
    private TableColumn<colis, String> col9;
    @FXML
    private ComboBox<String> tribox1;
    @FXML
    private TextField searchfield1;
    @FXML
    private CheckBox nonafc;
    @FXML
    private CheckBox nonlivre;
    @FXML
    private Pane userp;
    @FXML
    private TitledPane listecl;
    @FXML
    private TableView<user> tablecl;
    @FXML
    private TableColumn<user,String> secondnom,secondprenom;
    @FXML
    private TableColumn<user,Integer> secondtel;
    @FXML
    private TableColumn<user,String> secondmail;
    @FXML
    private TableColumn<user,String> secondnaissance;
    @FXML
    private TableColumn<user,String> secondcreation;
    @FXML
    private TableColumn<user,Integer> secondactive;
    @FXML
    private TableColumn<user,Integer> secondcourse;
    @FXML
    private TitledPane listech;
    @FXML
    private TableView<user> tablech;
    @FXML
    private TableColumn<user, String> nom;
    @FXML
    private TableColumn<user, String> prenom;
    @FXML
    private TableColumn<user,Integer> tel;
    @FXML
    private TableColumn<user, String> mail;
    @FXML
    private TableColumn<user, String>naissance;
    @FXML
    private TableColumn<user, String> creation;
    @FXML
    private TableColumn<user,Integer> active;
    @FXML
    private TableColumn<user,Integer> cin;
    @FXML
    private TableColumn<user, String>nomc;
    @FXML
    private TableColumn<user,Integer> ribc;
    @FXML
    private TableColumn<user,Integer> experience;
    @FXML
    private TableColumn<user,Integer> nbrcourse;
    @FXML
    private ComboBox<String> tribox;
    @FXML
    private TextField searchfield;
    @FXML
    private CheckBox checkifactive;
    @FXML
    private CheckBox checkifarchive;
    @FXML
    private Pane Reclamationp;
    @FXML
    private Label lbl_etat1;
    @FXML
    private ComboBox<String> comboEtat1;
    @FXML
    private TextArea txt_repAdmin1;
    @FXML
    private Button DelAdmin_btn1;
    @FXML
    private Button editAdmin_btn1;
    @FXML
    private Button saveAdmin_btn1;
    @FXML
    private Button mail_btn1;
    @FXML
    private Button btn_archive1;
    @FXML
    private Button imp_btn1;
    @FXML
    private TableView<Reclamation> tab_admin1;
    @FXML
    private TableColumn<Reclamation,String> id_admin1;
    @FXML
    private TableColumn<Reclamation,String> msg_admin1;
    @FXML
    private TableColumn<Reclamation,String> etat_admin1;
    @FXML
    private TableColumn<Reclamation,String> date_admin1;
    @FXML
    private TableColumn<Reclamation,String> rep_admin1;
    @FXML
    private TableColumn<Reclamation,String> mat1;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private Pane pane_Maintenance;
   
    
    //Configure table view
    //Configure Rendez vous
    @FXML private TableView<Rdventitie> tableView;
    @FXML private TableColumn<Rdventitie, Integer>id_rdvColumn;
    @FXML private TableColumn<Rdventitie, Integer>id_chauffeurColumn;
    @FXML private TableColumn<Rdventitie, java.sql.Date>date_rdvColumn;
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
    @FXML
    private Button btnrec;
    @FXML
    private Pane bgp;
    @FXML
    private ImageView fidelite_back1;
    //private Object com;
    @FXML
    public void SetBtnRdv(){
        this.RemoveBtnRdv.setDisable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bgp.toFront();
        type_c.getItems().addAll(typeclist);
        type_c.setVisible(false);
        afficherTableNormale();
        afficherTableArchive();
//        duree1.setVisible(false);
          comb.add("experience");        
        comb.add("nombre de course");
        comb.add("nom");
        ObservableList<String> trib= FXCollections.observableArrayList(comb);
        tribox.setItems(trib);
        combcol.add("etat");        
        combcol.add("poids");
        ObservableList<String> tric= FXCollections.observableArrayList(combcol);
        tribox1.setItems(tric);
        tablevaf.setVisible(false);
        fidelite_services sr=new fidelite_services();
        event_services ev=new event_services();
        String nbr;nbr = String.valueOf(sr.afficher_nbr_email());
        nbr_inv.setText(nbr);
        update_event();
        combov=combo_modif();
        ObservableList<String> comboliste = FXCollections.observableArrayList(combov);
        combo_modif.setItems(comboliste);
        String nom=txt_recherche.getText();
        update_recherche( nom);
        refresh2();
        combo_modif();
        update_fidelite();

        try {

            Connection c = ConnexionBD.getInstance().getCnx();
            PreparedStatement pt = c.prepareStatement("select cin from user");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                elcinet.add(String.valueOf(rs.getInt(1)));
                 //  combo1.getItems().add(listcin);
            }
            ObservableList listcin = FXCollections.observableArrayList(elcinet);
           combo.setItems(listcin);
            
        } catch (SQLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablec.setEditable(true);
        col_type.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        
        //Initilize For Maintenance
        
        
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
        date_rdvColumn.setCellValueFactory(new PropertyValueFactory<Rdventitie, java.sql.Date>("date_rdv"));
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
    void valider(ActionEvent event) {
        type_c.setVisible(true);
        duree.setVisible(true);

    }

    @FXML
    void ajouter_contrat(ActionEvent event) {
        String typec = type_c.getSelectionModel().getSelectedItem().toString();

        int dur = Integer.parseInt(duree.getText());
        service_contrat srv = new service_contrat();
        contrat c = new contrat(typec, dur);
        int cinn = Integer.valueOf(combo.getValue().toString());
//          if (typec == "CDD") {
//            duree.setDisable(false);
//
//        } else if (typec == "CDI") {
//            duree.setDisable(true);
//        }
        srv.ajoutercontrat(c, cinn);
      
         //tablec.getItems().clear();
        // tablec.setItems(listc);
         
        //afficher_contrat();
        
        JOptionPane.showMessageDialog(null, " Ajout du contrat avec succes ");

        
    }

    @FXML
    void pane_ajouter_contrat(ActionEvent event) {
        bgp.toFront();
        ajouter_contrat_pane.toFront();
        afficher_contrat();
        //  pane_consulter_vehicule.setVisible(false);

    }
        @FXML
    void consulter_contrat_pane(ActionEvent event) {
        bgp.toFront();
        consulter_contrat_pane.toFront();
        tablec.getItems().clear();
        afficher_contrat();
        //tablec.getItems().clear();
        //tablec.setItems(listc);
       // refresh();
        //  pane_consulter_vehicule.setVisible(false);

    }
    @FXML
       void consulter_contrat_paneBack(ActionEvent event) {
        consulter_contrat_pane.toBack();
        //tablec.getItems().clear();
        //  pane_consulter_vehicule.setVisible(false);

    }

    void afficher_contrat() {
        Connection c = ConnexionBD.getInstance().getCnx();
        try {

            PreparedStatement pt = c.prepareStatement("SELECT user.nom,user.prenom,user.cin,contrat.type_c,contrat.duree FROM user,contrat where id_contrat=id_u");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("user.nom");
                //System.out.println(nom);
                String prenom = rs.getString("user.prenom");
                int cinn = rs.getInt("user.cin");
                listc.add(new contrat(nom, prenom, cinn, rs.getString("contrat.type_c"), rs.getInt("contrat.duree")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("Type_c"));
        col_duree.setCellValueFactory(new PropertyValueFactory<>("Duree"));
        
        tablec.setItems(listc);
    }
      @FXML
    public void supprimer_contrat(ActionEvent event) throws SQLException {
        contrat selected = tablec.getSelectionModel().getSelectedItem();
        int dur = selected.getDuree();
        Connection c = ConnexionBD.getInstance().getCnx();
        service_contrat srv = new service_contrat();
    
            String req = "DELETE FROM `contrat` WHERE `duree`=" + dur + "";
            PreparedStatement pt = c.prepareStatement(req);
            pt.executeUpdate();
           tablec.getItems().clear();
            //afficher_contrat();
           tablec.setItems(listc);
            JOptionPane.showMessageDialog(null, " Suppression avec succes ");
         //tablec.setItems(listc);
         afficher_contrat();

    }
    
    public void changeStatusCellEvent(TableColumn.CellEditEvent edditedCell) {
        contrat statusSelected = tablec.getSelectionModel().getSelectedItem();
        statusSelected.setType_c(edditedCell.getNewValue().toString());
        vehicule r = new vehicule();
        service_contrat srv = new service_contrat();
        srv.modifier_contratt(statusSelected);


    }


     

    private void handleuseradmin(ActionEvent event) {
        bgp.toFront();
        userp.toFront();
    }

    @FXML
    private void handleaffichch(MouseEvent event) {
            usertype="chauffeur";
            tablechauff();
            arr= src.afficherAllchauffeur(usertype);
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
            arr= src.afficherAllchauffeur(usertype);
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
        String choix=tribox.getValue().toString();
        if(choix.equals("experience"))
        {
            listetrie= src.getTrier(usertype);
            ObservableList<user> obstrie= FXCollections.observableArrayList(listetrie);
            tablech.setItems(obstrie);
        }
        else if(choix.equals("nombre de course"))
        {
            listetrie= src.getTrierByCourse(usertype);
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
    public void afficherlisteevent (String nomp)
    {
        listeevent=sre.modifierevent(nomp);
        for(int i=0;i<listeevent.size();i++)
        {
            nom1=listeevent.get(i).getNom();
            
            date=listeevent.get(i).getDate();
            String dodo =String.valueOf(listeevent.get(i).getDuree());
            duree.setText(dodo);
            emplacement=listeevent.get(i).getEmplacement();
            capacite=listeevent.get(i).getCapacite();
            
        }
    }
    public List<String> combo_modif()
    {    
        combov=sre.affichernomevent();
        for(int i=0;i<listeevent.size();i++)
        {
            nom1=listeevent.get(i).getNom();
            combov.add(nom1);
            
        } 
        return combov;
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
       public void tablecolis()
    {
            col1.setCellValueFactory(new PropertyValueFactory<>("depart"));
            col2.setCellValueFactory(new PropertyValueFactory<>("destination"));
            col3.setCellValueFactory(new PropertyValueFactory<>("nom_expediteur"));
            col4.setCellValueFactory(new PropertyValueFactory<>("nom_destinataire"));
            col5.setCellValueFactory(new PropertyValueFactory<>("mail_expediteur"));
            col6.setCellValueFactory(new PropertyValueFactory<>("mail_destinataire"));
            col7.setCellValueFactory(new PropertyValueFactory<>("poids"));
            col8.setCellValueFactory(new PropertyValueFactory<>("etat"));
            col9.setCellValueFactory(new PropertyValueFactory<>("id_karhba"));            
    }

    @FXML
    private void handleaffichcl(MouseEvent event) {
        usertype="client";
            tableclient();
            arr= src.afficherAllchauffeur(usertype);
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
   
    private void handleindex(ActionEvent event) {
      //  indexp.toFront();
    }

    private void handlecolisadmin(ActionEvent event) {
        colisadminp.toFront();
        tablecolis();
         listecol=sc.affichAllcolis();
            ObservableList<colis> coliet= FXCollections.observableArrayList(listecol);
            tablecol.setItems(coliet);
             FilteredList<colis> filteredData = new FilteredList<>(coliet, b -> true);
		searchfield1.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (user.getDepart().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (user.getDestination().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false;
			});
		});
                SortedList<colis> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tablecol.comparatorProperty());
		tablecol.setItems(sortedData);

    }
   
    @FXML
    private void handletriercolis(ActionEvent event) {
         
        tablecolis();
        String choix=tribox1.getValue().toString();
        if(choix.equals("etat"))
        {
            listecol= sc.getTrier();
            ObservableList<colis> trieet= FXCollections.observableArrayList(listecol);
            tablecol.setItems(trieet);
        }
        else if(choix.equals("poids"))
        {
            listecol= sc.getTrierbyp();
            ObservableList<colis> trieet= FXCollections.observableArrayList(listecol);
            tablecol.setItems(trieet);
        }
    }


    @FXML
    private void handlefiltreaffecte(ActionEvent event) {
          k++;
        tablecolis();
        if(k%2!=0 && h%2==0)
        {
            listecol= sc.affichcolisNAF(0);
            ObservableList<colis> aff= FXCollections.observableArrayList(listecol);
            tablecol.setItems(aff);
        }
        else
        {
            listecol= sc.affichAllcolis();
            ObservableList<colis> aff= FXCollections.observableArrayList(listecol);
            tablecol.setItems(aff);
        }
    }

    @FXML
    private void handlefiltrelivre(ActionEvent event) {
          h++;
        tablecolis();
        if(h%2!=0 && k%2==0)
        {
            listecol= sc.affichcolisNAF(2);
            ObservableList<colis> aff= FXCollections.observableArrayList(listecol);
            tablecol.setItems(aff);
        }
        else
        {
            listecol= sc.affichAllcolis();
            ObservableList<colis> aff= FXCollections.observableArrayList(listecol);
            tablecol.setItems(aff);
        }
    }

    @FXML
    private void handleaffectercolis(MouseEvent event) {
            nafdepart.setCellValueFactory(new PropertyValueFactory<>("depart"));
            nafdestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
            nafpoids.setCellValueFactory(new PropertyValueFactory<>("poids"));
            listecol= sc.affichcolisNAF(0);
            ObservableList<colis> aff= FXCollections.observableArrayList(listecol);
            tablecnaf.setItems(aff);
    }

    @FXML
    private void handlechoicolis(ActionEvent event) {
        tablevaf.setVisible(true);
        colis col = tablecnaf.getSelectionModel().getSelectedItem();
        idcoliselect=col.getId_c();
        System.out.println(idcoliselect);
          nafmatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            vnafdep.setCellValueFactory(new PropertyValueFactory<>("position"));
            vnafdest.setCellValueFactory(new PropertyValueFactory<>("destination"));
            getdepdest (idcoliselect);
            vehcol=sv.vehcolis(depchoix,destchoix);
            System.out.println(vehcol);
            ObservableList<vehicule> kar= FXCollections.observableArrayList(vehcol);
            tablevaf.setItems(kar);
    }

    void getdepdest (int idcoliselect)
    {
        listecol=sc.affichercolis(idcoliselect);
        for (int l = 0; l < listecol.size(); l++) {
           depchoix=listecol.get(l).getDepart();
           destchoix=listecol.get(l).getDestination();
        }
    }
    @FXML
    private void handleaffcv(ActionEvent event) {
        vehicule veh = tablevaf.getSelectionModel().getSelectedItem();
        idvselect=veh.getId_v();
        System.out.println(idvselect);
        sc.affecter(idcoliselect, idvselect);
        sc.modifieretat(1, idcoliselect);
        listecolis.toFront();
    }

    @FXML
    private void btn_event(ActionEvent event) {
        pnl_event.toFront();
    }


    @FXML
    private void event_delete(ActionEvent event) {
        String nom=combo_modif.getValue().toString();
    event_services e=new event_services();
    String title ="Supprimer evenement";
        String head="etes vous sure de  vouloir supprimer ? ☺ ";
        String fina="suppression avec succées";
//        verif(title,head,fina);
    e.delete(nom);
    update_event();
    }

    @FXML
    private void event_modifier(ActionEvent event) {
         combo_modif();
            String nomp=combo_modif.getValue().toString();
            afficherlisteevent (nomp);
                txt_nom.setText(nom1);
                String duree_ev = duree.getText();
                dureejdida.setText(duree_ev);
                String pattern = "yyyy-MM-dd";
                DateFormat df = new SimpleDateFormat(pattern);
                String dateAsString = df.format(date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateAsString, formatter);
                txt_date.setValue(localDate);
                String cap=Integer.toString(capacite);
                txt_capacite.setText(cap);
                txt_emplacement.setText(emplacement);
    }

    @FXML
    private void modif_event(ActionEvent event) {
        String dur=dureejdida.getText();
        int duree=Integer.parseInt(dur);
        String nomp=combo_modif.getValue().toString();
        String nom=txt_nom.getText();
       LocalDate date=txt_date.getValue();
       String emplacement=txt_emplacement.getText();
       String cap=txt_capacite.getText();
       int capacite=Integer.parseInt(cap);
       java.util.Date datejdida = java.sql.Date.valueOf(date);
       event_services s=new event_services();
       String title ="Modification d'une evennement";
       String head="etes vous sure de  vouloir modifier cette evennement ☺ ";
        String fina="evennement modifiée avec succées";
//        verif(title,head,fina);
       s.update(nomp, nom, datejdida, duree, capacite, emplacement);
       txt_capacite.clear();
       dureejdida.clear();
       txt_emplacement.clear();
       txt_nom.clear();
       refresh();
    }

    @FXML
    private void controle(KeyEvent event) {
        boolean etat1,etat2,etat3,etat4;
       String duree,capacite;
       duree=dureejdida.getText();
       capacite=txt_capacite.getText();
       String nom= txt_nom.getText();
       String emplacement=txt_emplacement.getText();
       etat2=cont(emplacement);
       etat1=cont(nom);
       etat3=cont_int(duree);
       etat4=cont_int(capacite);
       if(etat1==true){
           txt_nom.setStyle("-fx-text-inner-color: green");
       }  
       else if(etat1==false){
           txt_nom.setStyle("-fx-text-inner-color: red");
       }
       if(etat2==false){
           txt_emplacement.setStyle("-fx-text-inner-color: red");
       }
       else if(etat2==true){
           txt_emplacement.setStyle("-fx-text-inner-color: green");
       }
       if(etat3==true){
           dureejdida.setStyle("-fx-text-inner-color: green");
       }
       else if(etat3==false){
           dureejdida.setStyle("-fx-text-inner-color: red");
       }
        if(etat4==true){
           txt_capacite.setStyle("-fx-text-inner-color: green");
       }
       else if(etat4==false){
           txt_capacite.setStyle("-fx-text-inner-color: red");
       }
    }

    @FXML
    private void btn_recherche(KeyEvent event) {
    }

    @FXML
    private void btn_rech(KeyEvent event) {
         refresh_update();
    }

    @FXML
    private void tri_chk(ActionEvent event) {
        event_services ev=new event_services();
        listetri=ev.tri();
        System.out.println(listetri);
            col_nomev.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_dureeev.setCellValueFactory(new PropertyValueFactory<>("duree"));
            col_emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
            col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            //table.getItems().clear();
            
    ObservableList<event> listtri = FXCollections.observableArrayList(listetri);
            table.setItems(listtri);
    }

    private void btn_fidelite(ActionEvent event) {
        pnl_fidelite.toFront();
    }


    @FXML
    private void btn_stat(ActionEvent event) {
        pie_chart_1 p=new pie_chart_1();
        Stage s=new Stage();
        p.start(s);
    }

    @FXML
    private void btn_points(ActionEvent event) {
        String pt=txt_points.getText();
        int pts=Integer.parseInt(pt);
        fidelite_services f=new fidelite_services();
        String title ="Ajout points fidelite";
        String head="etes vous sure de  vouloir ajouter ces points ? ☺ ";
        String fina="points ajoutees avec succées";
        //verif(title,head,fina);
        f.affecter_pt(pts);
        refresh2();
        txt_points.clear();
    }
    public void update_event()
    {       col_nomev.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_dureeev.setCellValueFactory(new PropertyValueFactory<>("duree"));
            col_emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
            col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            listeevent=sre.updateevent ();
            ObservableList<event> eve =FXCollections.observableArrayList(listeevent);
            table.setItems(eve);
     
    }
    public void update_fidelite(){
            
            liste1=ev.getTrier();
            ObservableList<user> client2 = FXCollections.observableArrayList(liste1);
            nom_client.setCellValueFactory(new PropertyValueFactory<>("nom"));
            point_fidelite.setCellValueFactory(new PropertyValueFactory<>("point_fidelite"));
            tab_fidelite.setItems(client2);
    }
       public void update_recherche(String nom)
    {
             col_nomev.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_dureeev.setCellValueFactory(new PropertyValueFactory<>("duree"));
            col_emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
            col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            Connection conn = ConnexionBD.getInstance().getCnx();
            listeevent= sre.chercherevent (nom);
             ObservableList<event> eve =FXCollections.observableArrayList(listeevent);
            table.setItems(eve);
        
       
    }
       public void refresh(){
    for ( int i = 0; i<table.getItems().size(); i++) {
    table.getItems().clear();
    update_event();
}
}
public void refresh_update(){
    for ( int i = 0; i<table.getItems().size(); i++) {
    table.getItems().clear();
    String nom=txt_recherche.getText();
        update_recherche(nom);
}
}
public void refresh2(){
    for ( int i = 0; i<tab_fidelite.getItems().size(); i++) {
    tab_fidelite.getItems().clear();
    update_fidelite();
}
}
public void verif(String title,String head,String fina) {

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                        alert.setTitle(title);
                        alert.setHeaderText(head);
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.get() == ButtonType.OK) {

                            
                            
                            
                            Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                            alertSec.setHeaderText(fina);
                            alertSec.showAndWait();

                        }
 }
public boolean cont(String txt){
    boolean res=false;
    if(txt.length()==0){
            return false;
        }
    else{
    for(int i=0;i<Characters.length();i++){
        for(int j=0;j<txt.length();j++){
            if(txt.matches(Characters)){
                return true;
            }
        }
    }
    }
    return res;
}
public boolean cont_int(String txt){
    boolean res=false;
    if(txt.length()==0){
            return false;
        }
    else{
    for(int i=0;i<Numbers.length();i++){
        for(int j=0;j<txt.length();j++){
            if(txt.matches(Numbers)){
                return true;
            }
        }
    }}
    return res;
}

    @FXML
    private void btn_add(ActionEvent event) {
        String nom=txt_nom.getText();
       LocalDate date=txt_date.getValue();
       String dur=dureejdida.getText();
       int duree=Integer.parseInt(dur);
       String emplacement=txt_emplacement.getText();
       String cap=txt_capacite.getText();
       int c=Integer.parseInt(cap);
       java.util.Date date1 = java.sql.Date.valueOf(date);
       event_services srv=new event_services();
       srv.create(nom,date1,duree,emplacement,c);
       update_event();
       txt_capacite.clear();
       dureejdida.clear();
       txt_emplacement.clear();
       txt_nom.clear();
       refresh();
    }

    @FXML
    private void stat_event1(ActionEvent event) {
           pie_chart_1 p=new pie_chart_1();
        Stage s = new Stage();
        p.start(s);
    }
  @FXML
    public void btn_convertirPDF(){
         acr.convertirPDF();
        }
    
    private void setCellTableNormale(){
        id_admin1.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        msg_admin1.setCellValueFactory(new PropertyValueFactory<>("message"));
        etat_admin1.setCellValueFactory(new PropertyValueFactory<>("etat"));        
        date_admin1.setCellValueFactory(new PropertyValueFactory<>("date_rec"));        
        rep_admin1.setCellValueFactory(new PropertyValueFactory<>("reponse")); 
        mat1.setCellValueFactory(new PropertyValueFactory<>("prename")); 
    }
    
    private void setCellTableArchive(){
        id_arch.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        msg_arch.setCellValueFactory(new PropertyValueFactory<>("message"));
        etat_arch.setCellValueFactory(new PropertyValueFactory<>("etat"));        
        date_arch.setCellValueFactory(new PropertyValueFactory<>("date_rec"));        
        rep_arch.setCellValueFactory(new PropertyValueFactory<>("reponse")); 
        pre_arch.setCellValueFactory(new PropertyValueFactory<>("prename"));
    }
       public void findPerson(){
        FilteredList<Reclamation> filteredData = new FilteredList<>(oblist3, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txt_arch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getPrename().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getDate_rec().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tab_arch.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tab_arch.setItems(sortedData);
    } 
    public void afficherTableNormale(){
        acr.afficherReclamationAdmin(oblist1);
        setCellTableNormale();
        tab_admin1.setItems(oblist1);
        comboEtat1.setItems(stateEtat);
        editAdmin_btn.setDisable(true);
    }
    
    public void afficherTableArchive(){
       acr.afficherArchiveReclamationAdmin(oblist3);
        setCellTableArchive();
        tab_admin.setItems(oblist3);
        findPerson();
    }
  
    public void btn_afficherReclamation(){
        acr.afficherReclamationAdmin(oblist1);
    }
    
    public void AfficherArchive(){
        acr.afficherArchiveReclamationAdmin(oblist3);
    }
    
    @FXML
    private void handleadminrec(ActionEvent event) {
        Reclamationp.toFront();
        afficherTableNormale();
    }
    @FXML
    private void handleAdmin(ActionEvent event) {
        bgp.toFront();
        Reclamationp.toFront();
        afficherTableNormale();
    }
   
    @FXML
    private void btn_deleteRecAdmin(ActionEvent event) {
           Reclamation selected = tab_admin1.getSelectionModel().getSelectedItem();
            int idRec = selected.getId_reclamation();
            if(selected==null){
                Alert dialogE = new Alert(Alert.AlertType.ERROR);
                dialogE.setTitle("Fenêtre d'erreur");
                dialogE.setHeaderText("Erreur");
                dialogE.setContentText("Veuillez séléctionner une réclamation à partir de la table !");
                dialogE.showAndWait();
            }else{
/*                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("Confirmation de la suppression");
                dialogC.setHeaderText(null);
                dialogC.setContentText("Voulez-vous supprimer la réclamation séléctionée ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) {*/
                    acr.supprimerReclamationAdmin(idRec);
                    tab_admin1.getItems().clear();
                    acr.afficherReclamationAdmin(oblist1);
                    setCellTableNormale();
                    tab_admin1.setItems(oblist1);
                    //System.out.println("User chose OK");
            /*}
            else {
                 System.out.println("User chose Cancel or closed the dialog-box");
        }*/
            }
    }

    @FXML
    private void editerRepAdmin(ActionEvent event) {
        Reclamation selected = tab_admin1.getSelectionModel().getSelectedItem();
        txt_repAdmin1.setText(selected.getReponse());  
    }

    @FXML
    private void btn_saveEditAdmin(ActionEvent event) {
         if(txt_repAdmin1.getText().length()!=0){
            String respond = txt_repAdmin1.getText();
            Reclamation selectedItem = tab_admin1.getSelectionModel().getSelectedItem();
            String msg = comboEtat1.getSelectionModel().getSelectedItem().toString();
            int idRec = selectedItem.getId_reclamation();
            acr.modifierEtatReclamation(respond, msg, idRec);
            tab_admin1.getItems().clear();
            acr.afficherReclamationAdmin(oblist2);
            setCellTableNormale();
            tab_admin1.setItems(oblist2);
        } else {
            System.out.println("Erreur");
        }
    }

    @FXML
    private void sendEmail(ActionEvent event) {
         try {
           acr.sendMail("rmilioussama70@gmail.com");
        } catch (Exception ex) {
            Logger.getLogger(AdminuserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleArchive(ActionEvent event) {
        bgp.toFront();
          archivep.toFront();
        afficherTableArchive();
    }


    @FXML
    private void onselectedTab(MouseEvent event) {
         editAdmin_btn.setDisable(false);
    }

    @FXML
    private void btn_main(ActionEvent event) {
        pane_Maintenance.toFront();
    }

    private void btn_rec(ActionEvent event) {
        Reclamationp.toFront();
        afficherTableNormale();
    }

    @FXML
    private void btn_usr(ActionEvent event) {
        bgp.toFront();
        userp.toFront();
    }

    @FXML
    private void btn_com(ActionEvent event) {
        
    }

    @FXML
    private void btn_fid(ActionEvent event) {
        bgp.toFront();
        pnl_fidelite.toFront();
    }

    @FXML
    private void btn_coli(ActionEvent event) {
        bgp.toFront();
        colisadminp.toFront();
    } 

 

    //Configure Maintenance
    
    
    @FXML public void SelectedComboBoxService()
    {
        Rdv r = new Rdv();
        r.SELECTEDcomboBoxService(GarageCombo, ServiceCombo.getValue().toString());
        
             
    }
    
    //Update status For Table Rendez vous
    
    
    @FXML
    public void changeStatusCellEvent2 (TableColumn.CellEditEvent edditedCell)
    {
        Rdventitie statusSelected = tableView.getSelectionModel().getSelectedItem();
        statusSelected.setStatus(edditedCell.getNewValue().toString());
        Rdv r = new Rdv();
        r.edit_rdvs(statusSelected);
        
       
        try {
            Notification.sendNotification("Module Maintenance", "Status has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
   
//Update Name And Address For Table Garage
    
    
    @FXML
    public void changeNameCellEventG (TableColumn.CellEditEvent edditedCell)
    {
        Garageentitie nameSelected =  tableViewGarage.getSelectionModel().getSelectedItem();
        nameSelected.setName(edditedCell.getNewValue().toString());
        garage g = new garage();
        g.updateName(nameSelected);
      
        
        try {
            Notification.sendNotification("Module Maintenance", "Name has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }
    @FXML
    public void changeAddressCellEvent (TableColumn.CellEditEvent edditedCell)
    {
        Garageentitie nameSelected =  tableViewGarage.getSelectionModel().getSelectedItem();
        nameSelected.setAddress(edditedCell.getNewValue().toString());
        garage g = new garage();
        g.UpdateAddress(nameSelected);
      

        try {
            Notification.sendNotification("Module Maintenance", "Address has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
    }
    
//Update Name For Table Service
    
    
    @FXML
    public void changeNameCellEventS (TableColumn.CellEditEvent edditedCell)
    {
        Serviceentitie nameSelected = tableViewService.getSelectionModel().getSelectedItem();
        nameSelected.setName(edditedCell.getNewValue().toString());
        service s = new service();
        s.edit_rdvs(nameSelected);
       
       
        try {
            Notification.sendNotification("Module Maintenance", "Name has been updated ",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
  
    
//Configure Button For Table Rendez vous
    
    
    @FXML
    public void addButton() throws SQLException{
 
 Rdv r = new Rdv();
       
//        Integer.parseInt(id_chauffeurTextField.getText())

        Rdventitie newRdv = new Rdventitie(java.sql.Date.valueOf(date_rdvTextField.getValue()),
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
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
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
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
}
    
//Configure Button For Table Garage
    
    
    @FXML
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
            } catch (AWTException ex) {
                Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
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
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    
    
//Configure Button For Service
    
    
    @FXML
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
                Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
            }
       }

        
    }
    
    @FXML
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
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
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
    @FXML
     public void SetBtnService(){
        this.RemoveBtnService.setDisable(false);
    }
    @FXML
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
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ASUS\\Desktop\\ApiPdf\\Service.pdf"));
                } catch (DocumentException ex) {
                    Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
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
    try {
        document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\ASUS\\Desktop\\ApiPdf\\logo.png"));
    } catch (DocumentException ex) {
        Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
    }
} catch (IOException ex) {
    Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
    }
    
    @FXML
    private void createPDFGarage() {
        try {
            System.out.println("walid------------------------------------->"+selectedUser);
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            
            
            try {
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ASUS\\Desktop\\ApiPdf\\Garage.pdf"));
                } catch (DocumentException ex) {
                    Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
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
    try {
        document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\ASUS\\Desktop\\ApiPdf\\logo.png"));
    } catch (DocumentException ex) {
        Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
    }
} catch (IOException ex) {
    Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
    }
    
    
    @FXML
    private void createPDFRdv() {
        
        try {
            
            System.out.println("walid------------------------------------->"+selectedUser);
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            try {
                try {
                    PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\ASUS\\Desktop\\ApiPdf\\Rdv.pdf"));
                } catch (DocumentException ex) {
                    Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
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
    try {
        document.add(com.itextpdf.text.Image.getInstance("C:\\Users\\ASUS\\Desktop\\ApiPdf\\logo.png"));
    } catch (DocumentException ex) {
        Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
    }
} catch (IOException ex) {
    Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BackadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
    
}}
