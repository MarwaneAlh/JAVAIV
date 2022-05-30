package model;

/**
 *
 * Class de l'objet vehicule
 */
public class Vehicule {

    /*
    *type correspond au type de vehicule
    *numberplate correspond à la plaque d'immatriculation
     */
    private TypeOfVehicule _type;
    private String numberplate;

    /*
    *Constructeur de l'objet vehicule
     */
    public Vehicule(TypeOfVehicule _type, String numberplate) {
        this._type = _type;
        this.numberplate = numberplate;
    }

    /*
    *Getter du type de vehicule
     */
    public TypeOfVehicule getType() {
        return _type;
    }

    /*
    *Getter de la plaque d'immatriculation
     */
    public String getNumberplate() {
        return numberplate;
    }

    /*
    *Setter du type de vehicule
     */
    public void setType(TypeOfVehicule _type) {
        this._type = _type;
    }

    /*
    *Setter de la plaque d'immatriculation
     */
    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

}
