/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.utils.HtmlEmailSender;
import it.unitn.disi.wp.servizioSanitario.utils.Sha256;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.mail.*;
import javax.servlet.annotation.WebServlet;


/**
 *
 * @author Mike_TdT
 */
public class NewPassword extends HttpServlet {

    private DAOFactory daoFactory;
    private UserDAO userDAO;
    private PazienteDAO pazienteDAO;
    
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String context = getServletContext().getContextPath();
        if (!context.endsWith("/")) {
                context += "/";
        }

        HttpSession session = request.getSession();
        daoFactory = new DAOFactoryImplementation();
        userDAO = daoFactory.getUserDAO();
        pazienteDAO = daoFactory.getPazienteDAO();
        
        User user = (User) session.getAttribute("user");
        String password = Sha256.doHash(request.getParameter("NewPassword"));
        //System.out.println(password);
        String checkPassword = Sha256.doHash(request.getParameter("CheckPassword"));
        //System.out.println(checkPassword);
        //System.out.println(user.getPassword());
        try {
            if(password.equals(checkPassword)) {
                user.setPassword(password);
                System.out.println(user.getPassword());
                userDAO.updateUser(user.getId(), user);
                Paziente p = pazienteDAO.getById(user.getId());
                p.setEmail(user.getEmail());
                //INVIO EMAIL DI AVVENUTA MODIFICA DI PASSWORD
                final String host = "smtp.gmail.com";
                final String port = "465";
                final String username = "servizio.sanitario12@gmail.com";
                final String psw = "tfrenisdnepxyjxj";
                String toAddress = "mickitran@hotmail.it";//user.getEmail();
                String subject = "Conferma avvenuto cambio password";
                String message = "<p>Gentile " + p.getFirstname() + ",</p><br>";
                message += "<p>Ti confermiamo che abbiamo ricevuto ed eseguito la tua richiesta di cambio password per l'account " +  p.getEmail() + ".</p><br>";
                message += "<p>Se hai bisogno di aiuto, conttataci.</p><br>";
                message += "<p>Buona giornata!</p><br>";
                message += "<p><font size=\"2\"><strong>Michele Duc Toan Tran</strong><br><em>Assistenza clienti</em></font></p>";
                message += "<font color=blue size=\"2\"><p><em><strong>Servizio Sanitario</strong></em><br>Via Sommarive, 14<br>38123 Povo(TN)<br>Italia<br></p></font>";
                message += "<font color=blue size=\"2\"><p>Tel. 0461 281508<br>servizio.sanitario12@gmail.com<br><a href=\"http://localhost:8080/servizioSanitario/HomePage\">www.serviziosanitario.com</a></p></font>";
                
                HtmlEmailSender email = new HtmlEmailSender();
                try {
                    email.sendHtmlEmail(host, port, username, psw, toAddress, subject, message);
                } catch (MessagingException ex) {
                    Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
                    request.getRequestDispatcher("/WEB-INF/pagine/error.jsp").forward(request, response);
                }
                request.setAttribute("PasswordOK", "Password cambiata");
                daoFactory.closeConn();
                request.getRequestDispatcher("ImpostazioniPaziente").forward(request, response);
                System.out.println("PASSWORD IMPOSTATA");
            } else {
                request.setAttribute("errorPassword", "Password non uguali");
                System.out.println("NO SAI PASSWORD");
                daoFactory.closeConn();
                request.getRequestDispatcher("ImpostazioniPaziente").forward(request, response);
            }
        } catch (DaoException ex) {
            Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/error.jsp").forward(request, response);
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
