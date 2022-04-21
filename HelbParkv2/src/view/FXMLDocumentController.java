/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Parking;
import model.ParkingSpace;
import model.ParkingSpaceStatus;

/**
 *
 * @author Marwa
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private static int row = 4;
    private static int column = 5;
    private GridPane _grid;
    private Parking _parking;
    private Label _title;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void generateAll(Stage stage) throws IOException {
        initializeComponent();
        _parking = new Parking();
        showScene(_parking, stage);
      
       
    }
    
    public void showScene(Parking p,Stage stage){
         initializeComponent();
        createGrid(_grid, _parking, stage);
        VBox gridpart = new VBox(_grid);
        VBox headerpart = new VBox(_title);

        AnchorPane anchor = new AnchorPane(headerpart, gridpart);

        Scene scene = new Scene(anchor);
        stage.setScene(scene);
        stage.show();
        
    }

  


    private void clickPlaceParking(Button place, ParkingSpace p, Stage stage, Parking parking) {
        place.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EditInterfaceController edit = new EditInterfaceController();
                edit.generateEditScene(p, stage, parking);
            }
        });
    }

}
