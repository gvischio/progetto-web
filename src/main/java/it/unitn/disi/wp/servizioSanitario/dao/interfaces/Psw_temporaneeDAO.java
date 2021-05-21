/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Psw_temporanee;

/**
 *
 * @author Mike_TdT
 */
public interface Psw_temporaneeDAO {
    
    Psw_temporanee getByIdUser(Integer id) throws DaoException;
    Psw_temporanee getById(Integer id) throws DaoException;
    boolean addPsw(Psw_temporanee psw) throws DaoException;
    Psw_temporanee getByCode(String casuale) throws DaoException;
    boolean deletePsw(Psw_temporanee psw) throws DaoException;
    
}
