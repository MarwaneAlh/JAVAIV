/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Marwa
 */
public class Receipt {

    private String _today_date;
    private int _place_number;
    private TypeOfVehicule _type_vehicule;
    private int _price;
    private double _price_reducted;
    private int _percentage_offer;
    private String _offer;
    private String _platenumber;
    private String _movie_ticket_type_extension;
    private String _movie_code;
    private String _movie_ticket_type;
    private int _movie_ticket_value;
    private String _movie_bonus_game;

    public Receipt(ParkingSpace _parkingspace) {
        Date date = new Date();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        this._today_date = formatter.format(date);
        this._place_number = _parkingspace.getParking_space_number();
        this._type_vehicule = _parkingspace.getVehicule().getType();
        this._price = _parkingspace.getTotal_price();
        this._platenumber = _parkingspace.getVehicule().getNumberplate();
        generateTicketType();
        getDayOffer(date);
        calculateTotalPrice();
        generatePromotionalCode();
        generateMoviePromo();
        generateMovieBonusGame();

    }

    public String getToday_date() {
        return _today_date;
    }

    public String getMovie_ticket_type_extension() {
        return _movie_ticket_type_extension;
    }

    private void getDayOffer(Date _date) {
        DateFormat days = new SimpleDateFormat("EEEE");
        String day = days.format(_date);
        switch (day) {
            case "mardi":
                if (this._type_vehicule == TypeOfVehicule.MOTORBIKE) {
                    _offer = "Mardi - Moitié prix pour les motos";
                    _percentage_offer = 50;
                }
                break;
            case "mercredi":
                if (this._platenumber.toLowerCase().contains("p")) {
                    _offer = "Mercredi - -25% pour vehicule ayant une plaque contenant P";
                    _percentage_offer = 25;
                }
                break;
            case "vendredi":
                if (this._type_vehicule == TypeOfVehicule.TRUCK) {
                    _offer = "Vendredi - Moitié prix pour les camionettes";
                    _percentage_offer = 50;
                }
                break;
            case "samedi":
                DateFormat getdaynumber = new SimpleDateFormat("dd");
                int numberday = Integer.valueOf(getdaynumber.format(_date));
                if (numberday % 2 == 0) {
                    _offer = "Samedi - Moitié prix ,samedi pair";
                    _percentage_offer = 50;
                }
                break;
            default:
                _offer = "Prix de base";
                _percentage_offer = 0;

        }

    }

    private void calculateTotalPrice() {
        this._price_reducted = this._price - (((double) this._price / 100) * _percentage_offer);

    }

    private void generateTicketType() {
        String[] mixed_type = {"_std", "_sil", "_gol"};
        Random r = new Random();
        _movie_ticket_type_extension = mixed_type[r.nextInt(mixed_type.length - 0)];

    }

    private void generatePromotionalCode() {
        Random r = new Random();
        int number = r.nextInt(3000 - 0);
        _movie_code = "BTF" + number;
    }

    private void generateMoviePromo() {
        int[] std = {5, 10};
        int[] slv = {10, 15};
        int[] gld = {20, 40};
        Random r = new Random();
        int randompromo = r.nextInt(std.length - 0);
        switch (_movie_ticket_type_extension) {
            case "_sil":
                _movie_ticket_value = slv[randompromo];
                _movie_ticket_type = "Ticket Silver : Valeur : ";
                _movie_bonus_game = "SILVERBONUS";
                break;
            case "_std":
                _movie_ticket_value = std[randompromo];
                _movie_ticket_type = "Ticket Standar : Valeur : ";
                _movie_bonus_game = "NONE";
                break;
            case "_gol":
                _movie_ticket_value = gld[randompromo];
                _movie_ticket_type = "Ticket Gold : Valeur : ";
                _movie_bonus_game = "GOLDBONUS";
                break;

        }
    }

    private void generateMovieBonusGame() {
        String game;
        String[] symbole = {"O", "X", "P"};
        String[] gold_symbole = {"P", "A", "R", "K", "H", "E", "L", "B"};
        Random r = new Random();
        switch (_movie_bonus_game) {
            case "SILVERBONUS":
                game = symbole[r.nextInt(symbole.length)] + symbole[r.nextInt(symbole.length)];
                if (game.charAt(0) == game.charAt(1)) {
                    _movie_ticket_value = _movie_ticket_value * 2;
                    _movie_bonus_game += "\nFélicication Bonus !";
                }
                _movie_bonus_game = game;
                break;

            case "GOLDBONUS":
                _movie_bonus_game = "";
                String[][] bonusgold = new String[3][3];
                for (int i = 0; i < bonusgold.length; i++) {

                    for (int j = 0; j < bonusgold.length; j++) {
                        bonusgold[i][j] = gold_symbole[r.nextInt(gold_symbole.length)];
                        if (j == bonusgold.length - 1) {
                            _movie_bonus_game += bonusgold[i][j] + "\n";
                        } else {
                            _movie_bonus_game += bonusgold[i][j];
                        }
                    }
                }
                if (checkMovieBonusGold(bonusgold)) {
                    _movie_ticket_value = _movie_ticket_value * 2;
                     _movie_bonus_game += "\nFélicication Bonus !";
                }
                break;
            case "NONE":
                break;

        }
    }

    private boolean checkMovieBonusGold(String[][] bonus) {
        for (int i = 0; i < bonus.length - 1; i++) {
            for (int j = 0; j < bonus.length - 1; j++) {
                if (i + 1 > bonus.length - 1 || j + 1 > bonus.length - 1) {
                    return false;
                }

                if (bonus[i][j] == bonus[i + 1][j]
                        || bonus[i][j] == bonus[i][j + 1]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {

        return "----------------------------------------"
                + "\nDate : " + _today_date
                + "\nPlace occupée : " + _place_number
                + "\nType de véhicule : " + _type_vehicule
                + "\nImmatriculation : " + _platenumber
                + "\nPrix de base : " + _price + " euros"
                + "\nRéduction : " + _offer
                + "\nTotal a payer : " + _price_reducted + " euros"
                + "\nCode Promo Ciné : " + _movie_code
                + "\n" + _movie_ticket_type + _movie_ticket_value + "%"
                + "\n" + "Jeu :" + "\n" + _movie_bonus_game
                + "\n----------------------------------------";
    }

}
