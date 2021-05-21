/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
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
public class ListaVisiteDAOImplementation extends AbstractDAO implements ListaVisiteDAO{

    public ListaVisiteDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public ListaVisite getById(Integer id) throws DaoException {
        try {
            ListaVisite l = new ListaVisite();
            String query = "SELECT * FROM visitlist WHERE id_vislis = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                l.setId(res.getInt("id_vislis"));
                l.setName(res.getString("name"));
                l.setCategory(res.getInt("category"));
                l.setDescription(res.getString("description"));
                res.close();
                st.close();
                return l;
            }
            throw new NotFoundDAOException("Visit with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public ListaVisite getByName(String name) throws DaoException {
        try {
            ListaVisite l = new ListaVisite();
            String query = "SELECT * FROM visitlist WHERE name = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, name);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                l.setId(res.getInt("id_vislis"));
                l.setName(res.getString("name"));
                l.setCategory(res.getInt("category"));
                l.setDescription(res.getString("description"));
                res.close();
                st.close();
                return l;
            }
            throw new NotFoundDAOException("Visit named: " + name + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<ListaVisite> getByCategory(Integer category) throws DaoException {
        try {
            List<ListaVisite> l = new ArrayList<>();
            String query = "SELECT * FROM visitlist WHERE category = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, category);
            ResultSet res = st.executeQuery();
            ListaVisite lis;
            int i;
            while(res.next()) {
                i = 1;
                lis = new ListaVisite();
                lis.setId(res.getInt(i++));
                lis.setName(res.getString(i++));
                lis.setCategory(res.getInt(i++));
                lis.setDescription(res.getString(i++));
                l.add(lis);
            }
            res.close();
            st.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List<ListaVisite> getAll() throws DaoException {
        try {
            List<ListaVisite> l = new ArrayList<>();
            String query = "SELECT V.*, C.name AS categoryname FROM visitlist V, category C WHERE V.category=C.id_categ";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            ListaVisite lis;
            int i;
            while(res.next()) {
                i = 1;
                lis = new ListaVisite();
                lis.setId(res.getInt(i++));
                lis.setName(res.getString(i++));
                lis.setCategory(res.getInt(i++));
                lis.setDescription(res.getString(i++));
                lis.setCategoryname(res.getString("categoryname"));
                l.add(lis);
            }
            res.close();
            st.close();
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
