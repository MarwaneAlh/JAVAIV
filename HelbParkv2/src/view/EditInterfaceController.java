/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.ParkingSpace;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import model.Parking;
import model.ParkingSpaceStatus;
import model.Vehicule;

/**
 *
 * @author Marwa
 */
public class EditInterfaceController implements Initializable {

    @FXML
    private Label _title, _status, _type, _numberplate, total_price;
    Scene scene;
    Button quitbtn;
    Parking _parking;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void generateEditScene(ParkingSpace parkingspace, Stage stage, Parking parking) {
        AnchorPane anchor = new AnchorPane();
        _parking = parking;
        _title = new Label("Emplacement : " + parkingspace.getParking_space_number());
        _status = new Label("Status : " + parkingspace.getStatus());
        _type = new Label("Type Véhicule : " + parkingspace.getVehicule().getType());
        _numberplate = new Label("Immatriculation : " + parkingspace.getVehicule().getNumberplate());
        total_price = new Label("Total à payer : " + parkingspace.getTotal_price() + " euro");

        quitbtn = new Button("Libérer l'emplacement");
        quitbtn.setPrefSize(500, 50);
        quitbtn.setFont(new Font("Calibri", 40));

        quitbtn.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-border-color: #A2BA91; "
                + "-fx-background-color: #D3E9D4; ");

        clickFreeUpSpace(quitbtn, stage, parkingspace);

        _title.setPrefSize(1000, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));

        VBox header = new VBox(_title);
        VBox toedit = new VBox(_status, _type, _numberplate, total_price, quitbtn);
        toedit.setMargin(quitbtn, new Insets(40));
        toedit.setAlignment(Pos.CENTER);
        _status.setFont(new Font("Calibri", 30));
        _type.setFont(new Font("Calibri", 30));
        _numberplate.setFont(new Font("Calibri", 30));
        total_price.setFont(new Font("Calibri", 30));

        VBox all = new VBox(header, toedit);
        anchor.getChildren().add(all);

        scene = new Scene(anchor);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

    }

    private void clickFreeUpSpace(Button freeplace, Stage stage, ParkingSpace tomidfy) {
        freeplace.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _parking= new Parking();
                _parking.getParkingspace()[tomidfy.getParking_space_number()].setStatus(ParkingSpaceStatus.FREE);

                System.out.println(_parking.getParkingspace()[tomidfy.getParking_space_number()].getStatus());
                FXMLDocumentController open = new FXMLDocumentController();

                open.showScene(_parking, stage);

            }

        });

    }

}
