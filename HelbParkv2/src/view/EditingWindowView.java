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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


/**
 *
 * @author Marwa
 */
public class EditingWindowView {

    private Label _title, _status, _type, _numberplate, _total_price;
    private Button freePlaceButton, quitbutton;
    private Parent view;

    public EditingWindowView() {
        view = createView();

    }
    

    public void setTitle(Label _title) {
        this._title = _title;
    }

    public void setStatus(Label _status) {
        this._status = _status;
    }

    public void setType(Label _type) {
        this._type = _type;
    }

    public void setNumberplate(Label _numberplate) {
        this._numberplate = _numberplate;
    }

    public void setTotal_price(Label _total_price) {
        this._total_price = _total_price;
    }

    public void setFreePlaceButton(Button freePlaceButton) {
        this.freePlaceButton = freePlaceButton;
    }

    public void setQuitbutton(Button quitbutton) {
        this.quitbutton = quitbutton;
    }

    private AnchorPane createView() {
        AnchorPane anchor = new AnchorPane();

        editButtonFreeUp();
        editButtonQuit();
        editTitle();
        _status = createLabel("Status : ");
        _type = createLabel("Type Véhicule : ");
        _numberplate = createLabel("Immatriculation : ");
        _total_price = createLabel("Total à payer : ");
        VBox header = new VBox(_title);
        VBox toedit = new VBox(_status, _type, _numberplate, _total_price,
                freePlaceButton, quitbutton);
        toedit.setMargin(quitbutton, new Insets(40));
        toedit.setMargin(freePlaceButton, new Insets(40));
        toedit.setAlignment(Pos.CENTER);

        VBox all = new VBox(header, toedit);
        anchor.getChildren().add(all);

        return anchor;

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

}
