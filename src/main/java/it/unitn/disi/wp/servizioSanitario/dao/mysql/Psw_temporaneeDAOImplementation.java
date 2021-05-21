/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.UpdateException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.Psw_temporaneeDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Psw_temporanee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike_TdT
 */
public class Psw_temporaneeDAOImplementation extends AbstractDAO implements Psw_temporaneeDAO{

    public Psw_temporaneeDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Psw_temporanee getByIdUser(Integer id) throws DaoException {
        try {
            Psw_temporanee p = new Psw_temporanee();
            String query = "SELECT * FROM psw_temporanee WHERE id_utente = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                p.setId(res.getInt("id_psw"));
                p.setId_user(res.getInt("id_utente"));
                p.setTimestamp(res.getTimestamp("timestamp"));
                p.setCasuale(res.getString("stringacasuale"));
                res.close();
                st.close();
                return p;
            }
            throw new NotFoundDAOException("Password with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public Psw_temporanee getById(Integer id) throws DaoException {
        try {
            Psw_temporanee p = new Psw_temporanee();
            String query = "SELECT * FROM psw_temporanee WHERE id_psw = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                p.setId(res.getInt("id_psw"));
                p.setId_user(res.getInt("id_utente"));
                p.setTimestamp(res.getTimestamp("timestamp"));
                p.setCasuale(res.getString("stringacasuale"));
                res.close();
                st.close();
                return p;
            }
            throw new NotFoundDAOException("Password not found.");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public Psw_temporanee getByCode(String casuale) throws DaoException {
        try {
            Psw_temporanee p = new Psw_temporanee();
            String query = "SELECT * FROM psw_temporanee WHERE stringacasuale = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, casuale);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                p.setId(res.getInt("id_psw"));
                p.setId_user(res.getInt("id_utente"));
                p.setTimestamp(res.getTimestamp("timestamp"));
                p.setCasuale(res.getString("stringacasuale"));
                res.close();
                st.close();
                return p;
            }
            throw new NotFoundDAOException("Password not found.");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public boolean addPsw(Psw_temporanee psw) throws DaoException {
        boolean valid = psw.isValidOnCreate(dAOFactory);
            if (valid) {
                    try {
                        String query = "INSERT INTO psw_temporanee (id_utente,timestamp,stringacasuale) VALUES (?,?,?)";
                        PreparedStatement st = this.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        st.setInt(1, psw.getId_user());
                        st.setTimestamp(2, psw.getTimestamp());
                        st.setString(3, psw.getCasuale());
                        st.executeUpdate();
                        ResultSet rs = st.getGeneratedKeys();
                        if (rs.next()) {
                            psw.setId(rs.getInt(1));
                        }
                        st.close();
                        return valid;
                    } catch (SQLException ex) {
                        Logger.getLogger(Psw_temporaneeDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                        throw new UpdateException(ex);
                    }
            }
            return valid;
    }

    @Override
    public boolean deletePsw(Psw_temporanee psw) throws DaoException {
        boolean valid;
            try {
                String query = "DELETE FROM psw_temporanee WHERE id_utente = ?";
                PreparedStatement st = this.getCon().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                st.setInt(1, psw.getId());
                st.executeUpdate();
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    psw.setId(rs.getInt(1));
                }
                st.close();
                valid = true;
                return valid;
            } catch (SQLException ex) {
                Logger.getLogger(Psw_temporaneeDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                throw new UpdateException(ex);
            }
    }
    
}
