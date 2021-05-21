/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DistrictDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
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
public class CompilaVisitaBase extends HttpServlet {

    private DAOFactory daoFactory;
    
    private PazienteDAO pazienteDao;
    private VisitDAO visitDao;
    private DistrictDAO districtDao;
    private PrescriptionDAO prescriptionDao; 
    private ExamDAO examDao;
    private SpeckvisitDAO speckvisitDao;
    private DispositionDAO dispositionDao;
    private PhotoDAO photoDao;
    private Visit visit;
    
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
            
            //url richiesto: redirect/CompilaVisitaBase?visitId=xxx
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation ();
            visitDao= daoFactory.getVisitDAO();
            pazienteDao = daoFactory.getPazienteDAO();
            districtDao = daoFactory.getDistrictDAO();
            dispositionDao = daoFactory.getDispositionDAO();
            examDao = daoFactory.getExamDAO();
            prescriptionDao = daoFactory.getPrescriptionDAO();
            speckvisitDao = daoFactory.getSpeckvisitDAO();
            photoDao = daoFactory.getPhotoDAO();
            int visitId = Integer.parseInt(request.getParameter("visitId") ); 
            
            GregorianCalendar gc = new GregorianCalendar (); //data odierna
            int mese = gc.get(Calendar.MONTH)+1;
            Date today = Date.valueOf (gc.get(Calendar.YEAR)+"-"+mese+"-"+gc.get(Calendar.DAY_OF_MONTH));
            request.setAttribute("today", today);            
                                   
            try {                
                visit = visitDao.getById(visitId);
                request.setAttribute("visit", visit);
            }
            catch (Exception e)
            {
                System.out.println(" -> visita inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
                        
            Paziente paziente = pazienteDao.getById(visit.getPatient());
            request.setAttribute("paziente", paziente);
            
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
                request.setAttribute("district", districtDao.getById(paziente.getDistrict()));             

                List <Visit> visiteBase = new ArrayList(); //trovo l'ultima visita per ogni paziente
                    visiteBase = visitDao.getByPatient(visit.getPatient());
                    Collections.sort(visiteBase, new Comparator <Visit> () { //riordino le visite, dalla data più vecchia alla più recente (o eventualmente anche futura se è una visita da fare)
                    @Override
                        public int compare (Visit v1, Visit v2) {
                            return ( v1.getVisdate() ).compareTo( v2.getVisdate() );
                        }
                    });

                //trovo l'ultima visita fatta. Parto dalla fine della lista, salto le visite future e tengo la prima che ha la visdate già passata
                boolean b = true;      
                int i; //contatore che poi punterà all'ultima visita fatta
                Visit ultimaVisita = null;
                for (i=visiteBase.size(); b && i>0; i--) {
                    if ( today.compareTo( (visiteBase.get(i-1)).getVisdate() ) == 1 ) {
                        b=false;
                        ultimaVisita = visiteBase.get(i-1);
                    }
                }
                request.setAttribute("ultimaVisita", ultimaVisita);

                List<Disposition> dispositions = new ArrayList();
                dispositions = dispositionDao.getByVisit(visitId);
                List<Disposition> displayDisposition = new ArrayList();

                for (Disposition d: dispositions)
                {
                    if(null != d.getType())
                        switch (d.getType()) {
                        case E:
                            displayDisposition.add(examDao.getById(d.getId()));
                            break;
                        case P:
                            displayDisposition.add(prescriptionDao.getById(d.getId()));
                            break;
                        case S:
                            displayDisposition.add(speckvisitDao.getById(d.getId()));
                            break;
                        default:
                            break;
                    }
                }

                Photo ph = new Photo();
                Timestamp timestamp;
                try {
                    ph = photoDao.getByLastPhotoUser(paziente.getId());
                } catch(NotFoundDAOException ex) {
                    ph.setUser(paziente.getId());
                    ph.setPath("/data/utente.png");
                    timestamp = new Timestamp(System.currentTimeMillis());
                    ph.setTimestamp(timestamp);
                }
                request.setAttribute("element", ph);
                request.setAttribute("displayDisposition", displayDisposition);
                
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/dottore/CompilaVisitaBase.jsp").forward(request, response);
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
            Logger.getLogger(CompilaVisitaBase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CompilaVisitaBase.class.getName()).log(Level.SEVERE, null, ex);
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
