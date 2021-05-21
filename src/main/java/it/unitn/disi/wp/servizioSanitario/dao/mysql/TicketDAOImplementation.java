/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.TicketDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Ticket;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giacomo
 */
public class TicketDAOImplementation extends AbstractDAO implements TicketDAO {

    public TicketDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Ticket getById(Integer id) throws DaoException {
        try {
            Ticket t = new Ticket();
            String query = "SELECT * FROM ticket WHERE id_disp = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                t.setIdDisp(res.getInt("id_disp"));
                t.setIdPat(res.getInt("patient"));
                t.setTicket(res.getInt("ticket"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'E':
                        t.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        t.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        t.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                t.setMadeDate(res.getDate("madedate"));
                t.setDoneBy(res.getString("doneby"));
                t.setName(res.getString("name"));
                res.close();
                st.close();
                return t;
            }
            throw new NotFoundDAOException("Disposition with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Ticket> getPaidByPatient(Integer id_pat) throws DaoException {
        try {
            List<Ticket> tick = new ArrayList<>();
            String query = "SELECT * FROM ticket WHERE patient = ? AND paid = 1 ORDER BY madedate DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            Ticket t;
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                t = new Ticket();
                t.setIdDisp(res.getInt("id_disp"));
                t.setIdPat(res.getInt("patient"));
                t.setTicket(res.getInt("ticket"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'E':
                        t.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        t.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        t.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                t.setMadeDate(res.getDate("madedate"));
                t.setDoneBy(res.getString("doneby"));
                t.setName(res.getString("name"));
                tick.add(t);
            }
            return tick;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
