/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ExamDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaEsamiDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.Exam;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import java.util.ArrayList;
import java.util.List;
import it.unitn.disi.wp.servizioSanitario.entities.Drug;
import it.unitn.disi.wp.servizioSanitario.entities.ListaEsami;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
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
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ListaVisiteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.SpeckvisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.ListaVisite;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Speckvisit;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeVisit;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
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
public class DettaglioVisitaBaseToPDF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private User user;
    private DAOFactory daoFactory;
    private VisitDAO visitDao;
    private PazienteDAO pazienteDao;
    private DispositionDAO dispoDao;
    private PrescriptionDAO prescriptionDao;
    private DrugDAO drugDao;
    private ExamDAO examDao;
    private ListaVisiteDAO listaVisiteDao;
    private ListaEsamiDAO listaEsamiDao;
    private SpeckvisitDAO speckvisitDao;
    private ChsDAO chsDao;
    
    @Override
    public void init() throws ServletException {

        daoFactory = new DAOFactoryImplementation();
        visitDao = daoFactory.getVisitDAO();
        pazienteDao = daoFactory.getPazienteDAO();
        dispoDao = daoFactory.getDispositionDAO();
        prescriptionDao = daoFactory.getPrescriptionDAO();
        drugDao = daoFactory.getDrugDAO();
        examDao = daoFactory.getExamDAO();
        listaEsamiDao = daoFactory.getListaEsamiDAO();
        speckvisitDao = daoFactory.getSpeckvisitDAO();
        listaVisiteDao = daoFactory.getListaVisiteDAO();      
        chsDao = daoFactory.getChsDAO();
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
        

            //URL DettaglioVisitaBaseToPDF?visitaId=xxx


            HttpSession session = request.getSession();
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            User utente_paziente = (User) session.getAttribute("user");
            int visitaId = Integer.parseInt(request.getParameter("visitaId"));

            Visit visita = new Visit(); 
            Paziente paziente = new Paziente();
            Paziente familyDoctor = new Paziente();

            System.out.println("sono in Dettaglio VisitaBaseToPDF");

            List <Disposition> dispositions = new ArrayList ();

            try {
                visita = visitDao.getById( Integer.parseInt( request.getParameter("visitaId") ) );
            } catch (DaoException ex) {
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }

            boolean ok = false;
            try {
                Paziente paz = (Paziente) session.getAttribute("paziente");
                if(paz.getId() != visita.getPatient())
                    throw new Exception(paz.getId()+ " != " + visita.getPatient());
                ok = true;
            }
            catch(Exception e)
            {
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }

            if(ok)
            {

                try {
                    paziente = pazienteDao.getById(utente_paziente.getId());
                } catch (DaoException ex) {
                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    familyDoctor = pazienteDao.getById(paziente.getFamilydoctor());
                } catch (DaoException ex) {
                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }    

                try {
                    dispositions = dispoDao.getByVisit( Integer.parseInt( request.getParameter("visitaId") ) ) ;
                } catch (DaoException ex) {
                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }        



        ///////////con itext
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
                        document.add(new Paragraph("Visita del " + visita.getVisdate(), intestazione));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }    

                    Paragraph paragraph = new Paragraph();
                    paragraph.add(new Paragraph(" "));
                    try {
                        document.add(paragraph); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    PdfPTable table = new PdfPTable(2);

                    // t.setBorderColor(BaseColor.GRAY);
                    // t.setPadding(4);
                    // t.setSpacing(4);
                    // t.setBorderWidth(1);

                    try {
                        document.add(new Paragraph("Dettagli della visita", titoli));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String prescrittore;
                    if(visita.getType() == TypeVisit.V) //trovo chi ha prescritto la visita
                    {
                        Paziente doc = new Paziente();
                        try {
                            doc = pazienteDao.getById( visita.getFamilydoctor() );
                        } catch (DaoException ex) {
                            Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        prescrittore = "dott. " + doc.getFirstname() + " " + doc.getLastname();

                        table.addCell("Data visita");
                        table.addCell(visita.getVisdate().toString());
                        table.addCell("Medico di base");
                        table.addCell(prescrittore);
                    }else 
                    {
                        Chs chs = new Chs();
                        try {
                            chs = chsDao.getById(visita.getFamilydoctor());
                        } catch (DaoException ex) {
                            Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        prescrittore = chs.getName();
                        table.addCell("Data richiamo");
                        table.addCell(visita.getVisdate().toString());
                        table.addCell("Richiamo emesso da");
                        table.addCell(prescrittore);
                    }

                    table.addCell("Motivazione della prenotazione");
                    if (visita.getMotivation() == null)
                    {
                        table.addCell("Motivazione non presente");
                    }else{
                        table.addCell(visita.getMotivation());
                    }        
                    table.addCell("Paziente");
                    table.addCell( paziente.getFirstname() + " " + paziente.getLastname());
                    table.addCell("Codice Fiscale");
                    table.addCell( paziente.getSsn() );


                    try {
                        document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph("Anamnesi", titoli));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    } 

                    if(visita.getDoctorsays() == null || visita.getDoctorsays().length() < 2) 
                    {
                        try {
                            document.add(new Paragraph("Anamnesi non presente", testo));
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }else
                    {
                        try {
                            document.add(new Paragraph(visita.getDoctorsays(), testo));
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }


                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    table = new PdfPTable(2);

                    // t.setBorderColor(BaseColor.GRAY);
                    // t.setPadding(4);
                    // t.setSpacing(4);
                    // t.setBorderWidth(1);

                    try {
                        document.add(new Paragraph("Prescrizioni della visita", titoli));
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }         
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

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
                                ListaEsami nome = new ListaEsami();
                                try {
                                    exam = examDao.getById(k.getId());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                } 

                                try {
                                    nome = listaEsamiDao.getById(exam.getExamcode());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if(exam.getMadedate()!=null)
                                    table.addCell(exam.getMadedate().toString());
                                else
                                    table.addCell("Esame non effettuato");

                                table.addCell("Esame: " + nome.getName());
                            }else if ( k.getType() == TypeDisposition.S)
                            {
                                Speckvisit visit = new Speckvisit();
                                ListaVisite nome = new ListaVisite();
                                try {
                                    visit = speckvisitDao.getById(k.getId());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                } 

                                try {
                                    nome = listaVisiteDao.getById(visit.getVisitcode());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if(visit.getMadedate()!=null)
                                    table.addCell( visit.getMadedate().toString());
                                else
                                    table.addCell("Visita non effettuata");

                                table.addCell("Visita: " +nome.getName());
                                }else if (k.getType() == TypeDisposition.P)
                                {
                                Prescription prescription = new Prescription();
                                Drug nome = new Drug();

                                try {
                                    prescription = prescriptionDao.getById(k.getId());
                                    nome = drugDao.getById(prescription.getDrug());
                                } catch (DaoException ex) {
                                    Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if(prescription.getActive() == 1)
                                {
                                    table.addCell("Attiva");
                                    table.addCell("Ricetta: "+ nome.getName());
                                }else
                                {
                                    table.addCell(prescription.getMadedate().toString());
                                    table.addCell("Ricetta: "+ nome.getName());
                                }
                            }
                        }
                    }


                    try {
                        document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioVisitaBaseToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }catch(Exception e){
                    document.close();
                    e.printStackTrace();
                }
                
                document.close();
                daoFactory.closeConn();
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


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

}
