<header class="shadow" id="htitle" style="">
    <a href="HomeDottore">
        <img src="../../assets/img/logo.svg" class="mr-3 align-self-center" style="width: 70px;margin: 10px;position: absolute;cursor: pointer;">
    </a>
    <p id="ptitle">Portale per i servizi sanitari italiani</p>
    <div style="position: absolute;top: 0;right: 0;margin: 15px;">
        <a href="HomeDottore">
            <img src="../../assets/img/profilo.png" style="width: 35px;margin: 12.5px;cursor: pointer;" data-toggle="tooltip" title="bacheca profilo">
        </a>
        <a href="../../Logout">
            <img src="../../assets/img/logout.png" style="width: 35px;margin: 12.5px;cursor: pointer;" data-toggle="tooltip" title="logout">
        </a>
    </div>
</header>
<style>
    #htitle {
        margin: 0px;
        margin-bottom: 15px;
        background-color: #007bff;
        font-family: Raleway;
        position: relative;
        height: 90px;
    }
    #ptitle {
        font-size: 2.4em;
        color: white;
        position: relative;
        top: 15px;
        max-width: calc(100% - 240px);
        margin-left: 90px;
    }
    @media (max-width: 900px) {
        #ptitle {
            display: block;
            position: relative;
            margin: 0;
            top: 90px;
            left: 10px;
            max-width: calc(100% - 20px);
            text-align: center;
        }

        #htitle {
            height: 150px;
        }
    }
    @media (max-width: 615px) {
        #htitle {
            height: 200px;
        }
    }
</style>
