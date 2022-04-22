/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Parking;


/**
 *
 * @author Marwa
 */
public class main extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        /*FXMLDocumentController controller = new FXMLDocumentController();
        controller.generateAll(stage);*/
        //TEST 
        /*EditingWindowView e = new EditingWindowView();
        Scene scene = new Scene(e.view);*/
        Parking parking = new Parking();
        HomeWindowView e = new HomeWindowView(parking);
        Scene scene = new Scene(e.getView());
        stage.setScene(scene);
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
