/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marwa
 */
public class Receipt {
    
    private String _today_date;
    private int _place_number;
    private TypeOfVehicule _type_vehicule;
    private int _price;

    public Receipt(ParkingSpace _parkingspace) {
        Date date = new Date();
        DateFormat formatter= new SimpleDateFormat("dd/MM/yy");
        this._today_date = formatter.format(date);
        this._place_number =_parkingspace.getParking_space_number();
        this._type_vehicule =_parkingspace.getVehicule().getType();
        this._price = _parkingspace.getTotal_price();
        
    }

    @Override
    public String toString() {
        return "Receipt{" + "_today_date=" + _today_date + ", _place_number=" 
                + _place_number + ", _type_vehicule=" + _type_vehicule + ", _price=" + _price +'}';
    }
    
    
    
    
    
    
}
