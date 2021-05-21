/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.entities.District;
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
public class DistrictDAOImplementation extends AbstractDAO implements DistrictDAO{

    public DistrictDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public District getById(Integer id) throws DaoException {
        try {
            District c = new District();
            String query = "SELECT * FROM district WHERE id_dist = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                c.setId(res.getInt("id_dist"));
                c.setCity(res.getString("city"));
                c.setAcronym(res.getString("acronym"));
                res.close();
                st.close();
                return c;
            }
            throw new NotFoundDAOException("District with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<District> getByCity(String city) throws DaoException {
        try {
            List<District> d = new ArrayList<>();
            String query = "SELECT * FROM district WHERE city = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, city);
            ResultSet res = st.executeQuery();
            District dis;
            int i;
            while(res.next()) {
                i = 1;
                dis = new District();
                dis.setId(res.getInt(i++));
                dis.setCity(res.getString(i++));
                dis.setAcronym(res.getString(i++));
                d.add(dis);
            }
            res.close();
            st.close();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List<District> getAll() throws DaoException {
        try {
            List<District> d = new ArrayList<>();
            String query = "SELECT * FROM district";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            District dis;
            int i;
            while(res.next()) {
                i = 1;
                dis = new District();
                dis.setId(res.getInt(i++));
                dis.setCity(res.getString(i++));
                dis.setAcronym(res.getString(i++));
                d.add(dis);
            }
            res.close();
            st.close();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public District getByAcronym(String acronym) throws DaoException {
        try {
            District c = new District();
            String query = "SELECT * FROM district WHERE acronym = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, acronym);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                c.setId(res.getInt("id_dist"));
                c.setCity(res.getString("city"));
                c.setAcronym(res.getString("acronym"));
                res.close();
                st.close();
                return c;
            }
            throw new NotFoundDAOException("District with acronym: " + acronym + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
