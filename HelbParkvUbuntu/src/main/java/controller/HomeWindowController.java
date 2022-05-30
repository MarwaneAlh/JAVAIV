/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.lang.model.element.Element;
import model.Parking;
import model.ParkingSpaceStatus;
import model.TypeOfVehicule;
import model.Vehicule;

import view.HomeWindowView;

/**
 *
 * @author Marwa
 */
public class HomeWindowController {

    private Parking _parking;
    private HomeWindowView _view;
    private int whichPlace;
    private String _path_text;
    private boolean modesim;

    public HomeWindowController(Parking _parking, Scene scene, Stage stage, String _path, boolean modesim) {
        this._parking = _parking;
        this._path_text = _path;
        this.modesim = modesim;
        _view = new HomeWindowView(_parking);

    }

    public HomeWindowView getViewController() {
        return _view;
    }

    public void openEditWindow(Scene scene, Stage stage) {

        for (Node node : _view.getGrid().getChildren()) {
            Button button = (Button) node;
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    Button _whichBtnClicked = (Button) event.getSource();
                    String whichPlaceString = _whichBtnClicked.getText();
                    if (whichPlaceString.length() <= 4) {
                        whichPlace = Integer.parseInt(String.valueOf(whichPlaceString.charAt(0)));
                    } else {
                        whichPlace = Integer.parseInt(String.valueOf(whichPlaceString.substring(0, 2)));
                    }
                    EditingWindowController controller
                            = new EditingWindowController(_parking,
                                    _parking.getParkingspace()[whichPlace], scene, stage, _path_text);
                    Scene scene = new Scene(controller.getView().getEditView());
                    stage.setScene(scene);
                    stage.show();

                }
            });
        }
    }

    public void simulationfile(Scene scene) throws FileNotFoundException, IOException, InterruptedException {

        File absoulepath = new File(".");
        String test = absoulepath.getAbsolutePath();
        String finalsimpath = test.substring(0, test.length() - 1) + "simfile.txt";
        File simfile = new File(finalsimpath);

        FileReader readsim = new FileReader(simfile);
        BufferedReader br = new BufferedReader(readsim);
        String line;
        int cpt = 0;
        while ((line = br.readLine()) != null) {

            int time = Character.getNumericValue(line.charAt(0));
            String vehicule_type = line.substring(2, line.length() - 3);
            String vehicule_plate_number = line.substring(line.length() - 2, line.length());
            TimeUnit.SECONDS.sleep(time);

            System.out.println("Time: " + time + " type : " + vehicule_type
                    + " Plaque " + vehicule_plate_number);
            _parking.getParkingspace()[cpt].setVehicule(new Vehicule(TypeOfVehicule.CAR, vehicule_plate_number));
            _parking.getParkingspace()[cpt].setStatus(ParkingSpaceStatus.OCCUPIED);
            System.out.println(_parking.getParkingspace()[cpt].getStatus());
            Platform.runLater(() -> {
                _view.updateview(_parking);
            });
            cpt++;
        }
        br.close();
        readsim.close();
    }
}
