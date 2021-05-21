/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsCHS;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Psw_temporanee;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.servletsPaziente.NewPassword;
import it.unitn.disi.wp.servizioSanitario.utils.HtmlEmailSender;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author frape
 */
public class SalvaNuovoRichiamo extends HttpServlet {

    DAOFactory daoFactory;
    ExamDAO examDao;
    SpeckvisitDAO speckvisitDao;
    ChsDAO chsDao;
    
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
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation ();
            examDao = daoFactory.getExamDAO();
            speckvisitDao = daoFactory.getSpeckvisitDAO();
            chsDao = daoFactory.getChsDAO();
            
            
            char type = request.getParameter("prescr_type").charAt(0);
            int code = Integer.parseInt(request.getParameter("prescr_id"));
            int maxage = Integer.parseInt(request.getParameter("min_age"));
            int minage = Integer.parseInt(request.getParameter("max_age"));
                        
            User user = (User) session.getAttribute("user");
            Chs chs = chsDao.getById(user.getId());
                        
            char sex = 'N';
            String x = request.getParameter("check_m");
            if(x != null && x.equals("on")) sex = 'M';
            x = request.getParameter("check_f");
            if(x != null && x.equals("on"))
                if(sex == 'M')
                    sex = 'B';
                else
                    sex = 'F';
            
            if (minage>maxage) {
                int c = minage;
                minage=maxage;
                maxage=c;
            }
            List<String> email = null;
            List<String> nomi = null;
            boolean b = false;
            if (type=='E') {
                switch(sex)
                {
                    case 'M': { email = examDao.addRichiamo (chs, code, maxage, minage, "M"); b=true; break; }
                    case 'F': { email = examDao.addRichiamo (chs, code, maxage, minage, "F"); b=true; break; }
                    case 'B': { email = examDao.addRichiamo (chs, code, maxage, minage, "M"); 
                                email.addAll(examDao.addRichiamo (chs, code, maxage, minage, "F")); 
                                b=true; break; }
                    default: { break; }
                }
            }
            else if (type=='V') {
                
                switch(sex)
                {
                    case 'M': { email = speckvisitDao.addRichiamo (chs, code, maxage, minage, "M"); b=true; break; }
                    case 'F': { email = speckvisitDao.addRichiamo (chs, code, maxage, minage, "F"); b=true; break; }
                    case 'B': { email = speckvisitDao.addRichiamo (chs, code, maxage, minage, "M"); 
                                email.addAll(speckvisitDao.addRichiamo (chs, code, maxage, minage, "F"));
                                b=true; break; }
                    default: { break; }
                }
            }           
            
            if (b) {
                if(email == null)
                    throw new Exception();
                for (int i = 0; i < email.size(); i++) {
                    final String host = "smtp.gmail.com";
                    final String port = "465";
                    final String username = "servizio.sanitario12@gmail.com";
                    final String psw = "tfrenisdnepxyjxj";
                    String toAddress = "mickitran@hotmail.it";//user.getEmail();
                    String subject = "Notifica di nuovo richiamo";
                    String message = "<p>Gentile utente,</p><br>";
                    message += "<p>Le notifichiamo che le è stato inserito un nuovo richiamo da parte della sua azienda sanitaria provinciale.</p><br>";
                    message += "<p>Per maggiori informazioni la invitiamo a visualizzare il suo profilo personale: <a href=\"http://localhost:8080/servizioSanitario/ControlloCookie \"> Area personale </a></p><br>"; 
                    message += "<p>Buona giornata!</p><br>";
                    message += "<p><font size=\"2\"><strong>Michele Duc Toan Tran</strong><br><em>Assistenza clienti</em></font></p>";
                    message += "<font color=blue size=\"2\"><p><em><strong>Servizio Sanitario</strong></em><br>Via Sommarive, 14<br>38123 Povo(TN)<br>Italia<br></p></font>";
                    message += "<font color=blue size=\"2\"><p>Tel. 0461 281508<br>servizio.sanitario12@gmail.com<br><a href=\"http://localhost:8080/servizioSanitario/HomePage\">www.serviziosanitario.com</a></p></font>";

                    HtmlEmailSender send = new HtmlEmailSender();
                    try {
                        send.sendHtmlEmail(host, port, username, psw, toAddress, subject, message);
                        request.setAttribute("emailInviata", "Email inviata");
                        daoFactory.closeConn();
                        request.getRequestDispatcher("/WEB-INF/pagine/chs/HomeChs.jsp").forward(request, response);
                    } catch (MessagingException ex) {
                        Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
                        daoFactory.closeConn();
                        request.getRequestDispatcher("/WEB-INF/pagine/error.jsp").forward(request, response);
                    }
                }
            }
            daoFactory.closeConn();
            response.sendRedirect("HomeChs");
            
        }
        catch(Exception e) {
            daoFactory.closeConn();
            Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, e);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SalvaNuovoRichiamo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SalvaNuovoRichiamo.class.getName()).log(Level.SEVERE, null, ex);
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
