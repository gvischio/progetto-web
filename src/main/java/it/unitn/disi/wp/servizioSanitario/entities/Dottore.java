/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDoctor;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Dottore extends Paziente {
    
    private String doc_reg_numb;
    private String city;
    private TypeDoctor type;
    private String specialization;
    private int work_district;

    public Dottore() {
    }

    public String getDoc_reg_numb() {
        return doc_reg_numb;
    }

    public void setDoc_reg_numb(String doc_reg_numb) {
        this.doc_reg_numb = doc_reg_numb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public TypeDoctor getType() {
        return type;
    }

    public void setType(TypeDoctor type) {
        this.type = type;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getWork_district() {
        return work_district;
    }

    public void setWork_district(int work_district) {
        this.work_district = work_district;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.doc_reg_numb);
        hash = 19 * hash + Objects.hashCode(this.city);
        hash = 19 * hash + Objects.hashCode(this.type);
        hash = 19 * hash + Objects.hashCode(this.specialization);
        hash = 19 * hash + this.work_district;
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
        final Dottore other = (Dottore) obj;
        return true;
    }
    
}
