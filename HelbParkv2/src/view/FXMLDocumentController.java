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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Parking;
import sun.plugin.javascript.navig.Anchor;

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
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        initializeComponent();
        createGrid(_grid, _parking);
        VBox gridpart = new VBox(_grid);
        VBox headerpart = new VBox(_title);
        
        AnchorPane anchor = new AnchorPane(headerpart, gridpart);

        Scene scene = new Scene(anchor);
        stage.setScene(scene);
        stage.show();

    }

    public static void createGrid(GridPane grid, Parking parking) {
        //grid.setMinSize(500, 500);

        grid.setPadding(new Insets(100, 10, 10, 10));
        grid.setVgap(70);
        grid.setHgap(0);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Button b = new Button(parking.getParkingspace()[i].getVehicule().getType().toString());
                b.setPrefWidth(200);
                b.setPrefHeight(100);
                grid.add(b, i, j);
            }

        }

    }

    public void initializeComponent() {
        _grid = new GridPane();
        _parking = new Parking();
        _title = new Label("Parking Helb 2.0");
        _title.setPrefSize(800, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));
        

    }

}
