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
public class ParkingSpace {

    private Date _date;
    private int parking_space_number;
    private ParkingSpaceStatus _status;
    private Vehicule _vehicule;
    private int total_price;

    public ParkingSpace(Date _date, int parking_space_number, ParkingSpaceStatus _status, Vehicule _vehicule) {
        this._date = _date;
        this.parking_space_number = parking_space_number;
        this._status = _status;
        this._vehicule = _vehicule;
        this.total_price = getTypePrice(_vehicule.getType());
    }

    public ParkingSpace() {
    }

    public Date getDate() {
        return _date;
    }

    public int getParking_space_number() {
        return parking_space_number;
    }

    public ParkingSpaceStatus getStatus() {
        return _status;
    }

    public Vehicule getVehicule() {
        return _vehicule;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }

    public void setParking_space_number(int parking_space_number) {
        this.parking_space_number = parking_space_number;
    }

    public void setStatus(ParkingSpaceStatus _status) {
        this._status = _status;
    }

    public void setVehicule(Vehicule _vehicule) {
        this._vehicule = _vehicule;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "ParkingSpace{" + "_date=" + _date + ", parking_space_number=" + parking_space_number + ", _status=" + _status + ", _vehicule=" + _vehicule + ", total_price=" + total_price + '}';
    }

    public void freeParkingPlace() {
        this._status = ParkingSpaceStatus.FREE;
        this._vehicule = new Vehicule(TypeOfVehicule.NONE, "none");
        this.total_price = 0;
    }

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
