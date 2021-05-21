/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.CategoryDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author simmf
 */
public class DettaglioEsame extends HttpServlet {

    DAOFactory daoFactory;
    ExamDAO examDao;
    Exam exam;
    PazienteDAO pazienteDao;
    VisitDAO visitDao;
    CategoryDAO categoryDao;
    ListaEsamiDAO listaesamiDao;
    ListaEsami listaesami;
    ChsDAO chsDao;
    Chs chs;
    Paziente paz;
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
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            chsDao = daoFactory.getChsDAO();
            visitDao = daoFactory.getVisitDAO();
            pazienteDao = daoFactory.getPazienteDAO();
            
            System.out.println("DettaglioEsame: caricamento...");

            int examId = 0;
            try {
                examId = Integer.parseInt(request.getParameter("examId"));
                examDao = daoFactory.getExamDAO();
                exam = examDao.getById(examId);
                request.setAttribute("exam", exam);
            } catch (Exception e) {
                System.out.println(" -> esame inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }

            boolean ok = false;
            try {
                paz = (Paziente) session.getAttribute("paziente");
                if (paz.getId() != exam.getPatient()) {
                    throw new Exception(paz.getId() + " != " + exam.getPatient());
                }
                NotificationDAO notificationDao = daoFactory.getNotificationDAO();
                request.setAttribute("notif", notificationDao.getByUser(paz.getId()));
                ok = true;
            } catch (Exception e) {
                System.out.println(" -> accesso negato");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }

            if (ok) {
                
                Visit visit = visitDao.getById(exam.getVisit());
                request.setAttribute("data_emissione_esame", visit.getVisdate());
                
                String prescrittore;
                if(visit.getType() == TypeVisit.V)
                {
                    Paziente dottore = pazienteDao.getById(visit.getFamilydoctor());
                    prescrittore = "dott. " + dottore.getFirstname() + " " + dottore.getLastname();
                }
                else
                {
                    chs = chsDao.getById(visit.getFamilydoctor());
                    prescrittore = chs.getName();
                }
                request.setAttribute("prescrittore", prescrittore);

                

                listaesamiDao = daoFactory.getListaEsamiDAO();
                listaesami = listaesamiDao.getById(exam.getExamcode());
                request.setAttribute("descresame", listaesami.getDescription());

                categoryDao = daoFactory.getCategoryDAO();
                request.setAttribute("categesame", categoryDao.getById(listaesami.getCategory()));

                try {
                    chsDao = daoFactory.getChsDAO();
                    request.setAttribute("chs", chsDao.getById(exam.getProvidedby()).getName());
                    request.setAttribute("completa", 1);
                } catch (Exception ex) {
                    // chs non trovato => esame non fatto
                    request.setAttribute("chs", "");
                    request.setAttribute("completa", 0);
                }
                daoFactory.closeConn();

                System.out.println(" -> DettaglioEsame.jsp");
                request.getRequestDispatcher("/WEB-INF/pagine/paziente/DettaglioEsame.jsp").forward(request, response);
            }
        }catch (NotFoundDAOException ex){
            daoFactory.closeConn();
            response.sendError(404);
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
            Logger.getLogger(DettaglioEsame.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioEsame.class.getName()).log(Level.SEVERE, null, ex);
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
