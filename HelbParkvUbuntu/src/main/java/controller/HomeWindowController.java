package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Parking;
import model.ParkingSpaceStatus;
import model.TypeOfVehicule;
import model.Vehicule;

import view.HomeWindowView;

/**
 * Controller de la fenêtre qui affiche les places de parking
 *
 */
public class HomeWindowController {

    /*
    *Attribut parking ,correspondant à l'objet parking 
    *La fenetre est également stocker comme _view
    *L'attribut whichPlace sert a savoir quel place de parking 
    *Le path correspondant au path du dossier des tickets
    *L
     */
    private Parking _parking;
    private HomeWindowView _view;
    private int whichPlace;
    private String _path_text;

    /*
    Constructeur du controller avec les objets de l'applications
    *Methode open qui permet d'ouvrir la fenetre
     */
    public HomeWindowController(Parking _parking, Scene scene, Stage stage, String _path) {
        this._parking = _parking;
        this._path_text = _path;
        _view = new HomeWindowView(_parking);
        openEditWindow(scene, stage);

    }

    /*
    *getter de la fenetre
     */
    public HomeWindowView getViewController() {
        return _view;
    }

    /*
    *Methode qui lance la fenetre d'edition dans la scene 
    *suite au clique sur la place de parking Permet également de récuperer quel place
    *A été cliqué
     */
    public void openEditWindow(Scene scene, Stage stage) {

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

    /*
    *Fonction pour gérer le fichier de simulation
    *Le fichier est récupérer grace au chemin d'absolue
    *Le fichier est ensuite ouvert et traité
    *Pour le traiter des fonctions de convertions de la ligne de text
    *en un objet traitable au seins du parking ont été utilisé
    *pour finir modification du parking 
     */
    public void simulationfile(Scene scene) throws FileNotFoundException, IOException, InterruptedException {

        File absoulepath = new File(".");
        String test = absoulepath.getAbsolutePath();
        String finalsimpath = test.substring(0, test.length() - 1) + "simfile.txt";
        File simfile = new File(finalsimpath);

        FileReader readsim = new FileReader(simfile);
        BufferedReader br = new BufferedReader(readsim);
        String line;
        int cpt = 0;
        while ((line = br.readLine()) != null) {
            if (cpt == 20) {
                cpt = 0;
            }
            int time = getTimeSimeString(line);
            String vehicule_type = getTypeVehicule(line);
            String vehicule_plate_number = getPlateNumber(line);
            TimeUnit.SECONDS.sleep(time);
            if (_parking.getParkingspace()[cpt].getStatus() == ParkingSpaceStatus.OCCUPIED) {
                _parking.getParkingspace()[cpt].setVehicule(new Vehicule(getSimeVehicule(vehicule_type), vehicule_plate_number));
                EditingWindowController c = new EditingWindowController(_path_text);
                c.printTicket(_parking.getParkingspace()[cpt]);
            } else {
                _parking.getParkingspace()[cpt].setVehicule(new Vehicule(getSimeVehicule(vehicule_type), vehicule_plate_number));
                _parking.getParkingspace()[cpt].setStatus(ParkingSpaceStatus.OCCUPIED);
            }
            Platform.runLater(() -> {
                _view.updateview(_parking);
            });

            cpt++;
        }
        br.close();
        readsim.close();
    }

    /*
    *Methode pour récupérer le temps du fichier de simulation
     */
    private int getTimeSimeString(String line) {
        int time;
        Boolean flag = Character.isDigit(line.charAt(1));
        if (flag) {
            time = Character.getNumericValue(line.charAt(0) + line.charAt(1));
        } else {
            time = Character.getNumericValue(line.charAt(0));

        }
        return time;
    }

    /*
    *Methode pour récupérer le type de vehicule du fichier de simulation
     */
    private String getTypeVehicule(String line) {
        String vehicule_type = line.substring(2, line.length() - 3);
        if (vehicule_type.charAt(0) == ',') {
            vehicule_type = vehicule_type.substring(1, vehicule_type.length());
        }
        return vehicule_type;
    }

    /*
    *Methode pour récupérer la plaque d'immatriculation du fichier de simulation
     */
    private String getPlateNumber(String line) {
        return line.substring(line.length() - 2, line.length());
    }

    /*
    *Methode pour convertir le type de vehicule text en un type de vehicule 
    *de l'enumeration pour le traiter dans le parking
     */
    private TypeOfVehicule getSimeVehicule(String type) {
        switch (type) {
            case "moto":
                return TypeOfVehicule.MOTORBIKE;

            case "camionette":
                return TypeOfVehicule.TRUCK;

            case "voiture":
                return TypeOfVehicule.CAR;

        }
        return TypeOfVehicule.NONE;
    }
}
