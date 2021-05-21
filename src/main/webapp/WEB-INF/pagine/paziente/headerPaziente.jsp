<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../../assetsPazienti/css/header.css">
<header class="shadow" id="htitle" style="">
    <a href="HomePaziente">
        <img src="../../assets/img/logo.svg" class="mr-3 align-self-center" style="width: 70px;margin: 10px;position: absolute;cursor: pointer;">
    </a>
    <p id="ptitle">Portale per i servizi sanitari italiani</p>
    <div style="position: absolute;top: 0;right: 0;margin: 15px;">
        <a role="button" data-toggle="modal" data-target="#myModal" class="shortcut">
            <img src="../../assets/img/notifiche.png" style="width: 35px;margin: 12.5px;cursor: pointer;position: relative;" data-toggle="tooltip" title="notifiche">
        </a>
        <a href="HomePaziente" role="button" class="shortcut">
            <img src="../../assets/img/profilo.png" data-toggle="tooltip" title="bacheca profilo">
        </a>
        <a href="../../Logout" role="button" class="shortcut">
            <img src="../../assets/img/logout.png" data-toggle="tooltip" title="logout">
        </a>
    </div>
</header>

<div class="modal" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <h3 class="text-center">Notifiche</h3>
                <div id="notification-box">
                    <c:choose>
                        <c:when test="${requestScope.notif.size()>0}">
                            <script> var NotifCount = ${requestScope.notif.size()}; </script>
                            <c:forEach items="${requestScope.notif}" var="elem">
                                <div class='toast' data-autohide='false'>
                                    <div class='toast-header'>
                                        <strong class='mr-auto text-primary'> ${elem.title} </strong>
                                        <button type='button' class='ml-2 mb-1 close' data-dismiss='toast' onclick='hide_notification(${elem.id}, ${elem.user});'>&times;</button>
                                    </div>
                                    <div class='toast-body'> ${elem.content} </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:when test="${requestScope.notif.size()<=0}">
                            <i>Nessuna nuova notifica</i>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<script>
    $(document).ready(function(){
      $('.toast').toast('show');
    });
    
    function hide_notification(id, user)
    {
        var xmlhttp = null;
        if(typeof XMLHttpRequest != 'udefined'){
            xmlhttp = new XMLHttpRequest();
        }else if(typeof ActiveXObject != 'undefined'){
            xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
        }else 
            throw new Error('You browser doesn\'t support ajax');
        
        xmlhttp.open('GET', 'HideNotification?id='+id+'&user='+user, true);
        xmlhttp.onreadystatechange = function (){ };
        xmlhttp.send(null);
        
        NotifCount--;
        if(NotifCount == 0)
            document.getElementById('notification-box').innerHTML = "<i>Nessuna nuova notifica</i>";
    }
</script>