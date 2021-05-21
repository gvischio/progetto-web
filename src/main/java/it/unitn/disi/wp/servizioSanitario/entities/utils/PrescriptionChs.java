/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities.utils;

import java.sql.Date;

/**
 *
 * @author frape
 */
public class PrescriptionChs {
    
    int id;
    String drugname;
    Date visdate;
    int active;
    String drugstore;
    Date madedate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public void setVisdate(Date visdate) {
        this.visdate = visdate;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setDrugstore(String drugstore) {
        this.drugstore = drugstore;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    public String getDrugname() {
        return drugname;
    }

    public Date getVisdate() {
        return visdate;
    }

    public int getActive() {
        return active;
    }

    public String getDrugstore() {
        return drugstore;
    }

    public Date getMadedate() {
        return madedate;
    }
    
    
}
