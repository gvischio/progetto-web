/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.entities;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.utils.AbstractEntity;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Role;
import it.unitn.disi.wp.servizioSanitario.utils.Sha256;
import java.util.Objects;

/**
 *
 * @author Mike_TdT
 */
public class User extends AbstractEntity{
    
    private String email;
    private String password;
    private Role ruolo;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRuolo() {
        return ruolo;
    }

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.email);
        hash = 17 * hash + Objects.hashCode(this.password);
        hash = 17 * hash + Objects.hashCode(this.ruolo);
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
        final User other = (User) obj;
        return true;
    }
    
    @Override
    protected void validaSalva(DAOFactory daoFactory) throws DaoException {
        if (email == null || email.equals("")) {
            setError("email", "Non può essere lasciato vuoto");
        }
        if (email.length() > 255)
        {
            setError("email", "Non può contenere più di 40 caratteri");
        }
        if (password == null || password.equals("") || password.equals(Sha256.doHash(""))) {
            setError("password", "Non può essere lasciato vuoto");
        }
        //CONTROLLO RUOLO
    }
            
    //CRIPTAZIONE E/O DECRIPTAZIONE PASSWORD
}
