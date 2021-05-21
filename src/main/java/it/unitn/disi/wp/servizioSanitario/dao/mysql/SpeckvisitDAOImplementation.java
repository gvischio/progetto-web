/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import it.unitn.disi.wp.servizioSanitario.entities.utils.SpeckvisitChs;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike_TdT
 */
public class SpeckvisitDAOImplementation extends AbstractDAO implements SpeckvisitDAO{

    public SpeckvisitDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Speckvisit getById(Integer id) throws DaoException {
        try {
            Speckvisit spec = new Speckvisit();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate, "
                    + "S.*, L.name "
                    + "FROM speckvisit S, disposition D, visitlist L "
                    + "WHERE id_spvt = ? "
                    + "AND S.id_spvt = D.id_disp AND L.id_vislis = S.visitcode";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                spec.setPatient(res.getInt("patient"));
                spec.setFamilydoctor(res.getInt("familydoctor"));
                spec.setVisit(res.getInt("visit"));
                spec.setPaid(res.getByte("paid"));
                spec.setTicket(res.getInt("ticket"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'E':
                        spec.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        spec.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        spec.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                spec.setMadedate(res.getDate("madedate"));
                spec.setId(res.getInt("id_spvt"));
                spec.setSpecialist(res.getInt("specialist"));
                spec.setVisitcode(res.getInt("visitcode"));
                spec.setResult(res.getString("result"));
                spec.setName(res.getString("name"));
                res.close();
                st.close();
                return spec;
            }
            throw new NotFoundDAOException("Prescription with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
     @Override
    public boolean updateEsitoSpeckvisit (Integer id, Speckvisit visit) throws DaoException {
            boolean valid = visit.isValidOnUpdate(dAOFactory);
            if (valid) {
                    try {
                            String query = "UPDATE speckvisit SET result = ? WHERE id_spvt = ?";
                            PreparedStatement st = this.getCon().prepareStatement(query);
                            st.setString(1, visit.getResult());
                            st.setInt(2, id);
                            int count = st.executeUpdate();
                            st.close();
                            if (count != 1) {
                                    throw new NotFoundDAOException("Speckvisit with id: " + id + " not found");
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
    public List<Speckvisit> getBySpecialist(Integer specialist) throws DaoException {
        try {
            List<Speckvisit> s = new ArrayList<>();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate, "
                    + "S.*, L.name "
                    + "FROM speckvisit S, disposition D, visitlist L "
                    + "WHERE specialist = ? "
                    + "AND S.id_spvt = D.id_disp AND L.id_vislis = S.visitcode";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, specialist);
            ResultSet res = st.executeQuery();
            Speckvisit spec;
            int i;
            while(res.next()) {
                i = 1;
                spec = new Speckvisit();
                spec.setPatient(res.getInt(i++));
                spec.setFamilydoctor(res.getInt(i++));
                spec.setVisit(res.getInt(i++));
                spec.setPaid(res.getByte(i++));
                spec.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        spec.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        spec.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        spec.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                spec.setMadedate(res.getDate(i++));
                spec.setId(res.getInt(i++));
                spec.setSpecialist(res.getInt(i++));
                spec.setVisitcode(res.getInt(i++));
                spec.setResult(res.getString(i++));
                spec.setName(res.getString(i++));
                s.add(spec);
            }
            res.close();
            st.close();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Speckvisit> getByVisitCode(Integer visitcode) throws DaoException {
        try {
            List<Speckvisit> s = new ArrayList<>();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate, "
                    + "S.*, L.name "
                    + "FROM speckvisit S, disposition D, visitlist L "
                    + "WHERE visitcode = ? "
                    + "AND S.id_spvt = D.id_disp AND L.id_vislis = S.visitcode";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, visitcode);
            ResultSet res = st.executeQuery();
            Speckvisit spec;
            int i;
            while(res.next()) {
                i = 1;
                spec = new Speckvisit();
                spec.setPatient(res.getInt(i++));
                spec.setFamilydoctor(res.getInt(i++));
                spec.setVisit(res.getInt(i++));
                spec.setPaid(res.getByte(i++));
                spec.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        spec.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        spec.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        spec.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                spec.setMadedate(res.getDate(i++));
                spec.setId(res.getInt(i++));
                spec.setSpecialist(res.getInt(i++));
                spec.setVisitcode(res.getInt(i++));
                spec.setResult(res.getString(i++));
                spec.setName(res.getString(i++));
                s.add(spec);
            }
            res.close();
            st.close();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(PrescriptionDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Speckvisit> getByPatient(Integer id_pat) throws DaoException {
        try {
            List<Speckvisit> s = new ArrayList<>();
            String query = "SELECT * FROM speckvisit S, disposition D, visitlist L WHERE patient = ? AND S.id_spvt = D.id_disp AND L.id_vislis = S.visitcode";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            Speckvisit spec;
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                spec = new Speckvisit();
                spec.setPatient(res.getInt("patient"));
                spec.setFamilydoctor(res.getInt("familydoctor"));
                spec.setVisit(res.getInt("visit"));
                spec.setPaid(res.getByte("paid"));
                spec.setTicket(res.getInt("ticket"));
                spec.setType(TypeDisposition.S);
                spec.setMadedate(res.getDate("madedate"));
                spec.setId(res.getInt("id_spvt"));
                spec.setSpecialist(res.getInt("specialist"));
                spec.setVisitcode(res.getInt("visitcode"));
                spec.setResult(res.getString("result"));
                spec.setName(res.getString("name"));
                s.add(spec);
            }
            res.close();
            st.close();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Speckvisit> getToDoByPatient(Integer id_pat) throws DaoException {
        try {
            List<Speckvisit> s = new ArrayList<>();
            String query = "SELECT * FROM speckvisit S, disposition D, visitlist L " +
                    "WHERE patient = ? AND (madedate IS NULL OR DATE(madedate) > CURRENT_DATE) AND S.id_spvt = D.id_disp AND L.id_vislis = S.visitcode";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            //st.setString(2, String.valueOf(java.time.LocalDate.now()));            
            Speckvisit spec;
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                spec = new Speckvisit();
                spec.setPatient(res.getInt("patient"));
                spec.setFamilydoctor(res.getInt("familydoctor"));
                spec.setVisit(res.getInt("visit"));
                spec.setPaid(res.getByte("paid"));
                spec.setTicket(res.getInt("ticket"));
                spec.setType(TypeDisposition.S);
                spec.setMadedate(res.getDate("madedate"));
                spec.setId(res.getInt("id_spvt"));
                spec.setSpecialist(res.getInt("specialist"));
                spec.setVisitcode(res.getInt("visitcode"));
                spec.setResult(res.getString("result"));
                spec.setName(res.getString("name"));
                s.add(spec);
            }
            res.close();
            st.close();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    public boolean addSpeckvisit(Speckvisit speckvisit) throws DaoException {
        try {
            String query = "INSERT INTO disposition (patient, familydoctor, visit, ticket, type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, speckvisit.getPatient());
            st.setInt(2, speckvisit.getFamilydoctor());
            st.setInt(3, speckvisit.getVisit());
            st.setInt(4, speckvisit.getTicket());
            st.setString(5, speckvisit.getType().toString());
            st.executeUpdate();
            
            query = "SELECT id_disp FROM disposition WHERE patient = ? ORDER BY id_disp DESC LIMIT 1";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, speckvisit.getPatient());
            ResultSet res = st.executeQuery();
            int dispositionId = 0;
            if(res.first()) {
                dispositionId = res.getInt("id_disp");
            }
            res.close();
            
            query = "INSERT INTO speckvisit (id_spvt, visitcode) VALUES (?, ?)";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, dispositionId);
            st.setInt(2, speckvisit.getVisitcode());
            st.executeUpdate();
            
            st.close();
            return true;
        } catch (SQLException ex) {
                Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
    }
    
    @Override
    public List <SpeckvisitChs> getSpeckvisitChsByPatiente (Integer patientId) throws DaoException {
        try {
            List<SpeckvisitChs> s = new ArrayList<>();
            String query = "SELECT SV.id_spvt, VL.name, V.visdate, D.madedate, P.firstname, P.lastname FROM speckvisit SV, visitlist VL, disposition D, visit V, patient P WHERE SV.visitcode=VL.id_vislis AND SV.id_spvt=D.id_disp AND D.visit=V.id_visit AND SV.specialist=P.id_pat AND D.patient=?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, patientId);           
            SpeckvisitChs spec;
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                spec = new SpeckvisitChs();
                spec.setId(res.getInt("id_spvt"));
                spec.setName(res.getString("name"));
                spec.setVisdate(res.getDate("visdate"));
                spec.setMadedate(res.getDate("madedate"));
                spec.setFirstname(res.getString("firstname"));
                spec.setLastname(res.getString("lastname"));
                s.add(spec);
            }
            res.close();
            st.close();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    public List<String> addRichiamo (Chs chs, Integer code, Integer maxage, Integer minage, String sex) throws DaoException {
        List<String> email = new ArrayList<>();
        try {
                List<Integer> patientId = new ArrayList<>();
                List<Integer> familydoctorId = new ArrayList<>();
                String query = "SELECT id_pat, familydoctor FROM (SELECT id_pat, familydoctor, YEAR (birthday) AS birthday, YEAR(now()) AS year FROM patient WHERE pdistrict="+chs.getCdistrict()+" AND sex='"+sex+"') AS tabella WHERE birthday<=year-"+minage+" AND birthday>=year-"+maxage+"";
                PreparedStatement st = this.getCon().prepareStatement(query);
                ResultSet res = st.executeQuery();
                while(res.next())
                {
                    patientId.add(res.getInt("id_pat"));
                    familydoctorId.add(res.getInt("familydoctor"));
                }           

                query = "SELECT MAX(id_visit) AS max FROM visit";
                st = this.getCon().prepareStatement(query);
                res = st.executeQuery();
                int j=0; 
                while (res.next()) { j = res.getInt("max"); }

                query = "SELECT MAX(id_disp) AS max FROM disposition";
                st = this.getCon().prepareStatement(query);
                res = st.executeQuery();
                int k=0;
                while (res.next()) { k = res.getInt("max"); }

                for (int i=0; i<patientId.size(); i++) {
                    
                    query = "SELECT email FROM user WHERE id_user = "+ patientId.get(i);
                    st = this.getCon().prepareStatement(query);
                    System.out.println(patientId.get(i));
    //                st.setInt(1, patientId.get(i));
                    res = st.executeQuery();
                    if(res.first()) {
                        System.out.println(res.getString("email"));
                        email.add(res.getString("email"));
                    } else throw new SQLException();
                    
                    System.out.println("dimensione pazienti : " + patientId.size());
                    j++;
                    k++;
                    System.out.println("inizio if. -- k " + k );
                    query = "INSERT INTO visit (patient,familydoctor, visdate, motivation, type) VALUES ("+patientId.get(i)+", "+chs.getId()+", now(), 'richiamo', 'R')";
                    st = this.getCon().prepareStatement(query);
                    st.executeUpdate();

                    System.out.println("secondo st if. -- k " + k );
                    query = "INSERT INTO disposition (patient, familydoctor, visit, paid, ticket, type) VALUES ("+patientId.get(i)+", "+chs.getId()+", "+j+", 0, 0, 'S')";
                    st = this.getCon().prepareStatement(query);
                    st.executeUpdate();
                    
                    System.out.println("k: " + k);
                    System.out.println("code: " + code);
                    query = "INSERT INTO speckvisit (id_spvt, visitcode) VALUES ( "+k+", "+code+" )";
                    st = this.getCon().prepareStatement(query);
                    st.executeUpdate();
                }        
                res.close();
                st.close();
            } catch (SQLException ex) {
                    Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        return email;
        }
    
    @Override
    public boolean BookSpeckVisit(Integer id_exam, Timestamp date, Integer doc)
    {
        try {
            PreparedStatement st = this.getCon().prepareStatement("UPDATE disposition SET madedate=? WHERE id_disp=?");
            st.setTimestamp(1, date);
            st.setInt(2, id_exam);
            st.executeUpdate();
            
            st = this.getCon().prepareStatement("UPDATE speckvisit SET specialist=? WHERE id_spvt=?");
            st.setInt(1, doc);
            st.setInt(2, id_exam);
            st.executeUpdate();
            
            return true;
        }
        catch (Exception e) { System.out.println(e.getMessage()); return false; }
    }
    
    @Override
    public List<Appuntamento> getTodayVisitsForSpecialist(Integer id_speckdoc) throws DaoException
    {
        try {
            List<Appuntamento> vis = new ArrayList<>();
            String query = "SELECT D.madedate AS data, L.name AS visita, D.id_disp AS id_disp, CONCAT(P.firstname, ' ', P.lastname) AS patname " +
                "FROM disposition D JOIN visit V ON D.visit=V.id_visit JOIN speckvisit S ON D.id_disp=S.id_spvt JOIN visitlist L ON S.visitcode=L.id_vislis JOIN patient P ON P.id_pat=D.patient " +
                "WHERE DATE(D.madedate)=CURRENT_DATE AND S.specialist=? ORDER BY D.madedate";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_speckdoc);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                vis.add(new Appuntamento(res.getInt("id_disp"), res.getString("patname"), res.getTimestamp("data"), res.getString("visita")));
            }
            res.close();
            st.close();
            return vis;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
}
