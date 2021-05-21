/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import java.util.List;

/**
 * Interface for the DottoreDAO object
 * 
 * @author Mike_TdT
 */
public interface DottoreDAO {
    
    Dottore getById(Integer id) throws DaoException;
    Dottore getByDocRegNumb(String doc) throws DaoException;
    List<Dottore> getBySpec(String specialization) throws DaoException;
    List<Dottore> getByWorkDistrict(int wd) throws DaoException;
    List<Dottore> getByType(char type) throws DaoException;
    List<Dottore> getByTypeExtended(char type) throws DaoException;
}
