/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.utils.AbstractEntity;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class Psw_temporanee extends AbstractEntity{
    
    private int id_user;
    private Timestamp timestamp;
    private String casuale;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getCasuale() {
        return casuale;
    }

    public void setCasuale(String casuale) {
        this.casuale = casuale;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id_user;
        hash = 83 * hash + Objects.hashCode(this.timestamp);
        hash = 83 * hash + Objects.hashCode(this.casuale);
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
        final Psw_temporanee other = (Psw_temporanee) obj;
        return true;
    }
    
    @Override
    protected void validaSalva(DAOFactory daoFactory) throws DaoException {
        //No need, input non dall'utente
    }
    
}
