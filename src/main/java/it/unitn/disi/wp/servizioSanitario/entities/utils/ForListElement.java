/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities.utils;

/**
 *
 * @author giacomo
 */
public class ForListElement {
    private int id_disp;
    private String cardname;
    private String prescrby;
    private String madeby;
    private String prescrdate;
    private String madedate;
    private TypeDisposition type;

    public int getIdDisp() {
        return id_disp;
    }

    public void setIdDisp(int id_disp) {
        this.id_disp = id_disp;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname;
    }

    public String getPrescrby() {
        return prescrby;
    }

    public void setPrescrby(String prescrby) {
        this.prescrby = prescrby;
    }

    public String getMadeby() {
        return madeby;
    }

    public void setMadeby(String madeby) {
        this.madeby = madeby;
    }

    public String getPrescrdate() {
        return prescrdate;
    }

    public void setPrescrdate(String prescrdate) {
        this.prescrdate = prescrdate;
    }

    public String getMadedate() {
        return madedate;
    }

    public void setMadedate(String madedate) {
        this.madedate = madedate;
    }

    public TypeDisposition getType() {
        return type;
    }

    public void setType(TypeDisposition type) {
        this.type = type;
    }
}
