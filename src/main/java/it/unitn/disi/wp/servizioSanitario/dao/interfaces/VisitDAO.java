/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface VisitDAO {
    
    Visit getById(Integer id) throws DaoException;
    Visit getLastByPatient(Integer id_pat) throws DaoException;
    Visit getNextByPatient(Integer id_pat) throws DaoException;
    List<Visit> getByPatient(Integer id_pat) throws DaoException;
    List<Visit> getByFamilyDoctor(Integer id_doc) throws DaoException;
    List<Visit> getByVisDate(Date visdate) throws DaoException;
    boolean updateAnamnesiVisit (Integer id, Visit visit) throws DaoException;
    Visit insertVisitDottore (Integer pazienteId, Integer dottoreId) throws DaoException;
    List<Appuntamento> getTodayVisitsByFamilyDoctor(int familydoctor) throws DaoException;
    Timestamp getNextVisitAvailableForFamilyDoctor(int fam_doc) throws DaoException;
    Boolean insertVisitPaziente (Visit visit, Timestamp tmstp) throws DaoException;
}
