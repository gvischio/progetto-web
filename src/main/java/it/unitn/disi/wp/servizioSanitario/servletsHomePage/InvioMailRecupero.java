/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsHomePage;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.Psw_temporaneeDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Psw_temporanee;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.servletsPaziente.NewPassword;
import it.unitn.disi.wp.servizioSanitario.utils.GeneratoreStringa;
import it.unitn.disi.wp.servizioSanitario.utils.HtmlEmailSender;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
public class InvioMailRecupero extends HttpServlet {

    private DAOFactory daoFactory;
    private UserDAO u;
    private PazienteDAO pazienteDAO;
    private Psw_temporaneeDAO pswDAO;
    
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

        User user;
        String email = (String) request.getParameter("recuperoEmail");
        daoFactory = new DAOFactoryImplementation();
        u = daoFactory.getUserDAO();
        pazienteDAO = daoFactory.getPazienteDAO();
        pswDAO = daoFactory.getPswTemporaneeDAO();
        
        try {
            GeneratoreStringa codice = new GeneratoreStringa();
            String casuale = codice.GeneraCodice(20);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            boolean trovato = u.checkByEmail(email);
            if(trovato) {
                user = u.getByEmail(email);
                
                
                //FUNZIONANO SOLO SE L'UTENTE NON CHIUDE IL BROWSER - COME PASSARLO NEL LINK?!
//                HttpSession session = request.getSession();
//                session.setAttribute("casuale", casuale);
                //////////////////////////////////////////////////////////////////////////////////////////
                
                Paziente p = pazienteDAO.getById(user.getId());
                //INIZIO INVIO EMAIL
                final String host = "smtp.gmail.com";
                final String port = "465";
                final String username = "servizio.sanitario12@gmail.com";
                final String psw = "tfrenisdnepxyjxj";
                String toAddress = "mickitran@hotmail.it";//user.getEmail();
                String subject = "Recupero password";
                String message = "<p>Gentile " + p.getFirstname() + ",</p><br>";
                message += "<p>Abbiamo ricevuto una richiesta per recuperare la tua password.</p><br>";
                message += "<p>Clicca su questo link: <a href=\"http://localhost:8080/servizioSanitario/ControlloCodiceMail?codice=" + casuale + "\"> Cambio password</a></p><br>"; 
                message += "<p>Se non sei stato tu ad avanzare la richiesta, ignora questa mail.</p><br>";
                message += "<p>Se hai bisogno di aiuto, conttataci.</p><br>";
                message += "<p>Buona giornata!</p><br>";
                message += "<p><font size=\"2\"><strong>Michele Duc Toan Tran</strong><br><em>Assistenza clienti</em></font></p>";
                message += "<font color=blue size=\"2\"><p><em><strong>Servizio Sanitario</strong></em><br>Via Sommarive, 14<br>38123 Povo(TN)<br>Italia<br></p></font>";
                message += "<font color=blue size=\"2\"><p>Tel. 0461 281508<br>servizio.sanitario12@gmail.com<br><a href=\"http://localhost:8080/servizioSanitario/HomePage\">www.serviziosanitario.com</a></p></font>";

                HtmlEmailSender send = new HtmlEmailSender();
                try {
                    send.sendHtmlEmail(host, port, username, psw, toAddress, subject, message);
                    
                    Psw_temporanee password = new Psw_temporanee();
                    password.setId_user(p.getId());
                    password.setTimestamp(timestamp);
                    password.setCasuale(casuale);
                    boolean ok = pswDAO.addPsw(password);
                    if(ok) {
                        request.setAttribute("emailOK", "Email inviata");
                        daoFactory.closeConn();
                        request.getRequestDispatcher("/WEB-INF/pagine/homePage/Login.jsp").forward(request, response);
                    }
                    daoFactory.closeConn();
                } catch (MessagingException ex) {
                    Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendError(400, ex.getMessage());
                }
                //FINE INVIO EMAIL
                
                //SALVATAGGIO IN DB STRINGA CASUALE PER SUCCESSIVO CONTROLLO
                
            } else {
                daoFactory.closeConn();
                request.setAttribute("emailNO", "Email non inviata");
                request.getRequestDispatcher("/WEB-INF/pagine/homePage/RecuperoPassword.jsp").forward(request, response);
            }
        } catch (DaoException ex) {
            request.setAttribute("notFound", "Email non trovata");
            System.out.println("MAIL NOT FOUND");
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
