/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
        createGrid(_grid, _parking);
        VBox gridpart = new VBox(_grid);
        VBox headerpart = new VBox(_title);

        AnchorPane anchor = new AnchorPane(headerpart, gridpart);

        Scene scene = new Scene(anchor);
        stage.setScene(scene);
        stage.show();

    }

    private void createGrid(GridPane grid, Parking parking) {
        
        grid.setPadding(new Insets(100, 10, 10, 10));
        grid.setVgap(70);
        grid.setHgap(0);
        int cpt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Button button_parking_place = new Button();
                colorParkingPlace(button_parking_place, parking.getParkingspace()[cpt]);
                button_parking_place.setPrefWidth(200);
                button_parking_place.setPrefHeight(100);
                cpt++;
                grid.add(button_parking_place, j, i);
            }

        }

    }

    private void colorParkingPlace(Button place, ParkingSpace parking) {
        if (parking.getStatus() == ParkingSpaceStatus.OCCUPIED) {
            place.setText(String.valueOf(parking.getParking_space_number())
                    + "\n" + parking.getVehicule().getNumberplate());
            switch (parking.getVehicule().getType()) {
                case MOTORBIKE:
                    place.setStyle("-fx-background-color: #D9E8FB; -fx-border-color: #859FBA;");
                    break;
                case CAR:
                     place.setStyle("-fx-background-color: #F6CDC9; -fx-border-color: #BA676F;");
                     break;
                case TRUCK:
                     place.setStyle("-fx-background-color: #E2D5E7; -fx-border-color: #9F8CA8;");
            }

        } else {
            place.setText(String.valueOf(parking.getParking_space_number()));
            place.setStyle("-fx-background-color: #D3E9D4; -fx-border-color: #9AB588");
        }

    }

    private void initializeComponent() {
        _grid = new GridPane();
        _parking = new Parking();
        _title = new Label("Parking Helb 2.0");
        _title.setPrefSize(800, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));

    }

}
