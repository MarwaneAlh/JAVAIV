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
public class Parking {

    private ParkingSpace[] _parkingspace;
    private int _sizeParking = 20;

    public Parking() {
        ParkingSpace[] parking = new ParkingSpace[_sizeParking];
        for (int i = 0; i < _sizeParking; i++) {
            parking[i] = new ParkingSpace(new Date(2, 2, 1998),
                    i, ParkingSpaceStatus.OCCUPIED,
                    new Vehicule(TypeOfVehicule.CAR,
                            "C" + i));
        }
        this._parkingspace = parking;
    }

    public ParkingSpace[] getParkingspace() {
        return _parkingspace;
    }

    public int getSizeParking() {
        return _sizeParking;
    }

    public void setParkingspace(ParkingSpace[] _parkingspace) {
        this._parkingspace = _parkingspace;
    }

    public void setSizeParking(int _sizeParking) {
        this._sizeParking = _sizeParking;
    }

}
