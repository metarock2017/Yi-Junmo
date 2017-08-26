<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title></title>
    <style type="text/css">

        .gridContainer {
            margin-left: auto;
            margin-right: auto;
            width: 100%;
        }
        #LayoutDiv1 {
            clear: both;
            float: left;
            margin-left: 0;
            width: 100%;
            display: block;
        }

        body {
            background-color:#f8e6ca;
            margin: 0;
            padding-left: 5px;
            padding-right: 5px;
        }
        .box {
            border:2px #b08023 solid;
            background-color:#f8e6ca;
            box-shadow:0 2px #fbf4eb;
            border-radius:5px;
        }
        #top_box {
            margin:10px auto;
            width:100%;}
        h1 {
            height:35px;
            margin:15px auto;
            display:block;
            font-size:28px;
            text-align:center;
            color:#824818;
            line-height:33px;}
        .b_form{
            display:block;
            float:left;
            line-height:33px;
            margin-left:0px;
            border:none}

        .b_select{
            width:100px;
            background:#fff;
            height:33px;
            font-size:18px;
            text-align:center;
            line-height:33px;
            border:2px #b08023 solid;

            text-align: center;
            /*padding: 5px 18px;*/
            padding-right: 2px;
            -webkit-appearance: none;
            vertical-align: middle;
        }

        .gallery {
            margin:5px auto 5px auto;
            font-size:22px;
            color:#824818;
        }
        #k_gallery {width:95%;}
        #k_gallery td {width:33%; text-align:center; vertical-align:center}

        .main_btn {
            background-color:#B08023;
            border:2px #b08023 solid;
            box-shadow:0 1px 2px #fbf4eb;
            border-radius:10px;
            text-align:center;
            color:#FFFFFF;
            display:block;
            font-size:22px;
            -webkit-appearance: none;
        }

        #creategame {margin:10px auto 0 auto;width:100%; height:60px;font-size:32px; line-height:100%;margin-bottom:30px }
        #middle_box {width:100%}
        #w_btn {margin:10px auto 0 auto;width:100%; height:60px;font-size:32px; line-height:100%; margin-bottom:30px}
        #w_gallery  {width:100%}
        #w_gallery td {width:50%; text-align:center; vertical-align:central}

        .input_table{width:95%; margin:5px auto;
            font-size:22px;
            color:#824818}
        .input_table td{  height:45px; line-height:45px; vertical-align:central; margin:10px auto}
        .random{width:85%; height:45px;}
        .text_input{
            border:2px #b08023 solid;
            width:85%;
            margin-right:5px;
            height:35px;
            background-color:#fff;
            box-shadow:0 2px #fbf4eb;
            border-radius:5px;
            font-size: 22px;
        }

        .pic_box {
            width:100%;
            text-align:center;
            font-size:28px;
            color:#824818;
            padding-top: 10px;
            padding-bottom: 10px;
        }
        .info_box {text-align:center; font-size:22px; color:#824818}

        .room_info {text-align:center; margin:10px auto;font-size:22px; color:#824818; line-height:32px}
        .room_info p{height:32px}
        hr {
            background-color:#c49e6a;
            height:2px;
            box-shadow:0 2px #faefdc;
            border:none;
            width:100%;
            margin:20px auto
        }
        .about_info {width:90%; margin:auto;text-align:center; font-size:19px; color:#824818; line-height:32px}


        .info_table {width:100%; line-height:40px}
        .red {color:#F00; font-style:italic}
        .refresh {width:90%; height:45px; margin:auto;  font-size:22px; font-weight:700; line-height:40px}

        .langrensha_select {
            width:95%;
            background:#fff;
            height:33px;
            font-size:18px;
            line-height:33px;
            border:2px #b08023 solid;
            text-align: center;
            /*padding: 5px 18px;*/
            padding-right: 2px;
            -webkit-appearance: none;
            vertical-align: middle;
        }

        .am_tab{font-size:22px; color:#F00!important; text-decoration:underline}
        .small {
            font-size:18px;
        }
        .tips
        {
            width:100%;
            font-size: 18px;
            margin:auto;
            text-align:center;
            color:#824818;
            line-height:32px
        }
        a.ads
        {
            font-size:18px;
            color:#2d64b3;
            text-decoration: none;
        }
    </style>
    <script type="text/javascript" >

        var xmlHttp;
        function createxmlHttpRequest()
        {
            if(window.ActiveXObject)
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            else if(window.XMLHttpRequest)
                xmlHttp = new XMLHttpRequest();
        }
        function doRequestUsingGet()
        {
            createxmlHttpRequest();
            var timestamp=new Date().getTime()/1000;
            var queryString = "http://momonet.s3.natapp.cc/getname";
            xmlHttp.open("POST",queryString);
            xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            xmlHttp.onreadystatechange = function(){
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    var json_data = eval("(" + xmlHttp.responseText + ")");
                    document.getElementById("wodi").value = json_data.wodi;
                    document.getElementById("pingmin").value = json_data.pingmin;
                }
            };
            xmlHttp.send("momo=momo.123");
        }
        function handleStateChange(data)
        {
            alert(data);
        }

        function change(people) {
            //alert(people);
            var wodi = 0;
            if(people >3 && people <= 6) {
                wodi = 1;
            }

            if(people > 6 && people <= 11) {
                wodi = 2;
            }

            if(people > 11 && people <= 15) {
                wodi = 3;
            }

            if(people > 15 && people <= 20) {
                wodi = 4;
            }
            var pingmin = people - wodi;
            document.getElementById("wodinum").innerHTML = wodi;
            document.getElementById("pingminnum").innerHTML = pingmin;
        }

    </script>
</head>
<body onload="doRequestUsingGet()">

<div class="gridContainer clearfix">
    <div id="top_box" class="box clearfix">
        <form action="http://momonet.s3.natapp.cc/creat" method="post">
            <input type="hidden" name="username" value="${name}" />
            <h1 class="clearfix"><span style="display:block; width:160px; height:33px;float:left">游戏人数</span>
                <div class="b_form">
                    <select class="b_select" name="people" onchange="change(this.value)">
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                    </select>
                </div>
            </h1>
            <table id="w_gallery" class="gallery">
                <tr>
                    <td>卧底  x <span id="wodinum">1</span></td>
                    <td>平民  x <span id="pingminnum">3</span></td>
                </tr>
            </table>
    </div>
    <div id="middle_box" class="box clearfix" >
        <table class="input_table">
            <tr><td width="40%"><b>游戏出题&nbsp;</b></td>
                <td width="60%" style="margin: 0 auto;">
                    <input id="randWord" type="button" onclick="doRequestUsingGet()" class="main_btn random" value="随机换题" />
                </td>
            </tr>
            <tr>
                <td width="40%">卧底词:</td>
                <td  width="60%">
                    <input type="text" class="text_input" name="wodi" id="wodi" value=""></input>
                </td>
            </tr>
            <tr>
                <td width="40%">平名词:</td>
                <td  width="60%">
                    <input type="text" class="text_input" name="pingmin" id="pingmin" value=""></input>
                </td>
            </tr>
        </table>
    </div>
    <input type="submit" id="w_btn" class="main_btn" value="创建游戏"/>
    </form></div></body>
</html>