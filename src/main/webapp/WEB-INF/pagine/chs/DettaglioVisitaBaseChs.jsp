<%-- 
    Document   : DettaglioVisitaBaseChs
    Created on : 21-ago-2019, 16.57.17
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

<body style="background-color: #f4f4f4;">
    <jsp:include page="headerChs.jsp"/>
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-10 col-xl-10 offset-lg-1 offset-xl-1" style="padding-right: 0;padding-left: 0;">
                <div class="card shadow-sm">
                    <div class="card-header" style="background-color: rgba(0,0,0,0.03);">
                        <h3 class="mb-0">Visita del ${requestScope.visit.visdate} </h3>
                    </div>
                    <div class="card-body">
                        <h5 class="card-title mb-0">Dettagli della visita</h5>
                        <div class="table-responsive table-dettagli" style="/*margin-bottom: -15px;*/">
                            <table class="table">
                                <thead>
                                    <tr></tr>
                                </thead>
                                <tbody style="margin-bottom: -8px;">
                                    <tr>
                                        <td>Paziente</td>
                                        <td> ${requestScope.paziente.firstname} ${requestScope.paziente.lastname} </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;">Data visita</td>
                                        <td style="width: 60%;"> ${requestScope.visit.visdate} </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;">Medico di base</td>
                                        <td style="width: 60%;"> ${requestScope.prescrittore} </td>
                                    </tr>
                                    <tr>
                                        <td>Motivazione della prenotazione</td>
                                        <c:if test="${requestScope.visit.motivation!=null}"> <td colspan="2" style="width: 60%;"> ${requestScope.visit.motivation} </td> </c:if>
                                        <c:if test="${requestScope.visit.motivation==null}"> <td colspan="2" style="width: 60%;"> <span style='font-style: italic'>Motivazione non presente </span></td> </c:if>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <h5 class="card-title mb-0">Anamnesi della visita</h5>
                        <c:choose>
                            <c:when test="${!empty requestScope.visita.doctorsays}">
                                    <p class="card-text">${requestScope.visita.doctorsays}</p>
                            </c:when>
                            <c:when test="${empty requestScope.visita.doctorsays}">
                                    <p class="card-text"><span style='font-style: italic'>Anamnesi non presente</span></p>
                            </c:when>
                        </c:choose> 
                        <h5 class="card-title mb-0">Prescrizioni</h5>
                        <div class="table-responsive table-dettagli" style="margin-bottom: -16px;">
                            <table class="table">
                                <c:if test="${!empty requestScope.tipo}">
                                    <thead>
                                        <tr>
                                            <th>Tipo</th>
                                            <th>Data esecuzione</th>
                                            <th>Nome</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach begin="0" end="${requestScope.tipo.size()-1}" var="i">
                                        <tr>
                                            <td> ${requestScope.tipo.get(i)} </td>
                                            <td> ${requestScope.data.get(i)} </td>
                                            <td> ${requestScope.nome.get(i)} </td>
                                            <c:if test="${requestScope.tipo.get(current)== 'Ricetta'}">
                                                <td class="text-center"><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioRicettaChs?prescriptionId=${requestScope.ids.get(current)}"><span style="text-decoration: underline;">dettagli</span></a></td>
                                            </c:if>
                                            <c:if test="${requestScope.tipo.get(current)=='Esame'}">
                                                <td class="text-center"><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioEsameChs?examId=${requestScope.ids.get(current)}"><span style="text-decoration: underline;">dettagli</span></a></td>
                                            </c:if>
                                            <c:if test="${requestScope.tipo.get(current)=='Visita Specialistica'}">
                                                <td class="text-center"><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioVisitaSpeckChs?speckId=${requestScope.ids.get(current)}"><span style="text-decoration: underline;">dettagli</span></a></td>
                                            </c:if>                                                
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty requestScope.tipo}">
                                    <span style='font-style: italic'>Nessuna prescrizione presente</span>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                        <a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioVisitaBaseChsToPDF?visitId=${requestScope.visit.id}" target="blank">
                            <div class="media"><img src="../../assetsPazienti/img/pdf.png" class="mr-3 align-self-center" style="width: 40px;cursor: pointer;">
                                <div class="media-body">
                                    <p style="margin-bottom: 0;padding: 10px;">Download dei dettagli dell'esame in formato PDF</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>