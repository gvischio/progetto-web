/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsCHS;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.District;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
public class CompilaEsame extends HttpServlet {

    private DAOFactory daoFactory;
    
    private ExamDAO examDao; 
    private PazienteDAO pazienteDao;
    private VisitDAO visitDao;
    private DistrictDAO districtDao;
    private ListaEsamiDAO listaEsamiDao;
    private PhotoDAO photoDao;
    private Exam exam;
    private ListaEsami listaEsami;
    private Paziente paziente;
    private District district;
    private Paziente familyDoctor;
    private Visit ultimaVisita;
    private Visit visit;
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
            
            //url:  CompilaEsame?examId=xxx
            
            int examId = Integer.parseInt( request.getParameter("examId") );
            System.out.println(examId);
            daoFactory = new DAOFactoryImplementation();
            pazienteDao = daoFactory.getPazienteDAO();
            examDao = daoFactory.getExamDAO();
            districtDao = daoFactory.getDistrictDAO();
            listaEsamiDao = daoFactory.getListaEsamiDAO();
            visitDao = daoFactory.getVisitDAO();
            
            try{
                exam = examDao.getById(examId);
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            request.setAttribute("exam", exam);
            request.setAttribute("examId", examId);
//            HttpSession session = request.getSession();
//            session.setAttribute("esame", examId);
                        
            try{
                listaEsami = listaEsamiDao.getById(exam.getExamcode());
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            request.setAttribute("listaEsami", listaEsami);
            
            try{
                paziente = pazienteDao.getById(exam.getPatient());
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            request.setAttribute("paziente", paziente);
            
            try{
                district = districtDao.getById(paziente.getDistrict());
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            request.setAttribute("district", district);
            
            try{
                familyDoctor = pazienteDao.getById(paziente.getFamilydoctor());
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }            
            request.setAttribute("familyDoctor", familyDoctor);
            
            try{
                ultimaVisita = visitDao.getLastByPatient(paziente.getId()); //l'ultima visita fatta dal paziente
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            } 
            request.setAttribute("ultimaVisita", ultimaVisita);
            
            
            try{
                visit = visitDao.getById(exam.getVisit()); //prendo la visita dell'esame
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            request.setAttribute("visit", visit);
            
            photoDao = daoFactory.getPhotoDAO();
            Photo ph = new Photo();
            Timestamp timestamp;
            try {
                ph = photoDao.getByLastPhotoUser(paziente.getId());
            } catch(NotFoundDAOException ex) {
                ph.setUser(paziente.getId());
                ph.setPath("/data/utente.png");
                timestamp = new Timestamp(System.currentTimeMillis());
                ph.setTimestamp(timestamp);
            }
            request.setAttribute("element", ph);
            
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/chs/CompilaEsame.jsp").forward(request, response);
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
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(CompilaEsame.class.getName()).log(Level.SEVERE, null, ex);
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
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(CompilaEsame.class.getName()).log(Level.SEVERE, null, ex);
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
