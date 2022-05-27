/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Marwa
 */
public class Receipt {
    
    private Date _today_date;
    private int _place_number;
    private TypeOfVehicule _type_vehicule;
    private int _price;
    private ParkingSpace _parkingplace;

    public Receipt(Date _today_date,ParkingSpace _parkingspace) {
        this._today_date = _today_date;
        this._place_number =_parkingspace.getParking_space_number();
        this._type_vehicule =_parkingspace.getVehicule().getType();
        this._price = _parkingspace.getTotal_price();
    }
    
    
    
}
