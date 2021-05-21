/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsCHS;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.User;
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
 * @author frape
 */
public class SegnaEsameComeEffettuato extends HttpServlet {

    private DAOFactory daoFactory;
    private ExamDAO examDao;
    private Exam exam;
    
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
                       
            int pazienteId = Integer.parseInt( request.getParameter("pazienteId") );
            int examId = Integer.parseInt( request.getParameter("examId") );
            System.out.println("entra nella servlet segna esame come effettuato");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            if(user == null)
                response.sendRedirect("ChiamaLogin");
            
            daoFactory = new DAOFactoryImplementation();            
            
            examDao = daoFactory.getExamDAO();
            try{
                exam = examDao.getById(examId);
            }catch(NotFoundDAOException ex){
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }            
           
            boolean ok = false;
            try {
                //System.out.println(paz.getId()+ " != " + exam.getPatient());
                if(pazienteId != exam.getPatient())
                    throw new Exception(pazienteId+ " != " + exam.getPatient());
                ok = true;
            }
            catch(Exception e)
            {
                response.sendRedirect("HomePaziente");
            }

            if(ok)
            {            
                System.out.println(" nell'if(ok) in dao in segna esame come effettuatp");
                exam.setProvidedby (user.getId());
                examDao.setEsameEffettuato(exam);
                System.out.println("prima di chiudere i dao in segna esame come effettuatp");
                daoFactory.closeConn();
                response.sendRedirect("DettaglioPazienteChs?pazienteId="+pazienteId);
            }
        }
        catch (Exception e)
        { System.out.println("SECE:" + e.getMessage()); }
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
        System.out.println("GET");
        try {
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(SegnaEsameComeEffettuato.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("POST");
        try {
           
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(SegnaEsameComeEffettuato.class.getName()).log(Level.SEVERE, null, ex);
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
