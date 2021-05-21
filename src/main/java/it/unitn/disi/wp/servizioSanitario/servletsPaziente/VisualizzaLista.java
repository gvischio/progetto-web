/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ForListDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.utils.ForListElement;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class VisualizzaLista extends HttpServlet {

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
            
            DAOFactory daoFactory = new DAOFactoryImplementation();
            HttpSession session = request.getSession();
            
            User user = (User) session.getAttribute("user");
            if(user == null)
            {
                daoFactory.closeConn();
                response.sendRedirect("ChiamaLogin");
            }
            int id = user.getId();
            
            System.out.println("VisualizzaLista: caricamento...");
            
            NotificationDAO notificationDao = daoFactory.getNotificationDAO();
            request.setAttribute("notif", notificationDao.getByUser(id));
            
            Paziente paziente = null;
            try {
                paziente = (Paziente) session.getAttribute("paziente");
                if(paziente == null) throw new Exception();
            }
            catch(Exception ex)
            {
                PazienteDAO pazienteDao = daoFactory.getPazienteDAO();
                paziente = pazienteDao.getById(id);
                session.setAttribute("paziente", paziente);
            }
            request.setAttribute("paziente", paziente);
            
            // ricavo il tipo di lista che devo visualizzare
            String tipoLista = request.getParameter("type");
            TypeDisposition tipo = TypeDisposition.E;
            if(tipoLista == null)
            {
                daoFactory.closeConn();
                response.sendRedirect("HomePaziente");
            }
            else
            {
                switch (tipoLista)
                {
                    case "exam":
                        tipo = TypeDisposition.E;
                        tipoLista = "esami";
                        break;
                    case "visi":
                        tipo = TypeDisposition.S;
                        tipoLista = "visite specialistiche";
                        break;
                    case "pres":
                        tipo = TypeDisposition.P;
                        tipoLista = "ricette";
                        break;
                    default:
                        daoFactory.closeConn();
                        response.sendRedirect("HomePaziente");
                        break;
                }
                request.setAttribute("tipolista", tipoLista);
                
                System.out.println("    visualizzazione " + tipoLista);

                // guardo se devo mostrare tutto o solo quelli/e attivi/e
                String mostra = request.getParameter("show");
                if(mostra == null) mostra = "all";
                request.setAttribute("mostra", mostra);

                // ricavo gli elementi dal relativo DAO
                ForListDAO forListDao = daoFactory.getForListDAO();
                List<ForListElement> elements;
                switch(tipo)
                {
                    case E: {
                        elements = forListDao.getExamsByPatient(id);
                        request.setAttribute("elements", elements);
                        break;
                    }
                    case P: {
                        elements = forListDao.getPrescriptionsByPatient(id);
                        request.setAttribute("elements", elements);
                        break;
                    }
                    case S: {
                        elements = forListDao.getVisitsByPatient(id);
                        request.setAttribute("elements", elements);
                        break;
                    }
                }
                
                System.out.println(" -> VisualizzaLista.jsp");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/paziente/VisualizzaLista.jsp").forward(request, response);
            }
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
            Logger.getLogger(VisualizzaLista.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(VisualizzaLista.class.getName()).log(Level.SEVERE, null, ex);
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
