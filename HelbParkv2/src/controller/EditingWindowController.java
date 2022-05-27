/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Parking;
import model.ParkingSpace;
import model.TypeOfVehicule;
import view.EditingWindowView;

/**
 *
 * @author Marwa
 */
public class EditingWindowController {

    private Parking _parking;
    private EditingWindowView _view;
    private Button freePlaceButton, quitbutton, typechange, numberplatechange;
    private Label _title, _status, _type, _numberplate, _total_price;
    private ComboBox _typevalue;
    private TextField numberplate;

    public EditingWindowController(Parking _parking, ParkingSpace parkingspace, Scene scene, Stage stage) {
        this._parking = _parking;
        this._view = new EditingWindowView();
        freePlaceButton = _view.getFreePlaceButton();
        quitbutton = _view.getQuitbutton();
        _title = _view.getTitle();
        _status = _view.getStatus();
        _type = _view.getType();
        _numberplate = _view.getNumberplate();
        _total_price = _view.getTotal_price();
        typechange = _view.getTypechange();
        numberplatechange = _view.getNumberplatechange();
        numberplate = _view.getNumberPlateTextField();
        _typevalue = _view.getTypevalue();
        editAllComponentWithParkingSpace(parkingspace);
        quitButton(scene, stage, parkingspace);
        freeSpaceButton(parkingspace, stage, scene);
        typechangeButton();
 
        editNumberPlateButtonOnclick();

    }

    public EditingWindowView getView() {
        return _view;
    }

    private void typechangeButton() {
        typechange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                _typevalue.setDisable(false);
            }
        });

    }

    private void quitButton(Scene scene, Stage stage, ParkingSpace parkingspace) {
        quitbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!numberplate.getText().equals("none") && !_typevalue.getValue().equals("NONE")) {
                    System.out.println("BON");
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setNumberplate(numberplate.getText());
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setType(TypeOfVehicule.valueOf(_typevalue.getValue().toString()));
                }

                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage);
                Scene scene = new Scene(controller.getViewController().getView());
                stage.setScene(scene);
                stage.show();

            }
        });
    }

    private void freeSpaceButton(ParkingSpace parkingspace, Stage stage, Scene scene) {
        freePlaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _parking.getParkingspace()[parkingspace.getParking_space_number()].freeParkingPlace();
                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage);
                Scene scene = new Scene(controller.getViewController().getView());
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    private void editAllComponentWithParkingSpace(ParkingSpace parkingspace) {
        _title.setText("Emplacement : " + parkingspace.getParking_space_number());
        _status.setText("Status : " + parkingspace.getStatus());
        _type.setText("Type Véhicule : ");
        _typevalue.setValue(parkingspace.getVehicule().getType());
        _numberplate.setText(("Immatriculation : "));
        _total_price.setText("Total à payer : " + parkingspace.getTotal_price());
        numberplate.setText(parkingspace.getVehicule().getNumberplate());
        numberplate.setEditable(false);
    }

    private void editNumberPlateButtonOnclick() {
        numberplatechange.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                numberplate.setEditable(true);
            }
        });
    }

}
