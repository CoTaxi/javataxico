/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import entities.user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.servicesuser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ChauffeurinterfaceController implements Initializable {

    @FXML
    private Button profilbtn;
    @FXML
    private Label mailfieldc;
    private String nom,prenom,naissance,creation,image,type,nom_compte;
    private int tel,nbr_course,cin,permis,rib_compte;
    servicesuser sru = new servicesuser();
    List <user> listechauffeur =new ArrayList<>();
    @FXML
    private Button btndecnx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void handledexch(ActionEvent event) {
      
        try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ChauffeurinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
