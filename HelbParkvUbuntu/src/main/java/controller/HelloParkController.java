package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Parking;

/**
 *
 * Class correspondant au controller principal Il instancie l'objet principal,
 * une seule foix ici Permets de mieux gérer l'utilisation de cet objet
 */
public class HelloParkController {

    private LaunchWindowController _homecontroller;
    private Parking _parking;

    /*
    Constructeur de la class
     */
    public HelloParkController(Stage stage, Scene scene) {
        _parking = new Parking();
        _homecontroller = new LaunchWindowController(scene, stage, _parking);
        Scene s = new Scene(_homecontroller.getViewController().getView());
        stage.setScene(s);
        stage.show();

    }

}
