package model;

import java.util.Date;

/**
 * Class de l'objet parking qui est le contenant des places de parkings
 *
 */
public class Parking {

    /*
    *_parkingspace correspond au tableau stockant toutes les places de parkings
    *la size permet de gerer le nombre de place de parking totals
     */
    private ParkingSpace[] _parkingspace;
    private int _sizeParking = 20;

    /*
    *Constructeur de l'objet parking ainsi que des places de parkings
     */
    public Parking() {
        ParkingSpace[] parking = new ParkingSpace[_sizeParking];
        for (int i = 0; i < _sizeParking; i++) {
            parking[i] = new ParkingSpace(new Date(2, 2, 1998),
                    i, ParkingSpaceStatus.FREE,
                    new Vehicule(TypeOfVehicule.NONE,
                            "XX"));
        }
        this._parkingspace = parking;
    }

    /*
    *Getter du contenant parking
     */
    public ParkingSpace[] getParkingspace() {
        return _parkingspace;
    }

    /*
    *Getter de la taille du parking
     */
    public int getSizeParking() {
        return _sizeParking;
    }

    /*
    *Setter de la place du parking
     */
    public void setParkingspace(ParkingSpace[] _parkingspace) {
        this._parkingspace = _parkingspace;
    }

    /*
    *Setter de la taille du parking
     */
    public void setSizeParking(int _sizeParking) {
        this._sizeParking = _sizeParking;
    }

}
