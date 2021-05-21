/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsDottore;

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
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDoctor;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import it.unitn.disi.wp.servizioSanitario.servletsPaziente.DettaglioVisitaBaseToPDF;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
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
public class DettaglioVisitaBaseDottoreToPDF extends HttpServlet {

    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private VisitDAO visitDao;
    private Paziente paziente;
    private Visit visit;
    private DispositionDAO dispositionDao;
    private SpeckvisitDAO speckvisitDao;
    private ExamDAO examDao;
    private PrescriptionDAO prescriptionDao;
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
        
            
            //URL: DettaglioVisitaBaseDottoreToPDF?visitId=xxx
            
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            visitDao = daoFactory.getVisitDAO();
            pazienteDao = daoFactory.getPazienteDAO();
            dispositionDao = daoFactory.getDispositionDAO();
            speckvisitDao = daoFactory.getSpeckvisitDAO();
            examDao = daoFactory.getExamDAO();
            prescriptionDao = daoFactory.getPrescriptionDAO();
            chsDao = daoFactory.getChsDAO();
            dottoreDao = daoFactory.getDottoreDAO();
            
            int visitId = Integer.parseInt( request.getParameter("visitId") );
            
            try {
                visit = visitDao.getById(visitId);
            }
            catch (Exception e)
            {
                System.out.println(" -> visita inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            paziente = pazienteDao.getById(visit.getPatient());
            
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
                
                Paziente dottorePaz = pazienteDao.getById(visit.getFamilydoctor());

                List <Disposition> dispositions = dispositionDao.getByVisit(visitId);
                
           
                ////////////PDF///////////////
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



                    /////////inizio corpo

                    try {
                        document.add(new Paragraph("Visita del " + visit.getVisdate(), intestazione));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }    

                    Paragraph paragraph = new Paragraph();
                    paragraph.add(new Paragraph(" "));
                    try {
                        document.add(paragraph); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    PdfPTable table = new PdfPTable(2);            
                    // t.setBorderColor(BaseColor.GRAY);
                    // t.setPadding(4);
                    // t.setSpacing(4);
                    // t.setBorderWidth(1);

                    try {
                        document.add(new Paragraph("Dettagli della visita", titoli));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String prescrittore;
                    if(visit.getType() == TypeVisit.V) //trovo chi ha prescritto la visita
                    {
                        Paziente doc = new Paziente();
                        try {
                            doc = pazienteDao.getById( visit.getFamilydoctor() );
                        } catch (DaoException ex) {
                            Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        prescrittore = "dott. " + doc.getFirstname() + " " + doc.getLastname();

                        table.addCell("Data visita");
                        table.addCell(visit.getVisdate().toString());
                        table.addCell("Medico di base");
                        table.addCell(prescrittore);
                    }else 
                    {
                        Chs chs = new Chs();
                        try {
                            chs = chsDao.getById(visit.getFamilydoctor());
                        } catch (DaoException ex) {
                            Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        prescrittore = chs.getName();
                        table.addCell("Data richiamo");
                        table.addCell(visit.getVisdate().toString());
                        table.addCell("Richiamo emesso da");
                        table.addCell(prescrittore);
                    }
                    table.addCell(dottorePaz.getFirstname() + " " + dottorePaz.getLastname());
                    table.addCell("Motivazione della prenotazione");
                    if (visit.getMotivation() == null)
                    {
                        table.addCell("Motivazione non presente");
                    }else{
                        table.addCell(visit.getMotivation());
                    }   
                    table.addCell("Paziente");
                    table.addCell( paziente.getFirstname() + " " + paziente.getLastname());
                    table.addCell("Codice Fiscale");
                    table.addCell( paziente.getSsn() );


                    try {
                        document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph("Anamnesi", titoli));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                    String anamnesi = "Anamnesi non presente";
                        if(visit.getDoctorsays()!=null)
                        {
                            anamnesi = visit.getDoctorsays();
                        }

                        System.out.println("--"+ anamnesi + "--");


                    System.out.println("anamnesi: " + anamnesi);
                    try {
                        document.add(new Paragraph(anamnesi, testo));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    table = new PdfPTable(2);

                    // t.setBorderColor(BaseColor.GRAY);
                    // t.setPadding(4);
                    // t.setSpacing(4);
                    // t.setBorderWidth(1);

                    try {
                        document.add(new Paragraph("Prescrizioni della visita", titoli));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }         
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("prima dell'elenco delle disposizioni");

                    if(dispositions.isEmpty())
                    {
                        try {
                            document.add(new Paragraph("Nessuna prescrizione presente", testo));
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }    
                    }else
                    {
                        for (Disposition k: dispositions)
                        {
                            if(k.getType() == TypeDisposition.E)
                            {
                                Exam exam = new Exam();
                                try {
                                    exam = examDao.getById(k.getId());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                } 

                                if(exam.getMadedate()!=null)
                                    table.addCell(exam.getMadedate().toString());
                                else
                                    table.addCell("Esame non effettuato");

                                table.addCell("Esame: " + exam.getName());

                            }else if ( k.getType() == TypeDisposition.S)
                            {
                                Speckvisit visit = new Speckvisit();

                                try {
                                    visit = speckvisitDao.getById(k.getId());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                } 

                                if(visit.getMadedate()!=null)
                                    table.addCell( visit.getMadedate().toString());
                                else
                                    table.addCell("Visita non effettuata");

                                table.addCell("Visita: " +visit.getName());

                            }else if (k.getType() == TypeDisposition.P)
                                {
                                Prescription prescription = new Prescription();

                                try {
                                    prescription = prescriptionDao.getById(k.getId());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if(prescription.getActive() == 1)
                                {
                                    table.addCell("Attiva");
                                    table.addCell("Ricetta: "+ prescription.getName());
                                }else
                                {
                                    table.addCell(prescription.getMadedate().toString());
                                    table.addCell("Ricetta: "+ prescription.getName());
                                }
                            }
                        }
                    }

                    try {
                        document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex); //aggiungo tutta la roba sopra nell'if/else
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
            Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioVisitaBaseDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
