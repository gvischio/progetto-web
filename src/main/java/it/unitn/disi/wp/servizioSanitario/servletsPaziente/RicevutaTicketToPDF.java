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
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.DispositionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.TicketDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Disposition;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.utils.Ticket;
import it.unitn.disi.wp.servizioSanitario.entities.utils.TypeDisposition;
import it.unitn.disi.wp.servizioSanitario.servletsDottore.DettaglioEsameDottoreToPDF;
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
public class RicevutaTicketToPDF extends HttpServlet {

    DAOFactory daoFactory;
    DispositionDAO dispositionDao;
    TicketDAO ticketDao;
    Disposition disposition;
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
                    
            
            //URL: RicevutaTicketToPDF?idDisp=xxx
            
            
            HttpSession session = request.getSession();
            Paziente paz = (Paziente) session.getAttribute("paziente");
            
            daoFactory = new DAOFactoryImplementation();
            dispositionDao = daoFactory.getDispositionDAO();
            ticketDao = daoFactory.getTicketDAO();
            
            int dispId = Integer.parseInt(request.getParameter("idDisp"));        
                    
             
            
            try {
                disposition = dispositionDao.getById(dispId);
            } catch (DaoException ex) {
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            }
            
            boolean ok = false;
            try {
                //System.out.println(paz.getId()+ " != " + exam.getPatient());
                if(paz.getId() != disposition.getPatient())
                    throw new Exception(paz.getId()+ " != " + disposition.getPatient());
                ok = true;
            }
            catch(Exception e)
            {
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err403.jsp").forward(request, response);
            }
            
                if(ok)
                {
                    Ticket ticket = ticketDao.getById(dispId);


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
                            document.add(new Paragraph("Ricevuta di pagamento per" + ticket.getName(), intestazione));
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        PdfPTable table = new PdfPTable(2);

                        // t.setBorderColor(BaseColor.GRAY);
                        // t.setPadding(4);
                        // t.setSpacing(4);
                        // t.setBorderWidth(1);

                        try {
                            document.add(new Paragraph("Dettagli prescrizione", titoli)); 
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        }             
                        try {
                            document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        } 


                        table.addCell("Eseguito da");
                        if(ticket.getDoneBy() == null)
                            table.addCell( "Non eseguito");
                        else
                            table.addCell( ticket.getDoneBy());
                        table.addCell("Data erogazione");
                        if(ticket.getMadeDate() == null)
                            table.addCell("Non eseguita");
                        else
                            table.addCell(ticket.getMadeDate().toString());
                        table.addCell("Paziente");
                        table.addCell(paz.getFirstname() + " " + paz.getLastname());
                        table.addCell("Codice Fiscale");
                        table.addCell( paz.getSsn() );
                        String tipologia = " ";
                        if(ticket.getType() == TypeDisposition.E)
                            tipologia = "Esme";
                        else if(ticket.getType() == TypeDisposition.P)
                            tipologia = "Ricetta";
                        else if(ticket.getType() == TypeDisposition.S)
                            tipologia = "Visita specialistica";

                        table.addCell("Tipologia");
                        table.addCell( tipologia );
                        table.addCell("Prestazione fornita");
                        table.addCell( ticket.getName() );
                        table.addCell("Prezzo ticket");
                        table.addCell( String.valueOf(ticket.getTicket()) );
                        table.addCell("ID prescrizione");
                        table.addCell( String.valueOf(ticket.getIdDisp()) );

                        try {
                            document.add(table);
                        } catch (DocumentException ex) {
                            Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                        } 

                        if (disposition.getPaid()==0)
                        {
                            try {
                                document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                            } catch (DocumentException ex) {
                                Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                            try {
                                document.add(new Paragraph("La disposizione non risulta pagata", titoli)); 
                            } catch (DocumentException ex) {
                                Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                        if (disposition.getPaid()==1)
                        {
                            try {
                                document.add(new Paragraph(" ")); ///aggiungo una linea vuota
                            } catch (DocumentException ex) {
                                Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                            try {
                                document.add(new Paragraph("La disposizione risulta pagata", titoli)); 
                            } catch (DocumentException ex) {
                                Logger.getLogger(DettaglioEsameDottoreToPDF.class.getName()).log(Level.SEVERE, null, ex);
                            } 
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
            Logger.getLogger(RicevutaTicketToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RicevutaTicketToPDF.class.getName()).log(Level.SEVERE, null, ex);
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
