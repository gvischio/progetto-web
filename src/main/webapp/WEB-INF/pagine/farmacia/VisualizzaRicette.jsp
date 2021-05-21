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

<body>
        <jsp:include page="headerFarmacia.jsp"/>
    <div class="container container-main">
        <div class="row">
            <div class="col-xl-10 offset-xl-1" style="padding-right: 0;padding-left: 0;padding-bottom: 15px;">
                <div class="card shadow-sm">
                    <div class="card-header">
                        <h3 class="mb-0">Lista ricette attive per ${requestScope.paziente.firstname} ${requestScope.paziente.lastname} </h3>
                    </div>
                    <div class="card-body" style="margin-bottom: -10;padding-bottom: 0;padding-top: 10px;">
                        <div class="row">
                           <div class="col" style="padding: 10px;padding-bottom: 0px;">
                                <c:if test="${requestScope.prescriptionAttive.size() > 0}">
                                    <c:forEach begin="0" end="${requestScope.prescriptionAttive.size()-1}" var="i"> 
                                        <div class="alert alert-primary text-right" role="alert" style="padding: 8px 20px;padding-right: 14px;padding-left: 14px;margin-bottom: 10px;">
                                            <h5 class="text-left" style="margin-bottom: 5px;"> ${requestScope.prescriptionAttive.get(i).name} </h5>
                                            <p class="text-left" style="margin-bottom: 0px;">Prescritta il: ${requestScope.visit.get(i).visdate} </p>
                                            <a class="btn btn-primary" role="button" href="${pageContext.servletContext.contextPath}/restricted/farmacia/ErogaRicetta?preId=${requestScope.prescriptionAttive.get(i).id}&patId=${requestScope.paziente.id}">Eroga ricetta</a>
                                        </div>
                                    </c:forEach>
                                </c:if>
                               <c:if test="${requestScope.prescriptionAttive.size() == 0}">
                                  <h5 class="text-left" style="margin-bottom: 5px;"> <span style='font-style: italic'> Nessuna ricetta attiva </span> </h5>
                               </c:if>
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
</body>

</html>