/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ForListDAO;
import it.unitn.disi.wp.servizioSanitario.entities.utils.ForListElement;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giacomo
 */
public class ForListDAOImplementation extends AbstractDAO implements ForListDAO {

    public ForListDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public List<ForListElement> getExamsByPatient(Integer id_pat) throws DaoException  {
        try{
            List<ForListElement> lis = new ArrayList<>();
            String query = "SELECT * FROM examforlist WHERE patient=? ORDER BY prescrdate DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            ForListElement elem;
            while(res.next()) {
                elem = new ForListElement();
                elem.setIdDisp(res.getInt("id_disp"));
                elem.setCardname(res.getString("cardname"));
                elem.setPrescrby(res.getString("prescrby"));
                elem.setPrescrdate(res.getString("prescrdate"));
                elem.setMadeby(res.getString("madeby"));
                elem.setMadedate(res.getString("madedate"));
                lis.add(elem);
            }
            res.close();
            st.close();
            return lis;

        }   catch (SQLException ex) {
            Logger.getLogger(ExamDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<ForListElement> getPrescriptionsByPatient(Integer id_pat) throws DaoException  {
        try{
            List<ForListElement> lis = new ArrayList<>();
            String query = "SELECT * FROM prescrforlist WHERE patient=? ORDER BY prescrdate DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            ForListElement elem;
            while(res.next()) {
                elem = new ForListElement();
                elem.setIdDisp(res.getInt("id_disp"));
                elem.setCardname(res.getString("cardname"));
                elem.setPrescrby(res.getString("prescrby"));
                elem.setPrescrdate(res.getString("prescrdate"));
                elem.setMadeby(res.getString("madeby"));
                elem.setMadedate(res.getString("madedate"));
                lis.add(elem);
            }
            res.close();
            st.close();
            return lis;

        }   catch (SQLException ex) {
            Logger.getLogger(ExamDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<ForListElement> getVisitsByPatient(Integer id_pat) throws DaoException  {
        try{
            List<ForListElement> lis = new ArrayList<>();
            String query = "SELECT * FROM visitforlist WHERE patient=? ORDER BY prescrdate DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            ForListElement elem;
            while(res.next()) {
                elem = new ForListElement();
                elem.setIdDisp(res.getInt("id_disp"));
                elem.setCardname(res.getString("cardname"));
                elem.setPrescrby(res.getString("prescrby"));
                elem.setPrescrdate(res.getString("prescrdate"));
                elem.setMadeby(res.getString("madeby"));
                elem.setMadedate(res.getString("madedate"));
                lis.add(elem);
            }
            res.close();
            st.close();
            return lis;

        }   catch (SQLException ex) {
            Logger.getLogger(ExamDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override 
    public List<ForListElement> getForBookingByPatient(Integer id_pat) throws DaoException  {
        try{
            List<ForListElement> lis = new ArrayList<>();
            String query = "SELECT * FROM (SELECT *, 'E' AS type FROM examforlist UNION SELECT *, 'V' AS type  FROM visitforlist)"
                    + "AS X WHERE madedate IS NULL AND patient=? ORDER BY prescrdate DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            ForListElement elem;
            while(res.next()) {
                elem = new ForListElement();
                elem.setIdDisp(res.getInt("id_disp"));
                elem.setCardname(res.getString("cardname"));
                elem.setPrescrby(res.getString("prescrby"));
                elem.setPrescrdate(res.getString("prescrdate"));
                elem.setMadeby(res.getString("madeby"));
                elem.setMadedate(res.getString("madedate"));
                elem.setType((res.getString("type").charAt(0) == 'E') ? TypeDisposition.E : TypeDisposition.S);
                lis.add(elem);
            }
            res.close();
            st.close();
            return lis;

        }   catch (SQLException ex) {
            Logger.getLogger(ExamDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
}
