/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Parking;
import view.homescene;

/**
 *
 * @author Marwa
 */
public class HomeSceneController {
    
    private Parking _parking;
    homescene home;
    AnchorPane root ;
    

    public HomeSceneController() {
        root = new AnchorPane();
        Stage stage = new Stage();
        home = new homescene(root,stage,_parking);
        
        
    }
    
    
    
    
    
    
}
