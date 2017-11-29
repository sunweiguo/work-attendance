<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>赛事活动</title>
    <link href="/static/css/bootstrap.css" rel='stylesheet' type='text/css' />
    <link href="/static/css/my.css" rel='stylesheet' type='text/css' />
</head>
<body>

<img src="/static/image/test.png" class="img-responsive" alt="背景图片" style="width: 100%;height: auto;">
<div class="clearfix"> </div>
<div id="content">

    <div id="title">打卡</div>
    <div id="line-help"></div>
    <div id="line">
        <hr style="height:1px;border:none;border-top:1px dashed #d1e8fd;" />
    </div>
    <div id="inner-icon" style="text-align: center">
        <button type="button" class="btn btn-primary" onclick="attendMorning()">打卡</button>
    </div>


</div>
<table>
    <tr style="background: #f7faff;text-align: center;" >
        <td><font color="#fff">1</font></td>
    </tr>
</table>

<div id="detail">
    <div id="detailtitle">打卡时间</div>
    <div id="line">
        <hr style="height:1px;border:none;border-top:1px solid #eee;" />
    </div>
    <div id="details">
    </div>
</div>

</body>

<!-- jQuery 2.2.3 -->
<script src="/static/jquery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script>
    function attendMorning() {
        var userid = "${userid}";
        alert("打卡成功---"+userid);
        $.ajax({
            type:"POST",
            url:"/sign/sign",
            data:userid,
            success:function (data) {
                var result = JSON.parse(data);
                alert(result.messgae);
                ${"details"}.innerHTML = "打卡时间为现在，记住就好了。。。作为后端工程师，我已经尽力了。。总之恭喜你打卡了。。";
            }
        });
    }
</script>
</html>