/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsHomePage;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.utils.CookieCipher;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mike_TdT
 */
public class ControlloCookie extends HttpServlet {

    private DAOFactory daoFactory;
    private UserDAO u;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DaoException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        daoFactory = new DAOFactoryImplementation();
        u = daoFactory.getUserDAO();
        Cookie[] cookies = request.getCookies();
        System.out.println("CONTROLLO");
        boolean trovato = false;
        if (cookies != null) {
                for (Cookie cookie : request.getCookies()) {
                        if (cookie.getName().equals("ricordami")) {
                                System.out.println("TROVATO IL COOKIE");
                                User user = u.getByEmail(CookieCipher.decrypt(cookie.getValue()));
                                HttpSession session = request.getSession();
                                session.setAttribute("user", user);
                                System.out.println("CI SEI GIA!");
                                trovato = true;
                                switch (user.getRuolo()) {
                                case P:
                                    System.out.println("OK");
                                    response.sendRedirect("restricted/paziente/HomePaziente");
                                    break; //contesto e path e concatenarlo
                                case D:
                                    response.sendRedirect("restricted/dottore/HomeDottore");
                                    break;
                                case S:
                                    response.sendRedirect("restricted/farmacia/HomeFarmacia"); 
                                    break;
                                case C:
                                    response.sendRedirect("restricted/chs/HomeChs");  
                                    break;
                                default:
                                    break;  
                            }
                        } else {
                            System.out.println("NON LO TROVO");
                        }
                }
                if(!trovato)
                {
                    daoFactory.closeConn();
                    response.sendRedirect("Login");
                }
        } else {
            daoFactory.closeConn();
            request.setAttribute("erroreGenerale", "Something went wrong");
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
            processRequest(request, response);
        } catch (DaoException ex) {
            Logger.getLogger(ControlloCookie.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControlloCookie.class.getName()).log(Level.SEVERE, null, ex);
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
