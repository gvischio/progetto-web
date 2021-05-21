<%-- 
    Document   : Prenotazione
    Created on : 5-set-2019, 18.30.40
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
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>
<script>
       
</script>
<body style="background-color: #f4f4f4;">
    <jsp:include page='headerPaziente.jsp'/>
    
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-8 col-xl-12 align-self-center">
                <h2 class="text-left" style="margin-bottom: 12px;">Pagina di prenotazione</h2>
            </div>
        </div>
        
        <c:choose>
            <c:when test="${requestScope.hasenso>0}">
                <jsp:include page='PrenotazioneContent.jsp'/>
            </c:when>
            <c:when test="${requestScope.hasenso<0}">
                <div class="row">
                    <div class="col">
                        <div class="alert alert-success" role="alert"><span style="font-size: 20px;margin-bottom: 5px;"><strong>Nessuna prescrizione prenotabile</strong></span>
                            <p style="margin-bottom: 0px;">Hai già una prenotazione per tutte le tue prescrizioni attive!</p>
                        </div><a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}/restricted/paziente/HomePaziente">Torna alla home</a></div>
                </div>
            </c:when>
        </c:choose>
        
    </div>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>
