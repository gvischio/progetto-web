/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsPaziente;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PazienteDAO;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.PhotoDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.entities.Paziente;
import it.unitn.disi.wp.servizioSanitario.entities.Photo;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileExistsException;

/**
 *
 * @author Mike_TdT
 */
public class UploadPhoto extends HttpServlet {
// Import required java libraries
    
   private DAOFactory daoFactory;
   PhotoDAO photoDAO;
   private boolean isMultipart;
   private String filePath;
   private final int maxFileSize = 2000 * 1024;
   private final int maxMemSize = 4 * 1024;
   private File file ;

   @Override
   public void init( ){
      // Get the file location where it would be stored.
      filePath = "C:\\Program Files\\apache-tomcat-9.0.22\\webapps\\data";
       System.out.println(filePath);
   }
   
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, java.io.IOException {
       System.out.println("SONO IN POST, PASSAMI LA PALLA");
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);
      response.setContentType("text/html");
      java.io.PrintWriter out = response.getWriter( );
  
      DiskFileItemFactory factory = new DiskFileItemFactory();
   
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
   
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File("C:\\Program Files\\apache-tomcat-9.0.22\\webapps\\temp"));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
   
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );
      try { 
           HttpSession session = request.getSession();
           User user = (User) session.getAttribute("user");
           int userID = user.getId();
           daoFactory = new DAOFactoryImplementation();
           PazienteDAO p = daoFactory.getPazienteDAO();
           Paziente paziente = p.getById(userID);
           photoDAO = daoFactory.getPhotoDAO();
           Photo photo = new Photo();
           try {
               photo = photoDAO.getByLastPhotoUser(userID);
           } catch(NotFoundDAOException ex) {
               photo.setPath("/data/" + paziente.getFirstname() + paziente.getLastname() + "/foto000.jpeg");
           }
           String path = photo.getPath();
           System.out.println(path);
           String controllo = path.substring(path.lastIndexOf("."));
           String jpeg = ".jpeg";
           String jpg = ".jpg";
           String png = ".png";
           if(controllo.compareTo(jpeg) != 0 && controllo.compareTo(jpg) != 0 && controllo.compareTo(png) != 0) {
               //ECCEZIONE BAD REQUEST?
               request.setAttribute("fileNonCompatibile", "Non compatibile");
               daoFactory.closeConn();
               request.getRequestDispatcher("ImpostazioniPaziente").forward(request, response);;
           } else {
            String sottopath = path.substring(path.lastIndexOf("/"));
            System.out.println(sottopath);
            String finale = sottopath.substring(5, 8);
            System.out.println(finale);
            int idCambio = Integer.parseInt(finale);
             //IN BASE ALLE CIFRE AGGIUNGO IL PATH GIUSTO
            if(idCambio >= 0 && idCambio < 10) {
                idCambio += 1;
                finale = "00" + String.valueOf(idCambio).toString();
            } else if(idCambio >= 10 && idCambio < 100) {
                idCambio += 1;
                finale = "0" + String.valueOf(idCambio).toString() + "0";
            } else if(idCambio >= 100 && idCambio < 999) {
                idCambio += 1;
                finale = String.valueOf(idCambio).toString();
            } else if(idCambio == 999) {
                finale = "001";
            }
             System.out.println(finale);
             //stampa numeretto corretto FINO A QUI

             //PATH CORRETTO IN SERVER
             String pathNew = path.substring(0, path.lastIndexOf("/")) + sottopath.substring(0, 5) + finale + path.substring(path.lastIndexOf("."));
             path = path.substring(5, path.lastIndexOf("/")) + sottopath.substring(0, 5) + finale + path.substring(path.lastIndexOf("."));

             System.out.println(path);
             System.out.println(pathNew);
          // Parse the request to get file items.
          List fileItems = upload.parseRequest(request);

          // Process the uploaded file items
          Iterator i = fileItems.iterator();

          while ( i.hasNext () ) {
             FileItem fi = (FileItem)i.next();
             if ( !fi.isFormField () ) {
                // Get the uploaded file parameters
                String fieldName = fi.getFieldName();
                String fileName = fi.getName();
                String contentType = fi.getContentType();
                boolean isInMemory = fi.isInMemory();
                long sizeInBytes = fi.getSize();
                System.out.println(fileName);
                 System.out.println(filePath);
                // Write the file
                if( fileName.lastIndexOf("/") >= 0 ) {

                   file = new File( filePath + path);
                    System.out.println(filePath + path);
                } else {
                   file = new File( filePath + path) ;
                   System.out.println(filePath + path);
                }
                fi.write( file ) ;
             }
          }

           Photo newPhoto = new Photo();
           newPhoto.setUser(userID);
           newPhoto.setPath(pathNew);
           boolean inserito = photoDAO.addPhoto(newPhoto);
           if(inserito) {
                 request.setAttribute("caricatoFile", "File caricato");
                 daoFactory.closeConn();
                 request.getRequestDispatcher("ImpostazioniPaziente").forward(request, response);
             } else {
                 request.setAttribute("erroreFile", "File non caricato");
                 daoFactory.closeConn();
                 request.getRequestDispatcher("ImpostazioniPaziente").forward(request, response);
             }
           }
         } catch(SizeLimitExceededException ex) {
            request.setAttribute("eccessoFile", "File fuori dal limite di grandezza");
             daoFactory.closeConn();
             request.getRequestDispatcher("ImpostazioniPaziente").forward(request, response);
         } catch(FileExistsException ex) {
            request.setAttribute("existingFile", "File già esistente");
            daoFactory.closeConn();
            request.getRequestDispatcher("ImpostazioniPaziente").forward(request, response);
         } catch (Exception ex) {
           System.out.println(ex);
         }
      }
      
      public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, java.io.IOException {

         throw new ServletException("GET method used with " +
            getClass( ).getName( )+": POST method required.");
      }
   }