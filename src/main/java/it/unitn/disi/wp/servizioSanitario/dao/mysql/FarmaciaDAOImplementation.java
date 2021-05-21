/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.FarmaciaDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Farmacia;
import java.sql.Connection;
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
public class FarmaciaDAOImplementation extends AbstractDAO implements FarmaciaDAO{

    public FarmaciaDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Farmacia getById(Integer id) throws DaoException {
        try {
            Farmacia drugstore = new Farmacia();
            String query = "SELECT * FROM drugstore WHERE id_drst = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                drugstore.setId(res.getInt("id_drst"));
                drugstore.setPiva(res.getString("piva"));
                drugstore.setCity(res.getString("city"));
                drugstore.setDdistrict(res.getInt("ddistrict"));
                res.close();
                st.close();
                return drugstore;
            }
            throw new NotFoundDAOException("Drugstore with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    //RITORNA LISTA DI FARMACIE per DISTRETTO 
    @Override
    public List<Farmacia> getByDdistrict(Integer ddistrict) throws DaoException {
        try{
            List<Farmacia> f = new ArrayList<>();
            String query = "SELECT * FROM drugstore WHERE ddistrict = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, ddistrict);
            ResultSet res = st.executeQuery();
            Farmacia farm;
            int i;
            while(res.next()) {
                 i = 1;
                 farm = new Farmacia();
                 farm.setId(res.getInt(i++));
                 farm.setPiva(res.getString(i++));
                 farm.setCity(res.getString(i++));
                 farm.setDdistrict(res.getInt(i++));
                 f.add(farm);
            }
            res.close();
            st.close();
            return f;
        }   catch (SQLException ex) {
            Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    //RITORNA LISTA DI FARMACIE per CITTA'
    @Override
    public List<Farmacia> getByCity(String city) throws DaoException {
        try {
            List<Farmacia> f = new ArrayList<>();
            String query = "SELECT * FROM drugstore WHERE city = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, city);
            ResultSet res = st.executeQuery();
            Farmacia farm;
            int i;
            while(res.next()) {
                i = 1;
                farm = new Farmacia();
                farm.setId(res.getInt(i++));
                farm.setPiva(res.getString(i++));
                farm.setCity(res.getString(i++));
                farm.setDdistrict(res.getInt(i++));
                f.add(farm);
            }
            res.close();
            st.close();
            return f;
    
            } catch (SQLException ex) {
                Logger.getLogger(FarmaciaDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                throw new DaoException(ex);
            }
    
    }
    
}
