/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import it.unitn.disi.wp.servizioSanitario.entities.utils.SpeckvisitChs;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface SpeckvisitDAO {
    
    Speckvisit getById(Integer id) throws DaoException;
    List<Speckvisit> getBySpecialist(Integer specialist) throws DaoException;
    List<Speckvisit> getByVisitCode(Integer visitcode) throws DaoException;
    List<Speckvisit> getByPatient(Integer id_pat) throws DaoException;
    List<Speckvisit> getToDoByPatient(Integer id_pat) throws DaoException;
    boolean updateEsitoSpeckvisit (Integer id, Speckvisit visit) throws DaoException;
    boolean addSpeckvisit(Speckvisit speckvisit) throws DaoException;
    boolean BookSpeckVisit(Integer id_exam, Timestamp date, Integer doc);
    List <SpeckvisitChs> getSpeckvisitChsByPatiente (Integer patientId) throws DaoException;
    List<String> addRichiamo (Chs chs, Integer code, Integer maxage, Integer minage, String sex) throws DaoException;
    List<Appuntamento> getTodayVisitsForSpecialist(Integer id_speckdoc) throws DaoException;
}
