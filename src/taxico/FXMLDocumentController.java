/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import entities.UserSession;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.servicesuser;

/**
 *
 * @author ASUS
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField mailfield;
    @FXML
    private TextField pwdfield;
    servicesuser sru = new servicesuser();
    private String nom,prenom,naissance,creation,image,type,nom_compte;
    private int tel,nbr_course,cin,permis,rib_compte;
    List <user> listeuser =new ArrayList<>();
    List <user> listechauffeur =new ArrayList<>();
    @FXML
    private Hyperlink sign_up;
    @FXML
    private Button btn_login;
    int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlecnx(ActionEvent event) {
           
      String  email =mailfield.getText();
      String mdp = pwdfield.getText();
      boolean active= sru.verifiercompte(email,mdp);
      Boolean test = sru.seconnecter(email, mdp);
      gettype(email);
            if(test)
            {
                if((email.equals("admin") && mdp.equals("admin")))
                {
                  try {
              Parent  conn_page = FXMLLoader.load(getClass().getResource("backadmin.fxml"));
              Scene conn_scene = new Scene(conn_page);
              Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              conn_stage.setScene(conn_scene);
              conn_stage.show();
          } catch (IOException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          }  
                }
               else  if(active)
            {
                if(type.equals("client"))
                {   UserSession.getInstace(id, email);
                    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("newinterfaceclient.fxml"));
            try {
                Parent root = loader.load();
                NewinterfaceclientController nicc = loader.getController();
                nicc.setMail(email);
                nicc.setid(id);
                mailfield.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
                }
                else   if(type.equals("chauffeur"))
                {
                 FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("newinterfacechauffeur.fxml"));
            try {
                Parent root = loader.load();
                NewinterfacechauffeurController nic = loader.getController();
                nic.setMail(email);
               // apc.setpdp(image);
                mailfield.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
                }
            }
                 else
                 {
                   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("comptedesactive.fxml"));
            try {
                Parent root = loader.load();
                ComptedesactiveController cdc = loader.getController();
                cdc.setMail(email);
                
               // apc.setpdp(image);
                mailfield.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
                     
                 }
            }
            else 
            {
          try {
              System.out.println("no");
              Parent  conn_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
              Scene conn_scene = new Scene(conn_page);
              Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              conn_stage.setScene(conn_scene);
              conn_stage.show();
          } catch (IOException ex) {
              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
          }
            }
        } 
    public void gettype(String mail)
    {
               listeuser = sru.afficherclient(mail);
        for (int i=0 ; i<listeuser.size();i++)
        {
            type =listeuser.get(i).getType();
            id=listeuser.get(i).getId_u();
        }
    }

    @FXML
    private void btn_signup(ActionEvent event) {
          
        try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("signup.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
