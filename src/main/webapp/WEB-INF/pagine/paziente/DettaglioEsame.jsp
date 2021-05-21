<%-- 
    Document   : DettaglioEsame
    Created on : 11-ago-2019, 10.17.30
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
        return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
    }
</script>
<body style="background-color: #f4f4f4;">
    <jsp:include page='headerPaziente.jsp'/>
    
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-10 col-xl-8 offset-lg-1 offset-xl-2" style="padding-right: 0;padding-left: 0;">
                <div class="card shadow-sm" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h3 class="mb-0">${requestScope.exam.name}</h3>
                    </div>
                    <div class="card-body">
                        <div class="card" style="margin: -5px;margin-bottom: 15px;">
                            <div class="card-header" style="padding: 8px 15px;">
                                <h5 class="mb-0">Prescrizione</h5>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive" style="margin-bottom: -15px;">
                                    <table class="table">
                                        <thead>
                                            <tr></tr>
                                        </thead>
                                        <tbody style="margin-bottom: -8px;">
                                            <tr>
                                                <td style="width: 40%;">Prescritto da</td>
                                                <td style="width: 60%;"> ${requestScope.prescrittore} </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 40%;">Data prescrizione</td>
                                                <td style="width: 60%;"><script> document.write(FormatDate(new Date('${requestScope.data_emissione_esame}'))); </script> </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="margin: -5px;margin-bottom: 15px;">
                            <div class="card-header">
                                <h5 class="mb-0">Informazioni sull'esame</h5>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive" style="margin-bottom: -15px;">
                                    <table class="table">
                                        <tbody style="margin-bottom: -8px;">
                                            <tr>
                                                <td style="width: 40%;">Categoria esame</td>
                                                <td colspan="2" style="width: 60%;"> ${requestScope.categesame.id} - ${requestScope.categesame.name}</td>
                                            </tr>
                                            <tr>
                                                <td style="width: 40%;">Note</td>
                                                <td colspan="2" style="width: 60%;">${requestScope.descresame}</td>
                                            </tr>
                                            <tr>
                                                <td style="width: 40%;">Data di esecuzione</td>
                                                <td colspan="2" style="width: 60%;">
                                                    <c:choose>
                                                        <c:when test="${requestScope.exam.madedate==null}">
                                                            <i>Data non fissata</i>
                                                        </c:when>
                                                        <c:when test="${requestScope.exam.madedate!=null}">
                                                            <script> document.write(FormatDate(new Date('${requestScope.exam.madedate}'))); </script>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 40%;">Struttura</td>
                                                <td colspan="2" style="width: 60%;">
                                                    <c:choose>
                                                        <c:when test="${requestScope.chs==''}">
                                                            <i>Struttura da definire</i>
                                                        </c:when>
                                                        <c:when test="${requestScope.chs!=''}">
                                                            ${requestScope.chs}
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Ticket</td>
                                                <td style="width: 30%;"> ${requestScope.exam.ticket} € </td>
                                                <td class="text-center" style="width: 30%;">
                                                    <c:choose>
                                                        <c:when test="${requestScope.completa==0}">
                                                            <i>da pagare</i>
                                                        </c:when>
                                                        <c:when test="${requestScope.completa==1}">
                                                            <a href="${pageContext.servletContext.contextPath}/restricted/paziente/RicevutaTicketToPDF?idDisp=${requestScope.exam.id}" target="blank" style="text-decoration: underline;">
                                                                Visualizza ricevuta
                                                            </a>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="margin: -5px;margin-bottom: 15px;">
                            <div class="card-header">
                                <h5 class="mb-0">Esito dell'esame</h5>
                            </div>
                            <c:choose>
                                <c:when test="${requestScope.exam.result!=null}">
                                    <div class="card-body">
                                        <p class="card-text"> ${requestScope.exam.result} </p>
                                    </div>
                                </c:when>
                                <c:when test="${requestScope.exam.result==null}">
                                    <div class="card-body">
                                        <p class="card-text"> <span style='font-style: italic'>Esito non presente</span> </p>
                                    </div>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="card" style="margin: -5px;margin-bottom: 0;">
                            <div class="card-header">
                                <h5 class="mb-0">Report</h5>
                            </div>
                            <div class="card-body">
                                <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioEsameToPDF?examId=${requestScope.exam.id}" target="blank">
                                    <div class="media"><img src="../../assetsPazienti/img/pdf.png" class="mr-3 align-self-center" style="width: 40px;cursor: pointer;">
                                        <div class="media-body">
                                            <p style="margin-bottom: 0;padding: 10px;">Download dei dettagli dell'esame in formato PDF</p>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../../assetsPazienti/js/script.min.js"></script>
</body>

</html>
