/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
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
public class PhotoDAOImplementation extends AbstractDAO implements PhotoDAO{

    public PhotoDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Photo getById(Integer id) throws DaoException {
        try {
            Photo p = new Photo();
            String query = "SELECT * FROM photo WHERE id_photo = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                p.setId(res.getInt("id_photo"));
                p.setUser(res.getInt("user"));
                p.setPath(res.getString("path"));
                p.setTimestamp(res.getTimestamp("data"));
                res.close();
                st.close();
                return p;
            }
            throw new NotFoundDAOException("Photo with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Photo> getByUser(Integer user) throws DaoException {
        try {
            List<Photo> p = new ArrayList<>();
            String query = "SELECT * FROM photo WHERE user = ? ORDER BY data DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, user);
            ResultSet res = st.executeQuery();
            Photo ph;
            int i;
            while(res.next()) {
                i = 1;
                ph = new Photo();
                ph.setId(res.getInt(i++));
                ph.setUser(res.getInt(i++));
                ph.setPath(res.getString(i++));
                ph.setTimestamp(res.getTimestamp(i++));
                p.add(ph);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public Photo getByPath(String path) throws DaoException {
        try {
            Photo p = new Photo();
            String query = "SELECT * FROM photo WHERE path = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, path);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                p.setId(res.getInt("id_photo"));
                p.setUser(res.getInt("user"));
                p.setPath(res.getString("path"));
                p.setTimestamp(res.getTimestamp("data"));
                res.close();
                st.close();
                return p;
            }
            throw new NotFoundDAOException("Photo with path: " + path + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public Photo getByLastPhotoUser(Integer user) throws DaoException {
        try {
            Photo p = new Photo();
            String query = "SELECT * FROM photo WHERE user = ? ORDER BY data DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, user);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                p.setId(res.getInt("id_photo"));
                p.setUser(res.getInt("user"));
                p.setPath(res.getString("path"));
                p.setTimestamp(res.getTimestamp("data"));
                res.close();
                st.close();
                return p;
            }
            throw new NotFoundDAOException("Photo with user: " + user + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(ListaEsamiDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public boolean addPhoto(Photo photo) throws DaoException {
        try {
            String query = "INSERT INTO photo (user, path) VALUES (?, ?)";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, photo.getUser());
            st.setString(2, photo.getPath());
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
                Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
    }

    @Override
    public boolean deletePhoto(Integer id) throws DaoException {
        try {
            String query = "DELETE FROM photo WHERE id_photo = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PhotoDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}
