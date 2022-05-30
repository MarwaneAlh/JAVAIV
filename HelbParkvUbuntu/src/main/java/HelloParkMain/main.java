package HelloParkMain;

import controller.HelloParkController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * /Class main, servant à lancer l'application La méthode star instancie un
 * composant Anchorpane Ce composant va être modifié par la suite pour afficher
 * le contenu adéquat grace à l'objet main Controller MainController représente
 * le contrôler principal
 */
public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane parent = new AnchorPane();
        Scene scene = new Scene(parent);
        HelloParkController maincontroller = new HelloParkController(stage, scene);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
