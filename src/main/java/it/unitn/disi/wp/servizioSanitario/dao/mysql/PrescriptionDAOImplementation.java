/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PrescriptionChs;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PrescriptionPaid;
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
 * @author Mike_TdT
 */
public class PrescriptionDAOImplementation extends AbstractDAO implements PrescriptionDAO{

    public PrescriptionDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Prescription getById(Integer id) throws DaoException {
        try {
            Prescription pres = new Prescription();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate,"
                    + "P.id_pres,P.drug,P.quantity,P.active,P.drugstore,G.name "
                    + "FROM prescription P, disposition D, drug G "
                    + "WHERE id_pres = ? "
                    + "AND P.id_pres = D.id_disp AND G.id_drug = P.drug";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                pres.setPatient(res.getInt("patient"));
                pres.setFamilydoctor(res.getInt("familydoctor"));
                pres.setVisit(res.getInt("visit"));
                pres.setPaid(res.getByte("paid"));
                pres.setTicket(res.getInt("ticket"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'E':
                        pres.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        pres.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        pres.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                pres.setMadedate(res.getDate("madedate"));
                pres.setId(res.getInt("id_pres"));
                pres.setDrug(res.getInt("drug"));
                pres.setQuantity(res.getInt("quantity"));
                pres.setActive(res.getInt("active"));
                pres.setDrugstore(res.getInt("drugstore"));
                pres.setName(res.getString("name"));
                res.close();
                st.close();
                return pres;
            }
            throw new NotFoundDAOException("Prescription with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Prescription> getByDrug(Integer drug) throws DaoException {
        try {
            List<Prescription> p = new ArrayList<>();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate,"
                    + "P.id_pres,P.drug,P.quantity,P.active,P.drugstore,G.name "
                    + "FROM prescription P, disposition D, drug G "
                    + "WHERE drug = ? "
                    + "AND P.id_pres = D.id_disp AND G.id_drug = P.drug";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, drug);
            ResultSet res = st.executeQuery();
            Prescription pres;
            int i;
            while(res.next()) {
                i = 1;
                pres = new Prescription();
                pres.setPatient(res.getInt(i++));
                pres.setFamilydoctor(res.getInt(i++));
                pres.setVisit(res.getInt(i++));
                pres.setPaid(res.getByte(i++));
                pres.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        pres.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        pres.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        pres.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                pres.setMadedate(res.getDate(i++));
                pres.setId(res.getInt(i++));
                pres.setDrug(res.getInt(i++));
                pres.setQuantity(res.getInt(i++));
                pres.setActive(res.getByte(i++));
                pres.setDrugstore(res.getInt(i++));
                pres.setName(res.getString(i++));
                p.add(pres);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Prescription> getByActive(byte active) throws DaoException {
         try {
            List<Prescription> p = new ArrayList<>();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate,"
                    + "P.id_pres,P.drug,P.quantity,P.active,P.drugstore,G.name "
                    + "FROM prescription P, disposition D, drug G "
                    + "WHERE active = ? "
                    + "AND P.id_pres = D.id_disp AND G.id_drug = P.drug";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, active);
            ResultSet res = st.executeQuery();
            Prescription pres;
            int i;
            while(res.next()) {
                i = 1;
                pres = new Prescription();
                pres.setPatient(res.getInt(i++));
                pres.setFamilydoctor(res.getInt(i++));
                pres.setVisit(res.getInt(i++));
                pres.setPaid(res.getByte(i++));
                pres.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        pres.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        pres.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        pres.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                pres.setMadedate(res.getDate(i++));
                pres.setId(res.getInt(i++));
                pres.setDrug(res.getInt(i++));
                pres.setQuantity(res.getInt(i++));
                pres.setActive(res.getByte(i++));
                pres.setDrugstore(res.getInt(i++));
                pres.setName(res.getString(i++));
                p.add(pres);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Prescription> getByPatient(Integer id_pat) throws DaoException {
        try {
            List<Prescription> p = new ArrayList<>();
            String query = "SELECT * FROM prescription P, disposition D, drug G WHERE patient = ? AND P.id_pres = D.id_disp AND G.id_drug = P.drug";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                Prescription pres;
                pres = new Prescription();
                pres.setPatient(res.getInt("patient"));
                pres.setFamilydoctor(res.getInt("familydoctor"));
                pres.setVisit(res.getInt("visit"));
                pres.setPaid(res.getByte("paid"));
                pres.setTicket(res.getInt("ticket"));
                pres.setType(TypeDisposition.P);
                pres.setMadedate(res.getDate("madedate"));
                pres.setId(res.getInt("id_pres"));
                pres.setDrug(res.getInt("drug"));
                pres.setQuantity(res.getInt("quantity"));
                pres.setActive(res.getByte("active"));
                pres.setDrugstore(res.getInt("drugstore"));
                pres.setName(res.getString("name"));
                p.add(pres);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Prescription> getActiveByPatient(Integer id_pat) throws DaoException {
        try {
            List<Prescription> p = new ArrayList<>();
            String query = "SELECT * FROM prescription P, disposition D, drug G WHERE patient = ? AND active = 1 AND P.id_pres = D.id_disp AND G.id_drug = P.drug";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                Prescription pres;
                pres = new Prescription();
                pres.setPatient(res.getInt("patient"));
                pres.setFamilydoctor(res.getInt("familydoctor"));
                pres.setVisit(res.getInt("visit"));
                pres.setPaid(res.getByte("paid"));
                pres.setTicket(res.getInt("ticket"));
                pres.setType(TypeDisposition.P);
                pres.setMadedate(res.getDate("madedate"));
                pres.setId(res.getInt("id_pres"));
                pres.setDrug(res.getInt("drug"));
                pres.setQuantity(res.getInt("quantity"));
                pres.setActive(res.getByte("active"));
                pres.setDrugstore(res.getInt("drugstore"));
                pres.setName(res.getString("name"));
                p.add(pres);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public boolean addPrescription (Prescription prescription) throws DaoException {
        try {
            String query = "INSERT INTO disposition (patient, familydoctor, visit, ticket, type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, prescription.getPatient());
            st.setInt(2, prescription.getFamilydoctor());
            st.setInt(3, prescription.getVisit());
            st.setInt(4, prescription.getTicket());
            st.setString(5, prescription.getType().toString());
            st.executeUpdate();
            
            query = "SELECT id_disp FROM disposition WHERE patient = ? ORDER BY id_disp DESC LIMIT 1";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, prescription.getPatient());
            ResultSet res = st.executeQuery();
            int dispositionId = 0;
            if(res.first()) {
                dispositionId = res.getInt("id_disp");
            }
            res.close();
            
            query = "INSERT INTO prescription (id_pres, drug, quantity) VALUES (?, ?, ?)";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, dispositionId);
            st.setInt(2, prescription.getDrug());
            st.setInt(3, prescription.getQuantity());
            System.out.println("DRUGID: " + prescription.getDrug());
            st.executeUpdate();
            
            st.close();
            return true;
        } catch (SQLException ex) {
                Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
    }
    
    @Override
    public void setActive (Integer id_pres, Integer id_drugstore) throws DaoException {
        try {
                String query = "UPDATE prescription SET active = ?, drugstore = ? WHERE id_pres = ?";
                PreparedStatement st = this.getCon().prepareStatement(query);
                st.setInt(1, 0);
                st.setInt(2, id_drugstore);
                st.setInt(3, id_pres);
                int count = st.executeUpdate();
                st.close();
                if (count != 1) {
                        throw new NotFoundDAOException("prescription with id: " + id_pres + " not found");
                }
        } catch (SQLException ex) {
                Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                throw new DaoException(ex);
        }
    }
    
    @Override
    public List<PrescriptionPaid> getPaidByDistrict (Integer district) throws DaoException {
        try {
            List<PrescriptionPaid> p = new ArrayList<>();
            String query = "SELECT PA.ssn as passn, DC.doc_reg_numb, G.name as gname, S.city as scity, D.ticket, D.madedate FROM prescription P,"
                            + " disposition D, drug G, drugstore S, patient PA, doctor DC WHERE S.ddistrict = ? AND P.drugstore = S.id_drst AND P.active = 0 "
                            + "AND P.id_pres = D.id_disp AND G.id_drug = P.drug AND PA.id_pat = D.patient AND D.familydoctor = DC.id_doc";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, district);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                PrescriptionPaid pres = new PrescriptionPaid();
                pres.setSsnPaziente(res.getString("passn"));
                pres.setDoctorCode(res.getString("doc_reg_numb"));
                pres.setDrug(res.getString("gname"));
                pres.setDrugstore("Farmacia di " + res.getString("scity"));
                pres.setTicket(res.getInt("ticket"));
                pres.setData(res.getTimestamp("madedate"));
                p.add(pres);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
        
    @Override
    public List<PrescriptionPaid> getPaidByFarmacia (Integer farmaciaId) throws DaoException {
        try {
            List<PrescriptionPaid> p = new ArrayList<>();
            String query = "SELECT PA.ssn as passn, DC.doc_reg_numb, G.name as gname, S.city as scity, D.ticket, D.madedate FROM prescription P,"
                            + " disposition D, drug G, drugstore S, patient PA, doctor DC WHERE P.drugstore = ? AND P.drugstore = S.id_drst AND P.active = 0 "
                            + "AND P.id_pres = D.id_disp AND G.id_drug = P.drug AND PA.id_pat = D.patient AND D.familydoctor = DC.id_doc";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, farmaciaId);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                PrescriptionPaid pres = new PrescriptionPaid();
                pres.setSsnPaziente(res.getString("passn"));
                pres.setDoctorCode(res.getString("doc_reg_numb"));
                pres.setDrug(res.getString("gname"));
                pres.setDrugstore("Farmacia di " + res.getString("scity"));
                pres.setTicket(res.getInt("ticket"));
                pres.setData(res.getTimestamp("madedate"));
                p.add(pres);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List <PrescriptionChs> getPrescriptionChsByPatient (int pazienteId) throws DaoException {
        try {
            List<PrescriptionChs> p = new ArrayList<>();
            String query = "SELECT DG.name, V.visdate, P.active, DS.city, D.madedate, P.id_pres FROM drug DG, prescription P, drugstore DS, disposition D, visit V "
                    + "WHERE DG.id_drug=P.drug AND P.drugstore=DS.id_drst AND D.id_disp=P.id_pres AND D.visit=V.id_visit AND D.patient=? "
                    + "ORDER BY V.visdate DESC, D.madedate DESC";  
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, pazienteId);
            ResultSet res = st.executeQuery();
            int i;
            PrescriptionChs pres;
            while(res.next()) {
                i = 1;
                pres = new PrescriptionChs();
                pres.setDrugname(res.getString(i++));
                pres.setVisdate (res.getDate(i++));
                pres.setActive(res.getInt(i++));
                pres.setDrugstore(res.getString(i++));
                pres.setMadedate(res.getDate(i++));
                pres.setId(res.getInt(i++));
                p.add(pres);
            }
            res.close();
            st.close();
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
}
