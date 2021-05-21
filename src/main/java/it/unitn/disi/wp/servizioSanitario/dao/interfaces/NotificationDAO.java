/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.interfaces;

import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Notification;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Giacomo
 */
public interface NotificationDAO {
    
    List<Notification> getByUser(Integer id_user);
    void hideNotification(Integer id_notif);
    void newNotification(Integer id_user, String title, String content);
}
