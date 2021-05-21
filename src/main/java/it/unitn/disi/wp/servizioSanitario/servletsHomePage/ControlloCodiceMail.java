/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsHomePage;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.Psw_temporaneeDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Psw_temporanee;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
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
 * @author Mike_TdT
 */
public class ControlloCodiceMail extends HttpServlet {

    private DAOFactory daoFactory;
    private Psw_temporaneeDAO pswDAO;
    private UserDAO userDAO;
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        //Controllo la string con quella in DB FUNZIONA SOLO SE L'UTENTE RIMANE NELLA STESSA SESSIONE
//        HttpSession session = request.getSession();
//        System.out.println("Sessione");
//        String casuale = (String) session.getAttribute("casuale");
//        System.out.println(casuale);
        //VEDI INVIOMAILRECUPERO riga 68
        HttpSession session = request.getSession();
        String casuale = request.getParameter("codice");
        daoFactory = new DAOFactoryImplementation();
        pswDAO = daoFactory.getPswTemporaneeDAO();
        userDAO = daoFactory.getUserDAO();
        try {
            Psw_temporanee psw = pswDAO.getByCode(casuale);
            System.out.println(psw.toString());
            User user = userDAO.getById(psw.getId());
            session.setAttribute("userRecupero", user);
            long ora = System.currentTimeMillis();
            long link = psw.getTimestamp().getTime();
            if(ora > link +(30*60*1000)) {
                //SCADUTO
                System.out.println("SCADUTO");
                request.setAttribute("expired", "Link scaduto");
                boolean del = pswDAO.deletePsw(psw);
                System.out.println(del);
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/homePage/Login.jsp").forward(request, response);
            } else {
                //OK, GO ON
                System.out.println("AVANTI TUTTA!");
                boolean del = pswDAO.deletePsw(psw);
                System.out.println(del);
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/homePage/CambioPassword.jsp").forward(request, response);
            }
        } catch (NullPointerException ex) {
            request.setAttribute("codiceNO", "link non trovato");
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/homePage/Login.jsp").forward(request, response);
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
            System.out.println("GET");
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(ControlloCodiceMail.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("POST");
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(ControlloCodiceMail.class.getName()).log(Level.SEVERE, null, ex);
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
