<%-- 
    Document   : lista_elem
    Created on : 11-ago-2019, 12.08.10
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
    <link rel="icon" href="../assetsHome/img/favicon.ico">
    <link rel="shortcut icon" href="../assetsHome/img/favicon.ico">
    <link rel="stylesheet" href="../../assetsPazienti/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../../assetsPazienti/css/styles.min.css">
</head>

<script>
    // creazione lista
    var lista_ticket = [];
    var hasenso = false;
    <c:if test="${requestScope.tickets.size() > 0}">
        hasenso = true;
        <c:forEach begin = "0" end="${requestScope.tickets.size()-1}" var="current">
            lista_ticket[${current}] = {
                id: ${requestScope.tickets.get(current).getIdDisp()},
                name: '${requestScope.tickets.get(current).getName()}',
                date: '${requestScope.tickets.get(current).getMadeDate()}',
                type: '${requestScope.tickets.get(current).getType()}',
                ticket: '${requestScope.tickets.get(current).getTicket()}',
                doneby: '${requestScope.tickets.get(current).getDoneBy()}'
            }
        </c:forEach>
    </c:if>
</script>

<body style="background-color: #f4f4f4;">
    <jsp:include page='headerPaziente.jsp'/>
    
    <div role="dialog" tabindex="-1" class="modal fade shadow" id="modal-data">
        <div class="modal-dialog" role="document">
            <div class="modal-content shadow">
                <div class="modal-header">
                    <h4 class="modal-title">Seleziona periodo</h4><button type="button" class="close" onclick="chiudi_selezione_data()" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                <div class="modal-body" style="padding-bottom: 0;">
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                                <tr>
                                    <td style="width: 30%;">Da:</td>
                                    <td><input class="form-control" type="date" id="pp_da" required/></td>
                                </tr>
                                <tr>
                                    <td style="width: 30%;">A:</td>
                                    <td><input class="form-control" type="date" id="pp_a" required/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-light" type="button" onclick="chiudi_selezione_data()">Annulla</button>
                    <button class="btn btn-primary" type="button" onclick="PeriodoPersonalizzato()">Applica</button>
                </div>
            </div>
        </div>
    </div>
    
    <div class="container container-main">
        <div class="row">
            <div class="col-xl-10 offset-xl-1" style="padding-right: 0;padding-left: 0;padding-bottom: 15px;">
                <div class="card shadow-sm">
                    <div class="card-header">
                        <h3 class="mb-0">Lista ticket pagati</h3>
                    </div>
                    <div class="card-body" style="margin-bottom: -10;padding-bottom: 0;padding-top: 10px;">
                        <div class="row">
                            <div class="col">
                                <div class="row">
                                    <div class="col text-left" style="padding-top: 5px;padding-bottom: 5px;padding-right: 0px;">
                                        <div class="dropdown d-inline-block"><button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button" style="padding-right: 12px;padding-left: 15px;">Visualizza&nbsp;</button>
                                            <div class="dropdown-menu" role="menu">
                                                <a class="dropdown-item" role="presentation" onclick="VisualizzaTutti()">Tutti</a>
                                                <a class="dropdown-item" role="presentation" onclick="FiltraPerEsami()">Esami</a>
                                                <a class="dropdown-item" role="presentation" onclick="FiltraPerVisite()">Visite specialistiche</a>
                                                <a class="dropdown-item" role="presentation" onclick="FiltraPerRicette()">Ricette</a>
                                            </div>
                                        </div>
                                        <div class="dropdown d-inline-block" style="margin-left: 15px;">
                                            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button" style="padding-right: 12px;padding-left: 15px;margin-left: 0;">Seleziona periodo    </button>
                                            <div role="menu" class="dropdown-menu">
                                                <button role="presentation" class="dropdown-item" onclick="VisualizzaTutti()">Tutti</button>
                                                <a role="presentation" class="dropdown-item" onclick="UltimaSettimana()">Ultima settimana</a>
                                                <a role="presentation" class="dropdown-item" onclick="UltimoMese()">Ultimo mese</a>
                                                <a role="presentation" class="dropdown-item" onclick="UltimoAnno()">Ultimo anno</a>
                                                <div role="presentation" class="dropdown-divider"></div>
                                                <a role="presentation" onclick="apri_selezione_data()" class="dropdown-item">Periodo personalizzato</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-12 col-lg-6 col-xl-6 text-right" style="height: 48px;padding-top: 5px;padding-bottom: 5px;">
                                <div class="input-group mb-3" style="width: 90%; margin-bottom: 0; position:absolute; right: 15px;">
                                    <input type="text" class="form-control" placeholder="" style="outline: none" id="txtcerca">
                                    <div class="input-group-append">
                                        <button class="btn btn-outline-secondary" type="button" onclick="cerca()">Cerca</button>
                                    </div>
                                </div>
                            </div>                            
                        </div>
                        <div class="row">
                            <div class="col" style="padding: 10px;" id="ticket_box">
                                <c:if test="${requestScope.tickets.size() == 0}">
                                    <div role="alert" class="alert alert-secondary">
                                        <span>
                                            <strong>Nessun ticket da visualizzare</strong>
                                        </span>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        VisualizzaTutti();
        
        var oggi = new Date();
        var month = ((12 + oggi.getMonth() - 1) % 12) + 1;
        var UnMeseFa = new Date(oggi.getFullYear() + '-' + month + '-' + oggi.getDate());
        console.log(UnMeseFa);
        var UnAnnoFa = new Date((oggi.getFullYear()-1) + '-' + (oggi.getMonth()+1) + '-' + oggi.getDate());
        console.log(UnAnnoFa);
        var UnaSettimanaFa = new Date(oggi.valueOf()-864E5*7);
        console.log(UnaSettimanaFa);
    
        function stampaElemento(obj)
        {
            var str = '<div class="alert alert-primary" role="alert" style="padding: 8px 20px;padding-right: 14px;padding-left: 14px;margin-bottom: 10px;"> '+
                      '              <div class="row"> <div class="col">' +
                      '                      <h5 style="margin-bottom: 5px;">' + obj.name + '</h5>' +
                      '                  </div> <div class="col-md-4 col-lg-3 col-xl-3">' +
                      '                      <p class="text-right" style="margin-bottom: 0;">' +
                      '                          <a href="${pageContext.servletContext.contextPath}/restricted/paziente/RicevutaTicketToPDF?idDisp=' + obj.id + '" target="blank">' +
                      '                         <span style="text-decoration: underline;">Visualizza ricevuta</span></a></p> ' + 
                      '                  </div> </div> <div class="row"> <div class="col">' +
                      '                      <div class="table-responsive" style="margin-bottom: -10px;">' +
                      '                          <table class="table ticket-block">' +
                      '                              <tbody> <tr>' +
                      '                                      <td style="width: 40%;">Data pagamento</td>' +
                      '                                      <td> ' + FormatDate(new Date(obj.date)) + '</td>' +
                      '                                  </tr> <tr>' +
                      '                                      <td>Tipologia</td>' +
                      '                                      <td>' + FormatType(obj.type) + '</td>' +
                      '                                  </tr>' +
                      '                              </tbody> </table>' +
                      '                      </div> </div>' +
                      '                  <div class="col"> <div class="table-responsive" style="margin-bottom: -10px;">' +
                      '                          <table class="table ticket-block">' +
                      '                              <tbody> <tr>' +
                      '                                      <td style="width: 40%;">Importo</td>' +
                      '                                      <td>' + obj.ticket +' €</td>' +
                      '                                  </tr> <tr>' +
                      '                                      <td>';
            if(obj.type == "S") str += "Specialista</td> <td> dott. ";
            else str += 'Luogo</td> <td>';
            if(obj.type == "P") str += "Farmacia di ";
            str+= obj.doneby + '</td> </tr> </tbody> </table> </div> </div> </div> </div> ';
            return str;
        }
        
        function FormatType(str)
        {
            if(str == "E") return "Esame";
            if(str == "S") return "Visita specialistica";
            if(str == "P") return "Ricetta";
            return str;
        }
        
        function FormatDate(xxdata)
        {
            var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
            var monthIndex = xxdata.getMonth();
            return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
        }
        
        function FiltraPerEsami()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) { 
                    if(lista_ticket[i].type == "E")
                        strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket di esami da visualizzare");
            }
        }
        
        function FiltraPerRicette()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) { 
                    if(lista_ticket[i].type == "P")
                        strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket di ricette da visualizzare");
            }
        }
        
        function FiltraPerVisite()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) { 
                    if(lista_ticket[i].type == "V")
                        strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket di visite da visualizzare");
            }
        }
        
        function VisualizzaTutti()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) { 
                    strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket da visualizzare");
            }
        }
        
        function UltimaSettimana()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) {
                    if((new Date(lista_ticket[i].date)) >= UnaSettimanaFa)
                        strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket nell'ultima settimana da visualizzare");
            }
        }
        
        function UltimoMese()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) {
                    if((new Date(lista_ticket[i].date)) >= UnMeseFa)
                        strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket nell'ultimo mese da visualizzare");
            }
        }
        
        function UltimoAnno()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) {
                    if((new Date(lista_ticket[i].date)) >= UnAnnoFa)
                        strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket nell'ultimo anno da visualizzare");
            }
        }
        
        function PeriodoPersonalizzato()
        {
            chiudi_selezione_data();
            if(hasenso)
            {
                var dav = document.getElementById("pp_da").value;
                var av = document.getElementById("pp_a").value;
                var da = (dav == "") ? new Date("1970-01-01") : new Date(dav);
                var a = (av == "") ? new Date() : new Date(av);
                var strs = "";
                for (i = 0; i < lista_ticket.length; i++) {
                    if((new Date(lista_ticket[i].date)) >= da && (new Date(lista_ticket[i].date)) <= a)
                        strs += stampaElemento(lista_ticket[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket nel periodo selezionato da visualizzare");
            }
        }
        
        function stampaNoResults(strs)
        {
            var st = '<div role="alert" class="alert alert-secondary"><span><strong>' + strs + '</strong></span></div>';
            document.getElementById('ticket_box').innerHTML = st;
        }
        function stampaResults(strs)
        {
            document.getElementById('ticket_box').innerHTML = strs;
        }
        
        function cerca()
        {
            if(hasenso)
            {
                var find = document.getElementById("txtcerca").value.toLowerCase();
                var strs = "";
                for(i=0; i<lista_ticket.length; i++)
                    if(lista_ticket[i].name.toLowerCase().includes(find))
                        strs += stampaElemento(lista_ticket[i]);
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun ticket corrispondente");
            }
        }
        
        function chiudi_selezione_data()
        {
            document.getElementById("modal-data").classList.remove("show");
            document.getElementById("modal-data").style.display = "none";
        }
    </script>
    <script src="../../assetsPazienti/js/script.min.js"></script>
    <script>
        $(document).ready(function(){
            $("txtcerca").on('keyup', function (e) {
                if (e.keyCode === 13) {
                    cerca();
                }
            });
        });
    </script>
    
</body>

</html>
