/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class NuovaVisitaDottore extends HttpServlet {

    private DAOFactory daoFactory;
    private VisitDAO visitDao;
    private PazienteDAO pazienteDao;
    private Paziente paziente;
    private User user;
    
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
            
            //url: /NuovaVisitaDottore?pazienteId=xxx
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            pazienteDao = daoFactory.getPazienteDAO();
            int pazienteId = Integer.parseInt( request.getParameter("pazienteId") );
            
            try {
                paziente = pazienteDao.getById(pazienteId);
            }
            catch (Exception e)
            {
                System.out.println(" -> paziente inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            boolean ok = false;
            try {
                user = (User) session.getAttribute("user");
                if(user.getId() != paziente.getFamilydoctor())
                    throw new Exception(paziente.getFamilydoctor() +" != "+ user.getId());
                ok = true;
            }
            catch(Exception e)
            {
                System.out.println(" -> accesso negato");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }
            
            if (ok) {
                int dottoreId = user.getId();            
                visitDao = daoFactory.getVisitDAO();

                GregorianCalendar gc = new GregorianCalendar (); //data odierna
                int mese = gc.get(Calendar.MONTH)+1;
                Date today = Date.valueOf (gc.get(Calendar.YEAR)+"-"+mese+"-"+gc.get(Calendar.DAY_OF_MONTH));
                System.out.println("creata data");

                Visit prossimaVisita = null;
                try{
                    prossimaVisita = visitDao.getNextByPatient(pazienteId);
                }catch (NotFoundDAOException ex) {
                    Logger.getLogger(NuovaVisitaDottore.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("nessuna visita trovata");
                }catch (Exception ex) {
                    Logger.getLogger(NuovaVisitaDottore.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("nessuna visita trovata");
                }

                System.out.println("presa prossima visita");
                if( (prossimaVisita == null) || prossimaVisita.getVisdate().toString().equalsIgnoreCase(today.toString())==false )
                {
                    System.out.println("nell'if");
                    prossimaVisita = visitDao.insertVisitDottore(pazienteId, dottoreId);
                    System.out.println("chiamato dao");
                }

                System.out.println("prima di andare alla servlet compila visita base");
                daoFactory.closeConn();
                response.sendRedirect("CompilaVisitaBase?visitId=" + prossimaVisita.getId());  
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
            Logger.getLogger(NuovaVisitaDottore.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NuovaVisitaDottore.class.getName()).log(Level.SEVERE, null, ex);
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
