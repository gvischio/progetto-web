<%-- 
    Document   : InfoMedicoPerPaziente
    Created on : 11-ago-2019, 12.07.36
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
            background-color: #4CAF50;
            color: white;
            opacity: 0.83;
            transition: opacity 0.6s;
            margin-bottom: 15px;
        }
        
        .alert-error {
            padding: 20px;
            background-color: #f44336;
            color: white;
            opacity: 0.83;
            transition: opacity 0.6s;
            margin-bottom: 15px;
        }

    </style>
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
        <c:if test="${!empty medicoNO}">
            <nav class="alert-error navbar navbar-expand navbar-expand-lg fixed-bottom">
                <div class="container-fluid"> 
                    <column><strong>Attenzione!</strong> Errore in aggiornamento medico, riprovare.</column>
                    <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
                </div>
            </nav>
        </c:if>
        <c:if test="${!empty medicoOK}">
            <nav class="alert-success navbar navbar-expand navbar-expand-lg fixed-bottom">
                <div class="container-fluid"> 
                    <column><strong>Successo!</strong> Medico aggiornato correttamente.</column>
                    <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
                </div>
            </nav>
        </c:if>
        
    <jsp:include page='headerPaziente.jsp'/>
    
    <div class="container container-main">
        <div class="row">
            <div class="col-sm-12 col-lg-6 col-xl-6" style="margin-bottom: 5px;">
                <div class="card">
                    <div class="card-header">
                        <h4 class="mb-0">Il tuo medico di base</h4>
                    </div>
                    <div class="card-body">
                        <div class="row" style="margin: 0px;">
                            <div class="col-12 col-sm-4 col-md-3 col-lg-12 col-xl-12 text-center">
                                <img class="rounded border rounded-circle border-dark align-self-center" src="${requestScope.element.path}" style="width: 100px;height: 100px;margin: 20px;border-width: 2px !important;"></div>
                            <div
                                class="col" style="padding-top: 10px;">
                                <div class="table-responsive">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle; width: 40%;"><strong>Dott.</strong></td>
                                                <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;"> 
                                                    ${requestScope.dottore_paziente.firstname} ${requestScope.dottore_paziente.lastname}
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Codice albo</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.dottore.doc_reg_numb}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Sesso</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.dottore_paziente.sex}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Comune</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.dottore.city}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Provincia</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.district.city}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>Orario</strong></td>
                                                <td>dal lunedì al venerdì<br>dalle 9:00 alle 13:00<br>dalle 15:00 alle 19:00</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <p style="text-decoration: underline;cursor: pointer;">
                                    <span style="text-decoration: underline; cursor: pointer" onclick="document.getElementById('divchangedoctor').style.display = 'block'">Non sei soddisfatto del tuo medico?</span>
                                </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card" style="margin-top: 10px; display: none" id="divchangedoctor">
                <div class="card-header">
                    <h4 class="mb-0">Cambia medico di base</h4>
                </div>
                <div class="card-body">
                    <p>Di seguito trovi la lista dei medici di base disponibili nella tua provincia:</p>
                    <div style="max-height: 250px !important;overflow: auto;">
                        <c:choose>
                            <c:when test="${empty requestScope.dottori_pazienti}">
                                <div class="alert alert-primary" role="alert" style="padding: 4px;margin-bottom: 8px;">
                                    <span><strong>Nessun medico di base disponibile nella tua provincia</strong></span>
                                </div>
                            </c:when>
                            <c:when test="${not empty requestScope.dottori_pazienti}">
                                <c:forEach begin="0" end="${requestScope.dottori_pazienti.size()-1}" var="current">
                                    <div class="alert alert-primary" role="alert" style="padding: 4px;margin-bottom: 8px;">
                                        <div class="row" style="margin: 0px;">
                                            <div class="col align-self-center">
                                                <p style="margin-bottom: 0;"><strong> dott. ${requestScope.dottori_pazienti.get(current).firstname} ${requestScope.dottori_pazienti.get(current).lastname} </strong></p>
                                            </div>
                                            <div class="col-xl-2 align-self-center"> 
                                                  <button class="btn btn-primary btn-sm" type="button" onclick="sceglidoc(${requestScope.dottori_pazienti.get(current).id}, '${requestScope.dottori_pazienti.get(current).firstname} ${requestScope.dottori_pazienti.get(current).lastname}')">scegli</button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach> 
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="alert alert-secondary" role="alert" style="padding: 6px 14px; margin: 5px; margin-bottom: 8px; display: none" id="alscelta">
                        
                    </div>
                    <form action="${pageContext.servletContext.contextPath}/restricted/paziente/CambiaMedicoBase" method="post">
                        <input class="form-control" type="hidden" name="idnewdoctor" id="idnewdoctor">
                        <button class="btn btn-primary" type="submit" style="margin-top: 10px;">Conferma operazione</button>
                        <button class="btn btn-light" type="reset" style="margin-left: 15px;margin-top: 10px;" onclick="document.getElementById('divchangedoctor').style.display = 'none'">Annulla</button>
                    </form>
                </div>
            </div>
        </div>                    
        <div class="col">
            <c:choose>
                <c:when test="${not empty requestScope.prossimaVisita}">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="mb-0">La tua prossima visita</h4>
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                La tua prossima visita con il dott. ${requestScope.dottore_paziente.firstname} ${requestScope.dottore_paziente.lastname} è prenotata per il giorno:
                            </p>
                            <p class="card-text" style="margin-right: 30px;margin-left: 30px;">
                                <script> document.write(FormatDate(new Date('${requestScope.prossimaVisita.getVisdate()}'))); </script>
                                alle ore
                                ${requestScope.prossimaVisita.getVistime()}</p>
                            <p class="card-text" style="margin-bottom: 2px;">Motivazione della visita:</p>
                            <p class="card-text" style="margin-right: 30px;margin-left: 30px;">${requestScope.prossimaVisita.motivation}</p>
                        </div>
                    </div>
                </c:when>
                <c:when test="${empty requestScope.prossimaVisita}">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="mb-0">Prenota una visita</h4>
                        </div>
                        <div class="card-body">
                            <p class="card-text" style="margin-bottom: 2px;"><strong>Data e ora prossima visita disponibile</strong></p>
                            <p class="card-text" style="margin-right: 30px;margin-left: 30px;">
                                <script>document.write(FormatDate(new Date('${requestScope.dataPrenota}')));</script> alle ore ${requestScope.oraPrenota}
                            </p>
                            <form class="text-left" method="post" action="PrenotaVisita">
                                <input type="hidden" name="data" value="${requestScope.tsPrenota}">
                                <p style="margin-bottom: 2px;"><strong>Motivazione della visita</strong></p>
                                <textarea class="form-control" name="motivazione" rows="4" spellcheck="true" required="" maxlength="150" autocomplete="off" style="resize: none;margin-bottom: 15px;"></textarea>
                                <button class="btn btn-primary" type="submit">Prenota</button>
                                <button class="btn btn-light" type="reset" style="margin-left: 15px;">Annulla</button>
                            </form>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
    </div>
    <script src="../../assetsPazienti/js/script.min.js"></script>
    <script>
        function sceglidoc(id, nome)
        {
            document.getElementById('alscelta').style.display = 'block';
            document.getElementById('alscelta').innerHTML = "<strong>Hai selezionato:</strong> dott. " + nome;
            document.getElementById('idnewdoctor').value = id;
        }
    </script>
</body>

</html>
