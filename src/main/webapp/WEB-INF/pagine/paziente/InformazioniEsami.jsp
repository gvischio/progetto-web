<%-- 
    Document   : InformazioniEsami
    Created on : 4-set-2019, 17.57.31
    Author     : Giacomo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="/WEB-INF/pagine/error.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Servizio Sanitario</title>
    <link rel="icon" href="../../assetsHome/img/favicon.ico">
    <link rel="shortcut icon" href="../../assetsHome/img/favicon.ico">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="../../assets/css/styles.min.css">
</head>

<script>
    var lista_categ = [];
    var lista = [];
    
    <c:if test="${requestScope.categorie.size() > 0}">
        <c:forEach begin="0" end="${requestScope.categorie.size()-1}" var="i">
            lista_categ[${requestScope.categorie.get(i).id}] = "${requestScope.categorie.get(i).name}";
        </c:forEach>
    </c:if>
    
    var j = 0;
    <c:if test="${requestScope.esami.size() > 0}">
        <c:forEach begin="0" end="${requestScope.esami.size()-1}" var="i">
            lista[j] = {
                nome: "${requestScope.esami.get(i).name}",
                descr: "${requestScope.esami.get(i).description}",
                categ: lista_categ[${requestScope.esami.get(i).category}]
            };
            j++;
        </c:forEach>
    </c:if>
        
    <c:if test="${requestScope.visite.size() > 0}">
        <c:forEach begin="0" end="${requestScope.visite.size()-1}" var="i">
            lista[j] = {
                nome: "${requestScope.visite.get(i).name}",
                descr:"${requestScope.visite.get(i).description}",
                categ: lista_categ[${requestScope.visite.get(i).category}]
            };
            j++;
        </c:forEach>
    </c:if>
</script>

<body style="background-color: #f4f4f4;">
    <jsp:include page='headerPaziente.jsp'/>
    
    <div class="container container-main">
        <div class="row">
            <div class="col-xl-10 offset-xl-1">
                <div class="card">
                    <div class="card-header">
                        <h4 class="mb-0">Ricerca esame</h4>
                    </div>
                    <div class="card-body">
                        <p class="card-text" style="margin: 0px 0px 10px;">
                            In questa sezione puoi cercare informazioni a riguardo gli esami e le visite specialistiche offerte dal Portale Servizio Sanitario Italiano
                        </p>
                        <p>Per iniziare, digita una qualunque parola chiave (es: emoglobina), &nbsp;poi clicca su un elemento per visualizzarne i dettagli</p>
                        <div class="input-group mb-3" style="width: 90%; margin: 0 auto; position: relative; margin-bottom: 10px;">
                            <input type="text" class="form-control" placeholder="Digita qui..." id="search" onkeyup="cerca(this.value)" style="outline: none">
                                <div class="input-group-append">
                                  <button class="btn btn-outline-secondary" type="button"onclick="cerca(document.getElementById('search').value)">Cerca</button>
                                </div>
                        </div>
                        <div style="max-height: 500px;padding: 5px;overflow: auto;" id="container-elementi">
                            <div role="alert" class="alert alert-secondary text-right shadow-sm" style="padding: 5px 10px;background-color: rgb(245,245,245); cursor: pointer; margin-right: 4px" onclick="open_paziente_alert(this)">
                                <p class="text-left" style="margin-bottom: 7px;margin-top: 3px;margin-left: 15px;font-size: 18px;">
                                    <strong>Nome esame</strong>
                                </p>
                                <div class="table-responsive text-left" style="display:none">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td style="width: 35%;">
                                                    <strong>Descrizione</strong>
                                                </td>
                                                <td>Lorem ipsum</td>
                                            </tr>
                                            <tr>
                                                <td style="width: 35%;">
                                                    <strong>Categoria</strong>
                                                </td>
                                                <td>Nome categoria</td>
                                            </tr>
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
    <script>
        stampaNoSearch();
        
        function cerca(find)
        {
            var strs = "";
            find = find.trim().toLowerCase();
            var ok = new Set();
            if(find.length > 0)
            {
                for (let item of lista)
                    if(item.nome.toLowerCase().includes(find) || item.categ.toLowerCase().includes(find))
                        strs += stampaElemento(item);
                if(strs != "")
                    stampaResults(strs);
                else
                    stampaNoResults();
            }
            else
                stampaNoSearch();
        }
        
        function stampaElemento(obj)
        {
            return '<div role="alert" class="alert alert-secondary text-right shadow-sm" style="padding: 5px 10px;background-color: rgb(245,245,245); cursor: pointer; margin-right: 4px" onclick="open_paziente_alert(this)">' +
                   '     <p class="text-left" style="margin-bottom: 7px;margin-top: 3px;margin-left: 15px;font-size: 18px;">' +
                   '         <strong>' + obj.nome + '</strong> </p>' +
                   '     <div class="table-responsive text-left" style="display:none">' +
                   '         <table class="table"> <tbody> <tr>' +
                   '                     <td style="width: 35%;"> <strong>Descrizione</strong> </td>' +
                   '                     <td>' + obj.descr + '</td>' +
                   '                 </tr> <tr>' +
                   '                     <td style="width: 35%;"> <strong>Categoria</strong> </td>' +
                   '                     <td>' + obj.categ + '</td>' +
                   '                 </tr> </tbody> </table> </div> </div>';
        }
        
        function stampaResults(strs) { document.getElementById("container-elementi").innerHTML = strs; }
            
        function stampaNoResults() { document.getElementById("container-elementi").innerHTML = "<div role='alert' class='alert alert-secondary' style='margin-bottom: 10px;padding: 8px 20px;'>Nessun risultato</div>"; }

        function stampaNoSearch() { document.getElementById("container-elementi").innerHTML = "<span style='font-style: italic'>Inserire almeno un carattere per avviare la ricerca</span>" }
    </script>
    <script src="../../assets/js/script.min.js"></script>
</body>

</html>
