<%-- 
    Document   : ConfermaPrenotazione
    Created on : 6-set-2019, 13.46.54
    Author     : Giacomo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/WEB-INF/pagine/error.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Servizio Sanitario</title>
    <link rel="icon" href="../../assetsHome/img/favicon.ico">
    <link rel="shortcut icon" href="../../assetsHome/img/favicon.ico">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto+Mono">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>

<body style="background-color: #f4f4f4;">
    <jsp:include page='headerPaziente.jsp'/>
    
    <div class="container">
        <c:choose>
            <c:when test="${requestScope.type=='E'}">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="mb-0">Prenotazione esame</h5>
                            </div>
                            <div class="card-body text-center">
                                <c:choose>
                                    <c:when test="${requestScope.esito=='ok'}">
                                        <div id="exam-success">
                                            <p class="text-left">La tua prenotazione è stata presa in carico dal sistema. A momenti riceverai una email di conferma</p>
                                            
                                        </div>
                                    </c:when>
                                    <c:when test="${requestScope.esito=='err'}">
                                        <div class="alert alert-danger text-left" role="alert" id="exam-error"><span style="margin-bottom: 5px;"><strong>Errore nella prenotazione</strong><br></span>
                                        <p style="margin-bottom: 0px;">Si è verificato un errore durante la procedura di prenotazione. La invitiamo a riprovare più tardi</p>
                                    </div>
                                    </c:when>
                                </c:choose>
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/Prenotazione" class="btn btn-primary" role="button" style="margin-right: 10px;">Nuova prenotazione</a>
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/HomePaziente" class="btn btn-primary btn-lg" role="button" style="margin-right: 5px;margin-left: 5px;">Torna alla tua homepage</a>
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=exam" class="btn btn-primary" role="button" style="margin-left: 10px;margin-right: 10px;">Visualizza tutti gli esami</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:when test="${requestScope.type == 'S'}">
                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header">
                                <h5 class="mb-0">Prenotazione visita specialistica</h5>
                            </div>
                            <div class="card-body text-center">
                                <c:choose>
                                    <c:when test="${requestScope.esito=='ok'}">
                                        <div id="visit-success">
                                            <p class="text-left">La tua prenotazione è stata presa in carico dal sistema. A momenti riceverai una email di conferma</p>
                                            
                                        </div>
                                    </c:when>
                                    <c:when test="${requestScope.esito=='err'}">
                                        <div class="alert alert-danger text-left" role="alert" id="visit-error"><span style="margin-bottom: 5px;"><strong>Errore nella prenotazione</strong><br></span>
                                            <p style="margin-bottom: 0px;">Si è verificato un errore durante la procedura di prenotazione. La invitiamo a riprovare più tardi</p>
                                        </div>
                                    </c:when>
                                </c:choose>
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/Prenotazione" class="btn btn-primary" role="button" style="margin-right: 10px;">Nuova prenotazione</a>
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/HomePaziente" class="btn btn-primary btn-lg" role="button" style="margin-right: 5px;margin-left: 5px;">Torna alla tua homepage</a>
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=visi" class="btn btn-primary" role="button" style="margin-left: 10px;margin-right: 10px;">Visualizza tutte le visite</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>
