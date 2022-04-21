/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Parking;
import model.ParkingSpace;
import model.ParkingSpaceStatus;

/**
 *
 * @author Marwa
 */
public class homescene extends Scene {

    @FXML
    private GridPane _grid;
    private Label _title;
    private int row=4;
    private int column=5;

    public homescene(Parent root,Stage stage,Parking parking) {
        super(root);
        initializeComponent();
        createGrid(_grid, parking, stage);

    }

    public void initializeComponent() {
        _grid = new GridPane();
        _title = new Label("Parking Helb 2.0");
        _title.setPrefSize(1000, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));

    }

    private void colorParkingPlace(Button place, ParkingSpace parking) {
        if (parking.getStatus() == ParkingSpaceStatus.OCCUPIED) {
            place.setText(String.valueOf(parking.getParking_space_number())
                    + "\n" + parking.getVehicule().getNumberplate());
            switch (parking.getVehicule().getType()) {
                case MOTORBIKE:
                    place.setStyle("-fx-background-color: #D9E8FB; "
                            + "-fx-border-color: #859FBA;");
                    break;
                case CAR:
                    place.setStyle("-fx-background-color: #F6CDC9; "
                            + "-fx-border-color: #BA676F;");
                    break;
                case TRUCK:
                    place.setStyle("-fx-background-color: #E2D5E7; -"
                            + "fx-border-color: #9F8CA8;");
            }

        } else {
            place.setText(String.valueOf(parking.getParking_space_number()));
            place.setStyle("-fx-background-color: #D3E9D4; "
                    + "-fx-border-color: #9AB588");
        }

    }
    
      public void createGrid(GridPane grid, Parking parking, Stage stage) {

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
                System.out.println("DEMO :  " + parking.getParkingspace()[cpt].toString());
                //clickPlaceParking(button_parking_place, parking.getParkingspace()[cpt], stage, parking);
                cpt++;

                grid.add(button_parking_place, j, i);
            }

        }

    }

}
