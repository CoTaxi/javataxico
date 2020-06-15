/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import entities.UserSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import services.servicesuser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfilchauffeurController implements Initializable {
    String mail;
    String image="";
    servicesuser sru = new servicesuser();
    @FXML
    private Button afficherbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button modifierpdpbtn;
    @FXML
    private Button desactiverbtn;
    private Pane confp;
    @FXML
    private Pane modifp;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField tel;
    @FXML
    private Button modifier;
    @FXML
    private DatePicker datenaiss;
    @FXML
    private Pane pdpp;
    @FXML
    private ImageView picture;
    @FXML
    private Button mpdpbtn;
    @FXML
    private Pane affp;
    @FXML
    private Button desbtn;
    @FXML
    private TextField textmail;
    @FXML
    private PasswordField textmdp;
    @FXML
    private Pane desp;
    @FXML
    private Label labelcreate;
    @FXML
    private ImageView photo;
    @FXML
    private Label labeltel;
    @FXML
    private Label labelnaiss;
    @FXML
    private Label labelprenom;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelnbrcourse;
    @FXML
    private Button editbtn;
    @FXML
    private Label labelcin,labelpermis,labelncompte,labelrib;
    @FXML
    private TextField cin;
    @FXML
    private TextField permis;
    @FXML
    private TextField compte;
    @FXML
    private TextField rib;
    @FXML
    private Button retour;
    @FXML
    private Button decnx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    public void setNom(TextField nom) {
        this.nom.setText(nom.getText());
    }
    

    public void setPrenom(TextField prenom) {
        this.prenom.setText(prenom.getText());
 
}
     
    void setNbr_course(int nbr_course) {
        
        String nbr=Integer.toString(nbr_course);
        this.labelnbrcourse.setText(nbr);
    }  
    void setNom(String nom) {
       this.labelnom.setText(nom);
       this.nom.setText(nom);
    }

    void setPrenom(String prenom) {
       this.labelprenom.setText(prenom);
       this.prenom.setText(prenom);

}
        void setTel(int tel) {
        String numtel=Integer.toString(tel);
        this.labeltel.setText(numtel);
        this.tel.setText(numtel);
}

    void setNaissance(String naissance) {
    this.labelnaiss.setText(naissance);
    LocalDate today = LocalDate.parse(naissance);
    this.datenaiss.setValue(today);
    }

    void setCreation(String creation) {
    this.labelcreate.setText(creation); 
    }
    void setMail(String email) {
    this.mail =email;  
    }

    void setCin(int cin) {
        String carte=Integer.toString(cin);
       this.labelcin.setText(carte);
       this.cin.setText(carte);
    }

    void setPermis(int permis) {
     String numpermis=Integer.toString(permis);
        this.labelpermis.setText(numpermis);
        this.permis.setText(numpermis);
    }

    void setNom_compte(String nom_compte) {
        this.labelncompte.setText(nom_compte);
        this.compte.setText(nom_compte);
    }

    void setRib_compte(int rib_compte) {
    String rib=Integer.toString(rib_compte);
        this.labelrib.setText(rib);
        this.rib.setText(rib);
    }

    @FXML
    private void handlepdpbtn(ActionEvent event) {
           int i=0;
            // TODO code application logic here
            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);
            panel.setMirrored(true);
            
            JFrame window = new JFrame("camera");
            window.add(panel);
            window.setResizable(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            while (i<6)
            {
                i++;
                if(i==5)
                {
                    try {
                        ImageIO.write(webcam.getImage(), "JPG", new File("src/TaxiCo/images/"+mail+".jpg"));
                        image="src/TaxiCo/images/"+mail+".jpg";
                        sru.ajouterpdp(mail,image);
                    } catch (IOException ex) {
                        Logger.getLogger(ProfiluserController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
                }
            }
    }


    @FXML
    private void handledesactiverbtn(ActionEvent event) {
 
        try {
            sru.desactivercompte(textmail.getText(), textmdp.getText());
            Parent  conn_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfiluserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleafficher(ActionEvent event) {
        affp.toFront();
    }

    @FXML
    private void handlemodifier(ActionEvent event) {
        modifp.toFront();
    }

    @FXML
    private void handlepdp(ActionEvent event) {
        pdpp.toFront();
        File file = new File("src/TaxiCo/images/"+mail+".jpg");
        Image im = new Image(file.toURI().toString());
        picture.setImage(im);
    }

    private void handleconfidentalite(ActionEvent event) {
        confp.toFront();
    }

    @FXML
    private void handledesactiver(ActionEvent event) {
        desp.toFront();
    }

    @FXML
    private void handleedit(ActionEvent event) {
        modifp.toFront();
    }

    @FXML
    private void handlemodifbtn(ActionEvent event) {
        String  first_name =nom.getText();
        String last_name = prenom.getText();
        String number = tel.getText();
        int num_tel = Integer.parseInt(number); 
        LocalDate date=datenaiss.getValue();
        String naiss=date.toString();
        String identite=cin.getText();
        int ciin=Integer.parseInt(identite); 
        String per=permis.getText();
        int perm=Integer.parseInt(per); 
        String nom_c=compte.getText();
        String rib_compte=rib.getText();
        int rib_c=Integer.parseInt(rib_compte); 
       sru.modifierchauffeur(mail,first_name,last_name,naiss,num_tel,ciin,perm,nom_c,rib_c);
                setNom(first_name);
                setPrenom(last_name);
                setTel(num_tel);
                setNaissance(naiss);
                setCin(ciin);
                setPermis(perm);
                setNom_compte(nom_c);
                setRib_compte(rib_c);
         desp.toFront();
    }

     @FXML
    private void handleretour(ActionEvent event) {
           try {
            sru.desactivercompte(textmail.getText(), textmdp.getText());
            Parent  conn_page = FXMLLoader.load(getClass().getResource("newinterfaceclient.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfiluserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handledecnx(ActionEvent event) {
          try {
            sru.desactivercompte(textmail.getText(), textmdp.getText());
            Parent  conn_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfiluserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
