<%-- 
    Document   : RecuperoPassword
    Created on : 16-ago-2019, 16.28.59
    Author     : Mike_TdT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="/WEB-INF/pagine/error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Servizio Sanitario</title>
        <link rel="icon" href="assetsHome/img/favicon.ico">
        <link rel="shortcut icon" href="assetsHome/img/favicon.ico">
        <link rel="stylesheet" href="../assetsHome/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic">
        <link rel="stylesheet" href="../assetsHome/fonts/ionicons.min.css">
        <link rel="stylesheet" href="../assetsHome/fonts/simple-line-icons.min.css">
        <link rel="stylesheet" href="../assetsHome/css/Footer-Basic.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/lightbox2/2.8.2/css/lightbox.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/3.3.1/css/swiper.min.css">
        <link rel="stylesheet" href="../assetsHome/css/Lightbox-Gallery.css">
        <link rel="stylesheet" href="../assetsHome/css/Login-Form-Dark-1.css">
        <link rel="stylesheet" href="../assetsHome/css/Simple-Slider.css">
        <link rel="stylesheet" href="../assetsHome/css/untitled.css">
        <style>
        .closebtn {
                padding-left: 15px;
                color: white;
                font-weight: bold;
                float: right;
                font-size: 20px;
                line-height: 18px;
                cursor: pointer;
                transition: 0.3s;
            }
            .closebtn:hover {
              color: black;
            }
            
            .alert {
                padding: 20px;
                background-color: red;
                color: white;
                opacity: 0.83;
                transition: opacity 0.6s;
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand navbar-expand-lg fixed-top">
            <a href="HomePage"><img src="../assetsHome/img/paramedic-1136916_640.png" height="60" width="60" /></a>
        </nav>
        <c:if test="${!empty emailNO}">
            <nav class="alert navbar navbar-expand navbar-expand-lg fixed-top">
                <div class="container-fluid"> 
                    <column><strong> Attenzione!</strong>  Email non inviata.</column>
                    <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
                </div>
            </nav>
        </c:if>
        <div class="login-dark">
            <form method="POST" action="InvioMailRecupero">
                <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
                <p align="center"><font size="2">Inserire la mail dell' account da recuperare</font></p>
                <div class="form-group"><input type="email" name="recuperoEmail" placeholder="Email" class="form-control" /></div>
                <div class="form-group"><button class="btn btn-primary btn-block" type="submit" onclick="this.disabled">INVIA</button></div>
            </form>
        </div>
        <div class="footer-basic">
            <footer>    
                <div class="social"><a href="https://www.instagram.com/serviziosanitarioweb/"><i class="icon ion-social-instagram"></i></a><a href="https://twitter.com/SSanitario"><i class="icon ion-social-twitter"></i></a><a href="https://www.facebook.com/marco.ssi.56863"><i class="icon ion-social-facebook"></i></a><a href="https://www.linkedin.com/in/servizio-sanitario-29bb00190/"><i class="icon ion-social-linkedin"></i></a></div>
                <ul class="list-inline">
                    <li class="list-inline-item"><a href="HomePage">Home</a></li>
                    <li class="list-inline-item"><a href="Chi">About</a></li>
                    <li class="list-inline-item"><a href="Terms">Condizioni d'utilizzo</a></li>
                    <li class="list-inline-item"><a href="Privacy">Privacy</a></li>
                </ul>
                <p class="copyright">© Servizio Sanitario 2019. All Rights Reserved.</p>
            </footer>
        </div>
    </body>
</html>
