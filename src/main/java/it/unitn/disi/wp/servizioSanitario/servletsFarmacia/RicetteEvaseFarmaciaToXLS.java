/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsFarmacia;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.ChsDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.FarmaciaDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PrescriptionDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Chs;
import it.unitn.disi.wp.servizioSanitario.entities.Farmacia;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.entities.utils.PrescriptionPaid;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author simmf
 */
public class RicetteEvaseFarmaciaToXLS extends HttpServlet {

    private DAOFactory daoFactory;
    
    private FarmaciaDAO farmaciaDao;
    private PrescriptionDAO prescriptionDao;
    private Farmacia farmacia;
    private List<PrescriptionPaid> erogate;
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
        
            
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user == null)
                response.sendRedirect("ChiamaLogin");
            
            daoFactory = new DAOFactoryImplementation();
            farmaciaDao = daoFactory.getFarmaciaDAO();             
            prescriptionDao = daoFactory.getPrescriptionDAO();
            
            try{
                farmacia = farmaciaDao.getById(user.getId());
                erogate =  new ArrayList(prescriptionDao.getPaidByFarmacia(farmacia.getId()));
            }catch(NotFoundDAOException ex){ 
                daoFactory.closeConn();
                request.getRequestDispatcher("/WEB-INF/pagine/err404.jsp").forward(request, response);
            } 
            
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sh = wb.createSheet();
            
            Row row = sh.createRow(0); // creo la prima riga
            Cell cell = row.createCell(0);
            cell.setCellValue("Data");
            cell = row.createCell(1);
            cell.setCellValue("Medicinale");
            cell = row.createCell(2);
            cell.setCellValue("Codice fiscale paziente");
            cell = row.createCell(3);
            cell.setCellValue("Codice dottore");
            cell = row.createCell(4);
            cell.setCellValue("Ticket");
            
            for(int numRighe = 0; numRighe < erogate.size(); numRighe++)
            {
               row = sh.createRow(numRighe+1); // creo una riga        
               
               cell = row.createCell(0);
               String data = erogate.get(numRighe).getData().toString();
               data = data.substring(0, 16);
               cell.setCellValue(data);
               cell = row.createCell(1);
               cell.setCellValue(erogate.get(numRighe).getDrug());
               cell = row.createCell(2);
               cell.setCellValue(erogate.get(numRighe).getSsnPaziente());
               cell = row.createCell(3);
               cell.setCellValue(erogate.get(numRighe).getDoctorCode());
               cell = row.createCell(4);
               cell.setCellValue(erogate.get(numRighe).getTicket());                
            }
            
            GregorianCalendar gc = new GregorianCalendar (); //data odierna
            int mese = gc.get(Calendar.MONTH)+1;
            Date today = Date.valueOf (gc.get(Calendar.YEAR)+"-"+mese+"-"+gc.get(Calendar.DAY_OF_MONTH));
            String nomeFile = ("farmacia_di_" + farmacia.getCity()+ "_evase_al_" + today.toString());

            //per salvare nella cartella dei downloads


            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte [] outArray = outByteStream.toByteArray();
            response.setContentType("application/vnd.ms-excel");
            response.setContentLength(outArray.length); 
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=" + nomeFile + ".xlsx");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            System.out.println("dopo write");
            wb.close();
            
            daoFactory.closeConn();
            response.sendRedirect("HomeFarmacia");
            
        
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
            Logger.getLogger(RicetteEvaseFarmaciaToXLS.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RicetteEvaseFarmaciaToXLS.class.getName()).log(Level.SEVERE, null, ex);
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
