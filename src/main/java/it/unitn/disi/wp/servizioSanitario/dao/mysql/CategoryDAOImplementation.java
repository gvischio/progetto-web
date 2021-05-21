/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.CategoryDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
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
public class CategoryDAOImplementation extends AbstractDAO implements CategoryDAO{

    public CategoryDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Category getById(Integer id) throws DaoException {
        try {
            Category c = new Category();
            String query = "SELECT * FROM category WHERE id_categ = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                c.setId(res.getInt("id_categ"));
                c.setName(res.getString("name"));
                res.close();
                st.close();
                return c;
            }
            throw new NotFoundDAOException("Category with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
        
    }

    @Override
    public Category getByName(String name) throws DaoException {
        try {
            Category c = new Category();
            String query = "SELECT * FROM category WHERE name = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, name);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                c.setId(res.getInt("id_categ"));
                c.setName(res.getString("name"));
                res.close();
                st.close();
                return c;
            }
            throw new NotFoundDAOException("Category with id: " + name + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List<Category> getAll() throws DaoException {
        try {
            List<Category> l = new ArrayList<>();
            Category c;
            String query = "SELECT * FROM category ";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                c = new Category();
                c.setId(res.getInt("id_categ"));
                c.setName(res.getString("name"));
                l.add(c);
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
