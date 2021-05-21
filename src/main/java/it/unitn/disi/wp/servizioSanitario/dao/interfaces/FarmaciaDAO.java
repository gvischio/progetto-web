/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Farmacia;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface FarmaciaDAO {
    
    Farmacia getById(Integer id) throws DaoException;
    List<Farmacia> getByDdistrict(Integer ddistrict) throws DaoException;
    List<Farmacia> getByCity(String city) throws DaoException;
    
}
