/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Parking;

/**
 *
 * @author Marwa
 */
public class HelloParkController {

    private LaunchWindowController _homecontroller;
    private Parking _parking;

    public HelloParkController(Stage stage, Scene scene) {
        _parking = new Parking();
        _homecontroller = new LaunchWindowController(scene, stage,_parking);
        Scene s = new Scene(_homecontroller.getViewController().getView());
        stage.setScene(s);
        stage.show();

    }

}
