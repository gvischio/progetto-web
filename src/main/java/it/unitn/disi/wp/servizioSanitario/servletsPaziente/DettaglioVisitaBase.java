/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.NotificationDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.io.IOException;
import java.io.PrintWriter;
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
public class DettaglioVisitaBase extends HttpServlet {

    private DAOFactory daoFactory;
    private VisitDAO visitDao;
    private PazienteDAO pazienteDao;
    private DispositionDAO dispoDao;
    private PrescriptionDAO prescriptionDao;
    private ExamDAO examDao;
    private SpeckvisitDAO speckvisitDao;
    private Paziente paziente;
    private Visit visita;
    private ChsDAO chsDao;
    private Chs chs;
    
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
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
        
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            pazienteDao = daoFactory.getPazienteDAO();
            visitDao = daoFactory.getVisitDAO();
            chsDao = daoFactory.getChsDAO();
            
            
            System.out.println("DettaglioVisitaBase: caricamento...");           
            
            
            int ID_visita = Integer.parseInt(request.getParameter("visitaId"));
            try {
                visita = visitDao.getById(ID_visita);            
                request.setAttribute("visita", visita);
            } catch(Exception e)
            {
                System.out.println(" -> visita inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            boolean ok = false;
            try {
                paziente = (Paziente) session.getAttribute("paziente");
                if(paziente.getId() != visita.getPatient())
                    throw new Exception(paziente.getId()+ " != " + visita.getPatient());
                ok = true;
                NotificationDAO notificationDao = daoFactory.getNotificationDAO();
                request.setAttribute("notif", notificationDao.getByUser(paziente.getId()));
            }
            catch(Exception e)
            {
                System.out.println(" -> accesso negato");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }

            if(ok)
            {
                Paziente familyDoctor = null;
                try {
                    familyDoctor = (Paziente) session.getAttribute("familydoctor");
                    if(familyDoctor == null) throw new Exception();
                }
                catch(Exception ex)
                {
                    familyDoctor = pazienteDao.getById(paziente.getFamilydoctor());
                    session.setAttribute("familydoctor", familyDoctor);
                }
                request.setAttribute("familyDoctor", familyDoctor);

                
                String prescrittore;
                if(visita.getType() == TypeVisit.V)
                {
                    paziente = pazienteDao.getById(visita.getFamilydoctor());
                    prescrittore = "dott. " + paziente.getFirstname() + " " + paziente.getLastname();
                }
                else
                {
                    chs = chsDao.getById(visita.getFamilydoctor());
                    prescrittore = chs.getName();
                }
                request.setAttribute("prescrittore", prescrittore);
                
                
                // recupero delle disposizioni associate a questa visita

                //inizializzazione dao
                dispoDao = daoFactory.getDispositionDAO();
                prescriptionDao = daoFactory.getPrescriptionDAO();
                examDao = daoFactory.getExamDAO();
                speckvisitDao = daoFactory.getSpeckvisitDAO();

                List <Disposition> dispositions = new ArrayList (dispoDao.getByVisit(ID_visita));
                List <Exam> exams = new ArrayList();
                List <Prescription> prescriptions = new ArrayList();
                List <Speckvisit> speckvisits = new ArrayList();
                for(int i=0; i<dispositions.size(); i++)
                {
                    Disposition k = dispositions.get(i);
                    switch(k.getType())
                    {
                        case P: {
                            prescriptions.add(prescriptionDao.getById(k.getId()));
                            break;
                        }
                        case E: {
                            exams.add(examDao.getById(k.getId()));
                            break;
                        }
                        case S: {
                            speckvisits.add(speckvisitDao.getById(k.getId()));
                            break;
                        }
                        default: { break; }
                    }
                }

                request.setAttribute("prescriptions", prescriptions);
                request.setAttribute("exams", exams); 
                request.setAttribute("speckvisits", speckvisits);          

                System.out.println(" -> DettaglioVisitaBase.jsp");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/paziente/DettaglioVisitaBase.jsp").forward(request, response);
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
            Logger.getLogger(DettaglioVisitaBase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioVisitaBase.class.getName()).log(Level.SEVERE, null, ex);
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
