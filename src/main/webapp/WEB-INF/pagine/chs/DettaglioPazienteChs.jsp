<%-- 
    Document   : DettaglioPazienteCHS
    Created on : 30-ago-2019, 16.21.24
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
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.18/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>
<script>
    var lista_foto = [];
    var hasenso = false;
    <c:if test="${requestScope.userpic.size()>0}">
        hasenso = true;
        <c:forEach begin = "0" end="${requestScope.userpic.size()-1}" var="current">
            lista_foto[${current}] = "${requestScope.userpic.get(current).path}";
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
        document.getElementById("photoimg").src = lista_foto[current];
    }
    
    function arretra() {
        if(current === 0) 
            current = ${requestScope.userpic.size()-1};
        else
            current--;
        document.getElementById("photoimg").src = lista_foto[current];
    }
    
    function FormatDate(xxdata)
    {
        var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
        var monthIndex = xxdata.getMonth();
        return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
    }
</script>
<body style="background-color: #f4f4f4;">
    <jsp:include page="headerChs.jsp"/>
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
        <!-- PANNELLO 1 -> scheda anagrafica -->                                        
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
                                                                    <tbody>
                                                                        <tr>
                                                                            <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;width: 40%;"><strong>Medico di base</strong></td>
                                                                            <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;">dott. ${requestScope.familyDoctor.firstname} ${requestScope.familyDoctor.lastname}</td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><strong>Data ultima visita</strong></td>
                                                                            <td>
                                                                                <a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioVisitaBaseChs?visitId=${requestScope.ultimaVisita.id}">
                                                                                    <span style="text-decoration: underline;"><script>document.write(FormatDate(new Date('${requestScope.ultimaVisita.visdate}')));</script> </span>
                                                                                </a>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
        <!-- PANNELLO 2: visite dal medico di base -->             
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
                                                                            <td>${requestScope.visite.get(current).visdate}</td>
                                                                            <td>dott. ${requestScope.mediciBase.get(requestScope.visite.get(current).getFamilydoctor()).firstname}&nbsp;
                                                                                    ${requestScope.mediciBase.get(requestScope.visite.get(current).getFamilydoctor()).lastname}</td>
                                                                            <td><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioVisitaBaseChs?visitId=${requestScope.visite.get(current).id}"><span style="text-decoration: underline;">visualizza</span></a></td>
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
        <!-- PANNELLO 3: ricette -->
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
                                                                <c:if test="${requestScope.prescriptionChs.size() > 0}">
                                                                    <c:forEach begin = "0" end="${requestScope.prescriptionChs.size()-1}" var="current">
                                                                        <tr>
                                                                            <c:choose>
                                                                                <c:when test="${requestScope.prescriptionChs.get(current).active==0}">
                                                                                    <td>${requestScope.prescriptionChs.get(current).drugname}<br></td>
                                                                                    <td><script> document.write(FormatDate(new Date('${requestScope.prescriptionChs.get(current).visdate}'))); </script></td>
                                                                                    <td>Evasa</td>
                                                                                    <td>Farmacia di ${requestScope.prescriptionChs.get(current).drugstore}<br></td>
                                                                                    <td><script> document.write(FormatDate(new Date('${requestScope.prescriptionChs.get(current).madedate}'))); </script></td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioRicettaChs?prescriptionId=${requestScope.prescriptionChs.get(current).id}"><span style="text-decoration: underline;">visualizza</span></a></td>
                                                                                </c:when>
                                                                                <c:when test="${requestScope.prescriptionChs.get(current).active==1}">
                                                                                    <td>${requestScope.prescriptionChs.get(current).drugname}<br></td>
                                                                                    <td><script> document.write(FormatDate(new Date('${requestScope.prescriptionChs.get(current).visdate}'))); </script></td>
                                                                                    <td>Attiva</td>
                                                                                    <td> - <br></td>
                                                                                    <td> - </td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioRicettaChs?prescriptionId=${requestScope.prescriptionChs.get(current).id}"><span style="text-decoration: underline;">visualizza</span></a></td>
                                                                                </c:when>
                                                                            </c:choose>
                                                                        </tr>
                                                                    </c:forEach>                                                                                                                                      
                                                                </c:if>  
                                                                <c:if test="${requestScope.prescriptionChs.size()==0}">
                                                                    <tr> <td colspan="6"> <i>Nessuna ricetta emessa</i> </td> </tr>
                                                                </c:if> 
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
        <!-- PANNELLO 4: esami -->
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
                                                                <c:if test="${requestScope.examChs.size() > 0}">
                                                                    <c:forEach begin = "0" end="${requestScope.examChs.size()-1}" var="current">
                                                                        <tr>
                                                                            <c:if test="${requestScope.examChs.get(current).paid == 1}">
                                                                                <td>${requestScope.examChs.get(current).examname}</td>
                                                                                <td><script> document.write(FormatDate(new Date('${requestScope.examChs.get(current).visdate}'))); </script></td>
                                                                                <td><script> document.write(FormatDate(new Date('${requestScope.examChs.get(current).madedate}'))); </script></td>
                                                                                <td>${requestScope.examChs.get(current).providedby}</td>
                                                                                <td><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioEsameChs?examId=${requestScope.examChs.get(current).id}">
                                                                                        <span style="text-decoration: underline;">visualizza</span>
                                                                                </a></td>
                                                                            </c:if>
                                                                            <c:if test="${requestScope.examChs.get(current).paid == 0}">
                                                                                <td>${requestScope.examChs.get(current).examname}<br></td>
                                                                                <td><script> document.write(FormatDate(new Date('${requestScope.examChs.get(current).visdate}'))); </script></td>
                                                                                <td> da effettuare <br></td>
                                                                                <td> -- </td>
                                                                                <td><a class="btn btn-success btn-sm" role="button" href="${pageContext.servletContext.contextPath}/restricted/chs/CompilaEsame?examId=${requestScope.examChs.get(current).id}">Compila</a><br></td>
                                                                            </c:if>
                                                                        </tr>
                                                                    </c:forEach>                                                                                                                                      
                                                                </c:if>  
                                                                <c:if test="${requestScope.examChs.size()==0}">
                                                                    <tr>
                                                                        <td colspan="5"><i>Nessun esame prescritto</i></td>
                                                                    </tr>
                                                                </c:if> 
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
        <!-- PANNELLO 5: visite specialistiche -->
                                                <div class="tab-pane" role="tabpanel" id="tab-5">
                                                    <h4 style="border-bottom: #999 solid 1px;padding-bottom: 5px;">Lista delle visite specialistiche prescritte</h4>
                                                    <div class="table-responsive" id="tabella-speckvisit">
                                                        <table class="table table-striped" id="tblspeck">
                                                            <thead>
                                                                <tr>
                                                                    <th>Tipo </th>
                                                                    <th>Data prescrizione</th>
                                                                    <th>Data effettuazione</th>
                                                                    <th>Nome medico</th>
                                                                    <th>Risultati</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <c:if test="${requestScope.speckvisitChs.size() > 0}">
                                                                    <c:forEach begin = "0" end="${requestScope.speckvisitChs.size()-1}" var="current">
                                                                        <tr>
                                                                            <c:choose>
                                                                                <c:when test="${requestScope.speckvisitChs.get(current).madedate != null}">
                                                                                    <td>${requestScope.speckvisitChs.get(current).name}<br></td>
                                                                                    <td>${requestScope.speckvisitChs.get(current).visdate}</td>
                                                                                    <td>${requestScope.speckvisitChs.get(current).madedate}<br></td>
                                                                                    <td>dott. ${requestScope.speckvisitChs.get(current).firstname} ${requestScope.speckvisitChs.get(current).lastname}</td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioVisitaSpeckChs?speckId=${requestScope.speckvisitChs.get(current).id}">
                                                                                            <span style="text-decoration: underline;">visualizza</span></a>
                                                                                    </td>
                                                                                </c:when>
                                                                                <c:when test="${requestScope.speckvisitChs.get(current).madedate == null}">
                                                                                    <td>${requestScope.speckvisitChs.get(current).name}<br></td>
                                                                                    <td>${requestScope.speckvisitChs.get(current).visdate}</td>
                                                                                    <td> da effettuare <br></td>
                                                                                    <td> -- </td>
                                                                                    <td><a href="${pageContext.servletContext.contextPath}/restricted/chs/DettaglioVisitaSpeckChs?speckId=${requestScope.speckvisitChs.get(current).id}">
                                                                                            <span style="text-decoration: underline;">visualizza</span></a>
                                                                                    </td>
                                                                                </c:when>
                                                                            </c:choose>
                                                                        </tr>
                                                                    </c:forEach>                                                                                                                                      
                                                                </c:if>  
                                                                <c:if test="${requestScope.speckvisitChs.size()==0}">
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
            <c:if test="${requestScope.prescriptionChs.size() > 0}">
            $('#tblpresc').DataTable();
            </c:if>
            <c:if test="${requestScope.examChs.size() > 0}">
            $('#tblesami').DataTable();
            </c:if>
            <c:if test="${requestScope.speckvisitChs.size() > 0}">
            $('#tblspeck').dataTable();
            </c:if>
        } );
    </script>
</body>

</html>

