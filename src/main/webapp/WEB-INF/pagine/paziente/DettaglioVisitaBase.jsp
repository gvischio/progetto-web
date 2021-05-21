<%-- 
    Document   : DettaglioVisitaBase
    Created on : 11-ago-2019, 12.06.24
    Author     : simmf
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/WEB-INF/pagine/error.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Servizio Sanitario</title>
    <link rel="icon" href="../../assetsHome/img/favicon.ico">
    <link rel="shortcut icon" href="../../assetsHome/img/favicon.ico">
    <link rel="stylesheet" href="../../assetsPazienti/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../../assetsPazienti/css/styles.min.css">
</head>
<script>
    function FormatDate(xxdata)
    {
        var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
        var monthIndex = xxdata.getMonth();
        if(isNaN(monthIndex)) return "<i>da prenotare</i>";
        return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
    }
</script>
<body style="background-color: #f4f4f4;">
    <jsp:include page='headerPaziente.jsp'/>
    
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-10 col-xl-8 offset-lg-1 offset-xl-2" style="padding-right: 0;padding-left: 0;">
                <div class="card shadow-sm">
                    <div class="card-header" style="background-color: rgba(0,0,0,0.03);">
                        <h3 class="mb-0">Visita del <script> document.write(FormatDate(new Date('${requestScope.visita.visdate}'))); </script></h3>
                    </div>
                    <div class="card-body">
                        <div class="card" style="margin: -5px;margin-bottom: 15px;">
                            <div class="card-header" style="padding: 8px 15px;">
                                <h5 class="mb-0">Dettagli della visita</h5>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive" style="margin-bottom: -15px;">
                                    <table class="table">
                                        <tbody style="margin-bottom: -8px;">
                                            <tr>
                                                <td style="width: 40%;">Data visita</td>
                                                <td style="width: 60%;"><script> document.write(FormatDate(new Date('${requestScope.visita.visdate}'))); </script> </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 40%;">Eseguito da</td>
                                                <td style="width: 60%;">${requestScope.prescrittore}</td>
                                            </tr>
                                            <tr>
                                                <td>Motivazione della prenotazione</td>
                                                <c:choose>
                                                    <c:when test="${empty requestScope.visita.motivation}">
                                                        <td> <span style='font-style: italic'>Motivazione non presente</span </td>
                                                    </c:when>
                                                    <c:when test="${!empty requestScope.visita.motivation}">
                                                        <td> ${requestScope.visita.motivation} </td>
                                                    </c:when>
                                                </c:choose>                                                 
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>                    
                        <div class="card" style="margin: -5px;margin-bottom: 15px;">
                            <div class="card-header">
                                <h5 class="mb-0">Anamnesi della visita</h5>
                            </div>
                            <c:choose>
                                <c:when test="${!empty requestScope.visita.doctorsays}">
                                    <div class="card-body">
                                        <p class="card-text">${requestScope.visita.doctorsays}</p>
                                    </div>
                                </c:when>
                                <c:when test="${empty requestScope.visita.doctorsays}">
                                    <div class="card-body">
                                        <p class="card-text"><span style='font-style: italic'>Anamnesi non presente</span></p>
                                    </div>
                                </c:when>
                            </c:choose> 
                        </div>
                        
                        <div class="card" style="margin: -5px;margin-bottom: 15px;">
                            <div class="card-header">
                                <h5 class="mb-0">Prescrizioni della visita</h5>
                            </div>
                            <div class="card-body">
                                <c:if test="${!empty requestScope.prescriptions}">
                                        <c:forEach begin="0" end="${requestScope.prescriptions.size()-1}" var="current">
                                            <div class="alert alert-primary" role="alert" style="margin-bottom: 12px;">
                                                <div class="row">
                                                    <div class="col-lg-3 col-xl-2">
                                                        <c:if test="${requestScope.prescriptions.get(current).getActive()==1}">
                                                            <p class="text-center" style="margin-bottom: 0px;"> Attiva </p>
                                                        </c:if>
                                                        <c:if test="${requestScope.prescriptions.get(current).getActive()==0}">
                                                            <p class="text-center" style="margin-bottom: 0px;">
                                                                <script> document.write(FormatDate(new Date('${requestScope.prescriptions.get(current).getMadedate()}'))); </script>
                                                            </p>
                                                        </c:if>
                                                    </div>
                                                    <div class="col">
                                                        <p style="margin-bottom: 0px;"><strong> ${requestScope.prescriptions.get(current).getName()} </strong></p>
                                                    </div>
                                                    <div class="col-lg-3 col-xl-2">
                                                        <p style="margin-bottom: 0px;">
                                                            <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioRicetta?prescriptionId=${requestScope.prescriptions.get(current).getId()}">
                                                                <span style="text-decoration: underline;">dettagli</span>
                                                            </a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                </c:if>              
                                <c:if test="${!empty requestScope.exams}">
                                    <c:forEach begin="0" end="${requestScope.exams.size()-1}" var="current">
                                        <div class="alert alert-primary" role="alert" style="margin-bottom: 12px;">
                                            <div class="row">
                                                <div class="col-lg-3 col-xl-2">
                                                    <p class="text-center" style="margin-bottom: 0px;">
                                                        <script> document.write(FormatDate(new Date('${requestScope.exams.get(current).getMadedate()}'))); </script>
                                                    </p>
                                                </div>
                                                <div class="col">
                                                    <p style="margin-bottom: 0px;"><strong> ${requestScope.exams.get(current).getName()} </strong></p>
                                                </div>
                                                <div class="col-lg-3 col-xl-2">
                                                    <p style="margin-bottom: 0px;">
                                                        <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioEsame?examId=${requestScope.exams.get(current).getId()}">
                                                            <span style="text-decoration: underline;">dettagli</span>
                                                        </a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${!empty requestScope.speckvisits}">
                                    <c:forEach begin="0" end="${requestScope.speckvisits.size()-1}" var="current">
                                        <div class="alert alert-primary" role="alert" style="margin-bottom: 12px;">
                                            <div class="row">
                                                <div class="col-lg-3 col-xl-2">
                                                    <p class="text-center" style="margin-bottom: 0px;">
                                                        <script> document.write(FormatDate(new Date('${requestScope.speckvisits.get(current).getMadedate()}'))); </script>
                                                    </p>
                                                </div>
                                                <div class="col">
                                                    <p style="margin-bottom: 0px;"><strong>  ${requestScope.speckvisits.get(current).getName()}   </strong></p>
                                                </div>
                                                <div class="col-lg-3 col-xl-2">
                                                    <p style="margin-bottom: 0px;">
                                                        <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioVisitaSpeck?speckId=${requestScope.speckvisits.get(current).getId()}">
                                                            <span style="text-decoration: underline;">dettagli</span>
                                                        </a>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>  
                                </c:if>
                                <c:if test="${empty requestScope.prescriptions}">     <!--QUESTO è IL CODICE GIUSTO NON CANCELLARLO, AL MASSIMO CAMBIA GLI HREF" -->                               
                                    <c:if test="${empty requestScope.exams}">    
                                        <c:if test="${empty requestScope.speckvisits}">
                                            <span style="font-style: italic">Nessuna prescrizione registrata</span>
                                        </c:if>
                                    </c:if>
                                </c:if>
                            </div>
                    </div>            
                        <div class="card" style="margin: -5px;margin-bottom: 0;">
                            <div class="card-header">
                                <h5 class="mb-0">Report</h5>
                            </div>                            
                            <div class="card-body">
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioVisitaBaseToPDF?visitaId=${requestScope.visita.id}" target="blank">
                                    <div class="media"><img src="../../assetsPazienti/img/pdf.png" class="mr-3 align-self-center" style="width: 40px;cursor: pointer;">
                                        <div class="media-body">
                                            <p style="margin-bottom: 0;padding: 10px;">Download dei dettagli della visita in formato PDF  </p>
                                        </div>
                                    </div>
                                </a>
                            </div>                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <script src="../../assetsPazienti/js/script.min.js"></script>
</body>

</html>
