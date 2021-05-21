<%-- 
    Document   : NuovaDisposizione
    Created on : 21-ago-2019, 16.59.58
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
    var listaesami = [];
    <c:if test="${requestScope.listaesami.size()!=0}">
        <c:forEach begin="0" end="${requestScope.listaesami.size()-1}" var="i">
            listaesami [${requestScope.listaesami.get(i).id}] = {
                id: '${requestScope.listaesami.get(i).id}',
                name: '${requestScope.listaesami.get(i).name}',
                description: '${requestScope.listaesami.get(i).description}',
                category: '${requestScope.listaesami.get(i).category}'
            }
        </c:forEach>    
    </c:if>
        
    var listavisite = [];
    <c:if test="${requestScope.listavisite.size()!=0}">
        <c:forEach begin="0" end="${requestScope.listavisite.size()-1}" var="i">
            listavisite [${requestScope.listavisite.get(i).id}] = {
                id: '${requestScope.listavisite.get(i).id}',
                name: '${requestScope.listavisite.get(i).name}',
                description: '${requestScope.listavisite.get(i).description}',
                category: '${requestScope.listavisite.get(i).category}'
            }
        </c:forEach>    
    </c:if>
        
    var listafarmaci = [];
    <c:if test="${requestScope.listafarmaci.size()!=0}">
        <c:forEach begin="0" end="${requestScope.listafarmaci.size()-1}" var="i">
            listafarmaci [${requestScope.listafarmaci.get(i).id}] = {
                id: '${requestScope.listafarmaci.get(i).id}',
                name: '${requestScope.listafarmaci.get(i).name}',
                description: '${requestScope.listafarmaci.get(i).description}'
            }
        </c:forEach>    
    </c:if>
    
</script>

<body>
        <jsp:include page="headerBaseSpeck.jsp"/>
    <div class="container container-main">
        <div class="row">
            <div class="col-lg-8 col-xl-8 align-self-center">
                <h2 class="text-left" style="margin-bottom: 12px;"> Visita del ${requestScope.visit.visdate} &gt; nuova prescrizione</h2>
            </div>
            <div class="col text-right align-self-center"></div>
        </div>
        <div class="row">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <h5 class="card-subtitle mb-2">Scegli il tipo di prescrizione</h5>
                        <button type="button" class="btn btn-primary btn-lg" style="margin: 1%;width: 31%;" onclick="new_prescr_apri_esame()" id="button-esame">ESAME</button>
                        <button class="btn btn-primary btn-lg" type="button" id="button-ricetta" style="margin: 1%;width: 31%;" onclick="new_prescr_apri_ricetta()">RICETTA</button>
                        <button class="btn btn-primary btn-lg" type="button" id="button-visita" style="margin: 1%;width: 31%;" onclick="new_prescr_apri_visita()">VISITA SPECIALISTICA</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-esame" style="/*display: none;*/">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <h5 class="card-subtitle mb-2" style="margin-bottom: 15px;">Seleziona esame da prescrivere</h5>
                        <div class="input-group mb-3" style="width: 90%; margin: 10px auto;">
                            <input type="text" class="form-control" placeholder="Ricerca esame..." style="outline: none; margin: 0" onkeyup="ricercaEsami(this.value, listaesami, 'tbodyesami');">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" style="margin: 0">Cerca</button>
                            </div>
                        </div>
                        <div style="max-height: 450px; overflow: auto;">
                            <div class="table-responsive" style="padding-right: 0px; margin-bottom: -15px;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;">ID esame</th>
                                            <th style="width: 25%;">Nome</th>
                                            <th style="width: 35%;">Descrizione</th>
                                            <th style="width: 20%;">Categoria</th>
                                            <th style="width: 10%;"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbodyesami">
                                        <%-- GIACOMO SA (esame) --%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-ricetta" style="/*display: none;*/">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <h5 class="card-subtitle mb-2" style="margin-bottom: 15px;">Seleziona farmaco da prescrivere</h5>
                        <div class="input-group mb-3" style="width: 90%; margin: 10px auto;">
                            <input type="text" class="form-control" placeholder="Ricerca farmaco..." style="outline: none; margin: 0" onkeyup="ricercaFarmaci(this.value, listafarmaci, 'tbodyfarmaci');">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" style="margin: 0">Cerca</button>
                            </div>
                        </div>
                        <div style="max-height: 450px; overflow: auto;">
                            <div class="table-responsive" style="padding-right: 0; margin-bottom: -15px;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;">ID farmaco</th>
                                            <th style="width: 25%;">Nome</th>
                                            <th style="width: 35%;">Descrizione</th>
                                            <th style="width: 10%;"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbodyfarmaci">
                                        <%-- GIACOMO SA (farmaco) --%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-visita" style="/*display: none;*/">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <h5 class="card-subtitle mb-2" style="margin-bottom: 15px;">Seleziona visita specialistica da prescrivere</h5>
                        <div class="input-group mb-3" style="width: 90%; margin: 10px auto;">
                            <input type="text" class="form-control" placeholder="Ricerca visita..." style="outline: none; margin: 0" onkeyup="ricercaVisite(this.value, listavisite, 'tbodyvisite');">
                            <div class="input-group-append">
                                <button class="btn btn-outline-secondary" type="button" style="margin: 0">Cerca</button>
                            </div>
                        </div>
                        <div style="max-height: 450px; overflow: auto;">
                            <div class="table-responsive" style="padding-right: 0px; margin-bottom: -15px;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th style="width: 10%;">ID visita</th>
                                            <th style="width: 25%;">Nome</th>
                                            <th style="width: 35%;">Descrizione</th>
                                            <th style="width: 20%;">Categoria</th>
                                            <th style="width: 10%;"></th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbodyvisite">
                                        <%-- GIACOMO SA (visita) --%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-conf-esame">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body text-center">
                        <h5 class="text-left card-subtitle mb-2">Conferma prescrizione esame</h5>
                        <h6 class="text-left text-muted" style="margin-right: 0px;margin-left: 10px;">Esame prescritto</h6>
                        <div class="alert alert-secondary text-left" role="alert" style="margin-right: 5px;margin-bottom: 5px;margin-left: 20px;" id="alertEsame"></div>
                        <form action="${pageContext.servletContext.contextPath}/restricted/dottore/base/SalvaNuovaDisposizione?visitId=${requestScope.visit.id}" method="post" id="form" name="form">
                            <input class="form-control" type="hidden" name="prescr_type" value="E">
                            <input class="form-control" type="hidden" name="prescr_id" value="1" id="prescr_id_exam">
                            <a class="btn btn-danger btn-lg" role="button" href="${pageContext.servletContext.contextPath}/restricted/dottore/base/CompilaVisitaBase?visitId=${requestScope.visitid}" style="padding: 8px 18px;margin: 5px 10px;">annulla</a>
                            <button class="btn btn-warning btn-lg" type="button" style="margin: 5px 10px;padding: 8px 18px;" onclick="new_prescr_apri_esame()">modifica</button>
                            <button class="btn btn-success btn-lg" type="submit" style="padding: 8px 18px;margin: 5px 10px;">conferma</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-conf-ricetta">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body text-center">
                        <h5 class="text-left card-subtitle mb-2">Conferma prescrizione farmaco</h5>
                        <h6 class="text-left text-muted" style="margin-right: 0px;margin-left: 10px;">Farmaco prescritto</h6>
                        <div class="alert alert-secondary text-left" role="alert" style="margin-right: 5px;margin-bottom: 5px;margin-left: 20px;" id="alertRicetta"></div>
                        <h6 class="text-left text-muted" style="margin-right: 0px;margin-left: 10px;margin-top: 15px;">Quantità da prescrivere</h6>
                        <form action="${pageContext.servletContext.contextPath}/restricted/dottore/base/SalvaNuovaDisposizione?visitId=${requestScope.visit.id}" method="post" id="form" name="form">
                            <input class="form-control" type="hidden" name="prescr_type" value="R">
                            <input class="form-control form-control" type="number" name="prescr_quantity" value="1" min="0" max="10" step="1" style="width: 40%;margin-left: 30px;margin-bottom: 10px;">
                            <input class="form-control" type="hidden" name="prescr_id" value="1" id="prescr_id_pres">
                            <a class="btn btn-danger btn-lg" role="button" href="${pageContext.servletContext.contextPath}/restricted/dottore/base/CompilaVisitaBase?visitId=${requestScope.visitid}" style="padding: 8px 18px;margin: 5px 10px;">annulla</a>
                            <button class="btn btn-warning btn-lg" type="button" style="margin: 5px 10px;padding: 8px 18px;" onclick="new_prescr_apri_ricetta()">modifica</button>
                            <button class="btn btn-success btn-lg" type="submit" style="padding: 8px 18px;margin: 5px 10px;">conferma</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-conf-visita">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body text-center">
                        <h5 class="text-left card-subtitle mb-2">Conferma prescrizione visita</h5>
                        <h6 class="text-left text-muted" style="margin-right: 0px;margin-left: 10px;">Visita prescritta</h6>
                        <div class="alert alert-secondary text-left" role="alert" style="margin-right: 5px;margin-bottom: 5px;margin-left: 20px;" id="alertVisita"></div>
                        <form action="${pageContext.servletContext.contextPath}/restricted/dottore/base/SalvaNuovaDisposizione?visitId=${requestScope.visit.id}" method="post" id="form" name="form">
                            <input class="form-control" type="hidden" name="prescr_type" value="V">
                            <input class="form-control" type="hidden" name="prescr_id" value="1" id="prescr_id_visi">
                            <a class="btn btn-danger btn-lg" role="button" href="${pageContext.servletContext.contextPath}/restricted/dottore/base/CompilaVisitaBase?visitId=${requestScope.visitid}" style="padding: 8px 18px;margin: 5px 10px;">annulla</a>
                            <button class="btn btn-warning btn-lg" type="button" style="margin: 5px 10px;padding: 8px 18px;" onclick="new_prescr_apri_visita()">modifica</button>
                            <button class="btn btn-success btn-lg" type="submit" style="padding: 8px 18px;margin: 5px 10px;">conferma</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.getElementById('row-esame').style.display = 'none';
            document.getElementById('row-ricetta').style.display = 'none';
            document.getElementById('row-visita').style.display = 'none';
            document.getElementById('row-conf-esame').style.display = 'none';
            document.getElementById('row-conf-ricetta').style.display = 'none';
            document.getElementById('row-conf-visita').style.display = 'none';
            
            stampaNoSearch("tbodyesami");
            stampaNoSearch("tbodyfarmaci");
            stampaNoSearch("tbodyvisite");

            function new_prescr_apri_esame()
            {
                document.getElementById('button-esame').classList.add('btn-success');
                document.getElementById('button-ricetta').classList.add('btn-secondary');
                document.getElementById('button-visita').classList.add('btn-secondary');
                document.getElementById('button-ricetta').classList.remove('btn-success');
                document.getElementById('button-visita').classList.remove('btn-success');

                document.getElementById('row-esame').style.display = 'block';
                document.getElementById('row-ricetta').style.display = 'none';
                document.getElementById('row-visita').style.display = 'none';

                document.getElementById('row-conf-esame').style.display = 'none';
                document.getElementById('row-conf-ricetta').style.display = 'none';
                document.getElementById('row-conf-visita').style.display = 'none';
            }

            function new_prescr_apri_ricetta()
            {
                document.getElementById('button-esame').classList.add('btn-secondary');
                document.getElementById('button-ricetta').classList.add('btn-success');
                document.getElementById('button-visita').classList.add('btn-secondary');
                document.getElementById('button-esame').classList.remove('btn-success');
                document.getElementById('button-visita').classList.remove('btn-success');

                document.getElementById('row-esame').style.display = 'none';
                document.getElementById('row-ricetta').style.display = 'block';
                document.getElementById('row-visita').style.display = 'none';

                document.getElementById('row-conf-esame').style.display = 'none';
                document.getElementById('row-conf-ricetta').style.display = 'none';
                document.getElementById('row-conf-visita').style.display = 'none';
            }

            function new_prescr_apri_visita()
            {
                document.getElementById('button-esame').classList.add('btn-secondary');
                document.getElementById('button-ricetta').classList.add('btn-secondary');
                document.getElementById('button-visita').classList.add('btn-success');
                document.getElementById('button-esame').classList.remove('btn-success');
                document.getElementById('button-ricetta').classList.remove('btn-success');

                document.getElementById('row-esame').style.display = 'none';
                document.getElementById('row-ricetta').style.display = 'none';
                document.getElementById('row-visita').style.display = 'block';

                document.getElementById('row-conf-esame').style.display = 'none';
                document.getElementById('row-conf-ricetta').style.display = 'none';
                document.getElementById('row-conf-visita').style.display = 'none';
            }

            function new_prescr_conf_esame(id)
            {
                document.getElementById('row-esame').style.display = 'none';
                document.getElementById('row-conf-esame').style.display = 'block';
                document.getElementById('prescr_id_exam').value = id;
                document.getElementById("alertEsame").innerHTML = "<strong>" + listaesami[id].name + "</strong> " + listaesami[id].description;
            }

            function new_prescr_conf_ricetta(id)
            {
                document.getElementById('row-ricetta').style.display = 'none';
                document.getElementById('row-conf-ricetta').style.display = 'block';
                document.getElementById('prescr_id_pres').value = id;
                document.getElementById("alertRicetta").innerHTML = "<strong>" + listafarmaci[id].name + "</strong> " + listafarmaci[id].description;
            }

            function new_prescr_conf_visita(id)
            {
                document.getElementById('row-visita').style.display = 'none';
                document.getElementById('row-conf-visita').style.display = 'block';
                document.getElementById('prescr_id_visi').value = id;
                document.getElementById("alertVisita").innerHTML = "<strong>" + listavisite[id].name + "</strong> " + listavisite[id].description;
            }
            
            // ricerca nella lista
            function ricercaEsami(find, lista, tbodyname)
            {
                var strs = "";
                find = find.trim().toLowerCase();
                if(find.length > 0)
                {
                    for(i=2; i<lista.length+2; i++)
                        if(lista[i] != undefined)
                            if(lista[i].name.toLowerCase().includes(find))
                                strs += "<tr><td>" + lista[i].id + "</td><td>" + lista[i].name + "</td><td>" + lista[i].description + "</td><td>" + lista[i].category + "</td>" +
                                        '<td class="text-center"><button class="btn btn-primary" type="button" onclick="new_prescr_conf_esame(' + lista[i].id + ')">scegli</button></td></tr>'; 
                    if(strs != "")
                        stampaResults(strs, tbodyname);
                    else
                        stampaNoResults(tbodyname);
                }
                else
                    stampaNoSearch(tbodyname);
            }
            
            function ricercaVisite(find, lista, tbodyname)
            {
                var strs = "";
                find = find.trim().toLowerCase();
                if(find.length > 0)
                {
                    for(i=2; i<lista.length+2; i++)
                        if(lista[i] != undefined)
                            if(lista[i].name.toLowerCase().includes(find))
                                strs += "<tr><td>" + lista[i].id + "</td><td>" + lista[i].name + "</td><td>" + lista[i].description + "</td><td>" + lista[i].category + "</td>" +
                                    '<td class="text-center"><button class="btn btn-primary" type="button" onclick="new_prescr_conf_visita(' + lista[i].id + ')">scegli</button></td></tr>'; 
                    if(strs != "")
                        stampaResults(strs, tbodyname);
                    else
                        stampaNoResults(tbodyname);
                }
                else
                    stampaNoSearch(tbodyname);
            }
            
            function ricercaFarmaci(find, lista, tbodyname)
            {
                var strs = "";
                find = find.trim().toLowerCase();
                if(find.length > 0)
                {
                    for(i=2; i<lista.length+2; i++)
                        if(lista[i] != undefined)
                            if(lista[i].name.toLowerCase().includes(find))
                                strs += "<tr><td>" + lista[i].id + "</td><td>" + lista[i].name + "</td><td>" + lista[i].description + "</td>" +
                                    '<td class="text-center"><button class="btn btn-primary" type="button" onclick="new_prescr_conf_ricetta(' + lista[i].id + ')">scegli</button></td></tr>'; 
                    if(strs != "")
                        stampaResults(strs, tbodyname);
                    else
                        stampaNoResults(tbodyname);
                }
                else
                    stampaNoSearch(tbodyname);
            }
            
            function stampaResults(strs, tbodyname) { document.getElementById(tbodyname).innerHTML = strs; }
            
            function stampaNoResults(tbodyname) { document.getElementById(tbodyname).innerHTML = "<tr><td colspan='3'>Nessun risultato</td></tr>"; }
            
            function stampaNoSearch(tbodyname) { document.getElementById(tbodyname).innerHTML = "<tr><td colspan='3'>Inserire almeno un carattere per avviare la ricerca</td></tr>" }
        </script>
    </div>
    <script src="../../../assets/js/jquery.min.js"></script>
    <script src="../../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../assets/js/script.min.js"></script>
</body>

</html>
