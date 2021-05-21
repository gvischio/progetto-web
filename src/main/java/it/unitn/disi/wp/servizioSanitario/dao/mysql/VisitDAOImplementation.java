/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Mike_TdT
 */
public class VisitDAOImplementation extends AbstractDAO implements VisitDAO{

    public VisitDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Visit getById(Integer id) throws DaoException {
        try {
            Visit v = new Visit();
            String query = "SELECT * FROM visit WHERE id_visit = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                v.setId(res.getInt("id_visit"));
                v.setPatient(res.getInt("patient"));
                v.setFamilydoctor(res.getInt("familydoctor"));
                v.setVisdate(res.getDate("visdate"));
                v.setVistimestamp(res.getTimestamp("visdate"));
                v.setDoctorsays(res.getString("doctorsays"));
                v.setMotivation(res.getString("motivation"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'V':
                        v.setType(TypeVisit.V);
                        break;
                    case 'R':
                        v.setType(TypeVisit.R);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                res.close();
                st.close();
                return v;
            }
            throw new NotFoundDAOException("Visit with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public boolean updateAnamnesiVisit (Integer id, Visit visit) throws DaoException {
            boolean valid = visit.isValidOnUpdate(dAOFactory);
            if (valid) {
                    try {
                            String query = "UPDATE visit SET doctorsays = ? WHERE id_visit = ?";
                            PreparedStatement st = this.getCon().prepareStatement(query);
                            st.setString(1, visit.getDoctorsays());
                            st.setInt(2, id);
                            int count = st.executeUpdate();
                            st.close();
                            if (count != 1) {
                                    throw new NotFoundDAOException("Visit with id: " + id + " not found");
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
    public List<Visit> getByPatient(Integer id_pat) throws DaoException {
        try {
            List<Visit> vis = new ArrayList<>();
            String query = "SELECT * FROM visit WHERE patient = ? AND type = 'V' ORDER BY visdate DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            ResultSet res = st.executeQuery();
            Visit v;
            int i;
            while(res.next()) {
                i = 1;
                v = new Visit();
                v.setId(res.getInt(i++));
                v.setPatient(res.getInt(i++));
                v.setFamilydoctor(res.getInt(i++));
                v.setVisdate(res.getDate(i));
                v.setVistimestamp(res.getTimestamp(i++));
                v.setDoctorsays(res.getString(i++));
                v.setMotivation(res.getString(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'V':
                        v.setType(TypeVisit.V);
                        break;
                    case 'R':
                        v.setType(TypeVisit.R);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                vis.add(v);
            }
            res.close();
            st.close();
            return vis;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Visit> getByFamilyDoctor(Integer id_doc) throws DaoException {
        try {
            List<Visit> vis = new ArrayList<>();
            String query = "SELECT * FROM visit WHERE familydoctor = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_doc);
            ResultSet res = st.executeQuery();
            Visit v;
            int i;
            while(res.next()) {
                i = 1;
                v = new Visit();
                v.setId(res.getInt(i++));
                v.setPatient(res.getInt(i++));
                v.setFamilydoctor(res.getInt(i++));
                v.setVisdate(res.getDate(i));
                v.setVistimestamp(res.getTimestamp(i++));
                v.setDoctorsays(res.getString(i++));
                v.setMotivation(res.getString(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'V':
                        v.setType(TypeVisit.V);
                        break;
                    case 'R':
                        v.setType(TypeVisit.R);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                vis.add(v);
            }
            res.close();
            st.close();
            return vis;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Visit> getByVisDate(Date visdate) throws DaoException {
        try {
            List<Visit> vis = new ArrayList<>();
            String query = "SELECT * FROM visit WHERE DATE(visdate) = '" + visdate.toString() + "'";
            PreparedStatement st = this.getCon().prepareStatement(query);
            ResultSet res = st.executeQuery();
            Visit v;
            int i;
            while(res.next()) {
                i = 1;
                v = new Visit();
                v.setId(res.getInt(i++));
                v.setPatient(res.getInt(i++));
                v.setFamilydoctor(res.getInt(i++));
                v.setVisdate(res.getDate(i));
                v.setVistimestamp(res.getTimestamp(i++));
                v.setDoctorsays(res.getString(i++));
                v.setMotivation(res.getString(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'V':
                        v.setType(TypeVisit.V);
                        break;
                    case 'R':
                        v.setType(TypeVisit.R);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                vis.add(v);
            }
            res.close();
            st.close();
            return vis;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public Visit getLastByPatient(Integer id) throws DaoException {
        try {
            Visit v = new Visit();
            String query = "SELECT * FROM `visit` WHERE patient = ? AND (DATE(visdate) < CURRENT_DATE) AND type = 'V' ORDER BY visdate DESC LIMIT 1";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            //st.setString(2, String.valueOf(java.time.LocalDate.now()));
            ResultSet res = st.executeQuery();
            if(res.first()) {
                v.setId(res.getInt("id_visit"));
                v.setPatient(res.getInt("patient"));
                v.setFamilydoctor(res.getInt("familydoctor"));
                v.setVisdate(res.getDate("visdate"));
                v.setVistimestamp(res.getTimestamp("visdate"));
                v.setDoctorsays(res.getString("doctorsays"));
                v.setMotivation(res.getString("motivation"));
                v.setType(TypeVisit.V);
                
                res.close();
                st.close();
                return v;
            }
            throw new NotFoundDAOException("Previous visit not found");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public Visit getNextByPatient(Integer id) throws DaoException {
        try {
            Visit v = new Visit();
            String query = "SELECT * FROM `visit` WHERE patient = ? AND (DATE(visdate) >= CURRENT_DATE) AND type = 'V' ORDER BY visdate LIMIT 1";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            //st.setString(2, String.valueOf(java.time.LocalDate.now()));
            ResultSet res = st.executeQuery();
            if(res.first()) {
                v.setId(res.getInt("id_visit"));
                v.setPatient(res.getInt("patient"));
                v.setFamilydoctor(res.getInt("familydoctor"));
                v.setVisdate(res.getDate("visdate"));
                v.setVistimestamp(res.getTimestamp("visdate"));
                v.setDoctorsays(res.getString("doctorsays"));
                v.setMotivation(res.getString("motivation"));
                v.setType(TypeVisit.V);
                
                res.close();
                st.close();
                return v;
            }
            throw new NotFoundDAOException("Next visit not found");
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public Visit insertVisitDottore (Integer pazienteId, Integer dottoreId) throws DaoException {
        try {
            String query = "INSERT INTO visit (patient, familydoctor, visdate, motivation) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, pazienteId);
            st.setInt(2, dottoreId);
            st.setString(3, "Visita senza prenotazione da parte del paziente");
            st.executeUpdate();
            st.close();
            return getNextByPatient(pazienteId);
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public Boolean insertVisitPaziente (Visit visit, Timestamp tmstp) throws DaoException {
        try {
            String query = "INSERT INTO visit (patient, familydoctor, visdate, motivation) VALUES (?, ?, ?, ?)";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, visit.getPatient());
            st.setInt(2, visit.getFamilydoctor());
            st.setTimestamp(3, tmstp);
            st.setString(4, visit.getMotivation());
            
            st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public List<Appuntamento> getTodayVisitsByFamilyDoctor(int familydoctor) throws DaoException 
    {
        try {
            List<Appuntamento> vis = new ArrayList<>();
            String query = "SELECT V.id_visit, CONCAT(P.firstname, ' ', P.lastname) AS patname, V.visdate, V.motivation "
                                + "FROM visit V, patient P WHERE V.patient=P.id_pat AND DATE(V.visdate)=CURRENT_DATE AND V.familydoctor = ? ORDER BY V.visdate";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, familydoctor);
            ResultSet res = st.executeQuery();
            while(res.next()) {
                vis.add(new Appuntamento(res.getInt("id_visit"), res.getString("patname"), res.getTimestamp("visdate"), res.getString("motivation")));
            }
            res.close();
            st.close();
            return vis;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public Timestamp getNextVisitAvailableForFamilyDoctor(int fam_doc) throws DaoException {
        try {
            String query = "SELECT MAX(visdate) AS dat FROM visit WHERE familydoctor=?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, fam_doc);
            ResultSet res = st.executeQuery();
            Timestamp ret = new Timestamp(1);
            if(res.first()) {
                Timestamp next =  res.getTimestamp("dat");
                //System.out.println(next.toString());
                Timestamp now = new Timestamp(System.currentTimeMillis());
                try{
                    if(next.before(now))
                    {
                        next = now;
                        next.setTime(next.getTime() + 60*60*1000);
                    }
                }
                catch(Exception e)
                {
                    next = now;
                }
                Calendar c = new GregorianCalendar();
                c.setTimeInMillis(next.getTime());
                int min = c.get(Calendar.MINUTE), hour = c.get(Calendar.HOUR_OF_DAY);
                
                long toadd = 0;
                // ORARIO 9:00-13:00 15:00-19:00
                // imposto l'orario alla mezz'ora successiva
                int type=1; // 1 alle :30, 2 alle :00
                if(min>=0 && min<30)
                    toadd += (30-min) * 60 * 1000;
                else if(min>=30 && min<60)
                {
                    toadd += (60 - min) * 60 * 1000;
                    hour++;
                    type=2;
                }
                // se sono finito nella pausa pranzo
                if(hour >= 13 && hour < 15)
                {
                    if(type==1) // devo aggiungere tot-1 ore e 30 minuti
                        toadd += 30*60*1000 + (14-hour)*60*60*1000;
                    else
                        toadd += (15-hour)*60*60*1000;
                }
                // se sono finito nell'orario di chiusura
                else if (hour >= 19)
                {
                    if(type==1) // devo aggiungere tot-1 ore e 30 minuti
                        toadd += 30*60*1000 + (32-hour)*60*60*1000;
                    else
                        toadd += (33-hour)*60*60*1000;
                }
                else if (hour < 9)
                {
                    if(type==1) // devo aggiungere tot-1 ore e 30 minuti
                        toadd += 30*60*1000 + (8-hour)*60*60*1000;
                    else
                        toadd += (9-hour)*60*60*1000;
                }
                // orario corretto
                else {}
                ret.setTime(next.getTime() + toadd);
            }
            st.close();
            return ret;
        } catch (SQLException ex) {
            Logger.getLogger(VisitDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
}
