/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao;

import it.unitn.disi.wp.servizioSanitario.dao.interfaces.CategoryDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.FarmaciaDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ForListDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.Psw_temporaneeDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.TicketDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import java.sql.Connection;

/**
 *
 * @author Mike_TdT
 */
public abstract class DAOFactory {
    
    protected Connection conn;
    public abstract Connection getConn();
    public abstract UserDAO getUserDAO();
    public abstract PazienteDAO getPazienteDAO();
    public abstract FarmaciaDAO getFarmaciaDAO();
    public abstract DrugDAO getDrugDAO();
    public abstract DottoreDAO getDottoreDAO();
    public abstract ChsDAO getChsDAO();
    public abstract DispositionDAO getDispositionDAO();
    public abstract ExamDAO getExamDAO();
    public abstract PrescriptionDAO getPrescriptionDAO();
    public abstract VisitDAO getVisitDAO();
    public abstract CategoryDAO getCategoryDAO();
    public abstract ListaEsamiDAO getListaEsamiDAO();
    public abstract ListaVisiteDAO getListaVisiteDAO();
    public abstract PhotoDAO getPhotoDAO();
    public abstract DistrictDAO getDistrictDAO();
    public abstract SpeckvisitDAO getSpeckvisitDAO();
    public abstract Psw_temporaneeDAO getPswTemporaneeDAO();
    public abstract TicketDAO getTicketDAO();
    public abstract ForListDAO getForListDAO();
    public abstract NotificationDAO getNotificationDAO();
    public abstract void closeConn();
    
}
