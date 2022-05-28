/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Parking;
import model.ParkingSpace;
import model.Receipt;
import model.TypeOfVehicule;
import view.EditingWindowView;

/**
 *
 * @author Marwa
 */
public class EditingWindowController {

    private Parking _parking;
    private EditingWindowView _view;
    private String _path_text;

    public EditingWindowController(Parking _parking, ParkingSpace parkingspace, Scene scene, Stage stage, String _path) {
        this._parking = _parking;
        this._view = new EditingWindowView();
        this._path_text = _path;
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
        _view.getTypechange().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ParkingSpace p = new ParkingSpace();
                _view.getTypevalue().setDisable(false);
                _view.getTypechange().setText("Appliquer");

                if (_view.getTypechange().getText().equals("Appliquer")) {
                    _view.getTotal_price().setText("Total à payer :" + p.getTypePrice(
                            TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString())));
                }
            }
        });

    }

    private void quitButton(Scene scene, Stage stage, ParkingSpace parkingspace) {
        _view.getQuitbutton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!_view.getNumberplate().getText().equals("none") && !_view.getTypevalue().getValue().equals("NONE")) {

                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setNumberplate(_view.getNumberPlateTextField().getText());
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setType(TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString()));
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .setTotal_price(_parking.getParkingspace()[parkingspace.getParking_space_number()]
                                    .getTypePrice(TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString())));

                }
                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage, _path_text);
                Scene scene = new Scene(controller.getViewController().getView());
                stage.setScene(scene);
                stage.show();

            }
        });
    }

    private void freeSpaceButton(ParkingSpace parkingspace, Stage stage, Scene scene) {
        _view.getFreePlaceButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printTicket(parkingspace);
                _parking.getParkingspace()[parkingspace.getParking_space_number()].freeParkingPlace();
                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage, _path_text);
                Scene scene = new Scene(controller.getViewController().getView());
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    private void editAllComponentWithParkingSpace(ParkingSpace parkingspace) {
        _view.getTitle().setText("Emplacement : " + parkingspace.getParking_space_number());
        _view.getStatus().setText("Status : " + parkingspace.getStatus());
        _view.getType().setText("Type Véhicule : ");
        _view.getTypevalue().setValue(parkingspace.getVehicule().getType());
        _view.getNumberplate().setText(("Immatriculation : "));
        _view.getTotal_price().setText("Total à payer : " + parkingspace.getTotal_price());
        _view.getNumberPlateTextField().setText(parkingspace.getVehicule().getNumberplate());
        _view.getNumberPlateTextField().setEditable(false);
    }

    private void editNumberPlateButtonOnclick() {
        _view.getNumberplatechange().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _view.getNumberPlateTextField().setEditable(true);
            }
        });
    }

    private void printTicket(ParkingSpace parkingspace) {

        Receipt r = new Receipt(parkingspace);
        System.out.println(_path_text);
        //String test = "C:\\Users\Marwa\OneDrive\Bureau";
        File file = new File(_path_text);
        boolean bool = file.mkdir();
        if (bool) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Sorry couldn’t create specified directory");
        }

        System.out.println("Recipes : " + r.toString());

    }

}
