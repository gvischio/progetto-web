/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.User;

/**
 * Interface for the UserDAO object
 * 
 * @author Mike_TdT
 */
public interface UserDAO {
    
    boolean checkByEmail(String email) throws DaoException;
    User getByEmail(String email) throws DaoException;
    boolean updateUser(Integer id, User user) throws DaoException;
    User getById(Integer id) throws DaoException;
}
