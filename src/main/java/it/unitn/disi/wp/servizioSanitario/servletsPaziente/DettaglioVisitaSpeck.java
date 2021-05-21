/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.CategoryDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class DettaglioVisitaSpeck extends HttpServlet {

    DAOFactory daoFactory;
    
    ListaVisiteDAO lista_speckvisite_Dao;
    ListaVisite lista_speckvisite;
    SpeckvisitDAO speckvisitDao;
    Speckvisit speckvisita;
    PazienteDAO pazienteDao;
    Paziente paziente;
    VisitDAO visitDao;
    Visit visit;
    CategoryDAO categoryDao;
    Category categoria;
    Chs chs;
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
        
            //URL: DettaglioVisitaSpeck?speckId=xxx            
            
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            int id = Integer.parseInt( request.getParameter("speckId") );

            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();

            System.out.println("DettaglioVisitaSpeck: caricamento...");

            try {
                speckvisitDao = daoFactory.getSpeckvisitDAO();
                speckvisita = speckvisitDao.getById(id);
                request.setAttribute("speckvisita", speckvisita);
            }
            catch(Exception e)
            {
                System.out.println(" -> visita inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            boolean ok = false;
            try {
                Paziente paz = (Paziente) session.getAttribute("paziente");
                if(paz.getId() != speckvisita.getPatient())
                    throw new Exception(paz.getId()+ " != " + speckvisita.getPatient());
                ok = true;
                NotificationDAO notificationDao = daoFactory.getNotificationDAO();
                request.setAttribute("notif", notificationDao.getByUser(paz.getId()));
            }
            catch(Exception e)
            {
                System.out.println(" -> accesso negato");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }

            if(ok)
            {
                visitDao = daoFactory.getVisitDAO();
                Visit visit = visitDao.getById(speckvisita.getVisit());
                request.setAttribute("data_prescrizione", visit.getVisdate() );
                
                lista_speckvisite_Dao  = daoFactory.getListaVisiteDAO();
                lista_speckvisite = lista_speckvisite_Dao.getById( speckvisita.getVisitcode() );
                request.setAttribute("lista_speckvisite", lista_speckvisite);

                categoryDao = daoFactory.getCategoryDAO();
                request.setAttribute("categoria", categoryDao.getById(lista_speckvisite.getCategory()).getName() );

                pazienteDao = daoFactory.getPazienteDAO();
                chsDao = daoFactory.getChsDAO();
                String prescrittore;
                if(visit.getType() == TypeVisit.V)
                {
                    Paziente dottore = pazienteDao.getById(visit.getFamilydoctor());
                    prescrittore = "dott. " + dottore.getFirstname() + " " + dottore.getLastname();
                }
                else
                {
                    chs = chsDao.getById(visit.getFamilydoctor());
                    prescrittore = chs.getName();
                }
                request.setAttribute("prescrittore", prescrittore);
                
                request.setAttribute("specialista", pazienteDao.getById(speckvisita.getSpecialist()));

                
                
                daoFactory.closeConn();
                System.out.println(" -> DettaglioVisitaSpeck.jsp");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/paziente/DettaglioVisitaSpeck.jsp").forward(request, response);
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
            Logger.getLogger(DettaglioVisitaSpeck.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioVisitaSpeck.class.getName()).log(Level.SEVERE, null, ex);
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
