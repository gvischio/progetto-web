/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
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
import it.unitn.disi.wp.servizioSanitario.entities.Dottore;

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
public class DettaglioRicettaToPDF extends HttpServlet {

    DAOFactory daoFactory;
    PrescriptionDAO prescriptionDao;
    PazienteDAO pazienteDao;
    Paziente paziente;
    VisitDAO visitDao;
    DrugDAO drugDao;
    FarmaciaDAO farmaciaDao;
    DottoreDAO dottoreDao;
    Prescription prescription;
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
            throws ServletException, IOException, DaoException, BadElementException, DocumentException {
        response.setContentType("text/html;charset=UTF-8");
            
        int prescriptionId = Integer.parseInt( request.getParameter("prescriptionId") );

        daoFactory = new DAOFactoryImplementation ();

        //dati della prescrizione da stampare
        prescriptionDao = daoFactory.getPrescriptionDAO();
        
        HttpSession session = request.getSession();
        
        try
        {
            prescription = prescriptionDao.getById(prescriptionId);
        }catch (DaoException ex)
        {
            daoFactory.closeConn();
            request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
        }
        
        boolean ok = false;
            try {
                Paziente paz = (Paziente) session.getAttribute("paziente");
                if(paz.getId() != prescription.getPatient())
                    throw new Exception(paz.getId()+ " != " + prescription.getPatient());
                ok = true;
            }
            catch(Exception e)
            {
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }

            if(ok)
            {
                //avere nome dottore e paziente
                pazienteDao = daoFactory.getPazienteDAO();
                Paziente dottore = pazienteDao.getById(prescription.getFamilydoctor());
                Paziente paziente = pazienteDao.getById(prescription.getPatient());

                //informazioni del dottore
                dottoreDao = daoFactory.getDottoreDAO();
                Dottore infoDottore = dottoreDao.getById(prescription.getFamilydoctor());
                //per avere la data di emissione
                visitDao = daoFactory.getVisitDAO();
                Visit visita = visitDao.getById(prescription.getVisit());

                //per avere il nome del farmaco
                drugDao = daoFactory.getDrugDAO();
                Drug drug = drugDao.getById(prescription.getDrug());

                //farmacia che ha eventualmente evaso la ricetta
                farmaciaDao = daoFactory.getFarmaciaDAO();
                Farmacia farmacia = farmaciaDao.getById( prescription.getDrugstore() );


                ///--------------------PDF------------------------////////


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
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }    


                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    try {
                        document.add(new Paragraph("Prescrizione", titoli)); 
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //creo la tabella con nome del dottore e data di prescrizione
                    PdfPTable table = new PdfPTable(2);

                    table.addCell("Prescritto da");
                    table.addCell("dott." + dottore.getFirstname() + " " + dottore.getLastname());
                    table.addCell("Data di emissione");
                    table.addCell(visita.getVisdate().toString());
                    table.addCell("Paziente");
                    table.addCell( paziente.getFirstname() + " " + paziente.getLastname());
                    table.addCell("Codice Fiscale");
                    table.addCell( paziente.getSsn() );

                    try {
                        document.add(table);
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph("Dettagli ricetta", titoli)); 
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
                            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            document.add(new Paragraph("Evasione della ricetta", titoli)); 
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        table = new PdfPTable(2);

                        table.addCell("Data di evasione");
                        table.addCell(prescription.getMadedate().toString());
                        table.addCell("Farmacia");
                        table.addCell("Farmacia di " + farmacia.getCity());
                        table.addCell("P.IVA");
                        table.addCell(farmacia.getPiva());
                        table.addCell("Ticket");
                        Integer ticket = prescription.getTicket();
                        table.addCell(ticket.toString());

                        try {
                        document.add(table);
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else if(prescription.getActive() == new Byte("1"))
                    {
                        table.addCell("Si");

                        try {
                        document.add(table);
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }


                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    ////QR CODE
                    try {
                        document.add(new Paragraph("QR CODE", titoli)); 
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    try {
                        document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                    } catch (DocumentException ex) {
                        Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //////////genero un QR CODE per poi aggiungerlo al pdf
                    String testoQR = "Codice dottore: " + infoDottore.getDoc_reg_numb() + "\n Codice discale paziente: " + paziente.getSsn() + "\n Data prescrizione: " 
                                    + visita.getVisdate() + "\n Codice univoco prescrizione: " + prescription.getId() + "\n Descrizione farmaco: " + drug.getDescription();
                    BarcodeQRCode my_code = new BarcodeQRCode( testoQR , 1, 1, null);
                    Image qr_image = my_code.getImage();
                    qr_image.scaleAbsolute(200, 200);


                    document.add(qr_image);


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
            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(DettaglioRicettaToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
