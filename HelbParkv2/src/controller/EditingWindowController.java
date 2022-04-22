/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Parking;
import view.EditingWindowView;

/**
 *
 * @author Marwa
 */
public class EditingWindowController {

    private Parking _parking;
    private EditingWindowView _view;
    private Button freePlaceButton, quitbutton;

    public EditingWindowController(Parking _parking, Scene scene, Stage stage) {
        this._parking = _parking;
        this._view = new EditingWindowView();
        freePlaceButton = _view.getFreePlaceButton();
        quitbutton = _view.getQuitbutton();

        quitButton(scene, stage);

    }

    public EditingWindowView getView() {
        return _view;
    }

    private void quitButton(Scene scene, Stage stage) {
        quitbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage);
                Scene scene = new Scene(controller.getViewController().getView());
                stage.setScene(scene);
                stage.show();

            }
        });
    }

}
