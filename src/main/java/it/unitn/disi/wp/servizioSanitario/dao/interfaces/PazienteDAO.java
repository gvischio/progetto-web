/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PazienteVista;
import java.sql.Date;
import java.util.List;

/**
 * Interface for the PazienteDAOImpl object
 * 
 * @author Mike_TdT
 */
public interface PazienteDAO {
	
    Paziente getById(Integer id) throws DaoException;
    Paziente getByssn(String ssn) throws DaoException;
    List<Paziente> getBynamedate(String firstname, String lastname, Date birthday) throws DaoException;
    List<Paziente> getForRecall(Date min, Date max, int district) throws DaoException;
    List<Paziente> getByfamilydoctor (int familydoctor) throws DaoException;
    List<Paziente> getByDistrict (int district) throws DaoException;
    boolean updatePaz(Integer id, Paziente paz) throws DaoException;
    List<Paziente> getAll() throws DaoException;
    List <PazienteVista> getPazienteVistaPerChs () throws DaoException;
    List <PazienteVista> getPazienteVistaPerDottoreBaseByDottoreId (int dottoreId) throws DaoException;
    List <PazienteVista> getPazienteVistaPerDottoreSpeck () throws DaoException;
    boolean updateDoc(Integer id_pat, Integer id_doc) throws DaoException;
    
}

