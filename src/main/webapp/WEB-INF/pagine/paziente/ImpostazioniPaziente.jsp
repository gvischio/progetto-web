<%-- 
    Document   : ImpostazioniPaziente
    Created on : 11-ago-2019, 12.07.13
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
        
        .alert {
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
    var lista_elem = [];
    var hasenso = false;
    <c:if test="${requestScope.elements.size()>0}">
        hasenso = true;
        <c:forEach begin = "0" end="${requestScope.elements.size()-1}" var="current">
            lista_elem[${current}] = "${requestScope.elements.get(current).path}";
        </c:forEach>
    </c:if>
        
    var current = 0;
        
    function get() {
        return current;
    }
    
    function avanza() {
        if(current === ${requestScope.elements.size()-1})
            current = 0;
        else
            current++;
        document.getElementById("photoimg").src = lista_elem[current];
    }
    
    function arretra() {
        if(current === 0) 
            current = ${requestScope.elements.size()-1};
        else
            current--;
        document.getElementById("photoimg").src = lista_elem[current];
    }
    
    function FormatDate(xxdata)
    {
        var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
        var monthIndex = xxdata.getMonth();
        return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
    }
</script>
<body style="background-color: #f4f4f4;">
    <c:if test="${!empty errorPassword}">
        <nav class="alert navbar navbar-expand navbar-expand-lg fixed-bottom">
            <div class="container-fluid"> 
                <column><strong>Attenzione!</strong> Password non corrispondenti.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if>
    <c:if test="${!empty eccessoFile}">
        <nav class="alert navbar navbar-expand navbar-expand-lg fixed-bottom">
            <div class="container-fluid"> 
                <column><strong>Attenzione!</strong> File non corretto.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if>
    <c:if test="${!empty fileNonCompatibile}">
        <nav class="alert navbar navbar-expand navbar-expand-lg fixed-bottom">
            <div class="container-fluid"> 
                <column><strong>Attenzione!</strong> Estensione file non corretta. Provare con .jpeg, .jpg, .png.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if>
    <c:if test="${!empty existingFile}">
        <nav class="alert navbar navbar-expand navbar-expand-lg fixed-bottom">
            <div class="container-fluid"> 
                <column><strong>Attenzione!</strong> File già esistente.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if>
    <c:if test="${!empty erroreFile}">
        <nav class="alert navbar navbar-expand navbar-expand-lg fixed-bottom">
            <div class="container-fluid"> 
                <column><strong>Attenzione!</strong> File non inserito.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if>
    <c:if test="${!empty caricatoFile}">
        <nav class="alert-success navbar navbar-expand navbar-expand-lg fixed-bottom">
            <div class="container-fluid"> 
                <column><strong>Successo!</strong> File caricato correttamente.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if>
    <c:if test="${!empty PasswordOK}">
        <nav class="alert-success navbar navbar-expand navbar-expand-lg fixed-bottom">
            <div class="container-fluid"> 
                <column><strong>Successo!</strong> Password aggiornata.</column>
                <column><span class="closebtn"  onclick="this.parentElement.parentElement.parentElement.style.display='none';">&times;</span></column>
            </div>
        </nav>
    </c:if>
    
    <jsp:include page='headerPaziente.jsp'/>
    
    <div class="container container-main">
        <div class="row">
            <div class="col">
                <h2 class="text-center" style="margin-bottom: 15px;">${requestScope.paziente.getFirstname()} ${requestScope.paziente.getLastname()}</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12 col-lg-10 col-xl-10 offset-lg-1 offset-xl-1">
                <div class="card border rounded shadow" style="margin-bottom: 10px;">
                    <div class="card-header text-left">
                        <h4 class="text-left mb-0">Dettagli personali</h4>
                    </div>
                    <div class="card-body" style="padding: 0;padding-right: 10px;padding-left: 10px;">
                        <div class="row" style="margin: 0px;">
                            <div class="col-12 col-sm-4 col-md-6 col-lg-5 col-xl-5 text-center">
                                <img src="../../assetsPazienti/img/precedente.png" style="width: 25px;cursor: pointer;" onclick="arretra()">
                                <a href="${requestScope.elements.get(0).path}" target="_blank">
                                    <img id="photoimg" class="rounded border rounded-circle border-dark align-self-center" src="${requestScope.elements.get(0).path}" style="width: 100px;height: 100px;margin: 0;margin-bottom: 20px;margin-top: 20px;margin-right: 20px;margin-left: 20px;border-width: 2px !important;/*vertical-align: middle;*/">
                                </a>
                                <img src="../../assetsPazienti/img/prossimo.png" style="width: 25px;cursor: pointer;" onclick="avanza()">
                            </div>
                            <div class="col-md-6 col-lg-7 col-xl-7">
                                <p style="margin-bottom: 10px;cursor: pointer;margin-top: 10px;">
                                    <span style="text-decoration: underline;" onclick="document.getElementById('sceglifile').style = ''">Carica nuova foto</span>
                                </p>
                                <div class="input-group" id="sceglifile" style="display: none">
                                    <form action="${pageContext.servletContext.contextPath}/restricted/paziente/UploadPhoto" method="post" enctype="multipart/form-data">
                                      <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="customFile" name="file"
                                               onchange="a(this)">
                                        <label id="id01" class="custom-file-label" for="customFile">Seleziona foto</label>
                                            <script>
                                                function a(elem) {
                                                    var file = elem.value;
                                                    file = file.substring(file.lastIndexOf("\\")+1, file.length);
                                                    document.getElementById('id01').classList.add('selected');
                                                    document.getElementById('id01').innerHTML = file;
                                                }
                                            </script>
                                        <input type="submit" class="btn btn-primary" style="margin-top: 5px" name="passPhoto" value="Carica">
                                      </div>
                                    </form>
                                </div>
                                    <p style="margin-top: 15px;margin-bottom: 10px;cursor: pointer;">
                                        <a style="text-decoration: underline;" href="${pageContext.servletContext.contextPath}/restricted/paziente/RemovePhoto?photoId=${requestScope.elements.get(current).id}">Rimuovi foto</a>
                                    </p>
                            </div>
                            <div class="col-xl-12" style="padding-top: 10px;margin-top: 0px;">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr></tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/width: 40%;"><strong>Codice Fiscale</strong></td>
                                                <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/">${requestScope.paziente.getSsn()}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Nato il</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">
                                                    <script> document.write(FormatDate(new Date('${requestScope.paziente.getBirthday()}'))); </script>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Luogo nascita</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.paziente.getBirthplace()}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Sesso</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.paziente.getSex()}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Provincia</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">${requestScope.district.getCity()}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                            <tr></tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/width: 40%;"><strong>Indirizzo e-mail</strong></td>
                                                <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/">${sessionScope.user.getEmail()}</td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;"><strong>Password</strong></td>
                                                <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;"><a onclick="document.getElementById('div-cambia-password').style.display = 'block';" style="text-decoration: underline; cursor: pointer">Modifica password</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col text-right" id="div-cambia-password" style="display: none;">
                                <form action="${pageContext.servletContext.contextPath}/restricted/paziente/NewPassword" method="POST">
                                    <div class="table-responsive text-left">
                                        <table class="table">
                                            <thead>
                                                <tr></tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td style="padding: 8px 0;padding-right: 0;padding-left: 5px;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/width: 40%;">
                                                        <strong>Nuova password</strong>
                                                    </td>
                                                    <td style="padding: 8px 0;padding-right: 5px;padding-left: 0;vertical-align: middle;/*border-top: 0 !important;*//*margin-top: 5px;*/">
                                                        <input name="NewPassword" class="form-control" type="password" style="width: 90%;padding: 2px 6px;" autocomplete="off">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="padding: 8px;padding-right: 0;padding-left: 5px;vertical-align: middle;">
                                                        <strong>Ripeti password</strong>
                                                    </td>
                                                    <td style="padding: 8px;padding-right: 5px;padding-left: 0;vertical-align: middle;">
                                                        <input name="CheckPassword" class="form-control" type="password" style="width: 90%;padding: 2px 6px;" autocomplete="off">
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div><button class="btn btn-light" type="reset" style="margin-top: -5px;margin-right: 10px;margin-bottom: 13px;" onclick="document.getElementById('div-cambia-password').style.display = 'none';">Annulla</button>
                                    <button class="btn btn-primary" type="submit" style="margin-top: -5px;margin-bottom: 13px;">Modifica password</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                                    <!--
            <div class="col">
                <div class="card shadow">
                    <div class="card-header">
                        <h4 style="margin-bottom: 0px;">Impostazioni notifiche</h4>
                    </div>
                    <div class="card-body">
                        <form>
                            <div style="padding: 5px;">
                                <p style="margin-bottom: 5px;margin-top: 10px;">Nuovi appuntamenti</p>
                                <div class="custom-control custom-checkbox" style="margin-left: 20px">
                                    <input type="checkbox" class="custom-control-input" id="customCheck1">
                                    <label class="custom-control-label" for="customCheck1">Notifiche e-mail</label>
                                </div>
                                <div class="custom-control custom-checkbox" style="margin-left: 20px">
                                    <input type="checkbox" class="custom-control-input" id="customCheck2">
                                    <label class="custom-control-label" for="customCheck2">Notifiche push</label>
                                </div>
                            </div>
                            <div style="padding: 5px;">
                                <p style="margin-bottom: 5px;margin-top: 10px;">Nuove prescrizioni di esami e visite</p>
                                <div class="custom-control custom-checkbox" style="margin-left: 20px">
                                    <input type="checkbox" class="custom-control-input" id="customCheck3">
                                    <label class="custom-control-label" for="customCheck3">Notifiche e-mail</label>
                                </div>
                                <div class="custom-control custom-checkbox" style="margin-left: 20px">
                                    <input type="checkbox" class="custom-control-input" id="customCheck4">
                                    <label class="custom-control-label" for="customCheck4">Notifiche push</label>
                                </div>
                            </div>
                            <div style="padding: 5px;">
                                <p style="margin-bottom: 5px;margin-top: 10px;">Nuove prescrizioni di ricette</p>
                                <div class="custom-control custom-checkbox" style="margin-left: 20px">
                                    <input type="checkbox" class="custom-control-input" id="customCheck5">
                                    <label class="custom-control-label" for="customCheck5">Notifiche e-mail</label>
                                </div>
                                <div class="custom-control custom-checkbox" style="margin-left: 20px">
                                    <input type="checkbox" class="custom-control-input" id="customCheck6">
                                    <label class="custom-control-label" for="customCheck6">Notifiche push</label>
                                </div>
                            </div><button class="btn btn-primary" type="submit" style="margin-top: 15px;">Salva modifiche</button>
                        </form>
                    </div>
                </div>
            </div>
                                    -->
        </div>
    </div>
    <script src="../../assetsPazienti/js/script.min.js"></script>
</body>

</html>
