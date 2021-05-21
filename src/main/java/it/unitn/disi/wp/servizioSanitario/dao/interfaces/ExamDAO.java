/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import java.sql.Timestamp;
import it.unitn.disi.wp.servizioSanitario.entities.utils.ExamChs;
import java.util.List;

/**
 *
 * @author Mike_TdT
 */
public interface ExamDAO {
    
    Exam getById(Integer id) throws DaoException;
    List<Exam> getByExamCode(Integer examcode) throws DaoException;
    List<Exam> getByProvidedBy(Integer providedby) throws DaoException;
    List<Exam> getByPatient(Integer id_pat) throws DaoException;
    List<Exam> getToDoByPatient(Integer id_pat) throws DaoException;
    boolean addExam (Exam exam) throws DaoException;
    boolean BookExam(Integer id_exam, Timestamp date, Integer doc);
    List<ExamChs> getExamChsByPatient (int pazienteId) throws DaoException;
    boolean updateRisultatoEsame (Exam exam) throws DaoException;
    boolean setEsameEffettuato (Exam exam) throws DaoException;
    List<String> addRichiamo (Chs chs, Integer code, Integer maxage, Integer minage, String sex) throws DaoException;
    List<Appuntamento> getTodayVisitsForChs(Integer id_chs) throws DaoException;
}
