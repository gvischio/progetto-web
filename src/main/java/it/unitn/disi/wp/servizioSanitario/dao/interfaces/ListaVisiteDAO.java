/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface ListaVisiteDAO {
    
    ListaVisite getById(Integer id) throws DaoException;
    ListaVisite getByName(String name) throws DaoException;
    List<ListaVisite> getByCategory(Integer category) throws DaoException;
    List<ListaVisite> getAll() throws DaoException;
    
}
