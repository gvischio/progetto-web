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
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DottoreDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DrugDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.FarmaciaDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.VisitDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Drug;
import it.unitn.disi.wp.servizioSanitario.entities.Farmacia;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Prescription;
import it.unitn.disi.wp.servizioSanitario.entities.Visit;
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
public class DettaglioRicettaChsToPDF extends HttpServlet {

    private DAOFactory daoFactory;
    private PazienteDAO pazienteDao;
    private DrugDAO drugDao;
    private PrescriptionDAO prescriptionDao;
    private Prescription prescription;
    private Paziente paziente;
    private FarmaciaDAO farmaciaDao;
    private Farmacia farmacia;
    private VisitDAO visitDao;
    private Visit visit;
    private Drug drug;
    private Paziente dottore;
    
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
            /* TODO output your page here. You may use following sample code. */
            
            //URL: DettaglioRicettaDottoreToPDF?prescriptionId=xxx
            HttpSession session = request.getSession();
            daoFactory = new DAOFactoryImplementation();
            pazienteDao = daoFactory.getPazienteDAO();
            prescriptionDao = daoFactory.getPrescriptionDAO();
            farmaciaDao = daoFactory.getFarmaciaDAO();
            visitDao = daoFactory.getVisitDAO();
            drugDao = daoFactory.getDrugDAO();    
            
            int prescriptionId = Integer.parseInt( request.getParameter("prescriptionId") );
                        
            try {
                prescription = prescriptionDao.getById(prescriptionId);
                request.setAttribute("prescription", prescription);
            }
            catch (Exception e)
            {
                System.out.println(" -> ricetta inesistente");
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            paziente = pazienteDao.getById(prescription.getPatient());
            


            dottore = pazienteDao.getById(prescription.getFamilydoctor());
            farmacia = farmaciaDao.getById(prescription.getDrugstore());
            visit = visitDao.getById(prescription.getVisit());
            drug = drugDao.getById(prescription.getDrug());



            ///////////////////PDF////////////////////////


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
                    document.add(new Paragraph("Ricetta", intestazione));
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }    


                try {
                    document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }


                try {
                    document.add(new Paragraph("Prescrizione", titoli)); 
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                //creo la tabella con nome del dottore e data di prescrizione
                PdfPTable table = new PdfPTable(2);

                table.addCell("Prescritto per");
                table.addCell( paziente.getFirstname() + " " + paziente.getLastname());
                table.addCell("Codice Fiscale");
                table.addCell( paziente.getSsn() );
                table.addCell("Prescritto da");
                table.addCell("dott." + dottore.getFirstname() + " " + dottore.getLastname());
                table.addCell("Data di emissione");
                table.addCell(visit.getVisdate().toString());           

                try {
                    document.add(table);
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    document.add(new Paragraph("Dettagli ricetta", titoli)); 
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                } catch (DocumentException ex) {
                    Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                }

                table = new PdfPTable(2);

                table.addCell("Farmaco prescritto");
                table.addCell(drug.getName());
                table.addCell("Quantità");
                Integer quantità = prescription.getQuantity();
                table.addCell(quantità.toString());
                table.addCell("Ricetta attiva");

                if(prescription.getActive() == new Byte("0"))
                {
                    table.addCell("No");

                    try {
                    document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph("Evasione della ricetta", titoli)); 
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    table = new PdfPTable(2);

                    table.addCell("Data di evasione");
                    table.addCell(prescription.getMadedate().toString());
                    table.addCell("Farmacia");
                    table.addCell("Farmacia di " + farmacia.getCity());
                    table.addCell("P.IVA");
                    table.addCell(farmacia.getPiva());

                    try {
                    document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(prescription.getActive() == new Byte("1"))
                {
                    table.addCell("Si");

                    try {
                    document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioRicettaChsToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
