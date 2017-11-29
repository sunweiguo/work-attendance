<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/includes.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户列表</title>
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
    <!-- jQuery 2.2.3 -->
    <script src="/static/jquery/jquery-2.2.3.min.js"></script>
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
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header"><button type="button" class="btn btn-block btn-google" onclick="sign()">打卡</button></li></li>
                <li class="treeview active">
                    <a href="#">
                        <i class="fa fa-dashboard"></i> <span>导航管理</span>
                        <span class="pull-right-container">
                          <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/user/userList"><i class="fa fa-circle-o"></i>用户列表</a></li>
                        <li><a href="/attend/personalAttend"><i class="fa fa-circle-o"></i>我的打卡</a></li>
                        <li><a href="/user/personalCenter"><i class="fa fa-circle-o"></i>个人中心</a></li>
                        <li class="active"><a href="/attend/count"><i class="fa fa-circle-o"></i>数据统计</a></li>
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
                我的打卡记录
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <!-- /.box-header -->
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="attend_grid" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th style="text-align: center">用户名</th>
                            <th style="text-align: center">总记录数</th>
                            <th style="text-align: center">正常打卡</th>
                            <th style="text-align: center">非正常打卡</th>
                        </tr>
                        </thead>

                        <c:forEach items="${countInfos}" var="countInfo">
                            <tr id="changeColor">
                                <th style="text-align: center">${countInfo.username}</th>
                                <th style="text-align: center">${countInfo.totalAttends}</th>
                                <th style="text-align: center"><font color="green">${countInfo.normal}</font></th>
                                <th style="text-align: center"><font color="red">${countInfo.abnormal}</font></th>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
                <div id="grid_pager"></div>
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
<!-- /.control-sidebar -->
<!-- Add the sidebar's background. This div must be placed
     immediately after the control sidebar -->
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
<script>
    window.onload = function () {
        var tbody = document.getElementsByTagName("tbody")[0];
        var trArr = tbody.getElementsByTagName("tr");


        for(var i=0;i<trArr.length;i++){

            if(i%2===0){
                trArr[i].style.backgroundColor = "#F0F0F0";
            }else{
                trArr[i].style.backgroundColor = ""

            }
            /*var color = "";
             trArr[i].onmouseover = function () {
             color = this.style.backgroundColor;
             this.style.backgroundColor = "#DEDEDE";

             }
             trArr[i].onmouseout = function () {

             this.style.backgroundColor = color;
             }*/
        }
    }

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

