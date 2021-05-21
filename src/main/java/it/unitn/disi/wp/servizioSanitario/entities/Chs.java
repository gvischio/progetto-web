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
public class Chs extends User {
     
    private int cdistrict;
    private String name;

    public int getCdistrict() {
        return cdistrict;
    }

    public void setCdistrict(int cdistrict) {
        this.cdistrict = cdistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.cdistrict;
        hash = 47 * hash + Objects.hashCode(this.name);
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
        final Chs other = (Chs) obj;
        return true;
    }

    public Chs() {
    }
    
    
}
