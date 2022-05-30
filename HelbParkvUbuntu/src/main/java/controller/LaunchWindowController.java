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
 * Controller de la fenetre de lancement de l'application
 *
 */
public class LaunchWindowController {

    /*
    *Attribut view qui correspond à la fenetre de lancement
    *Attribut parking qui correspond à l'objet parking de l'application
     */
    private LaunchWindowView _view;
    private Parking _parking;

    /*
    *Constructeur du controller qui permet également de l'afficher
    *Les interactions de la fenetre y sont également présent
     */
    public LaunchWindowController(Scene scene, Stage stage, Parking _parking) {
        _view = new LaunchWindowView();
        this._parking = _parking;
        _view.getPath().setEditable(false);
        chooseBtn();
        openHomeWindow(scene, stage, _parking);
        launchSimFileBtn(scene, stage, _parking);

    }

    /*
    *Getter de la fenetre de la view
     */
    public LaunchWindowView getViewController() {
        return _view;
    }

    /*
    *Fonction qui gére le bouton choisi qui permet de choisir un dossier ou stocker
    *les tickets à génénerer par la suite
     */
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

    /*
    *Fonction qui gére clique sur bouton de lancement classque
    *Il ouvre la fenetre qui affiche le parking sans fichier de simulation
    *Verifie également si un dossier à été choisis pour le dossier des tickets
     */
    private void openHomeWindow(Scene scene, Stage stage, Parking _parking) {
        _view.getSave_btn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!_view.getPath().getText().isEmpty()
                        && !_view.getPath().getText().equals("Veuillez d'abord choisir un emplacement!")) {
                    HomeWindowController controller = new HomeWindowController(
                            _parking, scene, stage, _view.getPath().getText());
                    Scene scene = new Scene(controller.getViewController().getView());
                    stage.setScene(scene);
                    stage.show();

                } else {
                    _view.getPath().setText("Veuillez d'abord choisir un emplacement!");
                }
            }
        });
    }

    /*
    *Methode qui gérer le lancement avec le fichier de simulation
    *Il affiche la fenetre avec les places de parking
    *Et lance la methode qui modifie le parking avec le fichier de simulation
    *aprés avoir afficher le parking permet d'eviter probleme avec le Thread
    *principal.
     */
    private void launchSimFileBtn(Scene scene, Stage stage, Parking _parking) {
        _view.getLaunchsimfile().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!_view.getPath().getText().isEmpty()
                        && !_view.getPath().getText().equals("Veuillez d'abord choisir un emplacement!")) {
                    HomeWindowController controller = new HomeWindowController(
                            _parking, scene, stage, _view.getPath().getText());
                    Scene scene = new Scene(controller.getViewController().getView());
                    stage.setScene(scene);
                    stage.show();
                    new java.util.Timer().schedule(new java.util.TimerTask() {
                        @Override
                        public void run() {
                            try {
                                controller.simulationfile(scene);
                            } catch (IOException ex) {
                                Logger.getLogger(LaunchWindowController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(LaunchWindowController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }, 2000);

                } else {
                    _view.getPath().setText("Veuillez d'abord choisir un emplacement!");
                }
            }
        });
    }

}
