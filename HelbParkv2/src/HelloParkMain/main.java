/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelloParkMain;

import controller.HelloParkController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Marwa
 */
public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
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
