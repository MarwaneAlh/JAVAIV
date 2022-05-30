package model;

import java.util.Date;

/**
 * Class de l'objet parkingSpace qui correspond à une place de parking
 */
public class ParkingSpace {

    /*
    *Date permet de stocket la date actuelle
    *parking_space_number permet de stocker le numero de la place de parking
    *_status correspond au status de la place (libre ou occupé)
    *_vehicule correspond au vehicule sur la place de parking
    *total_price est le prix total sans la promotion
     */
    private Date _date;
    private int parking_space_number;
    private ParkingSpaceStatus _status;
    private Vehicule _vehicule;
    private int total_price;

    /*
    *Constructeur de la place de parking
     */
    public ParkingSpace(Date _date, int parking_space_number, ParkingSpaceStatus _status, Vehicule _vehicule) {
        this._date = _date;
        this.parking_space_number = parking_space_number;
        this._status = _status;
        this._vehicule = _vehicule;
        this.total_price = getTypePrice(_vehicule.getType());
    }

    /*
    *Constructeur vide permet de créer l'objet sans parametre
     */
    public ParkingSpace() {
    }

    /*
    Getter de la date
     */
    public Date getDate() {
        return _date;
    }

    /*
    Getter du numero de la place de parking
     */
    public int getParking_space_number() {
        return parking_space_number;
    }

    /*
    Getter du status de la place
     */
    public ParkingSpaceStatus getStatus() {
        return _status;
    }

    /*
    Getter du vehicule sur la place
     */
    public Vehicule getVehicule() {
        return _vehicule;
    }

    /*
    Getter du prix de la place hors promo
     */
    public int getTotal_price() {
        return total_price;
    }

    /*
    Setter de la date
     */
    public void setDate(Date _date) {
        this._date = _date;
    }

    /*
    Setter du numero de la place du parking
     */
    public void setParking_space_number(int parking_space_number) {
        this.parking_space_number = parking_space_number;
    }

    /*
    Setter du status de la place de parking
     */
    public void setStatus(ParkingSpaceStatus _status) {
        this._status = _status;
    }

    /*
    Setter du vehicule de la place de parking
     */
    public void setVehicule(Vehicule _vehicule) {
        this._vehicule = _vehicule;
    }

    /*
    Setter du prix de la place de parking
     */
    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    /*
    *Methode toString utilisé pour tester dans terminal 
     */
    @Override
    public String toString() {
        return "ParkingSpace{" + "_date=" + _date
                + ", parking_space_number=" + parking_space_number
                + ", _status=" + _status + ", _vehicule=" + _vehicule
                + ", total_price=" + total_price + '}';
    }

    /*
    *Methode qui libere une place de parking
     */
    public void freeParkingPlace() {
        this._status = ParkingSpaceStatus.FREE;
        this._vehicule = new Vehicule(TypeOfVehicule.NONE, "none");
        this.total_price = 0;
    }

    /*
    Methode qui donne le prix de la place en fonction du type de vehicule
     */
    public int getTypePrice(TypeOfVehicule type) {
        int price = 0;
        switch (type) {
            case CAR:
                price = 20;
                break;
            case MOTORBIKE:
                price = 10;
                break;
            case TRUCK:
                price = 30;
                break;
        }
        return price;
    }

}
