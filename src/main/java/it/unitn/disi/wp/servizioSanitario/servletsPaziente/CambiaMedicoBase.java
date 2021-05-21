/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
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
 * @author Mike_TdT
 */
public class CambiaMedicoBase extends HttpServlet {
    
    private int idDottoreNuovo;
    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        daoFactory = new DAOFactoryImplementation();
        try {
            idDottoreNuovo = Integer.parseInt(request.getParameter("idnewdoctor"));
            HttpSession session = request.getSession();
            
            Paziente paziente = (Paziente) session.getAttribute("paziente");
            int idPaziente = paziente.getId();
            
            pazienteDao = daoFactory.getPazienteDAO();
            boolean buonFine = pazienteDao.updateDoc(idPaziente, idDottoreNuovo);
            if(buonFine) {
                request.setAttribute("medicoOK", "Medico cambiato");
                // aggiorno anche gli oggetti nella sessione
                session.setAttribute("familydoctor", pazienteDao.getById(idDottoreNuovo));
                session.setAttribute("paziente", pazienteDao.getById(idPaziente));
                daoFactory.closeConn();
                request.getRequestDispatcher("InfoMedicoPerPaziente").forward(request, response);
            } else {
                request.setAttribute("medicoNO", "Medico non cambiato");
                daoFactory.closeConn();
                request.getRequestDispatcher("InfoMedicoPerPaziente").forward(request, response);
            }
        } catch (DaoException ex) {
            Logger.getLogger(CambiaMedicoBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            daoFactory.closeConn();
            response.sendRedirect("/WEB-INF/pagine/error.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
