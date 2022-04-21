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
    private int numberplate;

    public Vehicule(TypeOfVehicule _type, int numberplate) {
        this._type = _type;
        this.numberplate = numberplate;
    }

    public TypeOfVehicule getType() {
        return _type;
    }

    public int getNumberplate() {
        return numberplate;
    }

    public void setType(TypeOfVehicule _type) {
        this._type = _type;
    }

    public void setNumberplate(int numberplate) {
        this.numberplate = numberplate;
    }
    
    
    
}
