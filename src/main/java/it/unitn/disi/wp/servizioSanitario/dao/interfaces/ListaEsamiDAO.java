/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface ListaEsamiDAO {
    
    ListaEsami getById(Integer id) throws DaoException;
    ListaEsami getByName(String name) throws DaoException;
    List<ListaEsami> getByCategory(Integer category) throws DaoException;
    List<ListaEsami> getAll() throws DaoException;
    
}
