/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.District;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import it.unitn.disi.wp.servizioSanitario.entities.User;
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
public class ImpostazioniPaziente extends HttpServlet {

    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private DistrictDAO districtDao;
    private PhotoDAO photoDao;
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
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            
            User user = (User) session.getAttribute("user");
            if(user == null)
            {
                daoFactory.closeConn();
                response.sendRedirect("ChiamaLogin");
            }
            int id = user.getId();
            
            System.out.println("ImpostaioniPaziente: caricamento...");
            
            NotificationDAO notificationDao = daoFactory.getNotificationDAO();
            request.setAttribute("notif", notificationDao.getByUser(id));
        
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
            
            List<Photo> photo;
            photoDao = daoFactory.getPhotoDAO();
            photo = photoDao.getByUser(user.getId());
            Photo ph = new Photo();
            if(photo.isEmpty()) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                ph.setTimestamp(timestamp);
                ph.setUser(user.getId());
                ph.setPath("/data/utente.png");
                photo.add(ph);
            }
            request.setAttribute("elements", photo);            
            
            System.out.println(" -> ImpostazioniPaziente.jsp");
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/paziente/ImpostazioniPaziente.jsp").forward(request, response);
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
            Logger.getLogger(ImpostazioniPaziente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ImpostazioniPaziente.class.getName()).log(Level.SEVERE, null, ex);
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
