/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * Class de la fenetre de lancment
 */
public class LaunchWindowView {

    private Label _title;
    private Parent view;
    private TextField _path;
    private Button _choose_path_btn;
    private Label _explanation;
    private Button _save_btn;
    private Button launchsimfile;

    /*
    *Constructeur de la fenetre 
     */
    public LaunchWindowView() {
        AnchorPane anchor = new AnchorPane();
        initializeComponent();
        view = createLaunchView();

    }

    /*
    *Getter du bouton choisis
     */
    public Button getChoose_path_btn() {
        return _choose_path_btn;
    }

    /*
    *Getter du bouton choisi
     */
    public Button getSave_btn() {
        return _save_btn;
    }

    /*
    *Getter du texfield contenant le path 
     */
    public TextField getPath() {
        return _path;
    }

    /*
    *Getter de la fenetre de la view
     */
    public Parent getView() {
        return view;
    }

    /*
    *Getter du bouton lancement avec simulation
     */
    public Button getLaunchsimfile() {
        return launchsimfile;
    }

    /*
    *Methode qui initialise tout les composants de la fenetre
     */
    private void initializeComponent() {
        _title = new Label("Parking Helb 2.0");
        _title.setPrefSize(1000, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));

        _path = new TextField();

        _explanation = new Label("Bonjour, bienvenu sur ParkingHelb ,"
                + "veuillez choisir un emplacement pour sauvegarder les tickets de caisse.");
        _explanation.setFont(new Font("Calibri", 20));

        _choose_path_btn = new Button("Choisi");
        _choose_path_btn.setFont(new Font("Calibri", 30));
        _choose_path_btn.setPrefSize(300, 10);
        _choose_path_btn.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-background-color: #D9E8FB; "
                + "-fx-border-color: #9DACC8;");

        _save_btn = new Button("Lancer classique");
        _save_btn.setFont(new Font("Calibri", 30));
        _save_btn.setPrefSize(300, 10);
        _save_btn.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-background-color: #D9E8FB; "
                + "-fx-border-color: #9DACC8;");

        launchsimfile = new Button("Lancer simulation");
        launchsimfile.setFont(new Font("Calibri", 28));
        launchsimfile.setPrefSize(300, 10);
        launchsimfile.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-background-color: #D9E8FB; "
                + "-fx-border-color: #9DACC8;");

    }

    /*
    *Methode pour generer une view avec tout les composants de la fenetre
     */
    private AnchorPane createLaunchView() {
        AnchorPane pane = new AnchorPane();
        HBox btntool = new HBox(_choose_path_btn, _save_btn, launchsimfile);
        VBox allcontent = new VBox(_title, _explanation, _path, btntool);
        pane.getChildren().add(allcontent);
        return pane;
    }

}
