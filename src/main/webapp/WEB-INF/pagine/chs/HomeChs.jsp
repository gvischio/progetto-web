<%-- 
    Document   : HomeCHS
    Created on : 30-ago-2019, 16.22.13
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

<style>
        .closebtn {
            padding-left: 15px;
            color: white;
            font-weight: bold;
            float: right;
            font-size: 20px;
            line-height: 18px;
            cursor: pointer;
            transition: 0.3s;
        }
        .closebtn:hover {
          color: black;
        }
        .alert-success {
            padding: 20px;
            background-color: green;
            color: white;
            opacity: 0.83;
            transition: opacity 0.6s;
            margin-bottom: 15px;
        }
</style>
<script>
    var lista = [];
    <c:if test="${requestScope.pazientevista.size()>0}">
        <c:forEach begin="0" end="${requestScope.pazientevista.size()-1}" var="i">
            lista [${i}] = {
                idpat: '${requestScope.pazientevista.get(i).id_pat}',
                patname: '${requestScope.pazientevista.get(i).patname}',
                birthday: '${requestScope.pazientevista.get(i).birthday}',
                docname: '${requestScope.pazientevista.get(i).docname}',
                examstodo: '${requestScope.pazientevista.get(i).examstodo}'
            };
        </c:forEach>    
    </c:if>
</script>

<body>
    <jsp:include page="headerChs.jsp"/>
    <c:if test="${!empty emailInviata}">
        <nav class="alert-success navbar navbar-expand navbar-expand-lg fixed-top">
            <div class="container-fluid"> 
                <column><strong> Successo!</strong> Email inviate.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if> 
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-4 col-xl-4 offset-xl-0">
                <div class="card" style="margin-bottom: 10px;">
                    <div class="card-body text-center">
                        <h3 style="margin-bottom: 10px;">${requestScope.chs.name}</h3><img src="../../assets/img/logo%20ulss.png" style="height: 100px;margin-bottom: 25px;">
                        <div class="table-responsive text-left" style="margin-bottom: 14px;">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td style="width: 50%;"><strong>Provincia</strong></td>
                                        <td>${requestScope.district.city}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <a href="${pageContext.servletContext.contextPath}/restricted/chs/RicetteEvaseChsToXLS" >
                            <div class="media text-left align-self-center"><img src="../../assets/img/Excel.png" class="mr-3 align-self-center" style="width: 40px;margin-right: 10px;cursor: pointer;">
                                <div class="media-body">
                                    <p style="margin-bottom: 0px;padding-top: 10px;padding-bottom: 10px;">Esportazione dell'elenco delle ricette evase in questa provincia</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="card" style="margin-bottom: 10px;">
                    <div class="card-header">
                        <h4 class="mb-0">Richiamo</h4>
                    </div>
                    <div class="card-body">
                        <a href="${pageContext.servletContext.contextPath}/restricted/chs/NuovoRichiamo" class="btn btn-primary btn-lg" role="button" style="font-size: 18px;">
                            Effettua un nuovo richiamo
                        </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card shadow" style="margin-bottom: 10px;">
                    <div class="card-header">
                        <h4 class="mb-0">Esami prenotati per oggi</h4>
                    </div>
                    <div class="card-body" style="margin-right: 0px;margin-bottom: 11px;padding-bottom: 0px;">
                        <div id="container-eventi-cal" style="max-height: 350px;overflow: auto;">
                            <c:choose>
                                <c:when test="${requestScope.calendario.size()>0}">
                                    <c:forEach begin="0" end="${requestScope.calendario.size()-1}" var="i">
                                        <a href="CompilaEsame?examId=${requestScope.calendario.get(i).getIdVisita()}">
                                            <div style="border: 0; border-left: 6px solid #007bff; padding: 3px 5px; margin-bottom: 10px; background-color: #eff2ff; border-radius: 10px !important; margin-right: 4px;" data-toggle="tooltip" title="Eroga esame">
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
                                    <span style="font-style: italic">Nessun esame prenotato per oggi</span>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="card shadow" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h4 class="mb-0">Ricerca pazienti</h4>
                    </div>
                    <div class="card-body">
                        <div style="height: 65px;">
                            <div class="input-group mb-3" style="width: 90%; margin: 0 auto;">
                                <input type="text" class="form-control" placeholder="Ricerca paziente..." id="search" onkeyup="cerca(this.value)" style="outline: none; padding: 8px 16px">
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button" style="padding-left: 16px; padding-right: 16px" onclick="cerca(document.getElementById('search').value)">Cerca</button>
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
                                '                        <td style="width: 50%;"><strong>Medico di base</strong></td>' +
                                '                        <td>dott. ' + item.docname + '</td>' +
                                '                    </tr> <tr>' +
                                '                        <td><strong>Esami da fare</strong></td>' +
                                '                        <td>' + item.examstodo + '</td>' +
                                '                    </tr> </tbody> </table> </div>' +
                                '    <div role="group" class="btn-group" style="display: none;">' +
                                '        <a class="btn btn-secondary btn-sm border rounded shadow-sm" role="button" href="${pageContext.servletContext.contextPath}' +
                                                                        '/restricted/chs/DettaglioPazienteChs?pazienteId=' + item.idpat + '" style="margin-right: 10px;">' +
                                '            Visualizza profilo</a> </div> </div>';
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
                                
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>