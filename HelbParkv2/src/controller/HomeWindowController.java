/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Parking;
import view.EditingWindowView;
import view.HomeWindowView;

/**
 *
 * @author Marwa
 */
public class HomeWindowController {

    private Parking _parking;
    private HomeWindowView _view;

    public HomeWindowController(Parking _parking, Scene scene, Stage stage) {
        this._parking = _parking;
        _view = new HomeWindowView(_parking);
        openEditWindow(scene, stage);
    }

    public HomeWindowView getViewController() {
        return _view;
    }

    private void openEditWindow(Scene scene, Stage stage) {
        for (Node node : _view.getGrid().getChildren()) {
            Button button = (Button) node;
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    EditingWindowController controller = new EditingWindowController(_parking, scene, stage);
                    Scene scene = new Scene(controller.getView().getEditView());
                    stage.setScene(scene);
                    stage.show();

                }
            });
        }
    }

}
