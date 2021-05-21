/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface CategoryDAO {
    Category getById(Integer id) throws DaoException;
    Category getByName(String name) throws DaoException;
    List<Category> getAll() throws DaoException;
}


