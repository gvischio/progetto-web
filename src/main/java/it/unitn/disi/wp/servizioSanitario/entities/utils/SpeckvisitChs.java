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
public class SpeckvisitChs {
    int id;
//    tipo visita "ecografia"
    String name;
//    data prescrizione
    Date visdate;
//    data effettuazione
    Date madedate;
//    nome medico
    String firstname;
    String lastname;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisdate(Date visdate) {
        this.visdate = visdate;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getVisdate() {
        return visdate;
    }

    public Date getMadedate() {
        return madedate;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
                                    
}
