/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Drug;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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
public class NuovaDisposizione extends HttpServlet {

    DAOFactory daoFactory;
    VisitDAO visitDao;
    Visit visit;
    ListaVisiteDAO listavisiteDao;
    List <ListaVisite> listavisite;
    ListaEsamiDAO listaesamiDao;
    List <ListaEsami> listaesami;
    DrugDAO drugDao;
    List <Drug> listafarmaci;
    PazienteDAO pazienteDao;
    Paziente paziente;
    
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
            
            //url: NuovaDisposizione?visitId=xxx
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            visitDao = daoFactory.getVisitDAO();
            listavisiteDao = daoFactory.getListaVisiteDAO();
            listaesamiDao = daoFactory.getListaEsamiDAO();
            drugDao = daoFactory.getDrugDAO();
            pazienteDao = daoFactory.getPazienteDAO();
            
            int visitId = Integer.parseInt( request.getParameter("visitId") );
            request.setAttribute("visitid", visitId);
            
            try {
                visit = visitDao.getById(visitId);
            }
            catch (Exception e)
            {
                System.out.println(" -> visita (a cui si vuole aggiungere una nuova disposizione) inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
                        
            paziente = pazienteDao.getById(visit.getPatient());
            boolean ok = false;
            try {
                User user = (User) session.getAttribute("user");
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
            
                request.setAttribute("visit", visit);

                listaesami = listaesamiDao.getAll();
                request.setAttribute("listaesami", listaesami);

                listavisite = listavisiteDao.getAll();
                request.setAttribute("listavisite", listavisite);

                listafarmaci = drugDao.getAll();
                request.setAttribute("listafarmaci", listafarmaci);

                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/dottore/NuovaDisposizione.jsp").forward(request, response);
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
            Logger.getLogger(NuovaDisposizione.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(NuovaDisposizione.class.getName()).log(Level.SEVERE, null, ex);
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
