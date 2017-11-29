<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改成功</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/static/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="/static/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/upload/cropper.min.css">
    <link rel="stylesheet" href="/static/upload/jquery-labelauty.css">
    <!-- jQuery 2.2.3 -->
    <script src="/static/jquery/jquery-2.2.3.min.js"></script>
    <script src="/static/upload/cropper.min.js"></script>
    <script src="/static/upload/bootstrap.min.js"></script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="../../index2.html" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>蜗牛生活</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>蜗牛生活</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="${domain}${loginUser.headimgurl}" class="user-image" alt="User Image" >
                            <span class="hidden-xs">${loginUser.username}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header" style="height: 150px;">
                                <img src="${domain}${loginUser.headimgurl}" class="img-circle">
                                <p>
                                    <small>加入于<fmt:formatDate pattern="yyyy/MM/dd" value="${loginUser.registerDate}"/></small>
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-left">
                                    <a href="/user/modifyPersonalPassword" class="btn btn-default btn-flat">更改密码</a>
                                </div>
                                <div class="pull-right">
                                    <a href="/user/logout" class="btn btn-default btn-flat">退出</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${domain}${loginUser.headimgurl}" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${loginUser.username}</p>
                    <i class="fa fa-circle text-success"></i> Online
                </div>
            </div>
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header"><button type="button" class="btn btn-block btn-google" onclick="sign()">打卡</button></li>
                <li class="treeview active">
                    <a href="#">
                        <i class="fa fa-dashboard"></i> <span>用户管理</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <shiro:hasRole name="admin">
                            <li><a href="/user/userList"><i class="fa fa-circle-o"></i>用户列表</a></li>
                        </shiro:hasRole>
                        <li><a href="/attend/personalAttend"><i class="fa fa-circle-o"></i>我的打卡</a></li>
                        <li class="active"><a href="/user/personalCenter"><i class="fa fa-circle-o"></i>个人中心</a></li>
                        <li><a href="/attend/count"><i class="fa fa-circle-o"></i>数据统计</a></li>
                    </ul>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                成功页面
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box" style="text-align: center">
                <h3><font color="#006400">修改密码成功！</font></h3><br>
                <a href="${pageContext.request.contextPath}/login"><span id="time">5</span>秒后系统会自动跳转到登陆页面，也可点击本处直接跳转哦</a>
                <script type="text/javascript">
                    $(document).ready(function(){
                        delayURL("${pageContext.request.contextPath}/login");
                    })

                    function delayURL(url) {
                        var delay = document.getElementById("time").innerHTML;
                        if (delay > 0) {
                            delay--;
                            document.getElementById("time").innerHTML = delay
                        } else {
                            window.top.location.href = url
                        }
                        setTimeout("delayURL('" + url + "')", 1000)
                    }
                </script>
            </div>
        </section>
    </div>

</div>
</section>
</div>
<footer class="main-footer">
    <div class="pull-right hidden-xs">
    </div>
    <strong>Copyright &copy; 2017-2018 <a href="http://almsaeedstudio.com">蜗牛生活</a>.</strong> All rights
    reserved.
</footer>
<div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<!-- jQuery 2.2.3 -->
<script src="/static/jquery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/daterangepicker/moment.min.js"></script>
<script src="/static/plugins/daterangepicker/daterangepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript">
        $(document).ready(function(){
            checkOldPasswd();
            checkNewPasswd1();
            checkNewPasswd2();
        });
    function checkOldPasswd(){
        $('#oldPassword').keyup(function(){
            var a=$(this).val();
            var param="${pageContext.request.contextPath}/user/checkOldPasswd?oldPassword="+a+"";
            $.ajax({
                url:param,
                type:"POST",
                dataType:"json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
                success:function(data){
                    if(data.message == 'check_old_fail'){
                        $("#oldPasswordPrompt").text("原始密码输入错误");
                        return false;
                    }else if(data.message == 'check_old_succ'){
                        $("#oldPasswordPrompt").text("");
                    }
                }
            });
        });
    }

    function checkNewPasswd1(){
        $('#newPassword').keyup(function(){
            var a=$(this).val();
            var param="${pageContext.request.contextPath}/user/checkNewPasswd1?newPassword="+a;
            $.ajax({
                url:param,
                type:"POST",
                dataType:"json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
                success:function(data){
                    if(data.message == 'check_new1_fail'){
                        $("#newPasswordPrompt").text("不要与原密码重复");
                        return false;
                    }else if(data.message == 'check_new1_succ'){
                        $("#newPasswordPrompt").text("");
                    }
                }
            });
        });
    }

    function checkNewPasswd2(){
        $('#newPassword2').keyup(function(){
            var a=$(this).val();
            var param="${pageContext.request.contextPath}/user/checkNewPasswd2?newPassword2="+a+"&newPassword="+$('#newPassword').val();
            $.ajax({
                url:param,
                type:"POST",
                dataType:"json",
                contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
                success:function(data){
                    if(data.message == 'check_new2_fail'){
                        $("#newPassword2Prompt").text("两次密码不一致");
                        return false;
                    }else if(data.message == 'check_new2_succ'){
                        $("#newPassword2Prompt").text("");
                    }
                }
            });
        });
    }

    function submitForm() {
        if ($("#oldPassword").val() === "") {
            $("#oldPasswordPrompt").text(" 请填写原始密码");
            return false;
        } else {
            $("#oldPasswordPrompt").text("");
        }

        if ($("#newPassword").val() === "") {
            $("#newPasswordPrompt").text(" 请填写新密码");
            return false;
        } else {
            $("#newPasswordPrompt").text("");
        }

        if ($("#newPassword2").val() === "") {
            $("#newPassword2Prompt").text(" 请确认新密码");
            return false;
        } else {
            $("#newPassword2Prompt").text("");
        }


        $("#submitBtn").attr("disabled", "disabled").text("提交中...");
        $("#createFrom").submit();
    };


        function sign() {
            var userId = "${loginUser.id}";
            $.ajax({
                type:"POST",
                url:"/attend/sign",
                data:null,
                success:function (data) {
                    var result = JSON.parse(data);
                    alert(result.messgae);
                    window.location.href = "/attend/personalAttend";
                }
            });
        }
</script>
</body>
</html>
