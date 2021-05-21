/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike_TdT
 */
public class UserDAOImplementation extends AbstractDAO implements UserDAO{
    
    public UserDAOImplementation (DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public boolean checkByEmail(String email) throws DaoException {
        try {
            boolean trovato = false;
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, email);
            ResultSet res = st.executeQuery();
            if(res.first()) return trovato = true;
            else return trovato;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    @Override
    public User getByEmail(String email) throws DaoException {
        try {
            User u = new User();
            String query = "SELECT * FROM user WHERE email = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, email);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                u.setId(res.getInt("id_user"));
                u.setEmail(res.getString("email"));
                u.setPassword(res.getString("password"));
                //da sistemare questa parte credo, gestione dell'errore??
                //serve un modo per poter aggiungere altri ruoli senza modificare questa roba? 
                //fare una funzione se serve richiamare  da altri parti questi if
                String ruoletto = res.getString("urole");   
                switch (ruoletto.charAt(0)) {
                    case 'P':
                        u.setRuolo(Role.P);
                        break;
                    case 'D':
                        u.setRuolo(Role.D);
                        break;
                    case 'S':
                        u.setRuolo(Role.S);
                        break;
                    case 'C':
                        u.setRuolo(Role.C);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                res.close();
                st.close();
                return u;
            }
            throw new NotFoundDAOException("User with email: " + email + " not found.");
        }   
        catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
	public boolean updateUser(Integer id, User user) throws DaoException {
		boolean valid = user.isValidOnUpdate(dAOFactory);
		if (valid) {
			try {
				String query = "UPDATE user SET email = ?, password = ?, urole = '" + user.getRuolo() + "' WHERE id_user = ?";
				PreparedStatement st = this.getCon().prepareStatement(query);
				st.setString(1, user.getEmail());
				st.setString(2, user.getPassword());
                                st.setInt(3, id);
				int count = st.executeUpdate();
				st.close();
				if (count != 1) {
					throw new NotFoundDAOException("User with id: " + id + " not found");
				}
				return valid;
			} catch (SQLException ex) {
				Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
				throw new DaoException(ex);
			}
		}
		return valid;
	}

    @Override
    public User getById(Integer id) throws DaoException {
        try {
            User u = new User();
            String query = "SELECT * FROM user WHERE id_user = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                u.setId(res.getInt("id_user"));
                u.setEmail(res.getString("email"));
                u.setPassword(res.getString("password"));
                //da sistemare questa parte credo, gestione dell'errore??
                //serve un modo per poter aggiungere altri ruoli senza modificare questa roba? 
                //fare una funzione se serve richiamare  da altri parti questi if
                String ruoletto = res.getString("urole");   
                switch (ruoletto.charAt(0)) {
                    case 'P':
                        u.setRuolo(Role.P);
                        break;
                    case 'D':
                        u.setRuolo(Role.D);
                        break;
                    case 'S':
                        u.setRuolo(Role.S);
                        break;
                    case 'C':
                        u.setRuolo(Role.C);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                res.close();
                st.close();
                return u;
            }
            throw new NotFoundDAOException("User with id: " + id + " not found.");
        }   
        catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
}
