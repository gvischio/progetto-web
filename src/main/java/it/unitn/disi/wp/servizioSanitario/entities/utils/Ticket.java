/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities.utils;

import java.util.Date;

/**
 *
 * @author Giacomo
 */
public class Ticket {
    private int id_disp;
    private int id_pat;
    private String name;
    private Date madedate;
    private TypeDisposition type;
    private int ticket;
    private String doneby;
    
    public Ticket () { 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getMadeDate() {
        return madedate;
    }

    public void setMadeDate(Date madedate) {
        this.madedate = madedate;
    }

    public TypeDisposition getType() {
        return type;
    }

    public void setType(TypeDisposition type) {
        this.type = type;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getDoneBy() {
        return doneby;
    }

    public void setDoneBy(String doneby) {
        this.doneby = doneby;
    }

    public int getIdDisp() {
        return id_disp;
    }

    public void setIdDisp(int id_disp) {
        this.id_disp = id_disp;
    }

    public int getIdPat() {
        return id_pat;
    }

    public void setIdPat(int id_pat) {
        this.id_pat = id_pat;
    }
    
    
}
