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

    private HomeWindowController _homecontroller;
    private Parking _parking;

    public HelloParkController(Stage stage, Scene scene) {
        _parking = new Parking();
        _homecontroller = new HomeWindowController(_parking, scene, stage);
        Scene s = new Scene(_homecontroller.getViewController().getView());
        stage.setScene(s);
        stage.show();

    }

}
