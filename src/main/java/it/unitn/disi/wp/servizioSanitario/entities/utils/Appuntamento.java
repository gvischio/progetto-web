/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities.utils;

import java.sql.Timestamp;

/**
 *
 * @author Giacomo
 */
public class Appuntamento
{
    int idVisita;
    String nomePaziente;
    Timestamp orario;
    String motivazione;

    public Appuntamento(int idVisita, String nomePaziente, Timestamp orario, String motivazione) {
        this.idVisita = idVisita;
        this.nomePaziente = nomePaziente;
        this.orario = orario;
        this.motivazione = motivazione;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public String getNomePaziente() {
        return nomePaziente;
    }

    public void setNomePaziente(String nomePaziente) {
        this.nomePaziente = nomePaziente;
    }

    public Timestamp getOrario() {
        return orario;
    }
    
    public String getOra() {
        return orario.toString().substring(11, 16);
    }

    public void setOrario(Timestamp orario) {
        this.orario = orario;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }
}
