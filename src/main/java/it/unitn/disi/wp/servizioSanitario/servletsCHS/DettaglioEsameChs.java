/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsCHS;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.CategoryDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDoctor;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DettaglioEsameChs extends HttpServlet {

    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private Paziente paziente;
    private DispositionDAO dispositionDao;
    private ExamDAO examDao;
    private Exam exam;
    private VisitDAO visitDao;
    private Visit visit;
    private CategoryDAO categoryDao;
    private Category category;
    private ListaEsamiDAO listaesamiDao;
    private ListaEsami listaesami;
    private ChsDAO chsDao;
    private Chs chs;
    private DottoreDAO dottoreDao;
    
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
            
            //URL: DettaglioEsameChs?examId=xxx
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            pazienteDao = daoFactory.getPazienteDAO();
            examDao = daoFactory.getExamDAO();
            visitDao = daoFactory.getVisitDAO();
            listaesamiDao = daoFactory.getListaEsamiDAO();
            categoryDao = daoFactory.getCategoryDAO();
            chsDao = daoFactory.getChsDAO();
            dottoreDao = daoFactory.getDottoreDAO();
            
            
            int examId = Integer.parseInt( request.getParameter("examId") );
            
            try {
                exam = examDao.getById(examId);
                request.setAttribute("exam", exam);
            }
            catch (Exception e)
            {
                System.out.println(" -> esame inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
                   
            paziente = pazienteDao.getById(exam.getPatient());
            
                
            request.setAttribute("paziente", paziente);


            visit = visitDao.getById(exam.getVisit());
            request.setAttribute("visit", visit);

            String prescrittore;
            if(visit.getType() == TypeVisit.V)
            {
                paziente = pazienteDao.getById(visit.getFamilydoctor());
                prescrittore = "dott. " + paziente.getFirstname() + " " + paziente.getLastname();
            }
            else
            {
                chs = chsDao.getById(visit.getFamilydoctor());
                prescrittore = chs.getName();
            }
            request.setAttribute("prescrittore", prescrittore);

            listaesami = listaesamiDao.getById(exam.getExamcode());
            request.setAttribute("listaesami", listaesami);

            category = categoryDao.getById(listaesami.getCategory());
            request.setAttribute("category", category);

            chs = chsDao.getById(exam.getProvidedby());
            request.setAttribute("chs", chs);

            daoFactory.closeConn();            
            request.getRequestDispatcher("/WEB-INF/pagine/chs/DettaglioEsameChs.jsp").forward(request, response);
            
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
            Logger.getLogger(DettaglioEsameChs.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioEsameChs.class.getName()).log(Level.SEVERE, null, ex);
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
