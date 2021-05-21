<%-- 
    Document   : VisualizzaLista
    Created on : 11-ago-2019, 12.07.54
    Author     : simmf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="/WEB-INF/pagine/error.jsp" %>
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
    // creazione lista
    var lista_elem = [];
    var hasenso = false;
    <c:if test="${requestScope.elements.size()!=0}">
        hasenso = true;
        var oggi = new Date();
        <c:forEach begin = "0" end="${requestScope.elements.size()-1}" var="current">
            lista_elem[${current}] = {
                id: ${requestScope.elements.get(current).getIdDisp()},
                name: '${requestScope.elements.get(current).getCardname()}',
                prescrdate: '${requestScope.elements.get(current).getPrescrdate()}',
                madedate: '${requestScope.elements.get(current).getMadedate()}',
                active: function() { return (this.madedate == "" || (new Date(this.madedate)) >= oggi); } ,
                prescrby: '${requestScope.elements.get(current).getPrescrby()}',
                madeby: '${requestScope.elements.get(current).getMadeby()}'
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
                                    <td><input type="date" class="form-control" required/></td>
                                </tr>
                                <tr>
                                    <td style="width: 30%;">A:</td>
                                    <td><input type="date" class="form-control" required/></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-light" type="button" onclick="chiudi_selezione_data()">Annulla</button>
                    <button class="btn btn-primary" type="button">Applica</button>
                </div>
            </div>
        </div>
    </div>
    <div class="container container-main">
        <div class="row">
            <div class="col-xl-10 offset-xl-1" style="padding-right: 0;padding-left: 0;padding-bottom: 15px;">
                <div class="card shadow-sm">
                    <div class="card-header">
                        <h3 class="mb-0">Lista ${requestScope.tipolista}</h3>
                    </div>
                    <div class="card-body" style="margin-bottom: -10;padding-bottom: 0;padding-top: 10px;">
                        <div class="row">
                            <div class="col">
                                <div class="row">
                                    <div class="col text-left" style="padding-top: 5px;padding-bottom: 5px;padding-right: 0px;">
                                        <div class="dropdown d-inline-block">
                                            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button" style="padding-right: 12px;padding-left: 15px;">Visualizza&nbsp;</button>
                                            <c:choose>
                                                <c:when test="${requestScope.tipolista == 'esami'}">
                                                    <div class="dropdown-menu" role="menu">
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaTutti()">Tutti</a>
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaAttivi()">Da effettuare</a>
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaNonAttivi()">Effettuati</a>
                                                    </div>
                                                </c:when>
                                                <c:when test="${requestScope.tipolista == 'visite specialistiche'}">
                                                    <div class="dropdown-menu" role="menu">
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaTutti()">Tutte</a>
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaAttivi()">Da effettuare</a>
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaNonAttivi()">Effettuate</a>
                                                    </div>
                                                </c:when>
                                                <c:when test="${requestScope.tipolista == 'ricette'}">
                                                    <div class="dropdown-menu" role="menu">
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaTutti()">Tutte</a>
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaAttivi()">Attive</a>
                                                        <a class="dropdown-item" role="presentation" onclick="VisualizzaNonAttivi()">Non attive</a>
                                                    </div>
                                                </c:when>
                                            </c:choose>
                                            
                                        </div>
                                        <div class="dropdown d-inline-block" style="margin-left: 15px;">
                                            <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button" style="padding-right: 12px;padding-left: 15px;margin-left: 0;">Seleziona periodo    </button>
                                            <div role="menu" class="dropdown-menu">
                                                <a role="presentation" class="dropdown-item" onclick="VisualizzaTutti()">Tutti</a>
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
                            <div class="col" style="padding: 10px;" id="elements-box">
                                <c:if test="${requestScope.elements.size() == 0}">
                                    <div role="alert" class="alert alert-secondary">
                                        <span>
                                            <strong>Nessun elemento da visualizzare</strong>
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
        <c:choose>
            <c:when test="${requestScope.mostra=='all'}">
                VisualizzaTutti();
            </c:when>
            <c:when test="${requestScope.mostra=='active'}">
                VisualizzaAttivi();
            </c:when>
        </c:choose>
        
        var oggi = new Date();
        var month = ((12 + oggi.getMonth() - 1) % 12) + 1;
        var UnMeseFa = new Date(oggi.getFullYear() + '-' + month + '-' + oggi.getDate());
        console.log(UnMeseFa);
        var UnAnnoFa = new Date((oggi.getFullYear()-1) + '-' + (oggi.getMonth()+1) + '-' + oggi.getDate());
        console.log(UnAnnoFa);
        var UnaSettimanaFa = new Date(oggi.valueOf()-864E5*7);
        console.log(UnaSettimanaFa);
        
        function Stampa(obj)
        {
            <c:if test='${requestScope.tipolista=="esami"}'>
                return stampaEsame(obj);
            </c:if>
            <c:if test='${requestScope.tipolista == "visite specialistiche"}'>
                return stampaVisita(obj);
            </c:if>
            <c:if test='${requestScope.tipolista == "ricette"}'>
                return stampaRicetta(obj);
            </c:if>
        }
    
        function stampaEsame(obj)
        {
            var strs = "";
            strs += '<div class="alert ';

            if(obj.active()) strs += 'alert-primary';
            else strs += 'alert-secondary';

            strs += '" role="alert" style="padding: 8px 20px;padding-right: 14px;padding-left: 14px;margin-bottom: 10px;">' +
                    '<h5 style="margin-bottom: 5px;">' + obj.name + '</h5>';

            if(obj.active()) strs += '<p class="text-danger" style="margin-bottom: 3px;"><strong>Esame da effettuare</strong></p>';
            else strs += '<p style="margin-bottom: 5px;">Esame effettuato il ' + FormatDate(new Date(obj.madedate)) + ' presso ' + obj.madeby + '</p>';

            strs += '<p style="margin-bottom: 0px;">Prescritto il ' + FormatDate(new Date(obj.prescrdate)) + ' da';
            strs += (obj.prescrby.includes("ULSS")) ? ' ' : 'l dott. ';
            strs += obj.prescrby;
            strs += '</p><p class="text-right" style="margin-bottom: 0;"><a href="'+
                    '${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioEsame?examId=' + obj.id +
                    '"><span style="text-decoration: underline;">dettagli</span></a></p></div>';
            
            return strs;
        }
        
        function stampaRicetta(obj)
        {
            var strs = "";
            strs += '<div class="alert ';

            if(obj.active()) strs += 'alert-primary';
            else strs += 'alert-secondary';

            strs += '" role="alert" style="padding: 8px 20px;padding-right: 14px;padding-left: 14px;margin-bottom: 10px;">' +
                    '<h5 style="margin-bottom: 5px;">' + obj.name + '</h5>';

            if(obj.active()) strs += '<p class="text-success" style="margin-bottom: 3px;"><strong>Ricetta ancora valida</strong></p>';
            else strs += '<p style="margin-bottom: 5px;">Ricetta evasa il ' + FormatDate(new Date(obj.madedate)) + ' presso ' + obj.madeby + '</p>';

            strs += '<p style="margin-bottom: 0px;">Prescritto il ' + FormatDate(new Date(obj.prescrdate)) + ' da ' +obj.prescrby + '</p>' +
                    '<p class="text-right" style="margin-bottom: 0;"><a href="'+
                    '${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioRicetta?prescriptionId=' + obj.id +
                    '"><span style="text-decoration: underline;">dettagli</span></a></p></div>';
            
            return strs;
        }
        
        function stampaVisita(obj)
        {
            var strs = "";
            strs += '<div class="alert ';

            if(obj.active()) strs += 'alert-primary';
            else strs += 'alert-secondary';

            strs += '" role="alert" style="padding: 8px 20px;padding-right: 14px;padding-left: 14px;margin-bottom: 10px;">' +
                    '<h5 style="margin-bottom: 5px;">' + obj.name + '</h5>';

            if(obj.active()) strs += '<p class="text-danger" style="margin-bottom: 3px;"><strong>Visita da effettuare</strong></p>';
            else strs += '<p style="margin-bottom: 5px;">Visita effettuata il ' + FormatDate(new Date(obj.madedate)) + ' presso ' + obj.madeby + '</p>';

            strs += '<p style="margin-bottom: 0px;">Prescritto il ' + FormatDate(new Date(obj.prescrdate)) + ' da';
            strs += (obj.prescrby.includes("ULSS")) ? ' ' : 'l dott. ';
            strs += obj.prescrby;
            strs += '</p><p class="text-right" style="margin-bottom: 0;"><a href="'+
                    '${pageContext.servletContext.contextPath}/restricted/paziente/DettaglioVisitaSpeck?speckId=' + obj.id +
                    '"><span style="text-decoration: underline;">dettagli</span></a></p></div>';
            
            return strs;
        }
        
        function FormatDate(xxdata)
        {
            var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
            var monthIndex = xxdata.getMonth();
            return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
        }
        
        function VisualizzaTutti()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_elem.length; i++) { 
                    strs += Stampa(lista_elem[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento da visualizzare");
            }
        }
        
        function VisualizzaAttivi()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_elem.length; i++) { 
                    if(lista_elem[i].active())
                        strs += Stampa(lista_elem[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento da visualizzare");
            }
        }
        
        function VisualizzaNonAttivi()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_elem.length; i++) { 
                    if(!lista_elem[i].active())
                        strs += Stampa(lista_elem[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento da visualizzare");
            }
        }
        
        function UltimaSettimana()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_elem.length; i++) {
                    if((new Date(lista_elem[i].prescrdate)) >= UnaSettimanaFa)
                        strs += Stampa(lista_elem[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento nell'ultima settimana da visualizzare");
            }
        }
        
        function UltimoMese()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_elem.length; i++) {
                    if((new Date(lista_elem[i].prescrdate)) >= UnMeseFa)
                        strs += Stampa(lista_elem[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento nell'ultimo mese da visualizzare");
            }
        }
        
        function UltimoAnno()
        {
            if(hasenso)
            {
                var strs = "";
                for (i = 0; i < lista_elem.length; i++) {
                    if((new Date(lista_elem[i].prescrdate)) >= UnAnnoFa)
                        strs += Stampa(lista_elem[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento nell'ultimo anno da visualizzare");
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
                for (i = 0; i < lista_elem.length; i++) {
                    if((new Date(lista_elem[i].prescrdate)) >= da && (new Date(lista_elem[i].prescrdate)) <= a)
                        strs += Stampa(lista_elem[i]);
                }
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento nel periodo selezionato da visualizzare");
            }
        }
        
        function cerca()
        {
            if(hasenso)
            {
                var find = document.getElementById("txtcerca").value.toLowerCase();
                var strs = "";
                for(i=0; i<lista_elem.length; i++)
                    if(lista_elem[i].name.toLowerCase().includes(find))
                        strs += Stampa(lista_elem[i]);
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults("Nessun elemento da visualizzare");
            }
        }
        
        function chiudi_selezione_data()
        {
            document.getElementById("modal-data").classList.remove("show");
            document.getElementById("modal-data").style.display = "none";
        }
        
        function stampaNoResults(strs)
        {
            var st = '<div role="alert" class="alert alert-secondary"><span><strong>' + strs + '</strong></span></div>';
            document.getElementById('elements-box').innerHTML = st;
        }
        
        function stampaResults(strs)
        {
            document.getElementById('elements-box').innerHTML = strs;
        }
    </script>
    <script src="../../assetsPazienti/js/script.min.js"></script>
</body>

</html>