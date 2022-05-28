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

    public HomeWindowController(Parking _parking, Scene scene, Stage stage, String _path) {
        this._parking = _parking;
        this._path_text = _path;
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

}
