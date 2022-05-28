/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marwa
 */
public class Vehicule {
    
    private TypeOfVehicule _type;
    private String numberplate;

    public Vehicule(TypeOfVehicule _type, String numberplate) {
        this._type = _type;
        this.numberplate = numberplate;
    }

    public TypeOfVehicule getType() {
        return _type;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setType(TypeOfVehicule _type) {
        this._type = _type;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }
    
    
    
}
