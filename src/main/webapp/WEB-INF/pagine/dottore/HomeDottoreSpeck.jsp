<%-- 
    Document   : HomeDottoreSpeck
    Created on : 21-ago-2019, 16.59.17
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
    <link rel="icon" href="../../../assetsHome/img/favicon.ico">
    <link rel="shortcut icon" href="../../../assetsHome/img/favicon.ico">
    <link rel="stylesheet" href="../../../assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../../../assets/css/styles.min.css">
</head>

<script>
    var lista = [];
    <c:if test="${requestScope.pazientevista.size()>0}">
        <c:forEach begin="0" end="${requestScope.pazientevista.size()-1}" var="i">
            lista [${i}] = {
                idpat: '${requestScope.pazientevista.get(i).id_pat}',
                patname: '${requestScope.pazientevista.get(i).patname}',
                birthday: '${requestScope.pazientevista.get(i).birthday}',
                docname: '${requestScope.pazientevista.get(i).docname}',
                lastvisit: '${requestScope.pazientevista.get(i).lastvisit}'
            };
        </c:forEach>    
    </c:if>
</script>

<body style="background-color: #f4f4f4;">
        <jsp:include page="headerBaseSpeck.jsp"/>
    <div class="container container-main">
        <div class="row" style="margin-bottom: 10px;">
            <div class="col">
                <div class="card">
                    <div class="card-body shadow" style="padding: 10px;">
                        <div class="row" style="margin: 0px;">
                            <div class="col-12 col-sm-3 col-md-2 col-lg-2 col-xl-2 text-center"><img class="rounded border rounded-circle border-dark align-self-center" src="${requestScope.element.path}" style="width: 80px;height: 80px;margin: 10px;border-width: 2px !important;/*vertical-align: middle;*/"></div>
                            <div class="col-sm-9 col-md-10 col-lg-7 col-xl-7"
                                style="padding-top: 10px;">
                                <h2 style="margin-bottom: 0px;">Dott. ${requestScope.dottorePaziente.firstname} ${requestScope.dottorePaziente.lastname}</h2>
                                <h5 class="text-secondary">Specialista in ${requestScope.dottore.specialization}</h5>
                            </div>
                            <div class="col-md-12 col-lg-3 col-xl-3 text-right align-self-end" style="padding-right: 0px;padding-left: 5px;">
                                <a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}/restricted/paziente/HomePaziente" style="/*word-wrap: break-word !important;*//*word-break: break-word;*/margin-right: 5px;">Vai al tuo profilo personale</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-xl-5 offset-xl-1">
                <div class="card shadow" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h4 class="mb-0">I tuoi appuntamenti di oggi</h4>
                    </div>
                    <div class="card-body" style="margin-right: 0px;margin-bottom: 11px;padding-bottom: 0px;">
                        <div id="container-eventi-cal" style="max-height: 525px;overflow: auto;">
                            <c:choose>
                                <c:when test="${requestScope.calendario.size()>0}">
                                    <c:forEach begin="0" end="${requestScope.calendario.size()-1}" var="i">
                                        <a href="CompilaVisitaSpeck?speckId=${requestScope.calendario.get(i).getIdVisita()}">
                                            <div style="border: 0; border-left: 6px solid #007bff; padding: 3px 5px; margin-bottom: 10px; background-color: #eff2ff; border-radius: 10px !important; margin-right: 4px;" data-toggle="tooltip" title="Vai alla visita">
                                                <div class="table-responsive" style="margin-bottom: -20px;">
                                                    <table class="table">
                                                        <thead>
                                                            <tr>
                                                                <th class="text-uppercase" colspan="2" style="padding: 4px;border: 0;padding-bottom: 0 !important;font-size: 15px;">
                                                                    ${requestScope.calendario.get(i).getNomePaziente()}
                                                                </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td class="text-center" style="width: 20%;vertical-align: middle;border: 0;">${requestScope.calendario.get(i).getOra()}</td>
                                                                <td style="border: 0;padding-top: 0px;">
                                                                    ${requestScope.calendario.get(i).getMotivazione()}
                                                                </td>
                                                            </tr>
                                                            <tr></tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${requestScope.calendario.size()<=0}">
                                    <span style="font-style: italic">Nessun appuntamento registrato oggi</span>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-xl-5">
                <div class="card shadow" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h4 class="mb-0">Ricerca pazienti</h4>
                    </div>
                    <div class="card-body">
                        <div style="height: 65px;">
                            <div class="input-group mb-3" style="width: 90%; margin: 0 auto;">
                                <input type="text" class="form-control" placeholder="Ricerca paziente..." id="search" onkeyup="cerca(this.value)" style="outline: none">
                                <div class="input-group-append">
                                  <button class="btn btn-outline-secondary" type="button"onclick="cerca(document.getElementById('search').value)">Cerca</button>
                                </div>
                            </div>
                        </div>
                        <div id="container-pazienti" style="max-height: 450px;overflow: auto;">
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>              
    <script>
        stampaNoSearch();
        
        function cerca(find)
        {
            var strs = "";
            find = find.trim().toLowerCase();
            var ok = new Set();
            if(find.length > 0)
            {
                findx = find.split(' ');
                for(i=0; i<findx.length; i++)
                    for(j=0; j<lista.length; j++)
                        if(lista[j].patname.toLowerCase().includes(findx[i]))
                            ok.add(lista[j]);
                
                if(ok.size > 0)
                {
                    for (let item of ok)
                    {
                        strs += '<div role="alert" class="alert alert-secondary text-right shadow-sm" style="padding: 5px 10px;background-color: rgb(245,245,245); cursor: pointer; margin-right: 4px" onclick="open_paziente_alert(this)">' +
                                '    <p class="text-left" style="margin-bottom: 7px;margin-top: 3px;margin-left: 15px;font-size: 18px;">' +
                                '        <strong>' + item.patname + '</strong>' +
                                '    </p> <div class="table-responsive text-left" style="display:none">' +
                                '            <table class="table"> <tbody> <tr>' +
                                '                        <td style="width: 50%;"><strong>Data di nascita</strong></td>' +
                                '                        <td>' + FormatDate(new Date(item.birthday)) + '</td>' +
                                '                    </tr> <tr>' +
                                '                        <td style="width: 50%;"> <strong>Medico di base</strong> </td>' +
                                '                        <td>dott. ' + item.docname + '</td>' +
                                '                    </tr><tr>' +
                                '                        <td style="width: 50%;"><strong>Data ultima visita</strong></td>' +
                                '                        <td>';
                if(item.lastvisit!='') strs += FormatDate(new Date(item.lastvisit));
                else strs += '<i>non trovata</i>';
                strs += '</td>' +
                                '                    </tr> </tbody> </table> </div>' +
                                '    <div role="group" class="btn-group" style="display: none;">' +
                                '        <a class="btn btn-secondary btn-sm border rounded shadow-sm" role="button" href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioPaziente?pazienteId=' + item.idpat + '" style="margin-right: 10px;">' +
                                '            Visualizza profilo</a>' +
                                '    </div> </div>';
                    }
                    stampaResults(strs);
                }
                else
                    stampaNoResults();
            }
            else
                stampaNoSearch();
        }
        
        function FormatDate(xxdata)
        {
            var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
            var monthIndex = xxdata.getMonth();
            return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
        }
        
        function stampaResults(strs) { document.getElementById("container-pazienti").innerHTML = strs; }
            
        function stampaNoResults() { document.getElementById("container-pazienti").innerHTML = "<div role='alert' class='alert alert-secondary' style='margin-bottom: 10px;padding: 8px 20px;'>Nessun risultato</div>"; }

        function stampaNoSearch() { document.getElementById("container-pazienti").innerHTML = "<span style='font-style: italic'>Inserire almeno un carattere per avviare la ricerca</span>" }
    </script>
    <script src="../../../assets/js/jquery.min.js"></script>
    <script src="../../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../assets/js/script.min.js"></script>
</body>

</html>