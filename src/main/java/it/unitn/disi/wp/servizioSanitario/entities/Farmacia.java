/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Farmacia extends User{
    
    private String piva;
    private String city;
    private int ddistrict;

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDdistrict() {
        return ddistrict;
    }

    public void setDdistrict(int ddistrict) {
        this.ddistrict = ddistrict;
    }

    public Farmacia() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.piva);
        hash = 67 * hash + Objects.hashCode(this.city);
        hash = 67 * hash + this.ddistrict;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Farmacia other = (Farmacia) obj;
        return true;
    }

}
