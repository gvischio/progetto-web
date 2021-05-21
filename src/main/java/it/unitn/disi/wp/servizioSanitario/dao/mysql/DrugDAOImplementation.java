/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Drug;
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
public class DrugDAOImplementation extends AbstractDAO implements DrugDAO{

    public DrugDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }
    
    @Override
    public Drug getById(Integer id) throws DaoException {
        try {
            Drug d = new Drug();
            String query = "SELECT * FROM drug WHERE id_drug = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                d.setId(res.getInt("id_drug"));
                d.setName(res.getString("name"));
                d.setDescription(res.getString("description"));
                res.close();
                st.close();
                return d;
            }
            throw new NotFoundDAOException("Drug with id: " + id + " not found");
        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List<Drug> getByName(String name) throws DaoException {
        try {
            List<Drug> drug = new ArrayList<>();
            String query = "SELECT * FROM drug WHERE name = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, name);
            ResultSet res = st.executeQuery();
            Drug d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Drug();
                d.setId(res.getInt(i++));
                d.setName(res.getString(i++));
                d.setDescription(res.getString(i++));
                drug.add(d);
            }
            res.close();
            st.close();
            return drug;
        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List<Drug> getAll() throws DaoException {
        try {
            List<Drug> drug = new ArrayList<>();
            String query = "SELECT * FROM drug";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            Drug d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Drug();
                d.setId(res.getInt(i++));
                d.setName(res.getString(i++));
                d.setDescription(res.getString(i++));
                drug.add(d);
            }
            res.close();
            st.close();
            return drug;
        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
