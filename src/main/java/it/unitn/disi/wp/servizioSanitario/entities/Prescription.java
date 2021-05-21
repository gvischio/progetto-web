/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Prescription extends Disposition {
    
    private int drug;
    private int quantity;
    private int active;
    private int drugstore;
    private String name;
    final public int TICKET = 3;
    
    public int getDrug() {
        return drug;
    }

    public void setDrug(int drug) {
        this.drug = drug;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getDrugstore() {
        return drugstore;
    }

    public void setDrugstore(int drugstore) {
        this.drugstore = drugstore;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.drug;
        hash = 73 * hash + this.quantity;
        hash = 73 * hash + this.active;
        hash = 73 * hash + this.drugstore;
        hash = 73 * hash + Objects.hashCode(this.name);

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
        final Prescription other = (Prescription) obj;
        return true;
    }
    
}