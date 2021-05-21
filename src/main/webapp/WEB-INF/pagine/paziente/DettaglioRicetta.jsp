<%-- 
    Document   : DettaglioRicetta
    Created on : 11-ago-2019, 11.19.39
    Author     : simmf
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
                <div class="card shadow-sm">
                    <div class="card-header">
                        <h3 class="mb-0">Ricetta</h3>
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
                                                <td style="width: 60%;">dott. ${requestScope.dottore.firstname} ${requestScope.dottore.lastname} </td>
                                            </tr>
                                            <tr>
                                                <td style="width: 40%;">Data di emissione</td>
                                                <td style="width: 60%;"><script> document.write(FormatDate(new Date('${requestScope.data_emissione_prescrizione}'))); </script></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="card" style="margin: -5px;margin-bottom: 15px;">
                            <div class="card-header">
                                <h5 class="mb-0">Dettagli ricetta</h5>
                            </div>
                            <div class="card-body">
                                <div class="media">
                                    <div id="qrcode" class="mr-3 align-self-center" style="width: 150px; height: 150px">
                                        
                                    </div>
                                    <div class="media-body">
                                        <div class="table-responsive" style="margin-bottom: 5px;">
                                            <table class="table">
                                                <thead>
                                                    <tr></tr>
                                                </thead>
                                                <tbody style="margin-bottom: -8px;">
                                                    <tr>
                                                        <td style="width: 40%;">Farmaco prescritto</td>
                                                        <td style="width: 60%;"> ${requestScope.nomefarmaco} </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 40%;">Quantità</td>
                                                        <td style="width: 60%;"> ${requestScope.prescription.quantity} </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 40%;">Ricetta attiva</td>
                                                        <td style="width: 60%;">
                                                            <c:choose>
                                                                <c:when test="${requestScope.prescription.active==1}">
                                                                    Sì
                                                                </c:when>
                                                                <c:when test="${requestScope.prescription.active==0}">
                                                                    No
                                                                </c:when>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioRicettaToPDF?prescriptionId=${requestScope.prescription.id}" target="blank">
                                            <div class="media">
                                                <img src="../../assetsPazienti/img/pdf.png"  class="mr-3 align-self-center" style="width: 40px;cursor: pointer;">
                                                <div class="media-body">
                                                    <span style="margin-bottom: 0;padding: 10px;">Download della ricetta in formato PDF</span>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <c:if test="${requestScope.prescription.active==0}">
                        <div class="card" style="margin: -5px;margin-bottom: 0;">
                            <div class="card-header">
                                <h5 class="mb-0">Evasione della ricetta</h5>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive" style="margin-bottom: -15px;">
                                    <table class="table">
                                        <tbody style="margin-bottom: -8px;">
                                            <tr>
                                                <td style="width: 40%;">Data di evasione</td>
                                                <td colspan="2" style="width: 60%;"> <script> document.write(FormatDate(new Date('${requestScope.prescription.madedate}'))); </script></td>
                                            </tr>
                                            <tr>
                                                <td style="width: 40%;">Farmacia</td>
                                                <td colspan="2" style="width: 60%;">${requestScope.farmacia.city}</td>
                                            </tr>
                                            <tr>
                                                <td>Codice farmacia</td>
                                                <td> ${requestScope.farmacia.piva} </td>
                                            </tr>
                                            <tr>
                                                <td>Ticket</td>
                                                <td style="width: 30%;"> ${requestScope.prescription.ticket} €</td>
                                                <td class="text-center" style="width: 30%;">
                                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/RicevutaTicketToPDF?idDisp=${requestScope.prescription.id}" target="blank" style="text-decoration: underline;">
                                                        Visualizza ricevuta
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>                          
                                    </table>
                                </div>
                            </div>
                        </div>
                    </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../../assetsPazienti/js/script.min.js"></script>
    <script src="../../assetsPazienti/js/jquery.qrcode.min.js"></script>
    
    <script>
        $("#qrcode").qrcode({text: "Codice univoco ricetta: ${requestScope.prescription.id}\n" +
                            "Farmaco prescritto: ${requestScope.prescription.name}\n" +
                            "Data prescrizione: ${requestScope.data_emissione_prescrizione}\n" +
                            "Codice fiscale paziente: ${requestScope.codicefiscale}",
                            width: 150, height: 150 });
    </script>
</body>

</html>
