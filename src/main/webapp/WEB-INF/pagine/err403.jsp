<%-- 
    Document   : err403
    Created on : 3-set-2019, 13.11.40
    Author     : Giacomo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Servizio Sanitario</title>
    <link rel="icon" href="assetsHome/img/favicon.ico">
    <link rel="shortcut icon" href="assetsHome/img/favicon.ico">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Mono">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/styles.min.css">
</head>

<body style="background-color: #f4f4f4">
    <header class="shadow" id="htitle" style="">
        <p id="ptitle">Portale per i servizi sanitari italiani</p>
    </header>
    <style>
        #htitle {
            margin: 0px;
            margin-bottom: 15px;
            background-color: #007bff;
            font-family: Raleway;
            position: relative;
            min-height: 90px;
        }
        #ptitle {
            font-size: 2.4em;
            color: white;
            position: relative;
            top: 15px;
            width: 100%;
            padding: 10px;
        }
    </style>
    <div class="container container-main">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 col-lg-5 col-xl-4">
                                <p class="text-center" style="font-size: 11em;color: #499cfc;font-family: 'Roboto Mono', monospace;margin-bottom: 0;">403</p>
                            </div>
                            <div class="col">
                                <h1 style="margin-top: 58px;">Accesso negato</h1>
                                <p style="margin-bottom: 35px;">Non sei autorizzato a visualizzare questo contenuto</p>
                                <c:choose>
                                    <c:when test="${sessionScope.user==null}">
                                        <a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}">Torna alla homepage</a>
                                    </c:when>
                                    <c:when test="${sessionScope.user.ruolo=='P'}">
                                        <a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}/restricted/paziente/HomePaziente">Torna alla tua homepage</a>
                                    </c:when>
                                    <c:when test="${sessionScope.user.ruolo=='D'}">
                                        <a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}/restricted/dottore/HomeDottore">Torna alla tua homepage</a>
                                    </c:when>
                                    <c:when test="${sessionScope.user.ruolo=='C'}">
                                        <a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}/restricted/chs/HomeChs">Torna alla tua homepage</a>
                                    </c:when>
                                    <c:when test="${sessionScope.user.ruolo=='S'}">
                                        <a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}/restricted/farmacia/HomeFarmacia">Torna alla tua homepage</a>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
