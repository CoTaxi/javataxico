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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
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
  int id;
    servicesuser sru = new servicesuser();
    private String nom,prenom,naissance,creation,image,type,nom_compte;
    private int tel,nbr_course,cin,permis,rib_compte;
    List <user> listeuser =new ArrayList<>();
    List <user> listechauffeur =new ArrayList<>();
    @FXML
    private Hyperlink sign_up;
    @FXML
    private Button btn_login;
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
//      String test7 = BCrypt.hashpw(mdp, BCrypt.gensalt(13));
//           test7.replace("$2y$", "$2a$");
//           test7.substring(4, test7.length());
//           String mdpcrypter = "$2y$"+test7.substring(4, test7.length());
      String role = sru.getclient(email, mdp).get(0).getRole();
//      gettype(email);
            if(test)
            {
                if((role.equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")))
                {
                  try {
              Parent  conn_page = FXMLLoader.load(getClass().getResource("adminuser.fxml"));
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
                if(role.equals("a:1:{i:0;s:11:\"ROLE_CLIENT\";}"))
                {FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("newinterfaceclient.fxml"));
            try {
                Parent root = loader.load();
                NewinterfaceclientController puc = loader.getController();
                puc.setMail(email);
                mailfield.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
                }
                else   if(role.equals("a:1:{i:0;s:14:\"ROLE_CHAUFFEUR\";}"))
                {
                 FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("chauffeurinterface.fxml"));
            try {
                Parent root = loader.load();
                ChauffeurinterfaceController cic = loader.getController();
                cic.setMail(email);
//                cic.setId(id);
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
