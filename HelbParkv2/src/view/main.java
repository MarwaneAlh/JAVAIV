/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.HelloParkController;
import controller.HomeWindowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Parking;

/**
 *
 * @author Marwa
 */
public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //TEST 
        /*EditingWindowView e = new EditingWindowView();
        Scene scene = new Scene(e.view);*/
 /*Parking parking = new Parking();
        HomeWindowView e = new HomeWindowView(parking);
        Scene scene = new Scene(e.getView());
        stage.setScene(scene);
        stage.show();*/
        AnchorPane parent = new AnchorPane();
        Scene scene = new Scene(parent);
        HelloParkController maincontroller = new HelloParkController(stage, scene);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
