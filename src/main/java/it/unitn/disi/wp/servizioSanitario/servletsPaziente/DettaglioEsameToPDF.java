/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

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
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Category;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.User;
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
public class DettaglioEsameToPDF extends HttpServlet {

    private DAOFactory daoFactory;
    private ExamDAO examDao;
    private Exam exam;
    private PazienteDAO pazienteDao;
    private VisitDAO visitDao;
    private CategoryDAO categoryDao;
    private ListaEsamiDAO listaesamiDao;
    private ListaEsami listaesami;
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
        
            
            //URL: DettaglioEsameToPDF?examId=xxx
            
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            daoFactory = new DAOFactoryImplementation();
            int examId = Integer.parseInt( request.getParameter("examId") );
            examDao = daoFactory.getExamDAO();
            
            try
            {
                exam = examDao.getById(examId);
            }catch (DaoException ex)
            {
                daoFactory.closeConn();
                response.sendError(404);
            }
            
            
            boolean ok = false;
            try {
                Paziente paz = (Paziente) session.getAttribute("paziente");
                //System.out.println(paz.getId()+ " != " + exam.getPatient());
                if(paz.getId() != exam.getPatient())
                    throw new Exception(paz.getId()+ " != " + exam.getPatient());
                ok = true;
            }
            catch(Exception e)
            {
                daoFactory.closeConn();
                response.sendError(403);
            }

            if(ok)
            {
                pazienteDao = daoFactory.getPazienteDAO();
                Paziente paziente = pazienteDao.getById(exam.getPatient());

                visitDao = daoFactory.getVisitDAO();
                Visit visit = visitDao.getById(exam.getVisit());

                listaesamiDao = daoFactory.getListaEsamiDAO();
                listaesami = listaesamiDao.getById(exam.getExamcode());

                categoryDao = daoFactory.getCategoryDAO();
                Category category = categoryDao.getById(listaesami.getCategory());

                chsDao = daoFactory.getChsDAO();
                Chs chs = chsDao.getById(exam.getProvidedby());
                String prescrittore;
                if(visit.getType() == TypeVisit.V) //trovo chi ha prescritto la visita
                {
                    Paziente doc = pazienteDao.getById( visit.getFamilydoctor() );
                    prescrittore = "dott. " + doc.getFirstname() + " " + doc.getLastname();
                }else 
                {
                    Chs chspresc = chsDao.getById(visit.getFamilydoctor());
                    prescrittore = chspresc.getName();
                }


                ////////////////////////////////PDF ///////////////////////////////

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
                        document.add(new Paragraph(exam.getName(), intestazione));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    PdfPTable table = new PdfPTable(2);

                    // t.setBorderColor(BaseColor.GRAY);
                    // t.setPadding(4);
                    // t.setSpacing(4);
                    // t.setBorderWidth(1);

                    try {
                        document.add(new Paragraph("Prescrizione", titoli)); 
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }             
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                    table.addCell("Prescritto da");
                    table.addCell(prescrittore);
                    table.addCell("Data prescrizione");
                    table.addCell(visit.getVisdate().toString());
                    table.addCell("Paziente");
                    table.addCell( paziente.getFirstname() + " " + paziente.getLastname());
                    table.addCell("Codice Fiscale");
                    table.addCell( paziente.getSsn() );

                    try {
                        document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }                 
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }            

                    table = new PdfPTable(2);

                    try {
                        document.add(new Paragraph("Informazioni sull'esame", titoli)); 
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }             
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                    table.addCell("Categoria dell'esame");
                    table.addCell(category.getName());
                    table.addCell("Note");
                    if( listaesami.getDescription() == null || listaesami.getDescription().length() < 2 )
                        table.addCell(" - ");
                    else
                        table.addCell(listaesami.getDescription());
                    table.addCell("Data di esecuzione");
                    if(exam.getMadedate() == null) //controllo che esista la data
                        table.addCell("Esame non eseguito");
                    else
                        table.addCell(exam.getMadedate().toString()); 

                    table.addCell("Struttura");

                    if(chs.getName() == null || chs.getName().length()<2 || chs.getId() == 1)
                    {      
                        table.addCell("Struttura non presente");
                    }else
                    {
                        table.addCell(chs.getName());
                    }

                    table.addCell("ticket");
                    table.addCell(String.valueOf (exam.getTicket()));

                    try {
                        document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph("Esito dell'esame", titoli));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        document.add(new Paragraph(" ")); //linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String risultato = "Risultato non presente";
                    if(exam.getResult()!= null)
                    {
                        risultato = exam.getResult();
                    }
                    try {
                        document.add(new Paragraph(risultato, testo));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }catch(Exception e){
                    document.close();
                    e.printStackTrace();
                }
                
                document.close();
                daoFactory.closeConn();
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
            Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioEsameToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
