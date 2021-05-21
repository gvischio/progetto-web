/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ForListDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.utils.ForListElement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author Giacomo
 */
public class Prenotazione extends HttpServlet {

    private PazienteDAO pazienteDao;
    private DAOFactory daoFactory;
    
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
                response.sendRedirect("../../ChiamaLogin");
            int id = user.getId();
            
            System.out.println("Prenotazione: caricamento...");
            
            daoFactory = new DAOFactoryImplementation();
            
            NotificationDAO notificationDao = daoFactory.getNotificationDAO();
            request.setAttribute("notif", notificationDao.getByUser(id));
            
            // dati del paziente -> servono??
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
            }
            request.setAttribute("paziente", paziente);
            
            // ricavo la lista degli esami/visite di cui manca la prenotazione
            ForListDAO forListDao = daoFactory.getForListDAO();
            List<ForListElement> listone = forListDao.getForBookingByPatient(id);
            request.setAttribute("listone", listone);
            
            if(listone.size() > 0)
            {
                request.setAttribute("hasenso", "1"); 
                
                // ricavo la lista di tutti i distretti
                DistrictDAO districtDao = daoFactory.getDistrictDAO();
                request.setAttribute("province", districtDao.getAll());

                // ricavo tutti i chs
                ChsDAO chsDao = daoFactory.getChsDAO();
                request.setAttribute("chs", chsDao.getAll());

                // ricavo tutti i medici specialisti
                DottoreDAO doctorDao = daoFactory.getDottoreDAO();
                request.setAttribute("speck", doctorDao.getByTypeExtended('S'));
            }
            else
                request.setAttribute("hasenso", -1);
            
        
            System.out.println("-> visualizzazione jsp");
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/paziente/Prenotazione.jsp").forward(request, response);
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
            Logger.getLogger(Prenotazione.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Prenotazione.class.getName()).log(Level.SEVERE, null, ex);
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
