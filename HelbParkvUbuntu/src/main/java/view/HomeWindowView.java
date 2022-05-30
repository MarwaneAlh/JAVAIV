/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Parking;
import model.ParkingSpace;
import model.ParkingSpaceStatus;

/**
 *
 * Class de la fenetre qui affiche le parking
 */
public class HomeWindowView {

    /*
    *grid= la grille qui va stocker le parking sous forme de boutton grid
    *title correspond au titre de la fenetre
    *row correspond au nombre de ligne voulue pour l'affichage du parking
    *view correspond a la fenetre
     */
    private GridPane _grid;
    private Label _title;
    private int _row = 4;
    private Parent view;

    /*
    *Constructeur de la fenetre
     */
    public HomeWindowView(Parking parking) {
        view = createHomeView(parking);
    }

    /*
    *Getter de la view
     */
    public Parent getView() {

        return view;
    }

    /*
    *Getter de la grille de boutton
     */
    public GridPane getGrid() {
        return _grid;
    }

    /*
    *Getter du titre de la fenetre
     */
    public Label getTitle() {
        return _title;
    }

    /*
    *Setter de la view
     */
    public void setView(Parent view) {
        this.view = view;
    }

    /*
    *Fonction qui creer la view avec tout ses composants
     */
    public AnchorPane createHomeView(Parking parking) {
        AnchorPane anchor = new AnchorPane();
        initializeComponent();
        createGrid(_grid, parking);
        VBox gridpart = new VBox(_grid);
        VBox headerpart = new VBox(_title);
        anchor.getChildren().add(headerpart);
        anchor.getChildren().add(gridpart);

        return anchor;

    }

    /*
    *Methode qui initialise les composants de la fenetre
     */
    public void initializeComponent() {
        _grid = new GridPane();
        _title = new Label("Parking Helb 2.0");
        _title.setPrefSize(1000, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));

    }

    /*
    *Methode qui créer dynbamiquement le parking sous forme de grille de boutton
     */
    public void createGrid(GridPane grid, Parking parking) {

        int column = parking.getSizeParking();
        if (column % _row == 0) {
            column = column / _row;

        } else {

            column = (column / _row) + 1;

        }

        grid.setPadding(new Insets(100, 10, 10, 10));
        grid.setVgap(70);
        grid.setHgap(0);
        int cpt = 0;

        for (int i = 0; i < _row; i++) {
            for (int j = 0; j < column; j++) {
                Button button_parking_place = new Button();
                if (cpt < parking.getSizeParking()) {
                    colorParkingPlace(button_parking_place, parking.getParkingspace()[cpt]);
                    button_parking_place.setPrefWidth(200);
                    button_parking_place.setPrefHeight(100);
                    cpt++;

                    grid.add(button_parking_place, j, i);
                }
            }

        }

    }

    /*
    *Methode qui change la couleure des bouttons en fonction du type de vehicule
    *sur la place de parking
     */
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

    /*
    *Fonction qui update la view , utile ici uniquement pour le fichier
    * de simulation
     */
    public void updateview(Parking _parking) {
        _grid.getChildren().clear();
        createGrid(_grid, _parking);

    }

}
