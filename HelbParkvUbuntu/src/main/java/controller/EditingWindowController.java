package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Parking;
import model.ParkingSpace;
import model.ParkingSpaceStatus;
import model.Receipt;
import model.TypeOfVehicule;
import view.EditingWindowView;

/**
 * Class editincontroller, correspondant au controller la fenêtre editingwindow
 * Se controller gère toutes les interactons de la fenêtre editingwindow
 *
 */
public class EditingWindowController {

    /*
    *Attribut parking servant à stocker l'objet parking qui est le contenant des places de parking
    *Attribut parking servant à stocker l'objet parking qui est le contenant des places de parking
    *Le string _path text ici correspond au lien ou va être stocké le dossier des tickets
     */
    private Parking _parking;
    private EditingWindowView _view;
    private String _path_text;

    /*
    *Constructeur de la Class tous les composants ainsi que 
    *les fonctions d'interactions sont instancié ici.
     */
    public EditingWindowController(Parking _parking, ParkingSpace parkingspace, Scene scene, Stage stage, String _path) {
        this._parking = _parking;
        this._view = new EditingWindowView();
        this._path_text = _path;
        editAllComponentWithParkingSpace(parkingspace);
        quitButton(scene, stage, parkingspace);
        freeSpaceButton(parkingspace, stage, scene);
        typechangeButton();
        editNumberPlateButtonOnclick();

    }

    /*
    Constructeur avec le lien il va être utile afin de pouvoir utiliser 
    *les méthodes de la Class sans créer d'objet avec trop de paramètres.
     */
    public EditingWindowController(String _path) {
        _path_text = _path;
    }

    /*
    Guetteur pour récupérer la view de la classe
     */
    public EditingWindowView getView() {
        return _view;
    }

    /*
    Méthode qui gère l'interaction du changement du type du vehicule.
     */
    private void typechangeButton() {
        _view.getTypechange().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                ParkingSpace p = new ParkingSpace();
                _view.getTypevalue().setDisable(false);
                _view.getTypechange().setText("Appliquer");

                if (_view.getTypechange().getText().equals("Appliquer")) {
                    _view.getTotal_price().setText("Total à payer :" + p.getTypePrice(
                            TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString())));
                }
            }
        });

    }

    /*
    Méthode qui gère l'interaction du bouton quitté dans la fenêtre d'édition 
    À la suite elle va modifier les attributs de la place de parking avec les informations de la fenêtre d'édition.
    Pour finir affichage de la scene avec le parking
     */
    private void quitButton(Scene scene, Stage stage, ParkingSpace parkingspace) {
        _view.getQuitbutton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!_view.getNumberplate().getText().equals("XX") && !_view.getTypevalue().getValue().equals("NONE")) {

                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setNumberplate(_view.getNumberPlateTextField().getText());
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .getVehicule().setType(TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString()));
                    _parking.getParkingspace()[parkingspace.getParking_space_number()]
                            .setTotal_price(_parking.getParkingspace()[parkingspace.getParking_space_number()]
                                    .getTypePrice(TypeOfVehicule.valueOf(_view.getTypevalue().getValue().toString())));
                    _parking.getParkingspace()[parkingspace.getParking_space_number()].setStatus(ParkingSpaceStatus.OCCUPIED);

                }
                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage, _path_text);
                Scene scene = new Scene(controller.getViewController().getView());
                stage.setScene(scene);
                stage.show();

            }
        });
    }

    /*
    Méthode qui gere l'action du bouton libérer place de parking 
    *Elle modifie les attributs des places de parking en paramètre pour lui attribue les valeurs d'une place vide
    *Elle va également grace à la fonction printTicket , sauvegarder le ticket dans un dossier. 
     */
    private void freeSpaceButton(ParkingSpace parkingspace, Stage stage, Scene scene) {
        _view.getFreePlaceButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                printTicket(parkingspace);
                _parking.getParkingspace()[parkingspace.getParking_space_number()].freeParkingPlace();
                HomeWindowController controller = new HomeWindowController(
                        _parking, scene, stage, _path_text);
                Scene scene = new Scene(controller.getViewController().getView());
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    /*
    Méthode qui va afficher les propriétés de l'objet place de parking dans les composants graphiques de la fenêtre d'édition.
     */
    private void editAllComponentWithParkingSpace(ParkingSpace parkingspace) {
        _view.getTitle().setText("Emplacement : " + parkingspace.getParking_space_number());
        _view.getStatus().setText("Status : " + parkingspace.getStatus());
        _view.getType().setText("Type Véhicule : ");
        _view.getTypevalue().setValue(parkingspace.getVehicule().getType());
        _view.getNumberplate().setText(("Immatriculation : "));
        _view.getTotal_price().setText("Total à payer : " + parkingspace.getTotal_price());
        _view.getNumberPlateTextField().setText(parkingspace.getVehicule().getNumberplate());
        _view.getNumberPlateTextField().setEditable(false);
    }

    /*
    Fonction qui edit le numero d'immatriculation de la place de parking
    Suite au clique sur bouton Editer place de parking
     */
    private void editNumberPlateButtonOnclick() {
        _view.getNumberplatechange().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _view.getNumberPlateTextField().setEditable(true);
            }
        });
    }

    /*
    Fonction qui va générer le ticket de la place de parking
    Il génère un objet receipt qui possède toutes les propriétés du ticket.
    Pour finir il génère un dossier Tickets dans le path choisi au début
    Le dossier comprend alors les tickets dans un sous-dossier à la date du jour
    Ticket composé du type de  ticket et de plaque d'immatriculation.
     */
    public void printTicket(ParkingSpace parkingspace) {

        Receipt r = new Receipt(parkingspace);
        String date = r.getToday_date();
        String namedirectory = date.replaceAll("/", "");
        File file = new File(_path_text + "/tickets");
        file.mkdir();
        File datasave = new File(_path_text + "/tickets/" + namedirectory);
        datasave.mkdir();
        String namefile = parkingspace.getVehicule().getNumberplate() + r.getMovie_ticket_type_extension();
        File tickettosave = new File(_path_text + "/tickets/" + namedirectory + "/" + namefile + ".txt");

        try {
            setTicket(tickettosave, r);
            tickettosave.createNewFile();
        } catch (IOException ex) {
            System.out.println("ERROR" + ex.getMessage());
        }

    }

    /*
    Fonction pour modifier le contenue du ticket
    Créer pour modularité du code
     */
    private void setTicket(File ticket, Receipt receipt) {
        try {
            FileWriter tickettosavewithvalue = new FileWriter(ticket);
            tickettosavewithvalue.write(receipt.toString());
            tickettosavewithvalue.close();
        } catch (IOException ex) {
            System.out.println("ERROR" + ex.getMessage());
        }
    }

}
