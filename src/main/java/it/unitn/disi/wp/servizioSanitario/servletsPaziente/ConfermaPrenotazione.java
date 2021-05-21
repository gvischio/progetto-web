/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Notification;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Giacomo
 */
public class ConfermaPrenotazione extends HttpServlet {

    int id_disp, pren_user;
    char type;
    Timestamp date;
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null)
                response.sendRedirect("../../ChiamaLogin");
            int id = user.getId();
            
            System.out.println("ConfermaPrenotazione: caricamento...");
            
            DAOFactory daoFactory = new DAOFactoryImplementation();
        
            NotificationDAO notificationDao = daoFactory.getNotificationDAO();
            request.setAttribute("notif", notificationDao.getByUser(id));
            
            // ricavo i dati dalla form di prenotazione
            try {
                id_disp = Integer.parseInt(request.getParameter("pren_disp_id"));
                type = request.getParameter("prescr_type").charAt(0);
                request.setAttribute("type", ""+type);
                pren_user = Integer.parseInt(request.getParameter("pren_user"));
                String dates = request.getParameter("pren_date") + " " + request.getParameter("pren_hour");
                date = Timestamp.valueOf(dates);
            }
            catch (Exception e)
            {
                // uno dei parametri non è stato inserito o non è corretto
                request.setAttribute("esito", "err");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/paziente/ConfermaPrenotazione.jsp").forward(request, response);
            }
            
            boolean ok = true;
            if(type == 'E')
            {
                ExamDAO examDao = daoFactory.getExamDAO();
                ok = examDao.BookExam(id_disp, date, pren_user);
            }
            else
            {
                SpeckvisitDAO speckDao = daoFactory.getSpeckvisitDAO();
                ok = speckDao.BookSpeckVisit(id_disp, date, pren_user);
            }
            
            if(ok)
            {
                request.setAttribute("esito", "ok");
                daoFactory.getNotificationDAO().newNotification(id, "Prenotazione confermata", "La tua prenotazione è stata confermata");
            }
            else
                request.setAttribute("esito", "err");
            
            System.out.println(" -> confermaPrenotazione.jsp : booked=" + ok);
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/paziente/ConfermaPrenotazione.jsp").forward(request, response);

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
