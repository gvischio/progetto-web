/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import it.unitn.disi.wp.servizioSanitario.entities.utils.AbstractEntity;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Visit extends AbstractEntity{
    
    private int patient;
    private int familydoctor;
    private Date visdate;
    private String doctorsays;
    private String motivation;
    private TypeVisit type;
    private Timestamp vistimestamp;
    
    public String getDoctorsays() {
        return doctorsays;
    }

    public void setDoctorsays(String doctorsays) {
        this.doctorsays = doctorsays;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public TypeVisit getType() {
        return type;
    }

    public void setType(TypeVisit type) {
        this.type = type;
    }
    
    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getFamilydoctor() {
        return familydoctor;
    }

    public void setFamilydoctor(int familydoctor) {
        this.familydoctor = familydoctor;
    }

    public Date getVisdate() {
        return visdate;
    }

    public void setVisdate(Date visdate) {
        this.visdate = visdate;
    }

    public String getVistime() {
        return vistimestamp.toString().substring(11, 16);
    }

    public Timestamp getVistimestamp() {
        return vistimestamp;
    }

    public void setVistimestamp(Timestamp vistimestamp) {
        this.vistimestamp = vistimestamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.patient;
        hash = 31 * hash + this.familydoctor;
        hash = 31 * hash + Objects.hashCode(this.visdate);
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
        final Visit other = (Visit) obj;
        return true;
    }
    
    
}
