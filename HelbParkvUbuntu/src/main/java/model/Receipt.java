package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * Class de l'objet Receipt qui est le ticket à imprimé
 */
public class Receipt {

    /*
    *_todyadate est la date actuelle
    *_place number est le numero de la place
    *type_vehicule est le type du vehicule
    *price est le prix de la place
    *_price_reducted est le prix aprés la promo
    *_percentage_offer est le pourcentage de la promo
    *_offer est le libélé de la promo
    *platenumber est la plaque d'immatriculation
    *_movie_ticket_type_extension est l'extension du fichier du ticket
    *_movie_code est le code promo du ciné
    *_movie_ticket_type est le type du ticket
    *_movie_ticket_value est le pourentage par rapport au type de ticket
    *_movie_bonus_game est le jeu en fin de ticket
     */
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

    /*
    *Constructeur de l'objet receipt instancie le ticket avec toutes ces valeurs
     */
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

    /*
    *Getter de la date actuelle
     */
    public String getToday_date() {
        return _today_date;
    }

    /*
    *Getter de l'extension de fichier a dicter avant .Txt
     */
    public String getMovie_ticket_type_extension() {
        return _movie_ticket_type_extension;
    }

    /*
    *Methode qui permet d'avoir le libéllé et le pourcentage de la promo
    *a appliqué par rapport au jours ainsi que le type de vehicule
     */
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

    /*
    *Methode qui calule le nouveau prix aprés promo
     */
    private void calculateTotalPrice() {
        this._price_reducted = this._price - (((double) this._price / 100) * _percentage_offer);

    }

    /*
    *Genere de maniere aléatoire le type du ticket
     */
    private void generateTicketType() {
        String[] mixed_type = {"_std", "_sil", "_gol"};
        Random r = new Random();
        _movie_ticket_type_extension = mixed_type[r.nextInt(mixed_type.length - 0)];

    }

    /*
    *Genere de maniere aleatoire le code promo
     */
    private void generatePromotionalCode() {
        Random r = new Random();
        int number = r.nextInt(3000 - 0);
        _movie_code = "BTF" + number;
    }

    /*
    *Genere le pourcentage en fonction du type de ticket
     */
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

    /*
    *Genere le bonus qui correspond soit au tableau 3x3 soit au deux symboles
    *Pour finir verifie si condition gagnant et augment la valeur de la promo
     */
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

    /*
    *Methode servant à parcourir le tableau 3x3 du ticket bonus gold
     */
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

    /*
    *Methode d'affichage du ticket qui va etre utilisé pour la géneration textuelle
    *du ticket
     */
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
