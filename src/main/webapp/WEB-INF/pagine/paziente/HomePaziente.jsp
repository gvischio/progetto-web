<%-- 
    Document   : HomePaziente
    Created on : 11-ago-2019, 9.40.33
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
    
    <div class="container container-main" style="margin-bottom: 10px;max-width: 2000px !important;width: 95% !important;">
        <div class="row">
            <div class="col-md-12 col-lg-4 col-xl-3">
                <div class="card border rounded shadow-sm">
                    <div class="card-header">
                                                
                             <h3 class="text-center mb-0"> ${requestScope.paziente.firstname} ${requestScope.paziente.lastname}</h3>
                    </div>
                    <div class="card-body" style="padding: 0;padding-right: 10px;padding-left: 10px;">
                        <div class="row">
                            <div class="col-12 col-sm-4 col-md-3 col-lg-12 col-xl-12 text-center">
                                <img class="rounded border rounded-circle border-dark align-self-center" src="${requestScope.element.path}" style="width: 100px;height: 100px;margin: 20px;border-width: 2px !important;">
                            </div>
                            <div class="col" style="padding-top: 10px;">
                                <div class="table-responsive">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/"><strong>Codice Fiscale</strong></td>
                                                <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/"> ${requestScope.paziente.ssn} </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Nato il</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;"><script>document.write(FormatDate(new Date('${requestScope.paziente.birthday}')));</script> </td>

                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Luogo nascita</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;"> ${requestScope.paziente.birthplace}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Sesso</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;"> <a> ${requestScope.paziente.sex} </a></td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Provincia</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;"> ${requestScope.district.city} </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <p class="text-right" style="padding-right: 10px;">
                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/ImpostazioniPaziente" style="text-decoration: underline">Impostazioni profilo</a>
                                </p>
                                <c:if test="${requestScope.dottore > 0}">
                                    <p class="text-right" style="padding-right: 10px;">
                                        <a class="btn btn-primary text-center" role="button" href="${pageContext.servletContext.contextPath}/restricted/dottore/HomeDottore" style="margin: 0px auto;margin-bottom: 5px;">
                                            Vai al tuo profilo professionale
                                        </a>
                                    </p>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card border rounded shadow-sm" style="margin-top: 10px;margin-bottom: 15px;">
                    <div class="card-body text-center" style="margin-top: 0px;padding: 15px;">
                        <style>
                            div.fast-action {
                                margin: 10px; padding: 15px; height: 120px; width: 120px; background-color: #007bff;
                                border-radius: 25px !important; font-size: 13px; color: white; margin-bottom: 0px; cursor: pointer;
                            }
                            
                            div.fast-action > img {
                                width: 40px; height: 40px; margin-top: 10px;
                            }
                            
                            div.fast-action > p {
                                margin-bottom: 0px; margin-top: 3px;
                            }
                        </style>
                        <h5 style="height: 24px;">Operazioni</h5>
                        
                        <a href="${pageContext.servletContext.contextPath}/restricted/paziente/Prenotazione">
                            <div class="text-center border rounded shadow-sm d-inline-block fast-action">
                                <img src="../../assetsPazienti/img/schedule.png"/>
                                <p>prenotazioni <br> visite - esami</p>
                            </div>
                        </a>
                            
                        <a href="${pageContext.servletContext.contextPath}/restricted/paziente/InfoMedicoPerPaziente">
                            <div class="text-center border rounded shadow-sm d-inline-block fast-action">
                                <img src="../../assetsPazienti/img/prenota%20visita.png"/>
                                <p>prenota visita dal tuo medico</p>
                            </div>
                        </a>

                        <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaTicket">
                            <div class="text-center border rounded shadow-sm d-inline-block fast-action">
                                <img src="../../assetsPazienti/img/ticket.png"/>
                                <p>visualizza ticket pagati</p>
                            </div>
                        </a>
                            
                        <a href="${pageContext.servletContext.contextPath}/restricted/paziente/InformazioniEsami">
                            <div class="text-center border rounded shadow-sm d-inline-block fast-action">
                                <img src="../../assetsPazienti/img/cerca%20esame.png"/>
                                <p>consulta lista esami</p>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <div class="col">
                        <div class="card border rounded shadow-sm">
                            <div class="card-body shadow-sm">
                                <h5>Overview</h5>
                                <div class="table-responsive-lg">
                                    <table class="table">
                                        <tbody style="/*padding: 10px;*/">
                                            <tr>
                                                <td style="padding: 8px;vertical-align: middle;"><strong>Medico di base</strong><br></td>
                                                <td style="padding: 8px;vertical-align: middle;"> dott. ${requestScope.familyDoctor.firstname} ${requestScope.familyDoctor.lastname} </td>
                                                <td class="text-center" style="padding: 8px;vertical-align: middle;">
                                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/InfoMedicoPerPaziente">
                                                        <span style="text-decoration: underline;">informazioni</span>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;vertical-align: middle;"><strong>Data ultima visita</strong></td>
                                                <c:choose>
                                                    <c:when test="${empty requestScope.ultimaVisita}">
                                                        <td style="padding: 8px;vertical-align: middle;" colspan="2"> nessuna informazione</td>
                                                    </c:when>
                                                    <c:when test="${not empty requestScope.ultimaVisita}">
                                                        <td style="padding: 8px;vertical-align: middle;"> 
                                                            <script>document.write(FormatDate(new Date('${requestScope.ultimaVisita.visdate}')));</script> 
                                                        </td>
                                                        <td class="text-center" style="padding: 8px;vertical-align: middle;">
                                                            <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioVisitaBase?visitaId=${requestScope.ultimaVisita.id}"><span style="text-decoration: underline;">visualizza</span></a>
                                                        </td> 
                                                    </c:when>
                                                </c:choose>                                        
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;vertical-align: middle;"><strong>Prossima visita</strong></td>
                                                <c:choose>
                                                    <c:when test="${empty requestScope.prossimaVisita}">
                                                        <td style="padding: 8px;vertical-align: middle;"> <span> Nessuna visita prenotata </span> </td>
                                                        <td class="text-center">
                                                            <a href="${pageContext.servletContext.contextPath}/restricted/paziente/InfoMedicoPerPaziente" style="text-decoration: underline">
                                                                prenota
                                                            </a>
                                                        </td>
                                                    </c:when>
                                                    <c:when test="${not empty requestScope.prossimaVisita}">
                                                        <td style="padding: 8px;vertical-align: middle;"> 
                                                            <script>document.write(FormatDate(new Date('${requestScope.prossimaVisita.visdate}')));</script> 
                                                        </td>
                                                        <td class="text-center" style="padding: 8px;vertical-align: middle;">
                                                            <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioVisitaBase?visitaId=${requestScope.prossimaVisita.id}">
                                                                <span style="text-decoration: underline;">visualizza</span>
                                                            </a>
                                                        </td> 
                                                    </c:when>
                                                </c:choose>                                                                                                                                           
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;vertical-align: middle;"><strong>Esami prenotati</strong></td>
                                                <td style="padding: 8px;vertical-align: middle;">${requestScope.exams.size()}</td>
                                                <td class="text-center" style="padding: 8px;vertical-align: middle;">
                                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=exam&show=active">
                                                        <span style="text-decoration: underline;">visualizza</span>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;vertical-align: middle;"><strong>Ricette attive</strong></td>
                                                <td style="padding: 8px;vertical-align: middle;">${requestScope.prescriptions.size()}</td>
                                                <td class="text-center" style="padding: 8px;vertical-align: middle;">
                                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=pres&show=active">
                                                        <span style="text-decoration: underline;">visualizza</span>
                                                    </a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;vertical-align: middle;"><strong>Visite specialistiche da fare</strong></td>
                                                <td style="padding: 8px;vertical-align: middle;">${requestScope.speckvisits.size()}</td>
                                                <td class="text-center" style="padding: 8px;vertical-align: middle;">
                                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=visi&show=active">
                                                        <span style="text-decoration: underline;">visualizza</span>
                                                    </a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="card border rounded shadow-sm" style="margin-top: 10px;">
                            <div class="card-header" style="cursor: pointer" data-toggle="tooltip" title="comprimi" onclick="action_ricette(document.getElementById('espandi-ricette'))">
                                <img src="../../assetsPazienti/img/comprimi.png" class="d-inline-block" id="espandi-ricette" style="width: 25px;margin-left: 0px;margin-right: 18px;margin-top: -5px;" />
                                <h5 class="d-inline-block mb-0">Ricette attive</h5>
                            </div>
                            <div class="card-body" id="box-ricette" >
                                <c:choose>
                                    <c:when test="${empty requestScope.prescriptions}">
                                        <div style="margin-bottom: 0px; padding: 8px">
                                            <span style="font-style: italic"> Nessuna ricetta attiva</span>
                                        </div>   
                                    </c:when>
                                    <c:when test="${!empty requestScope.prescriptions}">
                                        <c:forEach items="${requestScope.prescriptions}" var="pres">
                                            <div class="alert alert-primary" role="alert" style="margin-bottom: 12px;">
                                                <div class="row">
                                                    <div class="col-lg-3 col-xl-2">
                                                        <p class="text-center" style="margin-bottom: 0px;">attiva</p>
                                                    </div>
                                                    <div class="col">
                                                        <p style="margin-bottom: 0px;"><strong> ${pres.getName()} </strong></p>
                                                    </div>
                                                    <div class="col-lg-3 col-xl-2">
                                                        <p style="margin-bottom: 0px;">
                                                            <a href="${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioRicetta?prescriptionId=${pres.getId()}">
                                                                <span style="text-decoration: underline;">dettagli</span>
                                                            </a>
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>                                       
                                <p class="text-right" style="margin-bottom: 0px;margin-top: 5px;padding-right: 20px;">
                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=pres">
                                        <span style="text-decoration: underline;">visualizza altro</span>
                                    </a>
                                </p>
                            </div>
                        </div>
                        <div class="card border rounded shadow-sm" style="margin-top: 10px;">
                            <div class="card-header" style="cursor: pointer" data-toggle="tooltip" title="espandi" onclick="action_esami(document.getElementById('espandi-esami'))">
                                <img id="espandi-esami" src="../../assetsPazienti/img/espandi.png" class="d-inline-block" style="width: 25px;margin-left: 0px;margin-right: 18px;margin-top: -5px;"/>
                                <h5 class="d-inline-block mb-0">Esami da fare</h5>
                            </div>
                            <div class="card-body" id="box-esami" style="display: none;">
                                <c:choose>
                                    <c:when test="${empty requestScope.exams}">
                                        <div style="margin-bottom: 0px; padding: 8px">
                                            <span style="font-style: italic"> Nessun esame da fare </span>
                                        </div>   
                                    </c:when>
                                    <c:when test="${not empty requestScope.exams}">
                                        <c:forEach begin="0" end="${requestScope.exams.size()-1}" var="current">
                                            <div class="alert alert-primary" role="alert" style="margin-bottom: 12px;">
                                                <div class="row">
                                                    <div class="col-lg-3 col-xl-2">
                                                        <p class="text-center" style="margin-bottom: 0px;">
                                                            <c:choose>
                                                                <c:when test="${requestScope.exams.get(current).getMadedate()==null}">
                                                                    <i>da prenotare</i>
                                                                </c:when>
                                                                <c:when test="${requestScope.exams.get(current).getMadedate()!=null}">
                                                                    <script> document.write(FormatDate(new Date('${requestScope.exams.get(current).getMadedate()}'))); </script>
                                                                </c:when>
                                                            </c:choose>
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
                                    </c:when>
                                </c:choose>
                                <p class="text-right" style="margin-bottom: 0px;margin-top: 5px;padding-right: 20px;">
                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=exam">
                                        <span style="text-decoration: underline;">visualizza altro</span>
                                    </a>
                                </p>
                            </div>
                        </div>
                        <div class="card border rounded shadow-sm" style="margin-top: 10px;">
                            <div class="card-header" style="cursor: pointer" data-toggle="tooltip" title="espandi" onclick="action_visite(document.getElementById('espandi-visite'))">
                                <img id="espandi-visite" src="../../assetsPazienti/img/espandi.png" class="d-inline-block" style="width: 25px;margin-left: 0px;margin-right: 18px;margin-top: -5px;" />
                                <h5 class="d-inline-block mb-0">Visite da fare</h5>
                            </div>
                            <div class="card-body" id="box-visite" style="display: none;"> 
                                <c:choose>
                                    <c:when test="${empty requestScope.speckvisits}">
                                        <div style="margin-bottom: 0px; padding: 8px">
                                            <span style="font-style: italic"> Nessuna visita da fare </span>
                                        </div>   
                                    </c:when>
                                    <c:when test="${not empty requestScope.speckvisits}">
                                        <c:forEach begin="0" end="${requestScope.speckvisits.size()-1}" var="current">
                                            <div class="alert alert-primary" role="alert" style="margin-bottom: 12px;">
                                                <div class="row">
                                                    <div class="col-lg-3 col-xl-2">
                                                        <p class="text-center" style="margin-bottom: 0px;">
                                                            <c:choose>
                                                                <c:when test="${requestScope.speckvisits.get(current).getMadedate()==null}">
                                                                    <i>da prenotare</i>
                                                                </c:when>
                                                                <c:when test="${requestScope.speckvisits.get(current).getMadedate()!=null}">
                                                                    <script> document.write(FormatDate(new Date('${requestScope.speckvisits.get(current).getMadedate()}'))); </script>
                                                                </c:when>
                                                            </c:choose>
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
                                    </c:when> 
                                </c:choose>
                                <p class="text-right" style="margin-bottom: 0px;margin-top: 5px;padding-right: 20px;">
                                    <a href="${pageContext.servletContext.contextPath}/restricted/paziente/VisualizzaLista?type=visi">
                                        <span style="text-decoration: underline;">visualizza altro</span>
                                    </a>
                                </p>
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