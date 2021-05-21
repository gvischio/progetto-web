<%-- 
    Document   : DettaglioPaziente
    Created on : 21-ago-2019, 16.43.44
    Author     : simmf
--%>

<%@page import="it.unitn.disi.wp.servizioSanitario.entities.Visit"%>
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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>
<script>
    var lista_elem = [];
    var hasenso = false;
    <c:if test="${requestScope.userpic.size()>0}">
        hasenso = true;
        <c:forEach begin = "0" end="${requestScope.userpic.size()-1}" var="current">
            lista_elem[${current}] = "${requestScope.userpic.get(current).path}";
        </c:forEach>
    </c:if>
        
    var current = 0;
        
    function get() {
        return current;
    }
    
    function avanza() {
        if(current === ${requestScope.userpic.size()-1})
            current = 0;
        else
            current++;
        document.getElementById("photoimg").src = lista_elem[current];
    }
    
    function arretra() {
        if(current === 0) 
            current = ${requestScope.userpic.size()-1};
        else
            current--;
        document.getElementById("photoimg").src = lista_elem[current];
    }
    
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
            <div class="col">
                <div class="card">
                    <div class="card-header" style="padding: 10px 16px;">
                        <h3 class="mb-0"> ${requestScope.paziente.firstname} ${requestScope.paziente.lastname} </h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-lg-3 col-xl-2" style="padding: 0px 5px;margin-bottom: 10px;">
                                <ul class="nav nav-tabs flex-lg-column nav-pills" style="border-top: 1px solid #dee2e6;padding-top: 4px;padding-bottom: 4px;">
                                    <li class="nav-item"><a class="nav-link active" href="#tab-1" role="tab" data-toggle="tab">Scheda anagrafica</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#tab-2" role="tab" data-toggle="tab">Visite dal medico di base</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#tab-3" role="tab" data-toggle="tab">Ricette</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#tab-4" role="tab" data-toggle="tab">Esami</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#tab-5" role="tab" data-toggle="tab">Visite specialistiche</a></li>
                                </ul>
                            </div>
                            <div class="col">
                                <div class="card shadow-sm">
                                    <div class="card-body">
                                        <div>
                                            <ul class="nav nav-tabs" style="height: 0;display: none;">
                                                <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#tab-1">Tab 1</a></li>
                                                <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-2">Tab 2</a></li>
                                                <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-3">Tab 3</a></li>
                                                <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-5">Tab</a></li>
                                                <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#tab-4">Tab</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" role="tabpanel" id="tab-1">
                                                    <h4 style="border-bottom: #999 solid 1px;padding-bottom: 5px;">Scheda anagrafica</h4>
                                                    <div class="row" style="margin: 0px;">
                                                        <div class="col-12 col-sm-12 col-md-12 col-lg-5 col-xl-4 text-center align-self-center" style="padding: 0px;">
                                                            <img src="../../assetsPazienti/img/precedente.png" style="width: 25px;cursor: pointer;" onclick="arretra()">
                                                            <a href="${requestScope.userpic.get(0).path}">
                                                                <img id="photoimg" class="rounded border rounded-circle border-dark align-self-center" src="${requestScope.userpic.get(0).path}" style="width: 150px;height: 150px;border-width: 2px !important;/*vertical-align: middle;*/margin: 10px;">
                                                            </a>
                                                            <img src="../../assetsPazienti/img/prossimo.png" style="width: 25px;cursor: pointer;" onclick="avanza()">
                                                        </div>
                                                        <div class="col-lg-7 col-xl-8" style="padding-top: 10px;margin-top: 0px;">
                                                            <div class="table-responsive">
                                                                <table class="table">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td><strong>Nome</strong></td>
                                                                            <td>${requestScope.paziente.firstname}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><strong>Cognome</strong></td>
                                                                            <td>${requestScope.paziente.lastname}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;width: 40%;"><strong>Codice Fiscale</strong></td>
                                                                            <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.paziente.ssn}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Nato il</strong></td>
                                                                            <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;"><script>document.write(FormatDate(new Date('${requestScope.paziente.birthday}')));</script> </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Luogo nascita</strong></td>
                                                                            <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.paziente.birthplace}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Sesso</strong></td>
                                                                            <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.paziente.sex}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Provincia</strong></td>
                                                                            <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.district.city}</td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div class="table-responsive">
                                                                <table class="table">
                                                                    <thead>
                                                                        <tr></tr>
                                                                    </thead>
                                                                    <tbody>
                                                                        <tr>
                                                                            <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;width: 40%;"><strong>Medico di base</strong></td>
                                                                            <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;">dott. ${requestScope.familyDoctor.firstname} ${requestScope.familyDoctor.lastname}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><strong>Data ultima visita</strong></td>
                                                                            <td>
                                                                                <a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioVisitaBaseDottore?visitId=${requestScope.ultimaVisita.id}">
                                                                                    <c:if test="${requestScope.ultimaVisita.visdate!=null}" ><span style="text-decoration: underline;"><script>document.write(FormatDate(new Date('${requestScope.ultimaVisita.visdate}')));</script> </span></c:if>
                                                                                </a>
                                                                                <c:if test="${requestScope.ultimaVisita.visdate==null}" ><span style='font-style: italic'>Data non presente </span></c:if>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="tab-pane" role="tabpanel" id="tab-2">
                                                    <h4 style="border-bottom: #999 solid 1px;padding-bottom: 5px;">Visite effettuate dal medico di base</h4>
                                                    <div class="table-responsive" id="tabella-visite">
                                                        <table class="table table-striped" id="tblvisit">
                                                            <thead>
                                                                <tr>
                                                                    <th>Data visita</th>
                                                                    <th>Medico di base</th>
                                                                    <th>Informazioni sulla visita</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:if test="${requestScope.visite.size() > 0}">
                                                                    <c:forEach begin = "0" end="${requestScope.visite.size()-1}" var="current">
                                                                        <tr>
                                                                            <td><script>document.write(FormatDate(new Date('${requestScope.visite.get(current).visdate}')));</script></td>
                                                                            <td>dott. ${requestScope.mediciBase.get(requestScope.visite.get(current).getFamilydoctor()).firstname}&nbsp;
                                                                                    ${requestScope.mediciBase.get(requestScope.visite.get(current).getFamilydoctor()).lastname}</td>
                                                                            <td><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioVisitaBaseDottore?visitId=${requestScope.visite.get(current).id}"><span style="text-decoration: underline;">visualizza</span></a></td>
                                                                        </tr>
                                                                    </c:forEach>                                                                                                                                      
                                                                </c:if>  
                                                                <c:if test="${requestScope.visite.size()==0}">
                                                                    <tr>
                                                                        <td colspan="3"><i>Nessuna visita effettuata</i></td>
                                                                    </tr>
                                                                </c:if>   
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="tab-pane" role="tabpanel" id="tab-3">
                                                    <h4 style="border-bottom: #999 solid 1px;padding-bottom: 5px;">Lista delle ricette prescritte</h4>
                                                    <div class="table-responsive" id="tabella-ricette">
                                                        <table class="table table-striped" id="tblpresc">
                                                            <thead>
                                                                <tr>
                                                                    <th>Farmaco prescritto</th>
                                                                    <th>Data prescrizione</th>
                                                                    <th>Stato ricetta</th>
                                                                    <th>Farmacia</th>
                                                                    <th>Data evasione</th>
                                                                    <th>Ricetta</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:if test="${requestScope.prescriptions.size() > 0}">
                                                                    <c:forEach begin = "0" end="${requestScope.prescriptions.size()-1}" var="current">
                                                                        <tr>
                                                                            <c:choose>
                                                                                <c:when test="${requestScope.prescriptions.get(current).active==0}">
                                                                                    <td>${requestScope.prescriptions.get(current).drugname}<br></td>
                                                                                    <td><script> document.write(FormatDate(new Date('${requestScope.prescriptions.get(current).visdate}'))); </script></td>
                                                                                    <td>Evasa</td>
                                                                                    <td>Farmacia di ${requestScope.prescriptions.get(current).drugstore}<br></td>
                                                                                    <td><script> document.write(FormatDate(new Date('${requestScope.prescriptions.get(current).madedate}'))); </script></td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioRicettaDottore?prescriptionId=${requestScope.prescriptions.get(current).id}"><span style="text-decoration: underline;">visualizza</span></a></td>
                                                                                </c:when>
                                                                                <c:when test="${requestScope.prescriptions.get(current).active==1}">
                                                                                    <td>${requestScope.prescriptions.get(current).drugname}<br></td>
                                                                                    <td><script> document.write(FormatDate(new Date('${requestScope.prescriptions.get(current).visdate}'))); </script></td>
                                                                                    <td>Attiva</td>
                                                                                    <td> - <br></td>
                                                                                    <td> - </td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioRicettaDottore?prescriptionId=${requestScope.prescriptions.get(current).id}"><span style="text-decoration: underline;">visualizza</span></a></td>
                                                                                </c:when>
                                                                            </c:choose>                                                                                                                                   
                                                                        </tr>
                                                                    </c:forEach> 
                                                                </c:if>  
                                                                <c:if test="${requestScope.prescriptions.size()==0}">
                                                                    <tr>
                                                                        <tr> <td colspan="6"> <i>Nessuna ricetta emessa</i> </td> </tr>
                                                                    </tr>
                                                                </c:if> 
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="tab-pane" role="tabpanel" id="tab-4">
                                                    <h4 style="border-bottom: #999 solid 1px;padding-bottom: 5px;">Lista degli esami prescritti</h4>
                                                    <div class="table-responsive" id="tabella-esami">
                                                        <table class="table table-striped" id="tblesami">
                                                            <thead>
                                                                <tr>
                                                                    <th>Nome esame</th>
                                                                    <th>Data prescrizione</th>
                                                                    <th>Data effettuazione</th>
                                                                    <th>Luogo</th>
                                                                    <th>Risultati</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:if test="${requestScope.exams.size() > 0}">
                                                                    <c:forEach begin = "0" end="${requestScope.exams.size()-1}" var="current">
                                                                        <tr>
                                                                            <c:if test="${requestScope.exams.get(current).madedate != null}">
                                                                                <td>${requestScope.exams.get(current).examname}</td>
                                                                                <td><script> document.write(FormatDate(new Date('${requestScope.exams.get(current).visdate}'))); </script></td>
                                                                                <td><script> document.write(FormatDate(new Date('${requestScope.exams.get(current).madedate}'))); </script></td>
                                                                                <td>${requestScope.exams.get(current).providedby}</td>
                                                                                <td><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioEsameDottore?examId=${requestScope.exams.get(current).id}">
                                                                                        <span style="text-decoration: underline;">visualizza</span>
                                                                                    </a></td>
                                                                            </c:if>
                                                                            <c:if test="${requestScope.exams.get(current).madedate == null}">
                                                                                <td>${requestScope.exams.get(current).examname}<br></td>
                                                                                <td><script> document.write(FormatDate(new Date('${requestScope.exams.get(current).visdate}'))); </script></td>
                                                                                <td> da effettuare <br></td>
                                                                                <td> -- </td>
                                                                                <td><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioEsameDottore?examId=${requestScope.exams.get(current).id}">
                                                                                        <span style="text-decoration: underline;">visualizza</span>
                                                                                    </a></td>
                                                                            </c:if>
                                                                        </tr>
                                                                    </c:forEach>                                                                                                                                      
                                                                </c:if>  
                                                                <c:if test="${requestScope.exams.size()==0}">
                                                                    <tr>
                                                                        <td colspan="5"><i>Nessun esame prescritto</i></td>
                                                                    </tr>
                                                                </c:if> 
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="tab-pane" role="tabpanel" id="tab-5">
                                                    <h4 style="border-bottom: #999 solid 1px;padding-bottom: 5px;">Lista delle visite specialistiche prescritte</h4>
                                                    <div class="table-responsive" id="tabella-speckvisit">
                                                        <table class="table table-striped" id="tblspeck">
                                                            <thead>
                                                                <tr>
                                                                    <th>Tipo visita</th>
                                                                    <th>Data prescrizione</th>
                                                                    <th>Data effettuazione</th>
                                                                    <th>Nome medico</th>
                                                                    <th>Risultati</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:if test="${requestScope.speckvisits.size() > 0}">
                                                                    <c:forEach begin = "0" end="${requestScope.speckvisits.size()-1}" var="current">
                                                                        <tr>
                                                                            <c:choose>
                                                                                <c:when test="${requestScope.speckvisits.get(current).madedate != null}">
                                                                                    <td>${requestScope.speckvisits.get(current).name}<br></td>
                                                                                    <td>${requestScope.speckvisits.get(current).visdate}</td>
                                                                                    <td>${requestScope.speckvisits.get(current).madedate}<br></td>
                                                                                    <td>dott. ${requestScope.speckvisits.get(current).firstname} ${requestScope.speckvisits.get(current).lastname}</td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioVisitaSpeckDottore?speckId=${requestScope.speckvisits.get(current).id}">
                                                                                            <span style="text-decoration: underline;">visualizza</span></a>
                                                                                    </td>
                                                                                </c:when>
                                                                                <c:when test="${requestScope.speckvisits.get(current).madedate == null}">
                                                                                    <td>${requestScope.speckvisits.get(current).name}<br></td>
                                                                                    <td>${requestScope.speckvisits.get(current).visdate}</td>
                                                                                    <td> da effettuare <br></td>
                                                                                    <td> -- </td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioVisitaSpeckDottore?speckId=${requestScope.speckvisits.get(current).id}">
                                                                                            <span style="text-decoration: underline;">visualizza</span></a>
                                                                                    </td>
                                                                                </c:when>
                                                                            </c:choose>
                                                                        </tr>
                                                                    </c:forEach>                                                                                                                                      
                                                                </c:if>  
                                                                <c:if test="${requestScope.speckvisits.size()==0}">
                                                                    <tr>
                                                                        <td colspan="5"><i>Nessuna visita specialistica prescritta</i></td>
                                                                    </tr>
                                                                </c:if>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../assets/js/script.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.18/js/dataTables.bootstrap4.min.js"></script>
    <script>
        $(document).ready( function () {
            <c:if test="${requestScope.visite.size() > 0}">
            $('#tblvisit').DataTable();
            </c:if>
            <c:if test="${requestScope.prescriptions.size() > 0}">
            $('#tblpresc').DataTable();
            </c:if>
            <c:if test="${requestScope.exams.size() > 0}">
            $('#tblesami').DataTable();
            </c:if>
            <c:if test="${requestScope.speckvisits.size() > 0}">
            $('#tblspeck').dataTable();
            </c:if>
        } );
    </script>
</body>

</html>
