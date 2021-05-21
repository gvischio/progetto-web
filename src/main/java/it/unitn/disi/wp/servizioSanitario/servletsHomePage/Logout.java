/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsHomePage;

import java.io.IOException;
import java.io.PrintWriter;
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
public class Logout extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String path = getServletContext().getContextPath();
            if (!path.endsWith("/")) {
                    path += "/HomePage";
            }

            HttpSession session = request.getSession();

            // Delete all cookies
            Cookie cookies[] = request.getCookies();
            for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    cookie.setValue("");
    //			cookie.setPath("/servizioSanitario  ");
                    response.addCookie(cookie);
                    System.out.println("Annullato");
            }
            // Session is removed from sessionHandler (notifications)
            session.invalidate();
            System.out.println("Invalida sessione");
            response.sendRedirect(path);
    }

    @Override
    public String getServletInfo() {
            return "Servlet for logout handling. Only GET requests implemented.";
    }
	
}
