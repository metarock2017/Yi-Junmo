
<!doctype html>
<html lang="en-US">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <title>谁是卧底</title>
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
            border:2px #b08023 solid;}

        .gallery {
            margin:5px auto 5px auto;
            font-size:22px;
            color:#824818;
        }
        #k_gallery {width:95%;}
        #k_gallery td {width:33%; text-align:center; vertical-align:central}

        .main_btn {
            background-color:#B08023;
            border:2px #b08023 solid;
            box-shadow:0 1px 2px #fbf4eb;
            border-radius:10px;
            text-align:center;
            color:#FFFFFF;
            display:block;
            font-size:22px;
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
            border:2px #b08023 solid;}
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
</head>
<body>

<div class="gridContainer clearfix">
    <h1 font-size:34px; margin:20px auto;>房间号：<b>${roomid}</b></h1>
    <div class="box clearfix room_info">
        <table class="info_table">
            <tr>
                <td width="35%" align="right">配置：</td>
                <td width="65%" align="left">${wodinum}卧底，${pingminnum}平民</td>
            </tr>
            <tr>
                <td align="right">卧底词：</td>
                <td align="left">${wodi}</td>
            </tr>
            <tr>
                <td align="right">平民词：</td>
                <td align="left">${pingmin}</td>
            </tr>
            <!--
            <tr>
              <td align="right">状态：</td>
              <td align="left">
                还差4个人      </td>
            </tr>
            -->
            <tr>
                <td align="right">卧底：</td>
                <td align="left">${wodi_is}</td>
            </tr>
        </table>
    </div>
    <div class="tips">
        <p>回复房间号，可以再次查看房间信息</p>
    </div>
    <hr/>
</html>