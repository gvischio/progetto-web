<%-- 
    Document   : DettaglioEsameDottore
    Created on : 21-ago-2019, 16.42.25
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
    <link rel="stylesheet" href="../../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>
<script>
    function FormatDate(xxdata)
    {
        var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
        var monthIndex = xxdata.getMonth();
        if(isNaN(monthIndex)) return "<i> il testo che vuoi </i>";
        return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
    }
</script>
<body style="background-color: #f4f4f4;">
        <jsp:include page="headerDottore.jsp"/>
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-10 col-xl-8 offset-lg-1 offset-xl-2" style="padding-right: 0;padding-left: 0;">
                <div class="card shadow-sm" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h3 class="mb-0"> ${requestScope.exam.name} </h3>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title mb-0">Prescrizione</h5>
                        <div class="table-responsive table-dettagli">
                            <table class="table">
                                <tbody style="margin-bottom: -8px;">
                                    <tr>
                                        <td>Paziente</td>
                                        <td> ${requestScope.paziente.firstname} ${requestScope.paziente.lastname} </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;">Prescritto da</td>
                                        <td style="width: 60%;"> ${requestScope.prescrittore} </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;">Data prescrizione</td>
                                        <td style="width: 60%;"> ${requestScope.visit.visdate} </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <h5 class="card-title mb-0">Informazioni sull'esame</h5>
                        <div class="table-responsive table-dettagli">
                            <table class="table">
                                <thead>
                                    <tr></tr>
                                </thead>
                                <tbody style="margin-bottom: -8px;">
                                    <tr>
                                        <td style="width: 40%;">Categoria esame</td>
                                        <td colspan="2" style="width: 60%;"> ${requestScope.category.name} </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;">Note</td>
                                        <c:if test="${requestScope.listaesami.description!=null}"> <td colspan="2" style="width: 60%;"> <script>document.write(FormatDate(new Date('${requestScope.listaesami.description}')));</script> </td> </c:if>
                                        <c:if test="${requestScope.listaesami.description==null}"> <td colspan="2" style="width: 60%;"> <span style='font-style: italic'>Descrizione non presente </span></td> </c:if>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;">Data di esecuzione</td>
                                        <c:if test="${requestScope.exam.madedate!=null}"> <td colspan="2" style="width: 60%;"> ${requestScope.exam.madedate} </td> </c:if>
                                        <c:if test="${requestScope.exam.madedate==null}"> <td colspan="2" style="width: 60%;"> <span style='font-style: italic'>Data non presente </span></td> </c:if>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;">Struttura</td>
                                        <c:if test="${requestScope.exam.providedby!=1}"> <td colspan="2" style="width: 60%;"> ${requestScope.chs.name} </td> </c:if>
                                        <c:if test="${requestScope.exam.providedby==1}"> <td colspan="2" style="width: 60%;"> <span style='font-style: italic'>Strutttura non presente </span></td> </c:if>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <h5 class="card-title mb-0">Esito dell'esame</h5>
                        <c:if test="${requestScope.exam.result!=null}"> <%System.out.println("entra nel primo if");%><td colspan="2" style="width: 60%;"> ${requestScope.exam.result} </td> </c:if>
                        <c:if test="${requestScope.exam.result==null}"> <td colspan="2" style="width: 60%;"> <%System.out.println("entra nel secondo if");%><span style='font-style: italic'>Esito non presente </span></td> </td> </c:if>
                        <a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioEsameDottoreToPDF?examId=${requestScope.exam.id}" target="blank">
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
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>