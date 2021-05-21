<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="/WEB-INF/pagine/error.jsp"%>
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
    var lista = [];
    <c:if test="${requestScope.listapazienti.size()>0}">
        var minId = ${requestScope.listapazienti.get(0).id};
        var maxId = ${requestScope.listapazienti.get(requestScope.listapazienti.size()-1).id};
        <c:forEach begin="0" end="${requestScope.listapazienti.size()-1}" var="i">
            lista [${requestScope.listapazienti.get(i).id}] = {
                id: '${requestScope.listapazienti.get(i).id}',
                ssn: '${requestScope.listapazienti.get(i).ssn}',
                firstname: '${requestScope.listapazienti.get(i).firstname}',
                lastname: '${requestScope.listapazienti.get(i).lastname}',
                date: '${requestScope.listapazienti.get(i).birthday}'
            };
        </c:forEach>    
    </c:if>
</script>

<body>
        <jsp:include page="headerFarmacia.jsp"/>
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-4 col-xl-4 offset-xl-0">
                <div class="card">
                    <div class="card-body text-center">
                        <h3>Farmacia di ${requestScope.farmacia.city} </h3><img src="../../assets/img/logo-farmacia-caduceo-servizi.jpg" style="width: 100px;height: 100px;margin-bottom: 15px;">
                        <div class="table-responsive text-left" style="margin-bottom: 4px;">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td style="width: 50%;"><strong>Provincia</strong></td>
                                        <td>${requestScope.district.city}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <a href="${pageContext.servletContext.contextPath}/restricted/farmacia/RicetteEvaseFarmaciaToXLS" >
                            <div class="media text-left align-self-center"><img src="../../assets/img/Excel.png" class="mr-3 align-self-center" style="width: 40px;margin-right: 10px;cursor: pointer;">
                                <div class="media-body">
                                    <p style="margin-bottom: 0px;padding-top: 10px;padding-bottom: 10px;">Esportazione dell'elenco delle ricette evase da questa farmacia</p>
                                </div>

                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card" style="margin-bottom: 10px;">
                    <div class="card-header">
                        <h4 class="mb-0">Evasione ricetta</h4>
                    </div>
                    <div class="card-body">
                        <h5 class="text-secondary card-subtitle">Ricerca utente per codice fiscale</h5>
                        <div style="padding: 10px; margin-bottom: 10px; text-align: right">
                            <input id="incf" class="form-control" type="text" required="" placeholder="Inserisci codice fiscale" maxlength="16" minlength="16" autocomplete="off" style="margin-bottom: 10px;">
                            <button class="btn btn-primary text-right" style="margin-right: 10px;padding: 8px 20px;" onclick="cercaCF()">Cerca</button>
                        </div>
                        <h5 class="text-secondary card-subtitle">Ricerca utente per nome e cognome</h5>
                        <div style="padding: 10px; text-align: right">
                            <input id="inname" class="form-control" type="text" placeholder="Inserire nome..." style="margin-bottom: 5px;" onkeyup="cercaNC()">
                            <input id="insurn" class="form-control" type="text" placeholder="Inserire cognome..." style="margin-bottom: 10px;" onkeyup="cercaNC()">
                            <button class="btn btn-primary text-right" style="margin-right: 10px;padding: 8px 20px;" onclick="cercaNC()">Cerca</button>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Risultati della ricerca</h5>
                        <div id="risultati-ricerca" style="max-height: 200px;overflow: auto;">
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        stampaNoSearch();
        
        function cercaCF()
        {
            var strs = "";
            var find = document.getElementById("incf").value;
            find = find.trim().toLowerCase();
            if(find.length > 0)
            {
                for(i=minId; i<maxId; i++)
                    if(lista[i] != undefined)
                        if(lista[i].ssn.toLowerCase().includes(find))
                            strs += '<div class="alert alert-primary" role="alert" style="margin-bottom: 10px; padding: 8px 20px;">' +
                                        '<div class="row">' +
                                            '<div class="col"><span><strong>' + lista[i].firstname + ' ' + lista[i].lastname + '</strong>  ' + lista[i].date + '</span></div>' +
                                            '<div class="col-md-4 col-lg-4 col-xl-3 text-center">' +
                                                '<a style="text-decoration: underline;" href="${pageContext.servletContext.contextPath}/restricted/farmacia/VisualizzaRicette?patId=' + lista[i].id + '">visualizza ricette</a>' +
                                            '</div> </div> </div>';
                if(strs != "")
                    stampaResults(strs, "risultati-ricerca");
                else
                    stampaNoResults("Inserire un codice fiscale valido", "risultati-ricerca");
            }
            else
                stampaNoSearch("risultati-ricerca");
        }
        
        function cercaNC()
        {
            var strs = "";
            var findname = document.getElementById("inname").value;
            findname = findname.trim().toLowerCase();
            
            var findsurn = document.getElementById("insurn").value;
            findsurn = findsurn.trim().toLowerCase();
            if(findname.length > 0 || findsurn.length > 0)
            {
                for(i=minId; i<=maxId; i++)
                    if(lista[i] != undefined)
                        if(lista[i].firstname.toLowerCase().includes(findname) && lista[i].lastname.toLowerCase().includes(findsurn))
                            strs += '<div class="alert alert-primary" role="alert" style="margin-bottom: 10px;padding: 8px 20px;">' +
                                        '<div class="row">' +
                                            '<div class="col"><span><strong>' + lista[i].firstname + ' ' + lista[i].lastname + '</strong>&nbsp;&nbsp;' + lista[i].date + '</span></div>' +
                                            '<div class="col-md-4 col-lg-4 col-xl-3 text-center">' +
                                                '<a style="text-decoration: underline;" href="${pageContext.servletContext.contextPath}/restricted/farmacia/VisualizzaRicette?patId=' + lista[i].id + '">visualizza ricette</a>' +
                                            '</div> </div> </div>';
                if(strs != "")
                    stampaResults(strs, "risultati-ricerca");
                else
                    stampaNoResults("La ricerca non ha prodotto nessun risultato", "risultati-ricerca");
            }
            else
                stampaNoSearch("risultati-ricerca");
        }

        function stampaResults(strs, tbodyname) { document.getElementById(tbodyname).innerHTML = strs; }

        function stampaNoResults(str, tbodyname) { document.getElementById(tbodyname).innerHTML = "<div role='alert' class='alert alert-secondary' style='margin-bottom: 10px;padding: 8px 20px;'>" + str + "</div>"; }

        function stampaNoSearch(tbodyname) { document.getElementById(tbodyname).innerHTML = "<div role='alert' class='alert alert-secondary' style='margin-bottom: 10px;padding: 8px 20px;'>Inserire un parametro di ricerca</div>" }
        </script>
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>