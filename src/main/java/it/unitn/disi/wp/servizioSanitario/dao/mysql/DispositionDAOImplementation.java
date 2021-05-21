/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Ticket;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike_TdT
 */
public class DispositionDAOImplementation extends AbstractDAO implements DispositionDAO{

    public DispositionDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Disposition getById(Integer id) throws DaoException {
        try {
            Disposition d = new Disposition();
            String query = "SELECT * FROM disposition WHERE id_disp = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                d.setId(res.getInt("id_disp"));
                d.setPatient(res.getInt("patient"));
                d.setFamilydoctor(res.getInt("familydoctor"));
                d.setVisit(res.getInt("visit"));
                d.setPaid(res.getByte("paid"));
                d.setTicket(res.getInt("ticket"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate("madedate"));
                res.close();
                st.close();
                return d;
            }
            throw new NotFoundDAOException("Disposition with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    
    @Override
    public void setAsPaid (Integer id_disp) throws DaoException {
        try {
            String query = "UPDATE disposition SET paid = ?, madedate = CURRENT_TIMESTAMP WHERE id_disp = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setByte(1, new Byte("1"));
            st.setInt(2, id_disp);
            int count = st.executeUpdate();
            st.close();
            if (count != 1) {
                    throw new NotFoundDAOException("disposition with id: " + id_disp + " not found");
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public boolean deleteDispositionById (Integer id) throws DaoException {
        try {
            String query = "DELETE FROM exam WHERE id_exam = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            
            query = "DELETE FROM speckvisit WHERE id_spvt = ?";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            
            query = "DELETE FROM prescription WHERE id_pres = ?; ";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            
            query = "DELETE FROM disposition WHERE id_disp = ?;";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
            
            st.close();
            return true;
        } catch (SQLException ex) {
                Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
    }
 

    @Override
    public List<Disposition> getByPatient(Integer id_pat) throws DaoException {
        try{
            List<Disposition> dis = new ArrayList<>();
            String query = "SELECT * FROM disposition WHERE patient = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            Disposition d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Disposition();
                d.setId(res.getInt(i++));
                d.setPatient(res.getInt(i++));
                d.setFamilydoctor(res.getInt(i++));
                d.setVisit(res.getInt(i++));
                d.setPaid(res.getByte(i++));
                d.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate(i++));
                dis.add(d);
            }
            res.close();
            st.close();
            return dis;
        } catch (SQLException ex) {
            Logger.getLogger(DispositionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Disposition> getByFamilyDoctor(Integer id_doc) throws DaoException {
        try{
            List<Disposition> dis = new ArrayList<>();
            String query = "SELECT * FROM disposition WHERE familydoctor = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_doc);
            ResultSet res = st.executeQuery();
            Disposition d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Disposition();
                d.setId(res.getInt(i++));
                d.setPatient(res.getInt(i++));
                d.setFamilydoctor(res.getInt(i++));
                d.setVisit(res.getInt(i++));
                d.setPaid(res.getByte(i++));
                d.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate(i++));
                dis.add(d);
            }
            res.close();
            st.close();
            return dis;
        } catch (SQLException ex) {
            Logger.getLogger(DispositionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Disposition> getByVisit(Integer id_vis) throws DaoException {
        try{
            List<Disposition> dis = new ArrayList<>();
            String query = "SELECT * FROM disposition WHERE visit = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_vis);
            ResultSet res = st.executeQuery();
            Disposition d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Disposition();
                d.setId(res.getInt(i++));
                d.setPatient(res.getInt(i++));
                d.setFamilydoctor(res.getInt(i++));
                d.setVisit(res.getInt(i++));
                d.setPaid(res.getByte(i++));
                d.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate(i++));
                dis.add(d);
            }
            res.close();
            st.close();
            return dis;
        } catch (SQLException ex) {
            Logger.getLogger(DispositionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Disposition> getByPaid(Byte paid) throws DaoException {
        try{
            List<Disposition> dis = new ArrayList<>();
            String query = "SELECT * FROM disposition WHERE paid = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setByte(1, paid);
            ResultSet res = st.executeQuery();
            Disposition d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Disposition();
                d.setId(res.getInt(i++));
                d.setPatient(res.getInt(i++));
                d.setFamilydoctor(res.getInt(i++));
                d.setVisit(res.getInt(i++));
                d.setPaid(res.getByte(i++));
                d.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate(i++));
                dis.add(d);
            }
            res.close();
            st.close();
            return dis;
        } catch (SQLException ex) {
            Logger.getLogger(DispositionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Disposition> getByTicket(Integer ticket) throws DaoException {
        try{
            List<Disposition> dis = new ArrayList<>();
            String query = "SELECT * FROM disposition WHERE ticket = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, ticket);
            ResultSet res = st.executeQuery();
            Disposition d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Disposition();
                d.setId(res.getInt(i++));
                d.setPatient(res.getInt(i++));
                d.setFamilydoctor(res.getInt(i++));
                d.setVisit(res.getInt(i++));
                d.setPaid(res.getByte(i++));
                d.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate(i++));
                dis.add(d);
            }
            res.close();
            st.close();
            return dis;
        } catch (SQLException ex) {
            Logger.getLogger(DispositionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Disposition> getByType(char type) throws DaoException {
        try{
            List<Disposition> dis = new ArrayList<>();
            String query = "SELECT * FROM disposition WHERE type = '" + type + "'";
            PreparedStatement st = this.getCon().prepareStatement(query);
//            st.setObject(1, type);
            ResultSet res = st.executeQuery();
            Disposition d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Disposition();
                d.setId(res.getInt(i++));
                d.setPatient(res.getInt(i++));
                d.setFamilydoctor(res.getInt(i++));
                d.setVisit(res.getInt(i++));
                d.setPaid(res.getByte(i++));
                d.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate(i++));
                dis.add(d);
            }
            res.close();
            st.close();
            return dis;
        } catch (SQLException ex) {
            Logger.getLogger(DispositionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }        
    }

    @Override
    public List<Disposition> getByMadeDate(Date date) throws DaoException {
        try{
            List<Disposition> dis = new ArrayList<>();
            String query = "SELECT * FROM disposition WHERE DATE(madedate) = '" + date.toString() +"'";
            PreparedStatement st = this.getCon().prepareStatement(query);
//            st.setDate(1, date);
            ResultSet res = st.executeQuery();
            Disposition d;
            int i;
            while(res.next()) {
                i = 1;
                d = new Disposition();
                d.setId(res.getInt(i++));
                d.setPatient(res.getInt(i++));
                d.setFamilydoctor(res.getInt(i++));
                d.setVisit(res.getInt(i++));
                d.setPaid(res.getByte(i++));
                d.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        d.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        d.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        d.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                d.setMadedate(res.getDate(i++));
                dis.add(d);
            }
            res.close();
            st.close();
            return dis;
        } catch (SQLException ex) {
            Logger.getLogger(DispositionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
