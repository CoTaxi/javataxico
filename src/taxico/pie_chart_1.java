/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxico;

/**
 *
 * @author mahdi
 */
import utils.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.chart.PieChart; 
import javafx.scene.layout.*; 
import javafx.event.ActionEvent; 
import javafx.scene.AmbientLight; 
import javafx.scene.shape.Sphere; 
import javafx.scene.control.*; 
import javafx.stage.Stage; 
import javafx.scene.Group; 
import javafx.scene.PerspectiveCamera; 
import javafx.scene.paint.Color; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.FXCollections; 
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
   
public class pie_chart_1 extends Application { 
    
    Connection conn = ConnexionBD.getInstance().getCnx();
   private ObservableList data_pie;
   
    // launch the application 
   
   public void buildData(){
        try {
            data_pie = FXCollections.observableArrayList();
            
            PreparedStatement pt = conn.prepareStatement("select DISTINCT emplacement,COUNT(emplacement) as nbr from evennement group by emplacement");
            ResultSet rs = pt.executeQuery();
            while(rs.next()){
                //adding data on piechart data
                data_pie.add(new PieChart.Data(rs.getString("emplacement"),rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pie_chart_1.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          }
 
      
    public void start(Stage stage) 
    { 
        Image icon=new Image("file:src/img/taxico.png");
         stage.getIcons().add(icon); 
        // set title for the stage 
        //PIE CHART
        PieChart pieChart = new PieChart();
        buildData();
        pieChart.getData().addAll(data_pie);

        //Main Scene
        Scene scene = new Scene(pieChart);        

        stage.setScene(scene);
        stage.show();
        
        /*stage.setTitle("Pie Chart emplacement des evennements"); 
   
        // piechart data 
        PieChart.Data data[] = new PieChart.Data[5]; 
   
        // string and integer data 
        String status[] = {"Correct Answer", "Wrong Answer",  
                           "Compilation Error", "Runtime Error", 
                           "Others" }; 
                              
        int values[] = {20, 30, 10, 4, 2}; 
   
        for (int i = 0; i < 5; i++) { 
            data[i] = new PieChart.Data(status[i], values[i]); 
        } 
   
        // create a pie chart 
        PieChart pie_chart = new
                PieChart(FXCollections.observableArrayList(data)); 
   
        // create a Group 
        Group group = new Group(pie_chart); 
   
        // create a scene 
        Scene scene = new Scene(group, 500, 300); 
   
        // set the scene 
        stage.setScene(scene); 
   
        stage.show(); */
    } 
   
   
    // Main Method 
    public static void main(String args[]) 
    { 
           
        // launch the application 
        launch(args); 
    } 
}
