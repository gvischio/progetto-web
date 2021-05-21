/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PazienteVista;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Sex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike_TdT
 */
public class PazienteDAOImplementation extends AbstractDAO implements PazienteDAO{

    public PazienteDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Paziente getById(Integer id) throws DaoException {
        try {
            Paziente paz = new Paziente();
            String query = "SELECT * FROM patient WHERE id_pat = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                paz.setId(res.getInt("id_pat"));
                paz.setSsn(res.getString("ssn"));
                paz.setFirstname(res.getString("firstname"));
                paz.setLastname(res.getString("lastname"));
                paz.setBirthday(res.getDate("birthday"));
                paz.setBirthplace(res.getString("birthplace"));
                String sesso = res.getString("sex");   
                switch (sesso.charAt(0)) {
                    case 'M':
                        paz.setSex(Sex.M);
                        break;
                    case 'F':
                        paz.setSex(Sex.F);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                paz.setFamilydoctor(res.getInt("familydoctor"));
                paz.setDistrict(res.getInt("pdistrict"));
                res.close();
                st.close();
                return paz;
            }
            throw new NotFoundDAOException("Patient with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
        
    }

    @Override
    public Paziente getByssn(String ssn) throws DaoException {
        try {
            Paziente paz = new Paziente();
            String query = "SELECT * FROM patient WHERE ssn = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, ssn);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                paz.setId(res.getInt("id_pat"));
                paz.setSsn(res.getString("ssn"));
                paz.setFirstname(res.getString("firstname"));
                paz.setLastname(res.getString("lastname"));
                paz.setBirthday(res.getDate("birthday"));
                paz.setBirthplace(res.getString("birthplace"));
                String sesso = res.getString("sex");   
                switch (sesso.charAt(0)) {
                    case 'M':
                        paz.setSex(Sex.M);
                        break;
                    case 'F':
                        paz.setSex(Sex.F);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                paz.setFamilydoctor(res.getInt("familydoctor"));
                paz.setDistrict(res.getInt("pdistrict"));
                res.close();
                st.close();
                return paz;
            }
            throw new NotFoundDAOException("Patient with ssn: " + ssn + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }

     @Override
    public List<Paziente> getBynamedate(String firstname, String lastname, Date birthday) throws DaoException {
        try {
            List<Paziente> p = new ArrayList<>();
            String query = "SELECT * FROM patient WHERE firstname = ? AND lastname = ? AND birthday = '"+ birthday.toString() + "'";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, firstname);
            st.setString(2, lastname);
//            st.setDate(3, birthday);
            ResultSet res = st.executeQuery();
            Paziente paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new Paziente();
                paz.setId(res.getInt(i++));
                paz.setSsn(res.getString(i++));
                paz.setFirstname(res.getString(i++));
                paz.setLastname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setBirthplace(res.getString(i++));
                String sesso = res.getString(i++);   
                switch (sesso.charAt(0)) {
                    case 'M':
                        paz.setSex(Sex.M);
                        break;
                    case 'F':
                        paz.setSex(Sex.F);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                paz.setFamilydoctor(res.getInt(i++));
                paz.setDistrict(res.getInt(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
            
//            throw new NotFoundDAOException("Patient with firstname: " + firstname + " and lastname: " + lastname + "  and birthday: " +  birthday + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }
    @Override
    public List<Paziente> getForRecall(Date min, Date max, int district) throws DaoException {
        try {
            List<Paziente> p = new ArrayList<>();
            String query = "SELECT * FROM patient WHERE birthday <='"+ max.toString() + "' AND birthday >= '" + min.toString() + "' AND pdistrict = " + district +" ";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            Paziente paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new Paziente();
                paz.setId(res.getInt(i++));
                paz.setSsn(res.getString(i++));
                paz.setFirstname(res.getString(i++));
                paz.setLastname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setBirthplace(res.getString(i++));
                String sesso = res.getString(i++);   
                switch (sesso.charAt(0)) {
                    case 'M':
                        paz.setSex(Sex.M);
                        break;
                    case 'F':
                        paz.setSex(Sex.F);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                paz.setFamilydoctor(res.getInt(i++));
                paz.setDistrict(res.getInt(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
            
//            throw new NotFoundDAOException("Patient with firstname: " + firstname + " and lastname: " + lastname + "  and birthday: " +  birthday + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }

    @Override
    public List<Paziente> getByfamilydoctor(int familydoctor) throws DaoException {
        try {
            List<Paziente> p = new ArrayList<>();
            String query = "SELECT * FROM patient P, doctor_ext D WHERE P.familydoctor = ? AND P.familydoctor = D.id_doc";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, familydoctor);
            ResultSet res = st.executeQuery();
            Paziente paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new Paziente();
                paz.setId(res.getInt(i++));
                paz.setSsn(res.getString(i++));
                paz.setFirstname(res.getString(i++));
                paz.setLastname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setBirthplace(res.getString(i++));
                String sesso = res.getString(i++);   
                switch (sesso.charAt(0)) {
                    case 'M':
                        paz.setSex(Sex.M);
                        break;
                    case 'F':
                        paz.setSex(Sex.F);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                paz.setFamilydoctor(res.getInt(i++));
                paz.setDistrict(res.getInt(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
//            throw new NotFoundDAOException("Patient with this familydoctor: " + familydoctor + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }
    
    @Override
    public List<Paziente> getByDistrict(int district) throws DaoException {
        try {
            List<Paziente> p = new ArrayList<>();
            String query = "SELECT * FROM patient WHERE pdistrict = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, district);
            ResultSet res = st.executeQuery();
            Paziente paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new Paziente();
                paz.setId(res.getInt(i++));
                paz.setSsn(res.getString(i++));
                paz.setFirstname(res.getString(i++));
                paz.setLastname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setBirthplace(res.getString(i++));
                String sesso = res.getString(i++);   
                switch (sesso.charAt(0)) {
                    case 'M':
                        paz.setSex(Sex.M);
                        break;
                    case 'F':
                        paz.setSex(Sex.F);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                paz.setFamilydoctor(res.getInt(i++));
                paz.setDistrict(res.getInt(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
//            throw new NotFoundDAOException("Patient with this familydoctor: " + familydoctor + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }

    @Override
    public boolean updatePaz(Integer id, Paziente paz) throws DaoException {
        Boolean valido = paz.isValidOnUpdate(dAOFactory);
        if(valido) {
            try {
                String query = "UPDATE patient SET ssn = ?, firstname = ?, lastname = ?, birthday = ?, birthplace = ?, sex = ?, familydoctor = ?, pdistrict = ?";
                PreparedStatement st = this.getCon().prepareStatement(query);
                st.setString(1, paz.getSsn());
                st.setString(2, paz.getFirstname());
                st.setString(3, paz.getLastname());
                st.setDate(4, (Date) paz.getBirthday());
                st.setString(5, paz.getBirthplace());
                st.setObject(6, paz.getSex());
                st.setInt(7, paz.getFamilydoctor());
                st.setInt(8, paz.getDistrict());
                int contaSuccessi = st.executeUpdate();
                st.close();
                if (contaSuccessi != 1) {
                    throw new NotFoundDAOException("User with id: " + id + " not found!");
                }
                return valido;
            } catch (SQLException ex) {
                Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                throw new DaoException(ex);
            }

        }
        return valido;
    }
    
    @Override
    public List<Paziente> getAll() throws DaoException {
        try {
            List<Paziente> p = new ArrayList<>();
            String query = "SELECT * FROM patient ORDER BY id_pat";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            Paziente paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new Paziente();
                paz.setId(res.getInt(i++));
                paz.setSsn(res.getString(i++));
                paz.setFirstname(res.getString(i++));
                paz.setLastname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setBirthplace(res.getString(i++));
                String sesso = res.getString(i++);   
                switch (sesso.charAt(0)) {
                    case 'M':
                        paz.setSex(Sex.M);
                        break;
                    case 'F':
                        paz.setSex(Sex.F);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                paz.setFamilydoctor(res.getInt(i++));
                paz.setDistrict(res.getInt(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
//            throw new NotFoundDAOException("Patient with this familydoctor: " + familydoctor + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }
    
    public List <PazienteVista> getPazienteVistaPerChs() throws DaoException{
        try {
            List<PazienteVista> p = new ArrayList<>();
            String query = "SELECT id_pat, patname, docname, birthday, examstodo FROM patient4chs3doct ";
            PreparedStatement st = this.getCon().prepareStatement(query);
            
            ResultSet res = st.executeQuery();
            PazienteVista paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new PazienteVista();
                paz.setId_pat(res.getInt(i++));
                paz.setPatname(res.getString(i++));
                paz.setDocname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setExamstodo(res.getInt(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
        
    }
    
    @Override
    public List <PazienteVista> getPazienteVistaPerDottoreBaseByDottoreId (int dottoreId) throws DaoException{
        try {
            List<PazienteVista> p = new ArrayList<>();
            String query = "SELECT id_pat, patname, birthday, lastvisit FROM patient4chs3doct WHERE id_doc = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, dottoreId);
            ResultSet res = st.executeQuery();
            PazienteVista paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new PazienteVista();
                paz.setId_pat(res.getInt(i++));
                paz.setPatname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setLastvisit(res.getDate(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List <PazienteVista> getPazienteVistaPerDottoreSpeck () throws DaoException {
        try {
            List<PazienteVista> p = new ArrayList<>();
            String query = "SELECT id_pat, patname, birthday, docname, lastvisit FROM patient4chs3doct";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            PazienteVista paz;
            int i;
            while(res.next()) {
                i = 1;
                paz = new PazienteVista();
                paz.setId_pat(res.getInt(i++));
                paz.setPatname(res.getString(i++));
                paz.setBirthday(res.getDate(i++));
                paz.setDocname(res.getString(i++));
                paz.setLastvisit(res.getDate(i++));
                p.add(paz);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public boolean updateDoc(Integer id_pat, Integer id_doc) throws DaoException {
        try {
            String query = "UPDATE patient SET familydoctor = ? WHERE id_pat = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_doc);
            st.setInt(2, id_pat);
            int contaSuccessi = st.executeUpdate();
            st.close();
            if (contaSuccessi != 1) {
                throw new NotFoundDAOException("User with id: " + id_pat + " not found!");
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
