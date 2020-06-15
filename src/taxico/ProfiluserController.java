/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.input.MouseEvent;
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
public class ProfiluserController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelprenom;
    
    @FXML
    private Label labeltel,labelnaiss,labelcreate;
    @FXML
    private Button afficherbtn,modifierpdpbtn,desactiverbtn,modifierbtn;
    private Pane photop;
    private Button confidp;
    @FXML
    private TextField tel;
    private TextField naissance;
    servicesuser sru = new servicesuser();
    String mail;
    String image="";
    @FXML
    private TextField textmail;
    @FXML
    private PasswordField textmdp;
    private Pane confp;
    @FXML
    private Pane modifp;
    @FXML
    private Pane affp;
    @FXML
    private Pane pdpp;
    @FXML
    private Pane desp;
    @FXML
    private ImageView picture;
    @FXML
    private ImageView photo;
    @FXML
    private Label labelnbrcourse;
    @FXML
    private Button editbtn;
    @FXML
    private Button modifier;
    @FXML
    private DatePicker datenaiss;
    @FXML
    private Button mpdpbtn;
    @FXML
    private Button desbtn;
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
       sru.modifierclient(mail,first_name,last_name,naiss,num_tel);
                setNom(first_name);
                setPrenom(last_name);
                setTel(num_tel);
                setNaissance(naiss);
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
