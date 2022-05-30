/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Parking;
import model.ParkingSpace;
import model.ParkingSpaceStatus;
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

                if (!_view.getNumberplate().getText().equals("XX") && !_view.getTypevalue().getValue().equals("NONE")) {

                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setNumberplate(_view.getNumberPlateTextField().getText());
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setType(TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString()));
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .setTotal_price(_parking.getParkingspace()[parkingspace.getParking_space_number()]
                                    .getTypePrice(TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString())));
                    _parking.getParkingspace()[parkingspace.getParking_space_number()].setStatus(ParkingSpaceStatus.OCCUPIED);

                }
                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage, _path_text,false);
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
                        _parking, scene, stage, _path_text,false);
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
        String date = r.getToday_date();
        String namedirectory = date.replaceAll("/", "");
        File file = new File(_path_text + "/tickets");
        file.mkdir();
        File datasave = new File(_path_text + "/tickets/" + namedirectory);
        datasave.mkdir();
        String namefile = parkingspace.getVehicule().getNumberplate() + r.getMovie_ticket_type_extension();
        File tickettosave = new File(_path_text + "/tickets/" + namedirectory + "/" + namefile + ".txt");

        try {
            setTicket(tickettosave, r);
            tickettosave.createNewFile();
        } catch (IOException ex) {
            System.out.println("ERROR" + ex.getMessage());
        }

    }

    private void setTicket(File ticket, Receipt receipt) {
        try {
            FileWriter tickettosavewithvalue = new FileWriter(ticket);
            tickettosavewithvalue.write(receipt.toString());
            tickettosavewithvalue.close();
        } catch (IOException ex) {
            System.out.println("ERROR" + ex.getMessage());
        }
    }

}
