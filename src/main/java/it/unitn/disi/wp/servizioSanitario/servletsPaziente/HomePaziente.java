/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.District;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;

import it.unitn.disi.wp.servizioSanitario.entities.utils.Role;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author simmf
 */
public class HomePaziente extends HttpServlet {
    
    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private DistrictDAO districtDao;
    private VisitDAO visitDao;
    private ExamDAO examDao;
    private PhotoDAO photoDao;
    private PrescriptionDAO prescriptionDao;
    private SpeckvisitDAO speckvisitDao;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DaoException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        if(user == null)
            response.sendRedirect("../../ChiamaLogin");
        int id = user.getId();
        
        if(user.getRuolo() == Role.D)
            request.setAttribute("dottore", new Integer(1));
        else
            request.setAttribute("dottore", new Integer(0));
        
        daoFactory = new DAOFactoryImplementation();
        
        long sec = System.currentTimeMillis();
        System.err.println("HomePaziente: inizio caricamento");
        
        NotificationDAO notificationDao = daoFactory.getNotificationDAO();
        request.setAttribute("notif", notificationDao.getByUser(id));
        
        // PROVO A RECUPERARE DALLA SESSIONE ALCUNI DEI DATI
        // SE NON LI TROVO, VUOL DIRE CHE LI DEVO CARICARE DA ZERO
        Paziente paziente = null;
        try {
            paziente = (Paziente) session.getAttribute("paziente");
            if(paziente == null) throw new Exception();
        }
        catch(Exception ex)
        {
            pazienteDao = daoFactory.getPazienteDAO();
            paziente = pazienteDao.getById(id);
            session.setAttribute("paziente", paziente);
            System.out.println("aggiunto alla sessione il paziente dell'utente");
        }
        request.setAttribute("paziente", paziente);  
        
        District distretto = null;
        try {
            distretto = (District) session.getAttribute("district");
            if(distretto == null) throw new Exception();
        }
        catch(Exception ex)
        {
            districtDao = daoFactory.getDistrictDAO();
            distretto = districtDao.getById(paziente.getDistrict());
            session.setAttribute("district", distretto);
        }
        request.setAttribute("district", distretto);
        
        Paziente familyDoctor = null;
        try {
            familyDoctor = (Paziente) session.getAttribute("familydoctor");
            if(familyDoctor == null) throw new Exception();
        }
        catch(Exception ex)
        {
            pazienteDao = daoFactory.getPazienteDAO();
            familyDoctor = pazienteDao.getById(paziente.getFamilydoctor());
            session.setAttribute("familydoctor", familyDoctor);
        }
        request.setAttribute("familyDoctor", familyDoctor);
        
        long dopo = System.currentTimeMillis();
        long diff = dopo-sec;
        sec=dopo;
        System.err.println("    fine caricamento paziente: " + diff);
        
        // TROVARE ULTIMA VISITA
        visitDao = daoFactory.getVisitDAO();
        Visit visita = null;
        try {
            visita = visitDao.getLastByPatient(id);
        }
        catch (NotFoundDAOException ex) { }
        request.setAttribute("ultimaVisita", visita);
        
        // TROVARE PROSSIMA VISITA
        visita = null;
        try {
            visita = visitDao.getNextByPatient(id);
        }
        catch (NotFoundDAOException ex) { }
        request.setAttribute("prossimaVisita", visita);
        
        dopo = System.currentTimeMillis();
        diff = dopo-sec;
        sec=dopo;
        System.err.println("    fine caricamento visite: " + diff);
        
        //TROVA ESAMI DA FARE (RICHIAMI COMPRESI) ------------------------------
        
        // prendo gli esami da effettuare
        examDao = daoFactory.getExamDAO();
        List<Exam> exams = examDao.getToDoByPatient(id);
        request.setAttribute("exams", exams);

        dopo = System.currentTimeMillis();
        diff = dopo-sec;
        sec=dopo;
        System.err.println("    fine caricamento esami: " + diff);
        
        //TROVA RICETTE ATTIVE -------------------------------------------------
        prescriptionDao = daoFactory.getPrescriptionDAO();
        List <Prescription> prescriptions = prescriptionDao.getActiveByPatient(id);
        request.setAttribute("prescriptions", prescriptions);
        
        dopo = System.currentTimeMillis();
        diff = dopo-sec;
        sec=dopo;
        System.err.println("    fine caricamento ricette: " + diff);
        
        //TROVA VISITE SPECIALISTICHE DA FARE  ------------------------------
        speckvisitDao = daoFactory.getSpeckvisitDAO();
        List <Speckvisit> speckvisits = speckvisitDao.getToDoByPatient(id);
        request.setAttribute("speckvisits", speckvisits);
        
        dopo = System.currentTimeMillis();
        diff = dopo-sec;
        sec=dopo;
        System.err.println("    fine caricamento speck: " + diff);
        
        photoDao = daoFactory.getPhotoDAO();
        Photo ph = new Photo();
        Timestamp timestamp;
        try {
            ph = photoDao.getByLastPhotoUser(user.getId());
        } catch(NotFoundDAOException ex) {
            ph.setUser(user.getId());
            ph.setPath("/data/utente.png");
            timestamp = new Timestamp(System.currentTimeMillis());
            ph.setTimestamp(timestamp);
        }
        request.setAttribute("element", ph);        
        
        
        System.out.println("-> visualizzazione jsp");
        daoFactory.closeConn();
        request.getRequestDispatcher("/WEB-INF/pagine/paziente/HomePaziente.jsp").forward(request, response);
        
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("GET");
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(HomePaziente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("POST");
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(HomePaziente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
