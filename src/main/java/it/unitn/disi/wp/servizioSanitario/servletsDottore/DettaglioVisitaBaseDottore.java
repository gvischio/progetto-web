/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDoctor;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
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
public class DettaglioVisitaBaseDottore extends HttpServlet {

    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private VisitDAO visitDao;
    private Paziente paziente;
    private Visit visit;
    private DispositionDAO dispositionDao;
    private SpeckvisitDAO speckvisitDao;
    private ExamDAO examDao;
    private PrescriptionDAO prescriptionDao;
    private DrugDAO drugDao;
    private ChsDAO chsDao;
    private Chs chs;
    private DottoreDAO dottoreDao;
    
    
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
            
            //URL: DettaglioVisitaBaseDottore?visitId=xxx
            
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            visitDao = daoFactory.getVisitDAO();
            pazienteDao = daoFactory.getPazienteDAO();
            dispositionDao = daoFactory.getDispositionDAO();
            speckvisitDao = daoFactory.getSpeckvisitDAO();
            examDao = daoFactory.getExamDAO();
            prescriptionDao = daoFactory.getPrescriptionDAO();
            dottoreDao = daoFactory.getDottoreDAO();
            
            int visitId = Integer.parseInt( request.getParameter("visitId") );
                        
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
                        
            paziente = pazienteDao.getById(visit.getPatient());
            request.setAttribute("paziente", paziente);
            boolean ok = false;
            try {
                User user = (User) session.getAttribute("user");
                Dottore dottore = dottoreDao.getById(user.getId());
                
                if(dottore.getType() == TypeDoctor.P)
                {
                    if(user.getId() != paziente.getFamilydoctor())
                        throw new Exception(paziente.getFamilydoctor() +" != "+ user.getId());
                }
                ok = true;
            }
            catch(Exception e)
            {
                System.out.println(" -> accesso negato");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }
            
            if (ok) { 
                String prescrittore;
                if(visit.getType() == TypeVisit.V)
                {
                    paziente = pazienteDao.getById(visit.getFamilydoctor());
                    prescrittore = "dott. " + paziente.getFirstname() + " " + paziente.getLastname();
                }
                else
                {
                    chs = chsDao.getById(visit.getFamilydoctor());
                    prescrittore = chs.getName();
                }
                request.setAttribute("prescrittore", prescrittore);

                List <Disposition> disposizioni = dispositionDao.getByVisit(visitId);
                List <String> tipo = new ArrayList ();
                List <Date> data = new ArrayList ();
                List <String> nome = new ArrayList ();
                List ids = new ArrayList();
                for (Disposition d : disposizioni) {
                    if (d.getType() == TypeDisposition.P) {
                        tipo.add("Ricetta");
                        data.add(d.getMadedate());
                        nome.add( prescriptionDao.getById(d.getId()).getName() );
                        ids.add(d.getId());
                    }
                    if (d.getType() == TypeDisposition.E) {
                        tipo.add("Esame");
                        data.add(d.getMadedate());
                        nome.add( examDao.getById(d.getId()).getName() );
                        ids.add(d.getId());
                    }
                    if (d.getType() == TypeDisposition.S) {
                        tipo.add("Visita Specialistica");
                        data.add(d.getMadedate());
                        nome.add( speckvisitDao.getById(d.getId()).getName() );
                        ids.add(d.getId());
                    }
                }
                request.setAttribute("tipo", tipo);
                request.setAttribute("nome", nome);
                request.setAttribute("data", data);
                request.setAttribute("ids", ids);

                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/dottore/DettaglioVisitaBaseDottore.jsp").forward(request, response);
            }   
        }catch (NotFoundDAOException ex){
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
            Logger.getLogger(DettaglioVisitaBaseDottore.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioVisitaBaseDottore.class.getName()).log(Level.SEVERE, null, ex);
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
