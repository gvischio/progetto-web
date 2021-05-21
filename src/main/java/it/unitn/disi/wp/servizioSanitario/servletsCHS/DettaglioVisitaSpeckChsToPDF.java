/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsCHS;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.CategoryDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.io.FileOutputStream;
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
public class DettaglioVisitaSpeckChsToPDF extends HttpServlet {

    private DAOFactory daoFactory;
    
    private ListaVisiteDAO lista_speckvisite_Dao;
    
    private PazienteDAO pazienteDao;
    private Paziente paziente;
    private VisitDAO visitDao;
    private CategoryDAO categoryDao;
    private Speckvisit speckvisit;
    private SpeckvisitDAO speckvisitDao;
    private ChsDAO chsDao;
    
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
        
            
            //URL: DettaglioVisitaSpeckDottoreToPDF?speckId=xxx   
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            int speckId = Integer.parseInt( request.getParameter("speckId") );
            
            pazienteDao = daoFactory.getPazienteDAO();
            speckvisitDao = daoFactory.getSpeckvisitDAO();
            chsDao = daoFactory.getChsDAO();
            
            try {                
                speckvisit = speckvisitDao.getById(speckId);
            }
            catch (Exception e)
            {
                System.out.println(" -> visita inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            paziente = pazienteDao.getById(speckvisit.getPatient());
            
           

            lista_speckvisite_Dao  = daoFactory.getListaVisiteDAO();
            ListaVisite lista_speckvisite = lista_speckvisite_Dao.getById( speckvisit.getVisitcode() );

            categoryDao = daoFactory.getCategoryDAO();
            Category category = categoryDao.getById(lista_speckvisite.getCategory() );

            visitDao = daoFactory.getVisitDAO();
            Visit visit = visitDao.getById(speckvisit.getVisit()); 


            Paziente dottore = pazienteDao.getById( speckvisit.getFamilydoctor() );
            Paziente specialista = pazienteDao.getById( speckvisit.getSpecialist() );



            /////////////////////PDF////////////////////////////

            Document document = new Document();
            try{                   

                GregorianCalendar gc = new GregorianCalendar (); //data odierna
                int mese = gc.get(Calendar.MONTH)+1;
                Date today = Date.valueOf (gc.get(Calendar.YEAR)+"-"+mese+"-"+gc.get(Calendar.DAY_OF_MONTH));

                response.setContentType("application/pdf");
                PdfWriter.getInstance(document, response.getOutputStream());

                document.open();
                Font testo = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);
                Font titoli = FontFactory.getFont(FontFactory.TIMES_BOLD, 15, BaseColor.BLACK);
                Font intestazione = FontFactory.getFont(FontFactory.TIMES_BOLD, 17, BaseColor.BLACK);

                try {
                document.add(new Paragraph(speckvisit.getName(), intestazione));
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }            
                try {
                document.add(new Paragraph(" ")); //linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                document.add(new Paragraph("Prescrizione", titoli));
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }            
                try {
                document.add(new Paragraph(" ")); //linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
                //creo la tabella con nome del dottore e data di prescrizione
                PdfPTable table = new PdfPTable(2);

                table.addCell("Prescritto da");
                String prescrittore;
                if(visit.getType() == TypeVisit.V) //trovo chi ha prescritto la visita
                {
                    Paziente doc = new Paziente();
                    try {
                        doc = pazienteDao.getById( visit.getFamilydoctor() );
                    } catch (DaoException ex) {
                        Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    prescrittore = "dott. " + doc.getFirstname() + " " + doc.getLastname();

                    table.addCell("Medico di base");
                    table.addCell(prescrittore);
                }else 
                {
                    Chs chs = new Chs();
                    try {

                        chs = chsDao.getById(visit.getFamilydoctor());
                    } catch (DaoException ex) {
                        Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    prescrittore = chs.getName();
                    table.addCell(prescrittore);
                }
                table.addCell("Data prescrizione");
                table.addCell(visit.getVisdate().toString());
                table.addCell("Paziente");
                table.addCell( paziente.getFirstname() + " " + paziente.getLastname());
                table.addCell("Codice Fiscale");
                table.addCell( paziente.getSsn() );

                try {
                    document.add(table);
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }        

                try {
                    document.add(new Paragraph (" ")); ///aggiungo una linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                ////////Informazioni sulla visita
                try {
                document.add(new Paragraph("Informazioni sulla visita", titoli));
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }            
                try {
                document.add(new Paragraph(" ", intestazione)); //linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                //creo la tabella con nome del dottore e data di prescrizione
                table = new PdfPTable(2);

                table.addCell("Categoria esame");
                table.addCell(category.getId() + " - " + category.getName());
                table.addCell("Note");
                String note = " - ";
                if(lista_speckvisite.getDescription() != null)
                    note = lista_speckvisite.getDescription();
                table.addCell(note);            
                table.addCell("Data di esecuzione");

                String data = "Data non presente";  //faccio in modo che se la data è null non mi dia errore
                if(speckvisit.getMadedate() != null)
                    data = speckvisit.getMadedate().toString(); 

                table.addCell(data);
                table.addCell("Medico");
                String medico = " ";
                if(specialista != null)
                    medico = specialista.getFirstname() + specialista.getLastname();
                table.addCell(medico);

                try {
                    document.add(table);
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }  

                try {
                    document.add(new Paragraph (" ")); ///aggiungo una linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }            

                ////////esito della visita
                try {
                document.add(new Paragraph("Esito della visita", titoli));
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }            
                try {
                document.add(new Paragraph(" ")); //linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("prima di prendere l'esito");

                String esito = "Esito non presente";
                if(speckvisit.getResult()!= null && speckvisit.getResult().length() > 1)
                    esito = speckvisit.getResult(); 
                System.out.println("aggiungo esito al pdf");
                try {
                document.add(new Paragraph(esito, testo));
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }   

            }catch(Exception e){
                document.close();
                e.printStackTrace();
            }


            document.close();
            daoFactory.closeConn();
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
            Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioVisitaSpeckChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
