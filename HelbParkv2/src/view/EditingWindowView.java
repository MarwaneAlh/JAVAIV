/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.TypeOfVehicule;
import model.Vehicule;

/**
 *
 * @author Marwa
 */
public class EditingWindowView {

    private Label _title, _status, _type, _numberplate, _total_price;
    private Button freePlaceButton, quitbutton, typechange, numberplatechange;
    private Parent view;
    private ComboBox _typevalue;
    private TextField numberplate;

    public EditingWindowView() {
        view = createView();
    }

    public ComboBox getTypevalue() {
        return _typevalue;
    }

    public Parent getEditView() {
        return view;
    }

    public Button getFreePlaceButton() {
        return freePlaceButton;
    }

    public Button getQuitbutton() {
        return quitbutton;
    }

    public Label getTitle() {
        return _title;
    }

    public Label getStatus() {
        return _status;
    }

    public Label getType() {
        return _type;
    }

    public Label getNumberplate() {
        return _numberplate;
    }

    public Label getTotal_price() {
        return _total_price;
    }

    public Button getTypechange() {
        return typechange;
    }

    public Button getNumberplatechange() {
        return numberplatechange;
    }

    public TextField getNumberPlateTextField() {
        return numberplate;
    }

    private AnchorPane createView() {
        AnchorPane anchor = new AnchorPane();

        editButtonFreeUp();
        editButtonQuit();
        editTitle();
        _status = createLabel("Status : ");

        _type = createLabel("Type Véhicule : ");
        editComboBox();

        typechange = editButtonEditDataVehicule("Changer type");
        HBox vehiculeType = new HBox(_type, _typevalue, typechange);
        vehiculeType.setMargin(_typevalue, new Insets(10));
        vehiculeType.setAlignment(Pos.CENTER);

        _numberplate = createLabel("Immatriculation : ");
        numberplatechange = editButtonEditDataVehicule("Editer");
        numberplate = new TextField();
        HBox numberplateEdit = new HBox(_numberplate, numberplate, numberplatechange);
        numberplateEdit.setMargin(_numberplate, new Insets(10));
        numberplateEdit.setAlignment(Pos.CENTER);

        _total_price = createLabel("Total à payer : ");
        VBox header = new VBox(_title);
        VBox toedit = new VBox(_status, vehiculeType, numberplateEdit, _total_price,
                freePlaceButton, quitbutton);
        toedit.setMargin(quitbutton, new Insets(40));
        toedit.setMargin(freePlaceButton, new Insets(40));
        toedit.setAlignment(Pos.CENTER);

        VBox all = new VBox(header, toedit);
        anchor.getChildren().add(all);

        return anchor;

    }

    private void editComboBox() {
        _typevalue = new ComboBox();
        _typevalue.setDisable(true);
        _typevalue.setStyle("-fx-opacity: 1.0;"
                + " -fx-text-fill:"
                + " black;-fx-background-color: white");
        for (TypeOfVehicule enumvalue : TypeOfVehicule.values()) {
            _typevalue.getItems().add(enumvalue);

        }

    }

    private void editButtonFreeUp() {
        freePlaceButton = new Button("Libérer l'emplacement");
        freePlaceButton.setPrefSize(500, 50);
        freePlaceButton.setFont(new Font("Calibri", 40));

        freePlaceButton.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-border-color: #A2BA91; "
                + "-fx-background-color: #D3E9D4; ");
    }

    private void editButtonQuit() {
        quitbutton = new Button("Quitter");
        quitbutton.setPrefSize(200, 50);
        quitbutton.setFont(new Font("Calibri", 40));
        quitbutton.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-background-color: #F6CDC9; "
                + "-fx-border-color: #BA676F;");
    }

    private void editTitle() {
        _title = new Label("Emplacement : ");
        _title.setPrefSize(1000, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));

    }

    private Label createLabel(String text) {
        Label toreturn = new Label(text);
        toreturn.setFont(new Font("Calibri", 30));
        return toreturn;
    }

    private Button editButtonEditDataVehicule(String text) {
        Button btn = new Button(text);
        btn.setFont(new Font("Calibri", 20));
        btn.setPrefSize(150, 10);
        btn.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-background-color: #D9E8FB; "
                + "-fx-border-color: #9DACC8;");
        return btn;
    }

}
