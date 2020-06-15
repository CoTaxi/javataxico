/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import entities.user;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.servicesuser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class SignupController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_mdp;
    @FXML
    private CheckBox ck_chaufeur;
    @FXML
    private DatePicker txt_naissance;
    @FXML
    private Button btn_sign;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_permis;
    @FXML
    private TextField txt_nomcompte;
    @FXML
    private TextField txt_rib;
    @FXML
    private TextField txt_experience;
    int i=0;
    servicesuser s=new servicesuser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txt_cin.setVisible(false);
        txt_experience.setVisible(false);
        txt_nomcompte.setVisible(false);
        txt_permis.setVisible(false);
        txt_rib.setVisible(false);
    }    

   @FXML
    private void chk_chuffeur(ActionEvent event) {
       
        i++;
         if(i%2!=0){
        txt_cin.setVisible(true);
        txt_experience.setVisible(true);
        txt_nomcompte.setVisible(true);
        txt_permis.setVisible(true);
        txt_rib.setVisible(true);
         }
         else
         {
        txt_cin.setVisible(false);
        txt_experience.setVisible(false);
        txt_nomcompte.setVisible(false);
        txt_permis.setVisible(false);
        txt_rib.setVisible(false);
         }
    }
    @FXML
    private void btn_sign(ActionEvent event) {
          String nom=txt_nom.getText();
        String prenom=txt_prenom.getText();
        String t=txt_tel.getText();
        int tel=Integer.parseInt(t);
        String mail=txt_email.getText();
        String mdp=txt_mdp.getText();
        LocalDate nai=txt_naissance.getValue();
        String naissance=nai.toString();
        
        
        Date creation = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
        String create = df.format(creation);
        
        String p=txt_permis.getText();
        if(p.equals(""))
        {
        int active=1;
        String image="m";
        String type="client";
        int nb_course=0;
//String nom, String prenom,int tel,  String mail, String mdp, String naissance, String creation,int active,String image,String type,int nb_course
        user client = new user(tel,1,0,0,0,0,0,nom,prenom,mail,mdp,naissance,create,"image",type,"");
        
       s.ajouteruser(client);
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FXMLDocument.fxml"));
            try {
                Parent root = loader.load();
                FXMLDocumentController fdc = loader.getController();
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
        }
        else if(!p.equals(""))
        {
            
        int permis=Integer.parseInt(p);
        String c=txt_cin.getText();
        int cin=Integer.parseInt(c);
        String nom_c=txt_nomcompte.getText();
        String r=txt_rib.getText();
        int rib=Integer.parseInt(r);
        String x=txt_experience.getText();
        int experience=Integer.parseInt(x);
            String typejdid="chauffeur";
//int id_u, int tel, int active, int cin, int permis, int rib_compte, int experience, int nb_course, String nom, String prenom, String mail, String mdp, String naissance, String creation, String image, String type, String nom_compte
        user chauffeur=new user(tel,1,cin,permis,rib,experience,0,nom,prenom,mail,mdp,naissance,create,"image",typejdid,nom_c);
        s.ajouterchauffeur(chauffeur);
               FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("FXMLDocument.fxml"));
            try {
                Parent root = loader.load();
                FXMLDocumentController fdc = loader.getController();
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
        }
    }
    
}
