/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import it.unitn.disi.wp.servizioSanitario.entities.utils.AbstractEntity;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Disposition extends AbstractEntity{
    
    private int patient;
    private int familydoctor;
    private int visit;
    private byte paid;
    private int ticket;
    private TypeDisposition type;
    private Date madedate;

    public Disposition() {
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

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public byte getPaid() {
        return paid;
    }

    public void setPaid(byte paid) {
        this.paid = paid;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public TypeDisposition getType() {
        return type;
    }

    public void setType(TypeDisposition type) {
        this.type = type;
    }

    public Date getMadedate() {
        return madedate;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.patient;
        hash = 17 * hash + this.familydoctor;
        hash = 17 * hash + this.visit;
        hash = 17 * hash + this.paid;
        hash = 17 * hash + this.ticket;
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.madedate);
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
    
    public String getName(){
        return "";
    }
}
