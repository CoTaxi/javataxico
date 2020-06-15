/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;
import utils.ConnexionBD;
import com.jfoenix.controls.JFXButton;
import entities.Reclamation;
import entities.colis;
import entities.fidelite;
import entities.user;
import entities.vehicule;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.event_services;
import services.fidelite_services;
import services.servicescolis;
import services.servicesuser;
import services.servicesvehicule;
import entities.event;
import java.sql.Connection;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import services.AdminCRUD;

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

    
    /**
     * Initializes the controller class.
     */
    
    ObservableList<user> fid =FXCollections.observableArrayList();
    ObservableList<String> combo = FXCollections.observableArrayList(arrd);
    ObservableList<String> combo_fidelite = FXCollections.observableArrayList(liste);
    static public final String CharactersNumbers = "[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890èéòàùì ]";
    static public final String Characters = "[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùì ]";
    static public final String Numbers = "[1234567890]";
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
    int k=0;
    int h=0;
    String usertype="";
    int idveh,idcoliselect,idvselect;
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
    private Button colisbtn;
    @FXML
    private Pane colisadminp;
    @FXML
    private TitledPane listecl1;
    @FXML
    private TitledPane listecolis;
    @FXML
    private TableColumn<colis, String> col1,col2,col3,col4,col5,col6;
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
    private TableView<colis> tablecol,tablecnaf;
    @FXML
    private TableColumn<colis, String> nafdepart,nafdestination;
    @FXML
    private TableColumn<colis, Float> nafpoids;
    @FXML
    private TableView<vehicule> tablevaf;
    @FXML
    private TableColumn<vehicule, String> nafmatricule,vnafdep,vnafdest;
    String depchoix,destchoix;
    @FXML
    private Button btn_event;
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
    private TableColumn<event,String> col_nom;
    @FXML
    private TableColumn<event,Date> col_date;
    @FXML
    private TableColumn<event,Integer> col_duree;
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
    private Button btn_fidelite;
    @FXML
    private Pane pnl_fidelite;
    @FXML
    private Button btn_stat;
    @FXML
    private TableView<user> tab_fidelite;
    @FXML
    private TableColumn<user,String> nom_client;
    @FXML
    private TableColumn<user,Integer> point_fidelite;
    @FXML
    private TextField txt_points;
    @FXML
    private Button btn_points;
    @FXML
    private Label nbr_inv;
    @FXML
    private Pane pnl_stat;
    String nom1,emplacement;
    int duree,capacite;
    Date date;
    event_services sre = new event_services();
    
     List<String> combov= new ArrayList<>();
    @FXML
    private Button stat_event1;
    @FXML
    private Button Reclamationbtn;
    @FXML
    private Pane Reclamationp;
    @FXML
    private Label lbl_etat;
    @FXML
    private ComboBox<String> comboEtat;
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
    private TableColumn<Reclamation, String> id_admin;

    @FXML
    private TableColumn<Reclamation, String> msg_admin;

    @FXML
    private TableColumn<Reclamation, String> etat_admin;

    @FXML
    private TableColumn<Reclamation, String> date_admin;

    @FXML
    private TableColumn<Reclamation, String> rep_admin;
    
    @FXML
    private TableColumn<Reclamation, String> mat;
     ObservableList<String>stateEtat =
    FXCollections.observableArrayList("Non traitée","En cours de traitement","Traitée","Archivée");
    ObservableList<Reclamation> oblist1 = FXCollections.observableArrayList();
    ObservableList<Reclamation> oblist2 = FXCollections.observableArrayList();
    ObservableList<Reclamation> oblist3 = FXCollections.observableArrayList();
    AdminCRUD acr = new AdminCRUD();
    Reclamation r = new Reclamation();
    @FXML
    private Pane archivep;
    @FXML
    private TextField txt_arch;
    @FXML
    private TableView<Reclamation> tab_arch;

    @FXML
    private TableColumn<Reclamation, String> id_arch;

    @FXML
    private TableColumn<Reclamation, String> msg_arch;

    @FXML
    private TableColumn<Reclamation, String> etat_arch;

    @FXML
    private TableColumn<Reclamation, String> date_arch;

    @FXML
    private TableColumn<Reclamation, String> rep_arch;

    @FXML
    private TableColumn<Reclamation, String> pre_arch;
    @FXML
    private Button btn_arch;
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
    }    

    @FXML
    private void handleuseradmin(ActionEvent event) {
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
            
            duree=listeevent.get(i).getDuree();
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
   
    @FXML
    private void handleindex(ActionEvent event) {
        indexp.toFront();
    }

    @FXML
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
                String duree_ev = Integer.toString(duree);
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
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            col_emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
            col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            //table.getItems().clear();
            
    ObservableList<event> listtri = FXCollections.observableArrayList(listetri);
            table.setItems(listtri);
    }

    @FXML
    private void btn_fidelite(ActionEvent event) {
        pnl_fidelite.toFront();
    }

    @FXML
    private void pnl_fidelite(MouseEvent event) {
        
    }

    @FXML
    private void btn_stat(ActionEvent event) {
        pnl_stat.toFront();
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
    {       col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
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
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
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
        id_admin.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        msg_admin.setCellValueFactory(new PropertyValueFactory<>("message"));
        etat_admin.setCellValueFactory(new PropertyValueFactory<>("etat"));        
        date_admin.setCellValueFactory(new PropertyValueFactory<>("date_rec"));        
        rep_admin.setCellValueFactory(new PropertyValueFactory<>("reponse")); 
        mat.setCellValueFactory(new PropertyValueFactory<>("prename")); 
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
        tab_admin.setItems(oblist1);
        comboEtat.setItems(stateEtat);
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
        Reclamationp.toFront();
        afficherTableNormale();
    }
   
    @FXML
    private void btn_deleteRecAdmin(ActionEvent event) {
           Reclamation selected = tab_admin.getSelectionModel().getSelectedItem();
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
                    tab_admin.getItems().clear();
                    acr.afficherReclamationAdmin(oblist1);
                    setCellTableNormale();
                    tab_admin.setItems(oblist1);
                    //System.out.println("User chose OK");
            /*}
            else {
                 System.out.println("User chose Cancel or closed the dialog-box");
        }*/
            }
    }

    @FXML
    private void editerRepAdmin(ActionEvent event) {
        Reclamation selected = tab_admin.getSelectionModel().getSelectedItem();
        txt_repAdmin.setText(selected.getReponse());  
    }

    @FXML
    private void btn_saveEditAdmin(ActionEvent event) {
         if(txt_repAdmin.getText().length()!=0){
            String respond = txt_repAdmin.getText();
            Reclamation selectedItem = tab_admin.getSelectionModel().getSelectedItem();
            String msg = comboEtat.getSelectionModel().getSelectedItem().toString();
            int idRec = selectedItem.getId_reclamation();
            acr.modifierEtatReclamation(respond, msg, idRec);
            tab_admin.getItems().clear();
            acr.afficherReclamationAdmin(oblist2);
            setCellTableNormale();
            tab_admin.setItems(oblist2);
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
          archivep.toFront();
        afficherTableArchive();
    }


    @FXML
    private void onselectedTab(MouseEvent event) {
         editAdmin_btn.setDisable(false);
    }

   
    
}
