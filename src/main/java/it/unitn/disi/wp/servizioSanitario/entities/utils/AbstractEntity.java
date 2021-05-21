/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities.utils;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Mike_TdT
 */
public abstract class AbstractEntity implements Serializable{
    
    
    //isValidOnCreate chiama validaCrea/validaSalva (controllo errori)
    //serve un Hash o Map per salvare gli errori?!
    
    protected Integer id;
    
    protected final Map<String, Set<String>> error;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AbstractEntity() {
        this.error = new LinkedHashMap<> ();
    }
    
    public boolean isValidOnCreate(DAOFactory daoFactory) throws DaoException {
        this.clearError();
        this.validaSalva(daoFactory);
        return this.error.isEmpty();
    }
    
    public boolean isValidOnUpdate(DAOFactory daoFactory) throws DaoException {
        this.clearError();
        this.validaSalva(daoFactory);
        return this.error.isEmpty();
    }
    
    public boolean isValidOnDestroy(DAOFactory dAOFactory) {
            this.clearError();
            this.validaDistruggi(dAOFactory);
            return this.error.isEmpty();
    }
    
    protected void validaCrea(DAOFactory daoFactory) throws DaoException{
    }

    ;
    protected void validaUpdeita(DAOFactory daoFactory) throws DaoException{
    }

    ;
    protected void validaSalva(DAOFactory daoFactory) throws DaoException{
    }

    ;
    protected void validaDistruggi(DAOFactory daoFactory) {
    }

    ;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final AbstractEntity other = (AbstractEntity) obj;
        return true;
    }

    public void setError(String field, String error) {
        Set<String> fieldErrors = this.error.get(field);
        if (fieldErrors == null) {
                this.error.put(field, new HashSet<String>());
                fieldErrors = this.error.get(field);
        }
        fieldErrors.add(error);
    }
    
    public Map<String, Set<String>> getErrors() {
        return this.error;
    }

    public Set<String> getFieldErrors(String field) {
        return this.error.get(field);
    }

    private void clearError() {
        this.error.clear();
    }
}
