<%-- 
    Document   : CompilaVisitaSpeck
    Created on : 21-ago-2019, 16.32.12
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

<body style="background-color: #f4f4f4;">
        <jsp:include page="headerBaseSpeck.jsp"/>
    <div class="container container-main">
        <div class="row" style="margin-bottom: 5px;">
            <div class="col-lg-8 col-xl-8 align-self-center">
                <h1 class="text-left" style="margin-bottom: 12px;">${requestScope.speckvisita.name}</h1>
            </div>
                <div class="col text-right align-self-center">
                    <c:if test="${requestScope.speckvisita.paid == 0}">
                        <a class="btn btn-success btn-lg shadow" role="button" style="font-size: 19px;" href="${pageContext.servletContext.contextPath}/restricted/dottore/specialista/DisposizionePagata?id=${requestScope.speckvisita.id}&tipo=speckvisit">Segna visita come effettuata</a>
                    </c:if>
                </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-xl-4">
                <div class="card border rounded shadow-sm" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h4 class="text-left mb-0">Dettagli del paziente</h4>
                    </div>
                    <div class="card-body" style="padding: 0;padding-right: 10px;padding-left: 10px;">
                        <div class="row">
                            <div class="col-12 col-sm-4 col-md-3 col-lg-12 col-xl-12 text-center"><img class="rounded border rounded-circle border-dark align-self-center" src="${requestScope.element.path}" style="width: 95px;height: 95px;margin: 10px;border-width: 2px !important;/*vertical-align: middle;*/">
                            </div>
                            <div class="col text-right"
                                style="padding-top: 10px;">
                                <div class="table-responsive text-left">
                                    <table class="table">
                                        <thead>
                                            <tr></tr>
                                        </thead>
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
                                                <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/"><strong>Codice Fiscale</strong></td>
                                                <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/">${requestScope.paziente.ssn}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Nato il</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.paziente.birthday}</td>
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
                                            <tr>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td><strong>Ultima visita</strong></td>
                                                <td style="cursor: pointer;"><a href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioVisitaBaseDottore?visitId=${requestScope.ultimaVisita.id}"><span style="text-decoration: underline;">${requestScope.ultimaVisita.visdate}</span></a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div><a class="btn btn-light text-center" role="button" href="${pageContext.servletContext.contextPath}/restricted/dottore/DettaglioPaziente?pazienteId=${requestScope.paziente.id}" style="margin: 0px auto;margin-bottom: 10px;margin-top: 5px;margin-right: 10px;">Profilo completo</a></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h4 class="mb-0">Informazioni sulla visita</h4>
                    </div>
                    <div class="card-body" style="padding-bottom: 5px;margin-top: 0px;padding-top: 10px;">
                        <div class="table-responsive">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td><strong>Data di prescrizione</strong></td>
                                        <td>${requestScope.visita.visdate}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>Data visita</strong></td>
                                        <td>${requestScope.today}</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 40%;"><strong>Orario della visita</strong></td>
                                        <td> --- </td>
                                    </tr>
                                    <tr>
                                        <td><strong>Nome della visita</strong></td>
                                        <td>${requestScope.speckvisita.name}</td>
                                    </tr>
                                    <tr>
                                        <td><strong>Descrizione della visita</strong></td>
                                        <c:if test="${requestScope.listaVisite.description != null}"> <td>${requestScope.listaVisite.description}</td> </c:if>
                                        <c:if test="${requestScope.listaVisite.description == null}"> <td><span style='font-style: italic'>Descrizione non presente</span></td> </c:if>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="card" style="margin-bottom: 15px;">
                    <div class="card-header">
                        <h4 class="mb-0">Esito della visita</h4>
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.servletContext.contextPath}/restricted/dottore/specialista/SalvaEsitoSpeck?speckId=${requestScope.speckvisita.id}" method="POST">
                            <textarea class="form-control" rows="5"  name="esito"  ><c:if test="${requestScope.speckvisita.result.length() > 1}">${requestScope.speckvisita.result}</c:if></textarea>
                            <button class="btn btn-light" type="reset" style="margin-top: 10px;margin-right: 15px;">Pulisci</button>
                            <button class="btn btn-primary" type="submit" style="margin-top: 10px;">Salva</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../../../assets/js/jquery.min.js"></script>
    <script src="../../../assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../../../assets/js/script.min.js"></script>
</body>

</html>
