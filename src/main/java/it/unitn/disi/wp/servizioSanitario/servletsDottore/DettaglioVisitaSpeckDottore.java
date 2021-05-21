/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.CategoryDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDoctor;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
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
public class DettaglioVisitaSpeckDottore extends HttpServlet {
    
    DAOFactory daoFactory;
    
    private ListaVisiteDAO listaVisiteDao;
    private ListaVisite listaVisite;
    private SpeckvisitDAO speckvisitDao;
    private Speckvisit speckvisita;
    private PazienteDAO pazienteDao;
    private Paziente paziente;
    private VisitDAO visitDao;
    private Visit visit;
    private CategoryDAO categoryDao;
    private Chs chs;
    private ChsDAO chsDao;
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
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");            


            //URL: DettaglioVisitaSpeckDottore?speckId=xxx

            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation ();
            pazienteDao = daoFactory.getPazienteDAO();
            dottoreDao = daoFactory.getDottoreDAO();
            chsDao = daoFactory.getChsDAO();
            
            int speckId = Integer.parseInt( request.getParameter("speckId") );

            try {
                speckvisitDao = daoFactory.getSpeckvisitDAO();
                speckvisita = speckvisitDao.getById(speckId);
                request.setAttribute("speckvisita", speckvisita);
            }
            catch (Exception e)
            {
                System.out.println(" -> visita inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }

            paziente = pazienteDao.getById(speckvisita.getPatient());
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
                
                request.setAttribute("paziente", paziente);
                

                listaVisiteDao  = daoFactory.getListaVisiteDAO();
                listaVisite = listaVisiteDao.getById( speckvisita.getVisitcode() );
                request.setAttribute("listaVisite", listaVisite);
                String note = " - ";
                if(listaVisite.getDescription() != null)
                {
                    note = listaVisite.getDescription();
                }
                request.setAttribute("note", note);
                
                
                String dataEsecuzione = "Data non presente";
                if(speckvisita.getMadedate() != null)
                {
                    dataEsecuzione = speckvisita.getMadedate().toString();
                }
                request.setAttribute("dataEsecuzione", dataEsecuzione);
                
                
                Paziente dottore = pazienteDao.getById( speckvisita.getSpecialist() );
                String specialista = "Dottore non presente";
                if(speckvisita.getSpecialist() != 1)
                {
                    specialista = "dott. " + dottore.getFirstname() + " " + dottore.getLastname();
                }
                request.setAttribute("specialista", specialista);
                
                
                categoryDao = daoFactory.getCategoryDAO();
                request.setAttribute("categoria", categoryDao.getById( listaVisite.getCategory() ) );

                
                visitDao = daoFactory.getVisitDAO();
                visit = visitDao.getById(speckvisita.getVisit());
                String prescrittore;
                
                if(visit.getType() == TypeVisit.V)
                {
                    paziente = pazienteDao.getById(visit.getFamilydoctor());
                    prescrittore = "dott. " + paziente.getFirstname() + " " + paziente.getLastname();
                }
                else
                {
                    System.out.println("ID PRESCRITTORE CHS " + visit.getFamilydoctor());
                    chs = chsDao.getById(visit.getFamilydoctor());
                    prescrittore = chs.getName();
                }
                request.setAttribute("prescrittore", prescrittore);
                
                
                request.setAttribute("dataPrescrizione", visit.getVisdate() );

                
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/dottore/DettaglioVisitaSpeckDottore.jsp").forward(request, response);
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
            Logger.getLogger(DettaglioVisitaSpeckDottore.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioVisitaSpeckDottore.class.getName()).log(Level.SEVERE, null, ex);
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
