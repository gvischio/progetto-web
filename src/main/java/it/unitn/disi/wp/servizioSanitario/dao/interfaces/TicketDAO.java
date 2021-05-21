/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Ticket;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author giacomo
 */
public interface TicketDAO
{
    Ticket getById(Integer id) throws DaoException;
    List<Ticket> getPaidByPatient(Integer id_pat) throws DaoException;
}
