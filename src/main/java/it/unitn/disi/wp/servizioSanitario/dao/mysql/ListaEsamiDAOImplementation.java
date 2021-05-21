/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
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
public class ListaEsamiDAOImplementation extends AbstractDAO implements ListaEsamiDAO{

    public ListaEsamiDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public ListaEsami getById(Integer id) throws DaoException {
        try {
            ListaEsami l = new ListaEsami();
            String query = "SELECT * FROM examlist WHERE id_exlis = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                l.setId(res.getInt("id_exlis"));
                l.setName(res.getString("name"));
                l.setCategory(res.getInt("category"));
                l.setDescription(res.getString("description"));
                res.close();
                st.close();
                return l;
            }
            throw new NotFoundDAOException("Exam with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public ListaEsami getByName(String name) throws DaoException {
        try {
            ListaEsami l = new ListaEsami();
            String query = "SELECT * FROM examlist WHERE name = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, name);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                l.setId(res.getInt("id_exlis"));
                l.setName(res.getString("name"));
                l.setCategory(res.getInt("category"));
                l.setDescription(res.getString("description"));
                res.close();
                st.close();
                return l;
            }
            throw new NotFoundDAOException("Exam named: " + name + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<ListaEsami> getByCategory(Integer category) throws DaoException {
        try {
            List<ListaEsami> l = new ArrayList<>();
            String query = "SELECT * FROM examlist WHERE category = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, category);
            ResultSet res = st.executeQuery();
            ListaEsami lis;
            int i;
            while(res.next()) {
                i = 1;
                lis = new ListaEsami();
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
    public List<ListaEsami> getAll() throws DaoException {
        try {
            List<ListaEsami> l = new ArrayList<>();
            String query = "SELECT E.*, C.name AS categoryname FROM examlist E, category C WHERE E.category=C.id_categ";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            ListaEsami lis;
            int i;
            while(res.next()) {
                i = 1;
                lis = new ListaEsami();
                lis.setId(res.getInt(i++));
                lis.setName(res.getString(i++));
                lis.setCategory(res.getInt(i++));
                lis.setDescription(res.getString(i++));
                lis.setCategoryname(res.getString(i++));
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
