<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    function FormatDate(xxdata)
    {
        var monthNames = [ "gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno", "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre" ];
        var monthIndex = xxdata.getMonth();
        return xxdata.getDate() + ' ' + monthNames[monthIndex] + ' ' + xxdata.getFullYear();
    } 
    
    var lista = [];
    var minIdexam = 100000;
    var maxIdexam = 0;
    
    <c:if test="${requestScope.listone.size()>0}">
        <c:forEach items="${requestScope.listone}" var="elem">
            x = ${elem.getIdDisp()};
            lista[x] = { id: x,
                name: '${elem.getCardname()}',
                date: '${elem.getPrescrdate()}',
                by: '${elem.getPrescrby()}',
                type: '${elem.getType()}'
            };
            if(x > maxIdexam) maxIdexam = x;
            if(x < minIdexam) minIdexam = x;
        </c:forEach>
    </c:if>
        
    var province = [];
    <c:if test="${requestScope.province.size()>0}">
        <c:forEach items="${requestScope.province}" var="item"> province[${item.id}] = '${item.city}'; </c:forEach>
    </c:if>
        
    var list_chs = [];
    var minIdchs = 100000;
    var maxIdchs = 0;
    <c:if test="${requestScope.chs.size()>0}">
        <c:forEach items="${requestScope.chs}" var="elem">
            x = ${elem.getId()};
            list_chs[x] = {
                id: x,
                name: '${elem.getName()}',
                dist: province[${elem.getCdistrict()}]
            };
            if(x > maxIdchs) maxIdchs = x;
            if(x < minIdchs) minIdchs = x;
        </c:forEach>
    </c:if>
        
    var list_spk = [];
    var minIdspk = 100000;
    var maxIdspk = 0;
    <c:if test="${requestScope.speck.size()>0}">
        <c:forEach items="${requestScope.speck}" var="elem">
            x = ${elem.getId()};
            list_spk[x] = {
                id: x,
                name: '${elem.getFirstname()} ${elem.getLastname()}',
                speck: '${elem.getSpecialization()}',
                city: '${elem.getCity()}',
                x: ${elem.getDistrict()},
                dist: province[${elem.getWork_district()}]
            };
            if(x > maxIdspk) maxIdspk = x;
            if(x < minIdspk) minIdspk = x;
        </c:forEach>
    </c:if>
</script>

        <div class="row">
            <div class="col">
                <div class="progress" id="progress" style="margin-right: 20px;margin-left: 20px;">
                    <div class="progress-bar bg-primary progress-bar-animated" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100" style="width: 15%;"><span class="sr-only">15%</span></div>
                </div>
            </div>
        </div>
        <div class="row" id="row-prescr">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <h5 class="card-subtitle mb-2" style="margin-bottom: 15px;">Le tue prescrizioni</h5>
                        <div style="max-height: 450px;overflow: auto;">
                            <div class="table-responsive" style="padding-right: 0px;margin-bottom: -15px;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th style="width: 20%;">Data prescrizione</th>
                                            <th style="width: 40%;">Nome</th>
                                            <th style="width: 30%;">Prescritto da</th>
                                            <th style="width: 10%;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <script>
                                            for(i=minIdexam; i<=maxIdexam; i++)
                                            {
                                                if(lista[i] != undefined)
                                                {
                                                    document.write('<tr> <td>' + FormatDate(new Date(lista[i].date.substring(0, 10))) + '</td> <td>' + lista[i].name + '</td>' +
                                                        '<td>' + lista[i].by + '</td> <td class="text-center">' +
                                                        '<button class="btn btn-primary" type="button" onclick="new_booking(' + lista[i].id + ', \'' + lista[i].type +  '\');">prenota</button> </td> </tr>');
                                                }
                                            }
                                        </script>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-choose-chs" style="display: none;">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <h6 class="text-left text-muted" style="display: inline-block; margin-right: 0px;margin-left: 10px;margin-bottom: 0px;">Stai prenotando:&nbsp;</h6>
                        <p class="text-left" style="display: inline-block; margin-bottom: 20px;margin-top: 0px;margin-left: 10px;" id="booking_exam"></p>
                        <h5 class="card-subtitle mb-2" style="margin-bottom: 15px;">Seleziona struttura in cui effettuare l'esame</h5>
                        <div style="max-height: 300px;overflow: auto;">
                            <div class="table-responsive" style="padding-right: 0px;margin-bottom: -15px;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th style="width: 45%;">Nome</th>
                                            <th style="width: 40%;">Provincia</th>
                                            <th style="width: 15%;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <script>
                                            for(i=minIdchs; i<=maxIdchs; i++)
                                                if(list_chs[i] != undefined)
                                                    document.write('<tr> <td>' + list_chs[i].name + '</td> <td>' + list_chs[i].dist + '</td> <td>'+
                                                        '<button class="btn btn-primary" type="button" onclick="new_booking_choosed(' + list_chs[i].id + ', \'E\')">scegli</button> </td> </tr>');
                                        </script>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-choose-speck" style="display: none;">
            <div class="col">
                <div class="card" style="margin-top: 10px;">
                    <div class="card-body">
                        <h6 class="text-left text-muted" style="display: inline-block; margin-right: 0px;margin-left: 10px;margin-bottom: 0px;">Stai prenotando:&nbsp;</h6>
                        <p class="text-left" style="display: inline-block; margin-bottom: 20px;margin-top: 0px;margin-left: 10px;" id="booking_visit"></p>
                        <h5 class="card-subtitle mb-2" style="margin-bottom: 15px;">Seleziona specialista con cui effettuare la visita</h5>
                        <div style="max-height: 300px;overflow: auto;">
                            <div class="table-responsive" style="padding-right: 0px;margin-bottom: -15px;">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th style="width: 25%;">Nome</th>
                                            <th style="width: 35%;">Specializzazione</th>
                                            <th style="width: 15%;">Città</th>
                                            <th style="width: 15%;">Provincia</th>
                                            <th style="width: 10%;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <script>
                                            for(i=minIdspk; i<=maxIdspk; i++)
                                                if(list_spk[i] != undefined)
                                                    document.write('<tr> <td>dott. ' + list_spk[i].name + '</td> <td>' + list_spk[i].speck + '</td> <td>' + list_spk[i].city + '</td>' +
                                                        '<td>' + list_spk[i].dist + '</td> <td class="text-center">' +
                                                        '<button class="btn btn-primary" type="button" onclick="new_booking_choosed(' + list_spk[i].id + ')">scegli</button></td></tr>');
                                        
                                        </script>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" id="row-conf-book" style="display: none">
            <div class="col">
                <div class="card" style="margin-top: 10px;margin-bottom: 10px;">
                    <div class="card-body text-center">
                        <h5 class="text-left card-subtitle mb-2">Conferma prenotazione</h5>
                        <h6 class="text-left text-muted" style="margin-right: 0px;margin-left: 10px;">Prescrizione da prenotare</h6>
                        <div id="conf-name" class="alert alert-secondary text-left" role="alert" style="margin-right: 5px;margin-bottom: 5px;margin-left: 20px;"></div>
                        <h6 class="text-left text-secondary card-title" style="margin-top: 20px;margin-left: 10px;margin-bottom: 8px;">Presso</h6>
                        <p class="text-left" style="margin: 12px 20px;" id="conf-place"></p>
                        <h6 class="text-left text-secondary card-title" style="margin-top: 20px;margin-left: 10px;margin-bottom: 8px;">Seleziona data e ora</h6>
                        <p style="font-size: 0.8em; text-align: left; margin-left: 30px">Nota: l'orario potrebbe subire variazioni a seconda della disponibilità e di eventuali urgenze</p>
                        <form action="ConfermaPrenotazione" method="post" id="form" name="form">
                            <div class="table-responsive" style="margin-right: 30px;width: calc(100% - 60px);margin-left: 30px;">
                                <table class="table table-sm">
                                    <tbody>
                                        <tr>
                                            <td class="text-left" style="width: 30%;">Data</td>
                                            <td style="padding: 0px;">
                                                <input class="form-control" type="date" required name="pren_date" id="pren_date">
                                                <script> document.getElementById('pren_date').setAttribute("min", new Date().toISOString().substring(0,10)); </script>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="text-left">Ora</td>
                                            <td style="padding: 0px;">
                                                <select class="form-control" required name="pren_hour">
                                                    <option value="09:00:00">9:00</option>
                                                    <option value="09:30:00">9:30</option>
                                                    <option value="10:00:00">10:00</option>
                                                    <option value="10:30:00">10:30</option>
                                                    <option value="11:00:00">11:00</option>
                                                    <option value="11:30:00">11:30</option>
                                                    <option value="12:00:00">12:00</option>
                                                    <option value="12:30:00">12:30</option>
                                                    <option value="15:00:00">15:00</option>
                                                    <option value="15:30:00">15:30</option>
                                                    <option value="16:00:00">16:00</option>
                                                    <option value="16:30:00">16:30</option>
                                                    <option value="17:00:00">17:00</option>
                                                    <option value="17:30:00">17:30</option>
                                                    <option value="18:00:00">18:00</option>
                                                    <option value="18:30:00">18:30</option>
                                                </select></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <input class="form-control" type="hidden" name="prescr_type" id="pren_type">
                            <input class="form-control" type="hidden" name="pren_disp_id" id="pren_disp_id">
                            <input class="form-control" type="hidden" name="pren_user" id="pren_user">
                            <a class="btn btn-danger btn-lg" role="button" href="Prenotazione" style="padding: 8px 18px;margin: 5px 10px;">annulla prenotazione</a>
                            <button class="btn btn-warning btn-lg" type="button" style="margin: 5px 10px;padding: 8px 18px;" onclick="change_book()">cambia luogo</button>
                            <button class="btn btn-success btn-lg" type="submit" style="padding: 8px 18px;margin: 5px 10px;">conferma prenotazione</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

<script>
    document.getElementById('row-choose-chs').style.display = 'none';
    document.getElementById('row-choose-speck').style.display = 'none';
    document.getElementById('row-conf-book').style.display = 'none';
    
    var type="E";
    var tobookid = 0;
    
    function new_booking(id, typef)
    {
        document.getElementById("pren_type").value = typef;
        document.getElementById("pren_disp_id").value = id;
        document.getElementById('row-prescr').style.display = 'none';
        document.getElementsByClassName('progress-bar')[0].style.width = '40%';
        type = typef;
        if(type=="E")
        {
            document.getElementById('row-choose-chs').style.display = 'block';
            document.getElementById('booking_exam').innerHTML = lista[id].name;
        }
        else
        {
            document.getElementById('row-choose-speck').style.display = 'block';
            document.getElementById('booking_visit').innerHTML = lista[id].name;
        }
        tobookid = id;
    }

    function new_booking_choosed(id)
    {
        document.getElementById("pren_user").value = id;
        document.getElementById('row-choose-chs').style.display = 'none';
        document.getElementById('row-choose-speck').style.display = 'none';
        document.getElementById('row-conf-book').style.display = 'block';
        document.getElementsByClassName('progress-bar')[0].style.width = '75%';
        
        document.getElementById('conf-name').innerHTML = '<strong>'+lista[tobookid].name+'</strong>&nbsp;prescritto il ' + FormatDate(new Date(lista[tobookid].date));
        if (type=="E")
            document.getElementById('conf-place').innerHTML = '' + list_chs[id].name; 
        else
            document.getElementById('conf-place').innerHTML = 'dott. ' + list_spk[id].name;
    }
    
    function change_book()
    {
        if(type=="E")
            document.getElementById('row-choose-chs').style.display = 'block';
        else
            document.getElementById('row-choose-speck').style.display = 'block';
        document.getElementById('row-conf-book').style.display = 'none';
        document.getElementsByClassName('progress-bar')[0].style.width = '40%';
    }
</script>

