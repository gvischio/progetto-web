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
public class ExamChs {
    int id;
    String examname;
    Date visdate;
    Date madedate;
    String providedby;
    int paid;

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getPaid() {
        return paid;
    }

    public int getId() {
        return id;
    }

    public String getExamname() {
        return examname;
    }

    public Date getVisdate() {
        return visdate;
    }

    public Date getMadedate() {
        return madedate;
    }

    public String getProvidedby() {
        return providedby;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExamname(String examname) {
        this.examname = examname;
    }

    public void setVisdate(Date visdate) {
        this.visdate = visdate;
    }

    public void setMadedate(Date madedate) {
        this.madedate = madedate;
    }

    public void setProvidedby(String providedby) {
        this.providedby = providedby;
    }
    
}
