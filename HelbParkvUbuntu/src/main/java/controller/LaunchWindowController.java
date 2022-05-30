/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import model.Parking;
import view.LaunchWindowView;

/**
 *
 * @author Marwa
 */
public class LaunchWindowController {

    private LaunchWindowView _view;
    private Parking _parking;

    public LaunchWindowController(Scene scene, Stage stage, Parking _parking) {
        _view = new LaunchWindowView();
        this._parking = _parking;
        _view.getPath().setEditable(false);
        chooseBtn();
        openHomeWindow(scene, stage, _parking);
        launchSimFileBtn(scene, stage, _parking);

    }

    public LaunchWindowView getViewController() {
        return _view;
    }

    private void chooseBtn() {
        _view.getChoose_path_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int response = fc.showOpenDialog(fc);
                if (response == JFileChooser.APPROVE_OPTION) {
                    _view.getPath().setText(fc.getSelectedFile().toPath().toString());
                }
            }
        });
    }

    private void openHomeWindow(Scene scene, Stage stage, Parking _parking) {
        _view.getSave_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!_view.getPath().getText().isEmpty()
                        && !_view.getPath().getText().equals("Veuillez d'abord choisir un emplacement!")) {
                    HomeWindowController controller = new HomeWindowController(
                            _parking, scene, stage, _view.getPath().getText(), false);
                    Scene scene = new Scene(controller.getViewController().getView());
                    stage.setScene(scene);
                    stage.show();

                } else {
                    _view.getPath().setText("Veuillez d'abord choisir un emplacement!");
                }
            }
        });
    }

    private void launchSimFileBtn(Scene scene, Stage stage, Parking _parking) {
        _view.getLaunchsimfile().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!_view.getPath().getText().isEmpty()
                        && !_view.getPath().getText().equals("Veuillez d'abord choisir un emplacement!")) {
                    HomeWindowController controller = new HomeWindowController(
                            _parking, scene, stage, _view.getPath().getText(), true);
                    Scene scene = new Scene(controller.getViewController().getView());
                    stage.setScene(scene);
                    stage.show();
                    new java.util.Timer().schedule(new java.util.TimerTask() {
                        @Override
                        public void run() {
                            controller.simulationfile(scene);
                        }
                    }, 2000);

                } else {
                    _view.getPath().setText("Veuillez d'abord choisir un emplacement!");
                }
            }
        });
    }

}
