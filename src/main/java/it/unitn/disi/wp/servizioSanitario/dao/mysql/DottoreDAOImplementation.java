/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDoctor;
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
public class DottoreDAOImplementation extends AbstractDAO implements DottoreDAO{

    public DottoreDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Dottore getById(Integer id) throws DaoException {
        try {
            Dottore doctor = new Dottore();
            String query = "SELECT * FROM doctor WHERE id_doc = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                doctor.setId(res.getInt("id_doc"));
                doctor.setDoc_reg_numb(res.getString("doc_reg_numb"));
                doctor.setCity(res.getString("city"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'P':
                        doctor.setType(TypeDoctor.P);
                        break;
                    case 'S':
                        doctor.setType(TypeDoctor.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                doctor.setSpecialization(res.getString("specialization"));
                doctor.setWork_district(res.getInt("work_district"));
                res.close();
                st.close();
                return doctor;
            }
            throw new NotFoundDAOException("Doctor with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(DottoreDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }

    @Override
    public Dottore getByDocRegNumb(String doc) throws DaoException {
        try {
            Dottore doctor = new Dottore();
            String query = "SELECT * FROM doctor WHERE doc_reg_numb = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, doc);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                doctor.setId(res.getInt("id_doc"));
                doctor.setDoc_reg_numb("doc_reg_numb");
                doctor.setCity(res.getString("city"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'P':
                        doctor.setType(TypeDoctor.P);
                        break;
                    case 'S':
                        doctor.setType(TypeDoctor.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                doctor.setSpecialization(res.getString("specialization"));
                doctor.setWork_district(res.getInt("work_district"));
                res.close();
                st.close();
                return doctor;
            }
            throw new NotFoundDAOException("Doctor with registration number: " + doc + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(DottoreDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }

    }

      /////RITORNA UNA LISTA DI DOTTORI CON LA STESSA SPECIALIZZAZIONE
    @Override
    public List<Dottore> getBySpec(String specialization) throws DaoException {
        try {
            List<Dottore> d = new ArrayList<>();
            String query = "SELECT * FROM doctor WHERE specialization = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setString(1, specialization);
            ResultSet res = st.executeQuery();
            Dottore doc;
            int i;
            while(res.next()) {
                i = 1;
                doc = new Dottore();
                doc.setId(res.getInt(i++));
                doc.setDoc_reg_numb(res.getString(i++));
                doc.setCity(res.getString(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'P':
                        doc.setType(TypeDoctor.P);
                        break;
                    case 'S':
                        doc.setType(TypeDoctor.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                doc.setSpecialization(res.getString(i++));
                doc.setWork_district(res.getInt(i++));
                d.add(doc);
            }
            res.close();
            st.close();
            return d;
            
        } catch (SQLException ex) {
            Logger.getLogger(DottoreDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

      /////RITORNA UNA LISTA DI DOTTORI CON LO STESSO WD
    @Override
    public List<Dottore> getByWorkDistrict(int wd) throws DaoException {
        try {
            List<Dottore> d = new ArrayList<>();
            String query = "SELECT * FROM doctor WHERE work_district = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, wd);
            ResultSet res = st.executeQuery();
            Dottore doc;
            int i;
            while(res.next()) {
                i = 1;
                doc = new Dottore();
                doc.setId(res.getInt(i++));
                doc.setDoc_reg_numb(res.getString(i++));
                doc.setCity(res.getString(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'P':
                        doc.setType(TypeDoctor.P);
                        break;
                    case 'S':
                        doc.setType(TypeDoctor.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                doc.setSpecialization(res.getString(i++));
                doc.setWork_district(res.getInt(i++));
                d.add(doc);
            }
            res.close();
            st.close();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(DottoreDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }   

    @Override
    public List<Dottore> getByType(char type) throws DaoException {
        try {
            List<Dottore> d = new ArrayList<>();
            String query = "SELECT * FROM doctor WHERE type = '"+ type + "'";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            Dottore doc;
            int i;
            while(res.next()) {
                i = 1;
                doc = new Dottore();
                doc.setId(res.getInt(i++));
                doc.setDoc_reg_numb(res.getString(i++));
                doc.setCity(res.getString(i++));   
                switch (res.getString(i++).charAt(0)) {
                    case 'P':
                        doc.setType(TypeDoctor.P);
                        break;
                    case 'S':
                        doc.setType(TypeDoctor.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                doc.setSpecialization(res.getString(i++));
                doc.setWork_district(res.getInt(i++));
                d.add(doc);
            }
            res.close();
            st.close();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(DottoreDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List<Dottore> getByTypeExtended(char type) throws DaoException {
        try {
            List<Dottore> d = new ArrayList<>();
            String query = "SELECT * FROM doctor_ext WHERE type = '"+ type + "'";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            Dottore doc;
            int i;
            while(res.next()) {
                i = 1;
                doc = new Dottore();
                doc.setId(res.getInt("id_doc"));
                doc.setFirstname(res.getString("firstname"));
                doc.setLastname(res.getString("lastname"));
                doc.setDoc_reg_numb(res.getString("doc_reg_numb"));
                doc.setCity(res.getString("city"));   
                switch (res.getString("type").charAt(0)) {
                    case 'P':
                        doc.setType(TypeDoctor.P);
                        break;
                    case 'S':
                        doc.setType(TypeDoctor.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                doc.setSpecialization(res.getString("specialization"));
                doc.setWork_district(res.getInt("work_district"));
                d.add(doc);
            }
            res.close();
            st.close();
            return d;
        } catch (SQLException ex) {
            Logger.getLogger(DottoreDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
