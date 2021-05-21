/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.FarmaciaDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DettaglioRicetta extends HttpServlet {

    DAOFactory daoFactory;
    PrescriptionDAO prescriptionDao;
    Prescription prescription;
    PazienteDAO pazienteDao;
    Paziente paziente;
    VisitDAO visitDao;
    DrugDAO drugDao;
    FarmaciaDAO farmaciaDao;
        
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
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            System.out.println("entro nella servlet di DettaglioRicetta");
        
            //url: DettaglioRicetta?prescriptionId=xxx
            int prescriptionId = Integer.parseInt( request.getParameter("prescriptionId") );

            HttpSession session = request.getSession();
            
            System.out.println("DettaglioRicetta: caricamento...");
            
            daoFactory = new DAOFactoryImplementation();
            try {
                prescriptionDao = daoFactory.getPrescriptionDAO();
                prescription = prescriptionDao.getById(prescriptionId);
                request.setAttribute("prescription", prescription);
            }
            catch(Exception e)
            {
                System.out.println(" -> prescrizione inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }

            boolean ok = false;
            try {
                Paziente paz = (Paziente) session.getAttribute("paziente");
                if(paz.getId() != prescription.getPatient())
                    throw new Exception(paz.getId()+ " != " + prescription.getPatient());
                ok = true;
                request.setAttribute("codicefiscale", paz.getSsn());
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

                pazienteDao = daoFactory.getPazienteDAO();
                request.setAttribute("dottore", pazienteDao.getById(prescription.getFamilydoctor()));

                visitDao = daoFactory.getVisitDAO();
                request.setAttribute("data_emissione_prescrizione", (visitDao.getById(prescription.getVisit())). getVisdate() );

                drugDao = daoFactory.getDrugDAO();
                request.setAttribute("nomefarmaco", (drugDao.getById(prescription.getDrug())).getName() );

                farmaciaDao = daoFactory.getFarmaciaDAO();
                request.setAttribute("farmacia", farmaciaDao.getById( prescription.getDrugstore() ) );

                boolean boolActive=false;
                String attiva = "No";
                if(prescription.getActive() == new Byte("1"))
                {
                    attiva = "Sì";
                    boolActive=true;
                }
                request.setAttribute("attiva", attiva);
                request.setAttribute("boolActive", boolActive);
                daoFactory.closeConn();
                
                System.out.println(" -> DettaglioRicetta.jsp");
                request.getRequestDispatcher("/WEB-INF/pagine/paziente/DettaglioRicetta.jsp").forward(request, response);
            }
        }catch (NotFoundDAOException ex){
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
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
            Logger.getLogger(DettaglioRicetta.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioRicetta.class.getName()).log(Level.SEVERE, null, ex);
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
