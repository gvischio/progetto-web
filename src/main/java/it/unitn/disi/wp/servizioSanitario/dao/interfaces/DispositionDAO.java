/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Ticket;
import java.sql.Date;
import java.util.List;


/**
 *
 * @author Mike_TdT
 */
public interface DispositionDAO {
    
    Disposition getById(Integer id) throws DaoException;
    List<Disposition> getByPatient(Integer id_pat) throws DaoException;
    List<Disposition> getByFamilyDoctor(Integer id_doc) throws DaoException;
    List<Disposition> getByVisit(Integer id_vis) throws DaoException;
    List<Disposition> getByPaid(Byte paid) throws DaoException;
    List<Disposition> getByTicket(Integer ticket) throws DaoException;
    List<Disposition> getByType(char type) throws DaoException;
    List<Disposition> getByMadeDate(Date date) throws DaoException;
    void setAsPaid (Integer id_disp) throws DaoException;
    boolean deleteDispositionById (Integer id) throws DaoException;
}
