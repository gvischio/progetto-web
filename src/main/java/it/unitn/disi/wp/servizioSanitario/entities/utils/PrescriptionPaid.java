/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities.utils;

import java.sql.Timestamp;

/**
 *
 * @author simmf
 */
public class PrescriptionPaid {
    private Timestamp data;
    private String drug;
    private String drugstore;
    private String doctorCode;
    private String ssnPaziente;
    private int ticket;

    
    public void setData(Timestamp time){
        this.data = time;
    }
    
    public Timestamp getData(){
        return data;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getDrugstore() {
        return drugstore;
    }

    public void setDrugstore(String drugstore) {
        this.drugstore = drugstore;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getSsnPaziente() {
        return ssnPaziente;
    }

    public void setSsnPaziente(String ssnPaziente) {
        this.ssnPaziente = ssnPaziente;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }
    
}
