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
 * Class qui correpond a la fenetre d'edtion de place de parking
 */
public class EditingWindowView {

    private Label _title, _status, _type, _numberplate, _total_price;
    private Button freePlaceButton, quitbutton, typechange, numberplatechange;
    private Parent view;
    private ComboBox _typevalue;
    private TextField numberplate;

    /*
    *Constructeur de la fenetre 
     */
    public EditingWindowView() {
        view = createView();
    }

    /*
    *Getter de la combobox contenant le type de vehicule
     */
    public ComboBox getTypevalue() {
        return _typevalue;
    }

    /*
    *Getter de la fenetre view
     */
    public Parent getEditView() {
        return view;
    }

    /*
    *Getter du boutton qui libere la place de parking
     */
    public Button getFreePlaceButton() {
        return freePlaceButton;
    }

    /*
    *Getter du boutton qui quitte la fenetre
     */
    public Button getQuitbutton() {
        return quitbutton;
    }

    /*
    *Getter du titre de la fenetre
     */
    public Label getTitle() {
        return _title;
    }

    /*
    *Getter du status du vehicule
     */
    public Label getStatus() {
        return _status;
    }

    /*
    *Getter du type de vehicule
     */
    public Label getType() {
        return _type;
    }

    /*
    *Getter de la plaque d'immatriculation
     */
    public Label getNumberplate() {
        return _numberplate;
    }

    /*
    *Getter du prix total du vehicule
     */
    public Label getTotal_price() {
        return _total_price;
    }

    /*
    *Getter du bouton changement de type de vehicule
     */
    public Button getTypechange() {
        return typechange;
    }

    /*
    *Getter du numero de plaque
     */
    public Button getNumberplatechange() {
        return numberplatechange;
    }

    /*
    *Getter du textfield contenant le numero de plaque
     */
    public TextField getNumberPlateTextField() {
        return numberplate;
    }

    /*
    *Methode modulaire pour creer la view avec tout ses composants
     */
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
        numberplate = new TextField("");
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

    /*
    *Mthode qui modifie la combobox
     */
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

    /*
    *Methode qui modifie le bouton freePlaceButton avec du style et un text
     */
    private void editButtonFreeUp() {
        freePlaceButton = new Button("Libérer l'emplacement");
        freePlaceButton.setPrefSize(500, 50);
        freePlaceButton.setFont(new Font("Calibri", 30));

        freePlaceButton.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-border-color: #A2BA91; "
                + "-fx-background-color: #D3E9D4; ");
    }

    /*
    *Methode qui edit le bouton quit
     */
    private void editButtonQuit() {
        quitbutton = new Button("Quitter");
        quitbutton.setPrefSize(200, 50);
        quitbutton.setFont(new Font("Calibri", 30));
        quitbutton.setStyle("-fx-border-radius: 5px;"
                + " -fx-border-width: 2px;"
                + "-fx-background-color: #F6CDC9; "
                + "-fx-border-color: #BA676F;");
    }

    /*
    *Methode qui applique style sur titre
     */
    private void editTitle() {
        _title = new Label("Emplacement : ");
        _title.setPrefSize(1000, 100);
        _title.setAlignment(Pos.CENTER);
        _title.setFont(new Font("Calibri", 50));

    }

    /*
    *Methode pour creer automatiquement label avec un certain style
     */
    private Label createLabel(String text) {
        Label toreturn = new Label(text);
        toreturn.setFont(new Font("Calibri", 30));
        return toreturn;
    }

    /*
    *Methode pour edit le bouton editVehicule
     */
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
