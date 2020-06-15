/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

import java.io.IOException;
import java.net.URL;
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
public class ComptedesactiveController implements Initializable {
    servicesuser sru = new servicesuser();

    @FXML
    private Label email;
    @FXML
    private Button btnyes;
    @FXML
    private Button btnno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void setMail(String email) {
    this.email.setText(email);
    }
    @FXML
    private void handleactivation(ActionEvent event) {
                try {
            sru.activercompte(email.getText());
            Parent  conn_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ComptedesactiveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handledesactivation(ActionEvent event) {
        try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ComptedesactiveController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
