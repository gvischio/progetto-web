/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.District;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDoctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
public class InfoMedicoPerPaziente extends HttpServlet {
    
    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private DottoreDAO dottoreDao;
    private DistrictDAO districtDao;
    private PhotoDAO photoDao;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws DaoException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DaoException {
        try (PrintWriter out = response.getWriter()) {
            
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();

            User user = (User) session.getAttribute("user");
            int id = user.getId();

            System.out.println("InfoMedicoPerPaziente: caricamento...");
            
            NotificationDAO notificationDao = daoFactory.getNotificationDAO();
            request.setAttribute("notif", notificationDao.getByUser(id));

            // recupero dati paziente
            pazienteDao = daoFactory.getPazienteDAO();
            Paziente paziente = null;
            try {
                paziente = (Paziente) session.getAttribute("paziente");
                if(paziente == null) throw new Exception();
            }
            catch(Exception ex)
            {
                paziente = pazienteDao.getById(id);
                session.setAttribute("paziente", paziente);
            }
            request.setAttribute("paziente", paziente);  

            // recupero dati medico
            Paziente familydoctor = null;
            try {
                familydoctor = (Paziente) session.getAttribute("familydoctor");
                if(familydoctor == null) throw new Exception();
            }
            catch(Exception ex)
            {
                familydoctor = pazienteDao.getById(paziente.getFamilydoctor());
                session.setAttribute("familydoctor", familydoctor);
            }
            request.setAttribute("dottore_paziente", familydoctor);

            // recupero distretto
            District distretto = null;
            try {
                distretto = (District) session.getAttribute("district");
                if(distretto == null) throw new Exception();
            }
            catch(Exception ex)
            {
                districtDao = daoFactory.getDistrictDAO();
                distretto = districtDao.getById(paziente.getDistrict());
                session.setAttribute("district", distretto);
            }
            request.setAttribute("district", distretto);

            // recupero informazioni professionali del medico
            dottoreDao = daoFactory.getDottoreDAO();
            Dottore dottore = dottoreDao.getById(paziente.getFamilydoctor());
            request.setAttribute("dottore", dottore);

            // recupero della lista di tutti i dottori della zona -> per camnio medico
            List <Dottore> dottori = dottoreDao.getByWorkDistrict( paziente.getDistrict() );
            List <Paziente> dottori_pazienti = new ArrayList ();
            for (Dottore d : dottori) {
                if (d.getType() == TypeDoctor.P) {
                    dottori_pazienti.add(pazienteDao.getById(d.getId()));
                }
            }
            request.setAttribute("dottori_pazienti", dottori_pazienti);

            // recupero della prossima visita -> se già prenotata, altrimenti NULL
            VisitDAO visitDao = daoFactory.getVisitDAO();
            Visit visita = null;
            try {
                visita = visitDao.getNextByPatient(id);
                System.out.println(visita.getVisdate().toString() + " " + visita.getVistime());
            }
            catch (NotFoundDAOException ex) { }
            request.setAttribute("prossimaVisita", visita);

            // recupero della prossima data per la visita disponibile
            Timestamp next = visitDao.getNextVisitAvailableForFamilyDoctor(familydoctor.getId());
            request.setAttribute("tsPrenota", next.toString());
            request.setAttribute("dataPrenota", next.toString().substring(0, 10));
            request.setAttribute("oraPrenota", next.toString().substring(11, 16));

            // recupero della foto del dottore
            photoDao = daoFactory.getPhotoDAO();
            Photo ph = new Photo();
            Timestamp timestamp;
            try {
                ph = photoDao.getByLastPhotoUser(paziente.getFamilydoctor());
            } catch(NotFoundDAOException ex) {
                ph.setUser(user.getId());
                ph.setPath("/data/utente.png");
                timestamp = new Timestamp(System.currentTimeMillis());
                ph.setTimestamp(timestamp);
            }
            request.setAttribute("element", ph);            
            
            System.out.println(" -> InfoMedicoPerPaziente.jsp");
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/paziente/InfoMedicoPerPaziente.jsp").forward(request, response);
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
            Logger.getLogger(InfoMedicoPerPaziente.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(InfoMedicoPerPaziente.class.getName()).log(Level.SEVERE, null, ex);
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
