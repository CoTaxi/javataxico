/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import doryan.windowsnotificationapi.fr.Notification;
import entities.Reclamation;
import entities.colis;
import entities.user;
import entities.vehicule;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import services.event_services;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import entities.event;
import java.util.Date;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.ReclamationCRUD;
import services.servicescolis;
import services.servicesuser;
import services.fidelite_services;
import services.servicesvehicule;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ClientinterfaceController implements Initializable {

    @FXML
    private Button profilbtn;
    @FXML
    private Label mailfieldp;
    private String nom,prenom,naissance,creation,image,type,nom_compte;
    private int tel,nbr_course,cin,permis,rib_compte;
    servicesuser sru = new servicesuser();
    List <user> listeuser =new ArrayList<>();
    @FXML
    private Button btndecnx;
    private TextField departcol;
    @FXML
    private Label nomexp,prenomexp,telephoneexp;
    @FXML
    private TextField nomdestcol,teldestcol,maildestcol;
    @FXML
    private CheckBox checkmawjoud;
    @FXML
    private ComboBox<String> destmawjoud;
    @FXML
    private Button envoicolisbtn;
    servicesuser su =new servicesuser();
    servicescolis sc=new servicescolis();
    servicesvehicule sv=new servicesvehicule();
    List <user> listeusers = new ArrayList<>();
    List <user> expediteur = new ArrayList<>();
    List <String> esemi= new ArrayList<>();    
    List <String> zone= new ArrayList<>();
    List <String> zone1= new ArrayList<>();
    List <String> zone2= new ArrayList<>();
    List <String> zone3= new ArrayList<>();
    List <String> zone4= new ArrayList<>();
    List<String> wileya=new ArrayList<>();    
    List<vehicule> taxiyet=new ArrayList<>();
    List<vehicule> kraheb=new ArrayList<>();
    String zonepos;
    @FXML
    private Button colisintbtn;
    @FXML
    private AnchorPane colisep;
    int i=0;
    @FXML
    private TextField maildest;
    String naameexp;
    int telexp,idexpeditaur,teledest,iddest;
    @FXML
    private TextField poidsfield;
    String naamedest;
    @FXML
    private Button taxiintbtn;
    @FXML
    private Pane taxiep;
    @FXML
    private ComboBox<String> departtaxi;
    @FXML
    private Button trouvertaxibtn;
    
    @FXML
    private ComboBox<String> departbox,destbox,desttaxi;
    String matriculetaxi,couleurtaxi,modeletaxi,marquetaxi,positiontaxi,nomch,prenomch;
    int idchtaxi,tech;
    List<event> listeevent= new ArrayList<>();
    
    event_services ev =new event_services();
    List<String> arr= ev.affichagecombo();
    ObservableList<String> combo = FXCollections.observableArrayList(arr);

    @FXML
    private Label labelmatricule;
    @FXML
    private Label labelmarque;
    @FXML
    private Label labelmodele;
    @FXML
    private Label labelposition;
    @FXML
    private Label la9abchauffeur;
    @FXML
    private Label esmchauffeur;
    @FXML
    private Label numerochauffeur;
    @FXML
    private Button reservtaxibtn;
    @FXML
    private Button covaffbtn;
    @FXML
    private TextField covdepart;
    @FXML
    private TextField covdest;
    @FXML
    private TextField placescov;
    @FXML
    private DatePicker datecov;
    @FXML
    private Button trouvcovbtn;
    @FXML
    private Label msgerr;
    @FXML
    private Label telchcov;
    @FXML
    private Label nomchcov;
    @FXML
    private Label prenomchcov;
    @FXML
    private Button rservcovbtn;
    @FXML
    private Pane covvp;
    int idchauffeurcov,placecov;
    String matriccov,marquecov,modelecov,couleurcov ,positioncov,destinationcov,dateco;
    @FXML
    private Label vehmatricule;
    @FXML
    private Label vehmarque;
    @FXML
    private Label vehmodele;
    @FXML
    private Label vehcouleur;
    @FXML
    private Label vehposition;
    @FXML
    private Label vehdestination;
    @FXML
    private TableView<vehicule> tablekraheb;
    @FXML
    private TableColumn<vehicule, String> colmatcov;
    @FXML
    private TableColumn<vehicule, String> colposcov;
    @FXML
    private TableView<user> tablechkraheb;
    @FXML
    private TableColumn<user, String> colnamecov;
    @FXML
    private TableColumn<user, String> collastcov;
    @FXML
    private TableColumn<vehicule, String> colmarquecov,colmodelecov,coldestcov;
    @FXML
    private TableColumn<user, Integer> coltelcov;
    @FXML
    private Button btn_fidelite;
    @FXML
    private Button btn_event;
    @FXML
    private Pane pnl_fidelite;
    @FXML
    private Pane pnl_event;
    @FXML
    private TextField txt_email;
    @FXML
    private Button btn_email;
    @FXML
    private Button btn_game;
    @FXML
    private Label lbl_code;
    @FXML
    private Label lbl_balance;
    @FXML
    private Label lbl_game;
    @FXML
    private Button btn_promo;
    @FXML
    private Button btn_quit;
    @FXML
    private ComboBox<String> eve_nom;
    @FXML
    private Button bnt_participer;
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
    private Button googlempsbtn;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private Button btn_add;
    @FXML
    private TextArea msg_txt;
    @FXML
    private Button btn_del;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_save;
    @FXML
    private ComboBox<?> combo_list_pre;
    @FXML
    private ComboBox<?> combo_list_name;
    @FXML
    private TableView<Reclamation> tab_view;
    @FXML
    private TableColumn<Reclamation, String> clnm1;

    @FXML
    private TableColumn<Reclamation, String> clnm2;

    @FXML
    private TableColumn<Reclamation, String> clnm3;
    
    @FXML
    private TableColumn<Reclamation, String> clnm4;
    
    @FXML
    private TableColumn<Reclamation, String> clnm5;
    ReclamationCRUD cr = new ReclamationCRUD();
    Reclamation r = new Reclamation();
    private StringProperty pMsg ; 
    private StringProperty pEtat ;
    private StringProperty pDate ;
    private StringProperty pRep ;
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
    @FXML
    private Label lael_txt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
     destmawjoud.setVisible(false);
     reservtaxibtn.setVisible(false);
     tablechkraheb.setVisible(false);
     tablekraheb.setVisible(false);
     eve_nom.getItems().addAll(combo);
        update_event();
        refresh();
        this.tab_view.setEditable(true);
        cr.afficherReclamation(oblist);
        setCellTable();
        tab_view.setItems(oblist);
        combo_list_name.setVisible(false);
       // combo_list_name.setVisible(false);
        cr.afficherPrenomChauffeur(combo_list_pre);
        btn_save.setVisible(false);
    }    
   
    @FXML
    private void handleprofilaff(ActionEvent event) {
     getprofiluser(mailfieldp.getText());
                 FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("profiluser.fxml"));
            try {
                Parent root = loader.load();
                ProfiluserController puc = loader.getController();
                puc.setMail(mailfieldp.getText());
                puc.setNom(nom);
                puc.setPrenom(prenom);
                puc.setTel(tel);
                puc.setNaissance(naissance);
                puc.setCreation(creation);
                puc.setNbr_course(nbr_course);
               // apc.setpdp(image);
                mailfieldp.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }
    void initialiserzone ()
    {
        zone.add("--ZONE 1--");
        zone.add("ariana");
        zone.add("ariana soghra");
        zone.add("nasser1");
        zone.add("nasser2");
        zone.add("menzeh1");
        zone.add("menzeh5");
        zone.add("menzeh6");
        zone.add("menzeh7");
        zone.add("menzeh8");
        zone.add("menzeh9");
        zone.add("--ZONE 2--");
        zone.add("lac1");
        zone.add("lac2");
        zone.add("Aouina");
        zone.add("Marsa");
        zone.add("Carthage");
        zone.add("Sidi Bousaid");
        zone.add("Sidi Dhrif");
        zone.add("Gammarth");
        zone.add("--ZONE 3--");
        zone.add("Mourouj");
        zone.add("Ezzahra");
        zone.add("Hammem Lif");
        zone.add("Megrine");
        zone.add("Rades");
        zone.add("Boumhal");
        zone.add("--ZONE 4--");
        zone.add("Bardo");
        zone.add("Cite Ettadhamen");
        zone.add("Mannouba");
        zone.add("Tunis");
        zone.add("Khaznadar");
        zone.add("Denden");
        zone.add("Cite Ettahrir");
        zone.add("Ksar Said");
        
        
        zone1.add("ariana");
        zone1.add("ariana soghra");
        zone1.add("nasser1");
        zone1.add("nasser2");
        zone1.add("menzeh1");
        zone1.add("menzeh5");
        zone1.add("menzeh6");
        zone1.add("menzeh7");
        zone1.add("menzeh8");
        zone1.add("menzeh9");
        
        zone2.add("lac1");
        zone2.add("lac2");
        zone2.add("Aouina");
        zone2.add("Marsa");
        zone2.add("Carthage");
        zone2.add("Sidi Bousaid");
        zone2.add("Sidi Dhrif");
        zone2.add("Gammarth");
        
        zone3.add("Mourouj");
        zone3.add("Ezzahra");
        zone3.add("Hammem Lif");
        zone3.add("Megrine");
        zone3.add("Rades");
        zone3.add("Boumhal");
        
        zone4.add("Bardo");
        zone4.add("Cite Ettadhamen");
        zone4.add("Mannouba");
        zone4.add("Tunis");
        zone4.add("Khaznadar");
        zone4.add("Denden");
        zone4.add("Cite Ettahrir");
        zone4.add("Ksar Said");
    ObservableList<String> zonet= FXCollections.observableArrayList(zone);
    departtaxi.setItems(zonet);
    desttaxi.setItems(zonet);
    }
    void setMail(String email) {
      this.mailfieldp.setText(email);
    }
     public void getprofiluser (String mail)
    {
            listeuser = sru.afficherclient(mail);
        for (int i=0 ; i<listeuser.size();i++)
        {
            nom =listeuser.get(i).getNom();
            prenom =listeuser.get(i).getPrenom();
            tel = listeuser.get(i).getTel();
            naissance=listeuser.get(i).getNaissance();
            creation=listeuser.get(i).getCreation();
            image = listeuser.get(i).getImage();
            nbr_course=listeuser.get(i).getNb_course();
        }
    }

    @FXML
    private void handledeconnexion(ActionEvent event) {
        try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     void setcombo ()
    {
    wileya.add("Ariana");
    wileya.add("Beja");
    wileya.add("Ben Arous");
    wileya.add("Bizerte");
    wileya.add("Gabès");
    wileya.add("Gafsa");
    wileya.add("Jendouba");
    wileya.add("Kairouan");
    wileya.add("Kasserine");
    wileya.add("Kébili");
    wileya.add("Le Kef");
    wileya.add("Mahdia");
    wileya.add("La Manouba");
    wileya.add("Médenine");
    wileya.add("Monastir");
    wileya.add("Nabeul");
    wileya.add("Sfax");
    wileya.add("Sidi Bouzid");
    wileya.add("Siliana");
    wileya.add("Sousse");
    wileya.add("Tataouine");
    wileya.add("Tozeur");
    wileya.add("Tunis");
    wileya.add("Zaghouan");
    ObservableList<String> wileyet= FXCollections.observableArrayList(wileya);
    departbox.setItems(wileyet);
    destbox.setItems(wileyet);
    }

    @FXML
    private void handleenvoicolis(ActionEvent event) {
     
       String dep=departbox.getValue();
       String dest=destbox.getValue();
       String evnmail=maildest.getText();
       String pc =poidsfield.getText();
       float poidc =Float.parseFloat(pc);
       if(i%2!=0)
        {
           String maildest= destmawjoud.getValue();
           destinataireprofil(maildest);
        colis cl = new colis(0,0,0,idexpeditaur,teledest,dep,dest,naameexp,naamedest,evnmail,maildest,poidc);
        sc.ajoutercolis(cl);
   
        }
        else 
        {   
            String esmdest=nomdestcol.getText();
            String teldest=teldestcol.getText();
            int tellekher=Integer.parseInt(teldest);
            String maildest=maildestcol.getText(); 
        colis cl = new colis(0,0,0,idexpeditaur,tellekher,dep,dest,naameexp,esmdest,evnmail,maildest,poidc);
        sc.ajoutercolis(cl);
   
        }
        }
    private void combodata()
    {
        listeusers= su.afficherAll();
        for (int i=0 ; i<listeusers.size();i++)
        {   String mail=listeusers.get(i).getMail();
            if(!mail.equals(mailfieldp.getText()) && !mail.equals("admin") )
            esemi.add(mail);
        }
        ObservableList<String> trib= FXCollections.observableArrayList(esemi);
                destmawjoud.setItems(trib);
		new ComboBoxAutoComplete<String>(destmawjoud);
    }
    private void expediteurprofil()
    {
      expediteur=  su.finduser (mailfieldp.getText());
      for(int i=0;i<expediteur.size();i++)
      {
          naameexp =expediteur.get(i).getNom();
          telexp=expediteur.get(i).getTel();
          idexpeditaur=expediteur.get(i).getId_u();
          System.out.println(idexpeditaur);
      }
    }
     private void destinataireprofil(String adresse)
    {
      expediteur=  su.finduser (adresse);
      for(int i=0;i<expediteur.size();i++)
      {
           naamedest = expediteur.get(i).getNom();
          teledest=expediteur.get(i).getTel();
          iddest=expediteur.get(i).getId_u();
      }
    }
    @FXML
    private void handleecvcolis(ActionEvent event) {
        
        colisep.toFront();
        setcombo ();
        combodata();
 
        expediteurprofil();
        nomexp.setText(naameexp);
        maildest.setText(mailfieldp.getText());
        String numtel = Integer.toString(telexp);
        telephoneexp.setText(numtel);
    }

    @FXML
    private void handlecheckdone(ActionEvent event) {
        i++;
        if(i%2!=0)
        {
            destmawjoud.setVisible(true);
            nomdestcol.setVisible(false);
            teldestcol.setVisible(false);
            maildestcol.setVisible(false);
        }
        else 
        {
           destmawjoud.setVisible(false);
            nomdestcol.setVisible(true);
            teldestcol.setVisible(true);
            maildestcol.setVisible(true); 
        }
    }
    private void chercherzone1(String depart)
    {
       for (int i=0;i<zone1.size();i++)
       {
           if(depart.equals(zone1.get(i)))
           {
               zonepos="zone1";
           }
       }
    }
    private void chercherzone2(String depart)
    {
       for (int i=0;i<zone2.size();i++)
       {
           if(depart.equals(zone2.get(i)))
           {
               zonepos="zone2";
           }
       }
    }
    private void chercherzone3(String depart)
    {
       for (int i=0;i<zone3.size();i++)
       {
           if(depart.equals(zone3.get(i)))
           {
               zonepos="zone3";
           }
       }
    }
    private void chercherzone4(String depart)
    {
       for (int i=0;i<zone4.size();i++)
       {
           if(depart.equals(zone4.get(i)))
           {
               zonepos="zone4";
           }
       }
    }
    @FXML
    private void handlerechtaxi(ActionEvent event) {
        taxiep.toFront();
        initialiserzone ();
    }

    public void taxitrouve (String departure)
    {
         taxiyet=sv.afficherTaxi(departure,zonepos);
        if(taxiyet.isEmpty())
        {
           taxiyet=sv.afficherTaxiZone(departure,zonepos); 
        }
        for (int i=0;i<taxiyet.size();i++)
        {
          matriculetaxi=taxiyet.get(i).getMatricule();
          marquetaxi=taxiyet.get(i).getMarque();
          modeletaxi=taxiyet.get(i).getModele();
          positiontaxi=taxiyet.get(i).getPosition();
          idchtaxi=taxiyet.get(i).getId_v();
                  
        }
    }
    public void covtrouve (String depcov,String destincov,int nbrp,String nhar)
    {
        kraheb=sv.afficherco(depcov, destincov, nhar, nbrp);
        if(!kraheb.isEmpty())
        {
        for (int j = 0; j < kraheb.size(); j++) {
        idchauffeurcov=kraheb.get(j).getId_v();
        placecov=kraheb.get(j).getPlaces();
        matriccov=kraheb.get(j).getMatricule();
        marquecov = kraheb.get(i).getMarque();
        modelecov= kraheb.get(i).getModele();
        couleurcov = kraheb.get(i).getCouleur();
        positioncov = kraheb.get(i).getPosition();
        destinationcov = kraheb.get(i).getDestination();
        dateco= kraheb.get(i).getDateco();
        }
        }
        else 
        { 
            msgerr.setText("ce que vous cherchez est introuvable voici les co-voiturages disponible<3");
            tablechkraheb.setVisible(true);
            tablekraheb.setVisible(true);
            colmatcov.setCellValueFactory(new PropertyValueFactory<>("matricule"));
            colmodelecov.setCellValueFactory(new PropertyValueFactory<>("modele"));
            colmarquecov.setCellValueFactory(new PropertyValueFactory<>("marque"));
            coldestcov.setCellValueFactory(new PropertyValueFactory<>("destination"));
            colposcov.setCellValueFactory(new PropertyValueFactory<>("position"));
            colnamecov.setCellValueFactory(new PropertyValueFactory<>("nom"));
            collastcov.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            coltelcov.setCellValueFactory(new PropertyValueFactory<>("tel"));
             kraheb=sv.afficherlisteco ();  
             listeuser=su.afficherAllchauffeur ("chauffeur");
             ObservableList<vehicule> listecov= FXCollections.observableArrayList(kraheb);
             ObservableList<user> chauff= FXCollections.observableArrayList(listeuser);
            tablekraheb.setItems(listecov);
            tablechkraheb.setItems(chauff);
        }
    }
    public void listecovch ()
    {
        
    }
    @FXML
    private void handlereservation(ActionEvent event) {
    }


    @FXML
    private void handlereservationcov(ActionEvent event) {
    }
    
    public void chauffeurtaxi (int idchtaxi)
    {
        listeusers=su.finduserbyid(idchtaxi);
        for (int i=0;i<listeusers.size();i++)
        {
          nomch=listeusers.get(i).getNom();
          prenomch=listeusers.get(i).getPrenom();
          tech=listeusers.get(i).getTel();
                  
        }
    }
    @FXML
    private void handlefindtaxi(ActionEvent event) {
        reservtaxibtn.setVisible(true);
        String departure=departtaxi.getValue();
        String arrivee=desttaxi.getValue();
        chercherzone1(departure);
        chercherzone2(departure);
        chercherzone3(departure);
        chercherzone4(departure);
        taxitrouve (departure);
        chauffeurtaxi (idchtaxi);
        labelmarque.setText(marquetaxi);
        labelmatricule.setText(matriculetaxi);
        labelmodele.setText(modeletaxi);
        labelposition.setText(positiontaxi);
        esmchauffeur.setText(nomch);
        la9abchauffeur.setText(prenomch);
        String telch= Integer.toString(tech);
        numerochauffeur.setText(telch);
    }


    @FXML
    private void handlefoundcov(ActionEvent event) {
        covvp.toFront();
    }

    @FXML
    private void handlerechcov(ActionEvent event) {
        reservtaxibtn.setVisible(true);
        String departure=covdepart.getText();
        String arrivee=covdest.getText();
        LocalDate datecoco=datecov.getValue();
        String nhar=datecoco.toString();
        String placenbr =placescov.getText();
        int nbrp=Integer.parseInt(placenbr);
        covtrouve(departure, arrivee, nbrp, nhar);
        chauffeurtaxi (idchauffeurcov);
        vehmarque.setText(marquecov);
        vehmatricule.setText(matriccov);
        vehmodele.setText(modelecov);
        vehposition.setText(positioncov);
        vehdestination.setText(destinationcov);
        nomchcov.setText(nomch);
        prenomchcov.setText(prenomch);
        String telch= Integer.toString(tech);
        telchcov.setText(telch);
    }

    @FXML
    private void btn_fidelite(ActionEvent event) {
        pnl_fidelite.toFront();
    }

    @FXML
    private void btn_event(ActionEvent event) {
        pnl_event.toFront();
    }

    @FXML
    private void btn_email_clicked(ActionEvent event) {
        try {
            fidelite_services fs=new fidelite_services();
            String email=txt_email.getText();
            
            fs.sendMail(email);
            String sender=fs.sendMail(email);
            fs.ajout_mail(sender, email);
            txt_email.clear();
        } catch (Exception ex) {
            Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }

    @FXML
    private void btn_game(ActionEvent event) {
        Snake s=new Snake();
        s.show();
        lbl_game.setText("Come back in 24H");
    }

    @FXML
    private void btn_promo(ActionEvent event) {
        fidelite_services f=new fidelite_services();
        int i=f.affichage_code();
        String code=Integer.toString(i);
        int j=f.affichage_balance();
        String balance=Integer.toString(j);
        lbl_code.setText(code);
        lbl_balance.setText(balance);
    }

    @FXML
    private void btn_quit(ActionEvent event) {
        event_services sv = new event_services();
        sv.quit();
        try {
            Notification.sendNotification("module fidelite", "vous avez quitter",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void btn_participer(ActionEvent event) {
        event_services sv = new event_services();
       String nom=eve_nom.getValue();
        sv.affecter(nom);
        try {
            Notification.sendNotification("module fidelite", "evennement affecté",TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClientinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
        public void update_event()
    {       
        event_services ev=new event_services();
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            col_emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
            col_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
            listeevent=ev.updateevent ();
            ObservableList<event> eve =FXCollections.observableArrayList(listeevent);
            table.setItems(eve);
     
    }
      public void refresh(){
    for ( int i = 0; i<table.getItems().size(); i++) {
    table.getItems().clear();
    update_event();
}
}

    @FXML
    private void handleaffmaps(ActionEvent event) {
      /*  final Mapa example = new Mapa("test");
	example.generateMarker(map.getCenter());*/
    }

      private void setCellTable(){
        
        clnm1.setCellValueFactory(new PropertyValueFactory<>("message"));
        clnm2.setCellValueFactory(new PropertyValueFactory<>("etat"));
        clnm3.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
        clnm4.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        clnm5.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        
    }
  
    public void btn_afficherReclamation(){
            cr.afficherReclamation(oblist);
    }
    
    @FXML
    public void btn_afficherNomChauffeur(){
        combo_list_name.setVisible(true);
        combo_list_name.getItems().clear();
        String Prenom = combo_list_pre.getSelectionModel().getSelectedItem().toString();
        if(Prenom.length()!=0){
            cr.afficherNomChauffeur(Prenom, combo_list_name);
        }else{
            System.out.println("Veuillez choisir un prenom d'abord, Merci");
        }
    }
    
    @FXML
    public void btn_deleteRec(){ 
            Reclamation selected = tab_view.getSelectionModel().getSelectedItem();
            if(selected==null)
            {
                Alert dialogW = new Alert(Alert.AlertType.WARNING);
                dialogW.setTitle("Fenêtre d'alerte !");
                dialogW.setContentText("Veuillez sélectionner une réclamation");
                dialogW.showAndWait();
            }else{   
             /*   Alert dialogC = new Alert(AlertType.CONFIRMATION);
                dialogC.setTitle("Confirmation de la suppression");
                dialogC.setHeaderText(null);
                dialogC.setContentText("Voulez-vous supprimer la réclamation séléctionée ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) 
                {*/
                    int selectedId = selected.getId_reclamation();
                    cr.deleteRec(selectedId);
                    tab_view.getItems().clear();
                    cr.afficherReclamation(oblist);
                    setCellTable();
                    tab_view.setItems(oblist); 
                    //System.out.println("User chose OK");
               /* }
                else {
                System.out.println("User chose Cancel or closed the dialog-box");
                }*/
            }
            
           
            //}
    }
    
    @FXML
    public void editRec(){
        
        Reclamation selected = tab_view.getSelectionModel().getSelectedItem();
        msg_txt.setText(selected.getMessage());  
        btn_save.setVisible(true);
    }
    
    @FXML
     public void btn_saveEditRec(){
        String prenameItem_sel = combo_list_pre.getSelectionModel().getSelectedItem().toString();
        String nameItem_sel = combo_list_name.getSelectionModel().getSelectedItem().toString();      
        String msgEdit = msg_txt.getText();     
        Reclamation selected = tab_view.getSelectionModel().getSelectedItem();
        int selectedID = selected.getId_reclamation();
        cr.modifierReclamation(r, selectedID, msgEdit, prenameItem_sel, nameItem_sel);
        tab_view.getItems().clear();
        cr.afficherReclamation(oblist);
        setCellTable();
        tab_view.setItems(oblist);
            
     }
     
    @FXML
    public void btn_addRec(){
        
        String nameItem_sel = combo_list_name.getSelectionModel().getSelectedItem().toString();
        String prenameItem_sel = combo_list_pre.getSelectionModel().getSelectedItem().toString();
        String msg=msg_txt.getText();
      //  System.out.println(nameItem_sel+"/// "+prenameItem_sel+"/// "+msg);
        if(msg.equals(""))
        {
            Alert dialogW = new Alert(Alert.AlertType.WARNING);
            dialogW.setTitle("Fenêtre d'alerte !");
            dialogW.setContentText("Veuillez remplir tous les champs");
            dialogW.showAndWait();  
        } else {
            //System.out.println("Erreur");
            cr.ajouterReclamation(msg, r, nameItem_sel, prenameItem_sel);
            tab_view.getItems().clear();
            cr.afficherReclamation(oblist);
            setCellTable();
            tab_view.setItems(oblist);
        }    
    }
    
    @FXML
    public void testEtat(){
        Reclamation selected = tab_view.getSelectionModel().getSelectedItem();
        String selectedEtat = selected.getEtat();
        if(selectedEtat.equals("Traitée"))
        {
           // btn_edit.setVisible(false);
            btn_save.setVisible(false);
        }else{
            //btn_edit.setVisible(true);
            btn_save.setVisible(true);
        }
    }

    
}
