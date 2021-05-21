/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike_TdT
 */
public class ChsDAOImplementation extends AbstractDAO implements ChsDAO{

    public ChsDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Chs getById(Integer id) throws DaoException {
        try {
            Chs chs = new Chs();
            String query = "SELECT * FROM chs WHERE id_chs = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                chs.setId(res.getInt("id_chs"));
                chs.setCdistrict(res.getInt("cdistrict"));
                chs.setName(res.getString("name"));
                res.close();
                st.close();
                return chs;
            }
            throw new NotFoundDAOException("Chs with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

        @Override
    public Chs getByCdistrict(Integer district) throws DaoException {
        try {
            Chs chs = new Chs();
            String query = "SELECT * FROM chs WHERE cdistrict = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, district);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                chs.setId(res.getInt("id_chs"));
                chs.setCdistrict(res.getInt("cdistrict"));
                chs.setName(res.getString("name"));
                res.close();
                st.close();
                return chs;
            }
            throw new NotFoundDAOException("Chs with id: " + district + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public Chs getByName(String name) throws DaoException {
        try {
            Chs chs = new Chs();
            String query = "SELECT * FROM chs WHERE name = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, name);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                chs.setId(res.getInt("id_chs"));
                chs.setCdistrict(res.getInt("cdistrict"));
                chs.setName(res.getString("name"));
                res.close();
                st.close();
                return chs;
            }
            throw new NotFoundDAOException("Chs with name: " + name + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Chs> getAll() throws DaoException {
        try {
            List<Chs> c = new ArrayList<>();
            String query = "SELECT * FROM chs WHERE id_chs > 1";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            Chs chs;
            int i;
            while(res.next()) {
                chs = new Chs();
                chs.setId(res.getInt("id_chs"));
                chs.setCdistrict(res.getInt("cdistrict"));
                chs.setName(res.getString("name"));
                c.add(chs);
            }
            res.close();
            st.close();
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
