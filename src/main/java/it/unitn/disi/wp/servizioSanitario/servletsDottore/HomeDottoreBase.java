/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Appuntamento;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PazienteVista;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author simmf
 */
public class HomeDottoreBase extends HttpServlet {

    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private VisitDAO visitDao;
    private PhotoDAO photoDao;
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
            
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null)
                response.sendRedirect("ChiamaLogin");

            daoFactory = new DAOFactoryImplementation();
            dottoreDao = daoFactory.getDottoreDAO();
            pazienteDao = daoFactory.getPazienteDAO(); 
            visitDao = daoFactory.getVisitDAO();

            int dottoreId = user.getId();
            
            Dottore dottore = null;
            try {
                dottore = (Dottore) session.getAttribute("doctor");
                if(dottore == null) throw new Exception();
            }
            catch(Exception ex)
            {
                dottore = dottoreDao.getById(dottoreId);
                session.setAttribute("doctor", dottore);
            }
            request.setAttribute("doctor", dottore); 
            
            Paziente paziente = null;
            try {
                paziente = (Paziente) session.getAttribute("dottore");
                if(paziente == null) throw new Exception();
            }
            catch(Exception ex)
            {
                paziente = pazienteDao.getById(dottoreId);
                session.setAttribute("dottore", paziente);
            }
            request.setAttribute("dottore", paziente);

            List <PazienteVista> pazientevista = pazienteDao.getPazienteVistaPerDottoreBaseByDottoreId(dottoreId);
            request.setAttribute("pazientevista", pazientevista);
            
            List<Appuntamento> calendario = visitDao.getTodayVisitsByFamilyDoctor(dottoreId);
            request.setAttribute("calendario", calendario);

    //------------------------------------------------------------------------------
            
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
        
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/dottore/HomeDottoreBase.jsp").forward(request, response);
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
            Logger.getLogger(HomeDottoreBase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(HomeDottoreBase.class.getName()).log(Level.SEVERE, null, ex);
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
