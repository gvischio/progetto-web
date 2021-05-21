/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.dao.mysql;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import it.unitn.disi.wp.servizioSanitario.entities.utils.ExamChs;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import static java.time.LocalDate.now;

/**
 *
 * @author Mike_TdT
 */
public class ExamDAOImplementation extends AbstractDAO implements ExamDAO{

    public ExamDAOImplementation(DAOFactory dAOFactory) {
        super(dAOFactory);
    }

    @Override
    public Exam getById(Integer id) throws DaoException {
        try {
            Exam exam = new Exam();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate,"
                    + "E.id_exam,E.examcode,E.result,E.providedby, L.name "
                    + "FROM exam E, disposition D, examlist L "
                    + "WHERE id_disp = id_exam AND id_exlis = examcode "
                    + "AND id_exam = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id);
            ResultSet res = st.executeQuery();
            if(res.first()) {
                exam.setId(res.getInt("id_exam"));
                exam.setPatient(res.getInt("patient"));
                exam.setFamilydoctor(res.getInt("familydoctor"));
                exam.setVisit(res.getInt("visit"));
                exam.setPaid(res.getByte("paid"));
                exam.setTicket(res.getInt("ticket"));
                String tipo = res.getString("type");   
                switch (tipo.charAt(0)) {
                    case 'E':
                        exam.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        exam.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        exam.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                exam.setMadedate(res.getDate("madedate"));
                exam.setExamcode(res.getInt("examcode"));
                exam.setResult(res.getString("result"));
                exam.setProvidedby(res.getInt("providedby"));
                exam.setName(res.getString("name"));
                res.close();
                st.close();
                return exam;
            }
            throw new NotFoundDAOException("Exam with id: " + id + " not found.");
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Exam> getByExamCode(Integer examcode) throws DaoException {
        try{
            List<Exam> e = new ArrayList<>();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate,"
                    + "E.id_exam,E.examcode,E.result,E.providedby, L.name "
                    + "FROM exam E, disposition D, examlist L "
                    + "WHERE id_disp = id_exam AND id_exlis = examcode "
                    + "AND examcode = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, examcode);
            ResultSet res = st.executeQuery();
            Exam exam;
            int i;
            while(res.next()) {
                i = 1;
                exam = new Exam();
                exam.setPatient(res.getInt(i++));
                exam.setFamilydoctor(res.getInt(i++));
                exam.setVisit(res.getInt(i++));
                exam.setPaid(res.getByte(i++));
                exam.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        exam.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        exam.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        exam.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                exam.setMadedate(res.getDate(i++));
                exam.setId(res.getInt(i++));
                exam.setExamcode(res.getInt(i++));
                exam.setResult(res.getString(i++));
                exam.setProvidedby(res.getInt(i++));
                exam.setName(res.getString(i++));
                e.add(exam);
            }
            res.close();
            st.close();
            return e;

        }   catch (SQLException ex) {
            Logger.getLogger(ExamDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Exam> getByProvidedBy(Integer providedby) throws DaoException {
        try{
            List<Exam> e = new ArrayList<>();
            String query = "SELECT D.patient,D.familydoctor,D.visit,D.paid,D.ticket,D.type,D.madedate,"
                    + "E.id_exam,E.examcode,E.result,E.providedby, L.name "
                    + "FROM exam E, disposition D, examlist L "
                    + "WHERE id_disp = id_exam AND id_exlis = examcode "
                    + "AND providedby = ?";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, providedby);
            ResultSet res = st.executeQuery();
            Exam exam;
            int i;
            while(res.next()) {
                i = 1;
                exam = new Exam();
                exam.setPatient(res.getInt(i++));
                exam.setFamilydoctor(res.getInt(i++));
                exam.setVisit(res.getInt(i++));
                exam.setPaid(res.getByte(i++));
                exam.setTicket(res.getInt(i++));
                String tipo = res.getString(i++);   
                switch (tipo.charAt(0)) {
                    case 'E':
                        exam.setType(TypeDisposition.E);
                        break;
                    case 'P':
                        exam.setType(TypeDisposition.P);
                        break;
                    case 'S':
                        exam.setType(TypeDisposition.S);
                        break;
                    default:
                        System.out.println("Inserito carattere sbagliato nel DB");
                        break;
                }
                exam.setMadedate(res.getDate(i++));
                exam.setId(res.getInt(i++));
                exam.setExamcode(res.getInt(i++));
                exam.setResult(res.getString(i++));
                exam.setProvidedby(res.getInt(i++));
                exam.setName(res.getString(i++));
                e.add(exam);
            }
            res.close();
            st.close();
            return e;

        }   catch (SQLException ex) {
            Logger.getLogger(ExamDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Exam> getByPatient(Integer id_pat) throws DaoException {
        try {
            List<Exam> e = new ArrayList<>();
            String query = "SELECT * FROM exam E, disposition D, examlist L WHERE patient = ? AND E.id_exam = D.id_disp AND id_exlis = examcode";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            Exam exam;
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                exam = new Exam();
                exam.setId(res.getInt("id_exam"));
                exam.setPatient(res.getInt("patient"));
                exam.setFamilydoctor(res.getInt("familydoctor"));
                exam.setVisit(res.getInt("visit"));
                exam.setPaid(res.getByte("paid"));
                exam.setTicket(res.getInt("ticket"));   
                exam.setType(TypeDisposition.E);
                exam.setMadedate(res.getDate("madedate"));
                exam.setExamcode(res.getInt("examcode"));
                exam.setResult(res.getString("result"));
                exam.setProvidedby(res.getInt("providedby"));
                exam.setName(res.getString("name"));
                e.add(exam);
            }
            res.close();
            st.close();
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public List<Exam> getToDoByPatient(Integer id_pat) throws DaoException {
        try {
            List<Exam> e = new ArrayList<>();
            String query = "SELECT * FROM exam E, disposition D, examlist L " + 
                    "WHERE patient = ? AND (madedate IS NULL OR DATE(madedate) > CURRENT_DATE) AND E.id_exam = D.id_disp AND id_exlis = examcode";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_pat);
            //st.setString(2, String.valueOf(java.time.LocalDate.now())); 
            Exam exam;
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                exam = new Exam();
                exam.setId(res.getInt("id_exam"));
                exam.setPatient(res.getInt("patient"));
                exam.setFamilydoctor(res.getInt("familydoctor"));
                exam.setVisit(res.getInt("visit"));
                exam.setPaid(res.getByte("paid"));
                exam.setTicket(res.getInt("ticket"));   
                exam.setType(TypeDisposition.E);
                exam.setMadedate(res.getDate("madedate"));
                exam.setExamcode(res.getInt("examcode"));
                exam.setResult(res.getString("result"));
                exam.setProvidedby(res.getInt("providedby"));
                exam.setName(res.getString("name"));
                e.add(exam);
            }
            res.close();
            st.close();
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public boolean addExam (Exam exam) throws DaoException {
        try {
            String query = "INSERT INTO disposition (patient, familydoctor, visit, ticket, type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, exam.getPatient());
            st.setInt(2, exam.getFamilydoctor());
            st.setInt(3, exam.getVisit());
            st.setInt(4, exam.getTicket());
            st.setString(5, exam.getType().toString());
            st.executeUpdate();
            
            query = "SELECT id_disp FROM disposition WHERE patient = ? ORDER BY id_disp DESC LIMIT 1";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, exam.getPatient());
            ResultSet res = st.executeQuery();
            int dispositionId = 0;
            if(res.first()) {
                dispositionId = res.getInt("id_disp");
            }
            res.close();
            
            query = "INSERT INTO exam (id_exam, examcode) VALUES (?, ?)";
            st = this.getCon().prepareStatement(query);
            st.setInt(1, dispositionId);
            st.setInt(2, exam.getExamcode());
            st.executeUpdate();
            
            st.close();
            return true;
        } catch (SQLException ex) {
                Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
    }
    
    @Override
    public boolean BookExam(Integer id_exam, Timestamp date, Integer chs)
    {
        try {
            PreparedStatement st = this.getCon().prepareStatement("UPDATE disposition SET madedate=? WHERE id_disp=?");
            st.setTimestamp(1, date);
            st.setInt(2, id_exam);
            st.executeUpdate();
            
            st = this.getCon().prepareStatement("UPDATE exam SET providedby=? WHERE id_exam=?");
            st.setInt(1, chs);
            st.setInt(2, id_exam);
            st.executeUpdate();
            
            return true;
        }
        catch (Exception e) { return false; }
    }
    
    @Override
    public List<ExamChs> getExamChsByPatient (int pazienteId) throws DaoException{
        try {
            List<ExamChs> e = new ArrayList<>();
            String query = "SELECT E.id_exam, EL.name, V.visdate , D.madedate, C.name AS cname, D.paid FROM exam E, examlist EL, disposition D, visit V, chs C WHERE EL.id_exlis=E.examcode AND E.id_exam=D.id_disp AND D.visit=V.id_visit AND E.providedby=C.id_chs AND D.patient=? ORDER BY V.visdate DESC, D.madedate DESC";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, pazienteId);
            ExamChs exam;
            ResultSet res = st.executeQuery();
            while(res.next())
            {
                exam = new ExamChs();
                exam.setId(res.getInt("id_exam"));
                exam.setExamname(res.getString("name"));
                exam.setVisdate(res.getDate("visdate"));
                exam.setMadedate(res.getDate("madedate"));
                exam.setProvidedby(res.getString("cname"));
                exam.setPaid(res.getInt("paid"));
                e.add(exam);
            }
            res.close();
            st.close();
            return e;
        } catch (SQLException ex) {
            Logger.getLogger(PazienteDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw new DaoException(ex);
        }
    }
    
    @Override
    public boolean updateRisultatoEsame (Exam exam) throws DaoException {
            boolean valid = exam.isValidOnUpdate(dAOFactory);
            if (valid) {
                    try {
                            String query = "UPDATE exam SET result = ? WHERE id_exam = ?";
                            PreparedStatement st = this.getCon().prepareStatement(query);
                            st.setString(1, exam.getResult());
                            st.setInt(2, exam.getId());
                            int count = st.executeUpdate();
                            st.close();
                            if (count != 1) {
                                    throw new NotFoundDAOException("Exam with id: " + exam.getId() + " not found");
                            }
                            return valid;
                    } catch (SQLException ex) {
                            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                            throw new DaoException(ex);
                    }
            }
            return valid;
    }
    
    public boolean setEsameEffettuato (Exam exam) throws DaoException {
        boolean valid = exam.isValidOnUpdate(dAOFactory);
            if (valid) {
                    try {
                            String query = "UPDATE exam SET providedby = ? WHERE id_exam = ?";
                            PreparedStatement st = this.getCon().prepareStatement(query);
                            st.setInt(1, exam.getProvidedby());
                            st.setInt(2, exam.getId());
                            System.out.println("exam.getProvided " + exam.getProvidedby());
                            int count = st.executeUpdate();
                            System.out.println("count: " + count);
                            st.close();

                            query = "UPDATE disposition SET paid=1, madedate = now() WHERE id_disp=?";
                            st = this.getCon().prepareStatement(query);
                            st.setInt(1, exam.getId());
                            count = st.executeUpdate();
                            System.out.println("count disp: " + count);
                            st.close();
                            
                            if (count != 1) {
                                    throw new NotFoundDAOException("Exam with id: " + exam.getId() + " not found");
                            }
                            return valid;
                    } catch (SQLException ex) {
                            Logger.getLogger(UserDAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
                            throw new DaoException(ex);
                    }
            }
            return valid;
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
                j++;
                k++;
                
                query = "SELECT email FROM user WHERE id_user = "+ patientId.get(i);
                st = this.getCon().prepareStatement(query);
                System.out.println(patientId.get(i));
//                st.setInt(1, patientId.get(i));
                res = st.executeQuery();
                if(res.first()) {
                    System.out.println(res.getString("email"));
                    email.add(res.getString("email"));
                } else throw new SQLException();
                
                
                query = "INSERT INTO visit (patient,familydoctor, visdate, motivation, type) VALUES ("+patientId.get(i)+", "+chs.getId()+", now(), 'richiamo', 'R')";
                st = this.getCon().prepareStatement(query);
                st.executeUpdate();
                
                query = "INSERT INTO disposition (patient, familydoctor, visit, paid, ticket, type) VALUES ("+patientId.get(i)+", "+chs.getId()+", "+j+", 0, 0, 'E')";
                st = this.getCon().prepareStatement(query);
                st.executeUpdate();
                
                query = "INSERT INTO exam (id_exam, examcode) VALUES ("+k+", "+code+" )";
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
    public List<Appuntamento> getTodayVisitsForChs(Integer id_chs) throws DaoException
    {
        try {
            List<Appuntamento> vis = new ArrayList<>();
            String query = "SELECT D.madedate AS data, L.name AS visita, D.id_disp AS id_disp, CONCAT(P.firstname, ' ', P.lastname) AS patname " +
                    "FROM disposition D JOIN visit V ON D.visit=V.id_visit JOIN exam E ON D.id_disp=E.id_exam JOIN examlist L ON E.examcode=L.id_exlis JOIN patient P ON P.id_pat=D.patient " +
                    "WHERE DATE(D.madedate)=CURRENT_DATE AND E.providedby=? ORDER BY D.madedate";
            PreparedStatement st = this.getCon().prepareStatement(query);
            st.setInt(1, id_chs);
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
