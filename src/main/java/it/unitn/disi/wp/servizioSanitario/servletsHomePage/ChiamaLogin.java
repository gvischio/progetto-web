/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.wp.servizioSanitario.servletsHomePage;

import it.unitn.disi.wp.servizioSanitario.dao.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.DaoException;
import it.unitn.disi.wp.servizioSanitario.dao.exceptions.NotFoundDAOException;
import it.unitn.disi.wp.servizioSanitario.entities.User;
import it.unitn.disi.wp.servizioSanitario.dao.interfaces.UserDAO;
import it.unitn.disi.wp.servizioSanitario.dao.mysql.DAOFactoryImplementation;
import it.unitn.disi.wp.servizioSanitario.utils.CookieCipher;
import it.unitn.disi.wp.servizioSanitario.utils.Sha256;
import javax.servlet.annotation.WebServlet;


/*
 *
 * @author Mike_TdT
 */
public class ChiamaLogin extends HttpServlet {

    private int COOKIE_EXP = 60+60*24*365*2; //2 ANNI
    private DAOFactory daoFactory = new DAOFactoryImplementation();
    private UserDAO u = daoFactory.getUserDAO();
//
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        session.setAttribute("daoFactory", daoFactory);

        String email = (String) request.getParameter("email");
        System.out.println(email);
        String password = (String) request.getParameter("password");
        System.out.println(password);
        String checkPassword;
        try {
            checkPassword = Sha256.doHash(password);
            System.out.println(checkPassword);
            System.out.println("nel try nel Login");
            User user = u.getByEmail(email);
            System.out.println("password giusta : " + user.getPassword());
            if (checkPassword.compareTo(user.getPassword())==0) {
                //UTILE IN DB CON CRIPT
//			if (Sha256.checkPassword(password, user.getPassword())) {
                    // session is inserted into sessionHandler (notifications)
            System.out.println("CI SONO");
            
            session.setAttribute("user", user);     
            
            if (request.getParameter("ricordami") != null) {
                    boolean trovato = false;
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                            for (Cookie cookie : request.getCookies()) {
                                    if (cookie.getName().equals("ricordami")) {
                                            trovato = true;
                                            System.out.println("TROVATO");
                                    }
                            }
                    }
                    if (!trovato) {
                            Cookie userCookie = new Cookie("ricordami", CookieCipher.encrypt(email));
                            userCookie.setMaxAge(COOKIE_EXP);
                            response.addCookie(userCookie);
                            System.out.println("CREATO");
                    }
                }
                switch (user.getRuolo()) {
                    case P:
                        System.out.println("OK");
                        response.sendRedirect("restricted/paziente/HomePaziente");
                        break; //contesto e path e concatenarlo
                    case D:
                        response.sendRedirect("restricted/dottore/HomeDottore");
                        break;
                    case S:
                        response.sendRedirect("restricted/farmacia/HomeFarmacia"); 
                        break;
                    case C:
                        response.sendRedirect("restricted/chs/HomeChs");  
                        break;
                    default:
                        break;  
                }
            }
            else {
                request.setAttribute("errorLogin", "Email o password non validi");
                System.out.println("NO SAI LOGIN");
                request.getRequestDispatcher("/WEB-INF/pagine/homePage/Login.jsp").forward(request, response);
            }         
    } catch (NotFoundDAOException ex) {
      request.setAttribute("errorDAO", "Email non trovata");
        System.out.println("NO SAI DAO NO");
      request.getRequestDispatcher("/WEB-INF/pagine/homePage/Login.jsp").forward(request, response);
    } catch (DaoException ex) {
      Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      request.setAttribute("erroreGen", "Errore generale");
      request.getRequestDispatcher("/WEB-INF/pagine/homePage/Login.jsp").forward(request, response);
      //pagina di errore OPSS
    } catch (NullPointerException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("ControlloCookie").forward(request, response);
    }
  }
      /*
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
                processRequest(request, response);
  }
        
            /*
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
//    @Override
//    public void init() {
//        DAOFactory factory = (DAOFactory) this.getServletContext().getAttribute("daoFactory");
//        userDAO = factory.getUserDAO();
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
////    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }



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
