/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PrescriptionChs;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PrescriptionPaid;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface PrescriptionDAO {
    
    Prescription getById(Integer id) throws DaoException;
    List<Prescription> getByDrug(Integer drug) throws DaoException;
    List<Prescription> getByActive(byte active) throws DaoException;
    List<Prescription> getByPatient(Integer id_pat) throws DaoException;
    List<Prescription> getActiveByPatient(Integer id_pat) throws DaoException;
    boolean addPrescription (Prescription prescription) throws DaoException;
    void setActive (Integer id_pres, Integer id_drugstore) throws DaoException;
    List <PrescriptionChs> getPrescriptionChsByPatient (int pazienteId) throws DaoException;
//    Prescription getByDrugstore(Integer drugstore) throws DaoException;
    List<PrescriptionPaid> getPaidByDistrict (Integer district) throws DaoException;
    List<PrescriptionPaid> getPaidByFarmacia (Integer farmaciaId) throws DaoException;
        
}
