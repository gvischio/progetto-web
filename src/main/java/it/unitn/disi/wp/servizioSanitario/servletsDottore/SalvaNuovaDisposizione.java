/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Notification;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import it.unitn.disi.wp.servizioSanitario.utils.HtmlEmailSender;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author simmf
 */
public class SalvaNuovaDisposizione extends HttpServlet {

    DAOFactory daoFactory;
    VisitDAO visitDao;
    ExamDAO examDao;
    PrescriptionDAO prescriptionDao;
    SpeckvisitDAO speckvisitDao;
    UserDAO userDao;
    PazienteDAO pazienteDao;
    Visit visit;
    ListaVisiteDAO listaVisitaDao;
    ListaEsamiDAO listaEsamiDao;
    DrugDAO drugDao;
    
    
    
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
            throws ServletException, IOException, DaoException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //URL: /restricted/SalvaNuovaDisposizione?visitId=xxx
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            visitDao = daoFactory.getVisitDAO();
            examDao = daoFactory.getExamDAO();
            pazienteDao = daoFactory.getPazienteDAO();
            prescriptionDao = daoFactory.getPrescriptionDAO();
            speckvisitDao = daoFactory.getSpeckvisitDAO();
            userDao = daoFactory.getUserDAO();
            drugDao =daoFactory.getDrugDAO();
            listaEsamiDao = daoFactory.getListaEsamiDAO();
            listaVisitaDao = daoFactory.getListaVisiteDAO();
            
            String type = request.getParameter("prescr_type");
            int prescrId = Integer.parseInt( request.getParameter("prescr_id") );
            int visitId = Integer.parseInt( request.getParameter("visitId") );
            String nomeEX = listaEsamiDao.getById(prescrId).getName();
            System.out.println(nomeEX);
            try {
                visit= visitDao.getById(visitId);
            }
            catch (Exception e)
            {
                System.out.println(" -> visita inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            boolean ok = false;
            try {
                User user = (User) session.getAttribute("user");
                if(user.getId() != visit.getFamilydoctor())
                    throw new Exception(user.getId()+" != "+visit.getFamilydoctor());
                ok = true;
            }
            catch(Exception e)
            {
                System.out.println(" -> accesso negato");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }
            
            if (ok) {
                       
                Paziente paziente = pazienteDao.getById(visit.getPatient());
                Paziente dottore = pazienteDao.getById(visit.getFamilydoctor());

                //inizio del corpo della mail
                final String host = "smtp.gmail.com";
                final String port = "465";
                final String username = "servizio.sanitario12@gmail.com";
                final String psw = "tfrenisdnepxyjxj";
                String toAddress = "mickitran@hotmail.it";
                String subject = "Nuova prescrizione creata";
                String message = "<p>Gentile " + paziente.getFirstname() + ",</p> <br>";
                String notification = "";

                switch (type) {
                    case "E":  //ticket di 11 euro
                               Exam exam = new Exam();

                               exam.setPatient(visit.getPatient());
                               exam.setFamilydoctor(visit.getFamilydoctor());
                               exam.setVisit(visitId);
                               exam.setTicket(exam.TICKET); //meglio mettere nella classse direttamente che setTicket prende il ticket salvato nella classse?
                               exam.setType(TypeDisposition.E);
                               exam.setExamcode(prescrId);
                               examDao.addExam(exam);
//                               String nomeEX = listaEsamiDao.getById(prescrId).getName();
                               
                               message += "<p>E' stata registrata una nuova prescrizione per un  esame a tuo nome, dettagli:</p><br>";
                               message += "<p>Data visita di riferimento: " + visit.getVisdate() +"</p><br><p>Dott. " + dottore.getFirstname() + " " + dottore.getLastname() + "</p><br>";
                               message += "<p>Esame prescritto: " + nomeEX + "</p><br>";
                               
                               notification += "E' stato prescritto un nuovo esame per te: " + nomeEX;
                        break;
                    case "R": //ticket di 3 euro a ricetta.
                               Prescription prescription = new Prescription();
                               int prescr_quantity = Integer.parseInt( request.getParameter("prescr_quantity") );                          

                               prescription.setPatient(visit.getPatient());
                               prescription.setFamilydoctor(visit.getFamilydoctor());
                               prescription.setVisit(visitId);
                               prescription.setTicket(prescription.TICKET);
                               prescription.setType(TypeDisposition.P);
                               prescription.setDrug(prescrId);
                               prescription.setQuantity(prescr_quantity);                           
                               prescriptionDao.addPrescription(prescription);
                               String nomeDR = drugDao.getById(prescrId).getName();
                               
                               message += "<p>E' stata registrata una nuova prescrizione per una ricetta a tuo nome, dettagli:</p><br>";
                               message += "<p>Data visita di riferimento: " + visit.getVisdate() +"</p><br><p>Dott. " + dottore.getFirstname() + " " + dottore.getLastname() + "</p><br>";
                               message += "<p>Medicinale prescritto: " + nomeDR + "</p><br>";
                               
                               notification += "Ti e' stata prescritta una nuova ricetta per il medicinale " + nomeDR;
                        break;
                    case "V": //ricket 50 euro a visita
                                Speckvisit speckvisit = new Speckvisit();

                               speckvisit.setPatient(visit.getPatient());
                               speckvisit.setFamilydoctor(visit.getFamilydoctor());
                               speckvisit.setVisit(visitId);
                               speckvisit.setTicket(speckvisit.TICKET);
                               speckvisit.setType(TypeDisposition.S);
                               speckvisit.setVisitcode(prescrId);
                               speckvisitDao.addSpeckvisit(speckvisit);
                               String nomeVI = listaVisitaDao.getById(prescrId).getName();
                               
                               message += "<p>E' stata registrata una nuova prescrizione per una visita specialistica a tuo nome, dettagli:</p><br>";
                               message += "<p>Data visita di riferimento: " + visit.getVisdate() +"</p><br><p>Dott. " + dottore.getFirstname() + " " + dottore.getLastname() + "</p><br>";
                               message += "<p>Visita prescritta: " + nomeVI + "</p><br>";
                               
                               notification += "E' stata prescritta una nuova visita specialistica per te: " + nomeVI;
                        break;
                    default: System.out.println("passato qualcosa di sbagliato");
                        break;
                }

                message += "<p>Per maggiori informazioni ti invitiamo a visualizzare il tuo profilo personale: <a href=\"http://localhost:8080/servizioSanitario/ControlloCookie \"> Area personale </a></p><br>"; 
                message += "<p>Buona giornata!</p><br>";
                message += "<p><font size=\"2\"><strong>Michele Duc Toan Tran</strong><br><em>Assistenza clienti</em></font></p>";
                message += "<font color=blue size=\"2\"><p><em><strong>Servizio Sanitario</strong></em><br>Via Sommarive, 14<br>38123 Povo(TN)<br>Italia<br></p></font>";
                message += "<font color=blue size=\"2\"><p>Tel. 0461 281508<br>servizio.sanitario12@gmail.com<br><a href=\"http://localhost:8080/servizioSanitario/HomePage\">www.serviziosanitario.com</a></p></font>";

                HtmlEmailSender send = new HtmlEmailSender();
                try {
                    send.sendHtmlEmail(host, port, username, psw, toAddress, subject, message);
                    NotificationDAO notifDao = daoFactory.getNotificationDAO();
                    notifDao.newNotification(visit.getPatient(), subject, notification);
                    daoFactory.closeConn();
                    response.sendRedirect("CompilaVisitaBase?visitId="+visitId);
                } catch (MessagingException ex) {
                    System.out.println("nel catch...mail non inviata");
                    Logger.getLogger(HtmlEmailSender.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendError(400, ex.getMessage());
                }

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
            Logger.getLogger(SalvaNuovaDisposizione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SalvaNuovaDisposizione.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SalvaNuovaDisposizione.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SalvaNuovaDisposizione.class.getName()).log(Level.SEVERE, null, ex);
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
