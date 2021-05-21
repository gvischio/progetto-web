/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Notification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giacomo
 */
public class NotificationDAOImplementation extends AbstractDAO implements NotificationDAO {

    public NotificationDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public List<Notification> getByUser(Integer id_user) {
        try{
            List<Notification> lis = new ArrayList<>();
            String query = "SELECT * FROM notification WHERE user=? ORDER BY id_notify DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_user);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                lis.add(new Notification(res.getInt("id_notify"), id_user, res.getString("title"), res.getString("content")));
            }
            res.close();
            st.close();
            return lis;
        }
        catch (SQLException ex) { return null; }
    }

    @Override
    public void hideNotification(Integer id_notif) {
        try{
            List<Notification> lis = new ArrayList<>();
            String query = "DELETE FROM notification WHERE id_notify=?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_notif);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException ex) { }
    }

    @Override
    public void newNotification(Integer id_user, String title, String content) {
        try{
            String query = "INSERT INTO notification (user, title, content) VALUES(?, ?, ?)";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_user);
            st.setString(2, title);
            st.setString(3, content);
            st.executeUpdate();
            st.close();
        }
        catch (SQLException ex) { }
    }
    
}
