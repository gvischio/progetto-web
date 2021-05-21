/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
import it.unitn.disi.wp.servizioSanitario.entities.utils.ForListElement;
import java.util.List;

/**
 *
 * @author giacomo
 */
public interface ForListDAO {
    List<ForListElement> getExamsByPatient(Integer id_pat) throws DaoException ;
    List<ForListElement> getPrescriptionsByPatient(Integer id_pat) throws DaoException ;
    List<ForListElement> getVisitsByPatient(Integer id_pat) throws DaoException ;
    List<ForListElement> getForBookingByPatient(Integer id_pat) throws DaoException;
}


