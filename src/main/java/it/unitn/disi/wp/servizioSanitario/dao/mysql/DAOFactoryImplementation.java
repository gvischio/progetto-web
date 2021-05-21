/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
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
import it.unitn.disi.wp.servizioSanitario.database.Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike_TdT
 */
public class DAOFactoryImplementation extends DAOFactory{

    public DAOFactoryImplementation() {
        Database db = new Database();
            this.conn = db.getConnection();
    }
    
    @Override
    public PazienteDAO getPazienteDAO() {
        return new PazienteDAOImplementation(this);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImplementation(this);
    }

    @Override
    public FarmaciaDAO getFarmaciaDAO() {
        return new FarmaciaDAOImplementation(this);
    }

    @Override
    public DrugDAO getDrugDAO() {
        return new DrugDAOImplementation(this);
    }

    @Override
    public DottoreDAO getDottoreDAO() {
        return new DottoreDAOImplementation(this);
    }

    @Override
    public ChsDAO getChsDAO() {
        return new ChsDAOImplementation(this);
    }

    @Override
    public DispositionDAO getDispositionDAO() {
        return new DispositionDAOImplementation(this);
    }

    @Override
    public ExamDAO getExamDAO() {
        return new ExamDAOImplementation(this);
    }

    @Override
    public PrescriptionDAO getPrescriptionDAO() {
        return new PrescriptionDAOImplementation(this);
    }

    @Override
    public VisitDAO getVisitDAO() {
        return new VisitDAOImplementation(this);
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImplementation(this);
    }

    @Override
    public ListaEsamiDAO getListaEsamiDAO() {
        return new ListaEsamiDAOImplementation(this);
    }

    @Override
    public ListaVisiteDAO getListaVisiteDAO() {
        return new ListaVisiteDAOImplementation(this);
    }

    @Override
    public PhotoDAO getPhotoDAO() {
        return new PhotoDAOImplementation(this);
    }

    @Override
    public DistrictDAO getDistrictDAO() {
        return new DistrictDAOImplementation(this);
    }
    
    @Override
    public SpeckvisitDAO getSpeckvisitDAO() {
        return new SpeckvisitDAOImplementation(this);
    }

    @Override
    public Psw_temporaneeDAO getPswTemporaneeDAO() {
        return new Psw_temporaneeDAOImplementation(this);
    }

    @Override
    public TicketDAO getTicketDAO() {
        return new TicketDAOImplementation(this);
    }
    
    @Override
    public ForListDAO getForListDAO() {
        return new ForListDAOImplementation(this);
    }

    @Override
    public Connection getConn() {
        return conn;
    }

    @Override
    public void closeConn() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOFactoryImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public NotificationDAO getNotificationDAO() {
        return new NotificationDAOImplementation(this);
    }
    
}
