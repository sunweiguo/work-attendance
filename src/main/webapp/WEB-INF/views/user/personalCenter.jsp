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
    <link rel="stylesheet" href="/static/upload/cropper.min.css">
    <link rel="stylesheet" href="/static/upload/jquery-labelauty.css">
    <!-- jQuery 2.2.3 -->
    <script src="/static/jquery/jquery-2.2.3.min.js"></script>
    <script src="/static/upload/cropper.min.js"></script>
    <script src="/static/upload/bootstrap.min.js"></script>
    <script src="/static/upload/mycropper.js">
        $("#current-img").change(function(){
            var src = $("#current-img").attr("src");
            $("#aside-img").attr("src",src);
            $("#navbar-avatar").attr("src",src);
        });
    </script>
    <style>
        /*头像编辑*/
        .avatar-wrapper {
            height: 370px;
            width: 100%;
            margin-top: 15px;
            box-shadow: inset 0 0 5px rgba(0,0,0,.25);
            background-color: #fcfcfc;
            overflow: hidden;
        }
        .avatar-wrapper img { display: block; height: auto; max-width: 100%;}
        /*头像预览*/
        .avatar-preview {
            margin-top: 15px;
            margin-right: 15px;
            border: 1px solid #eee;
            border-radius: 4px;
            background-color: #fff;
            overflow: hidden;
            height: 96px;
            width: 96px;
        }/*
        .avatar-preview:hover { border-color: #ccf; box-shadow: 0 0 5px rgba(0,0,0,.15);}*/
        .preview-lg{
            width: 90px;
            height: 90px;
        }
        .preview-md{
            width:45px;
            height: 45px;
        }
        .preview-sm{
            width: 25px;
            height: 25px;
        }
    </style>
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
                        <i class="fa fa-dashboard"></i> <span>导航管理</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/user/userList"><i class="fa fa-circle-o"></i>用户列表</a></li>
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
                用户管理
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box" style="text-align: center">
                <input type="hidden" value="${loginUser.id}" id="myId">
                <%--<label style="width: 100px;text-align: left">用户头像：</label><img src="${domain}${loginUser.headimgurl}" alt="Avatar"  data-toggle="modal" data-target="#avatar-modal"><br><br>--%>



                <!--个人主页-->
                <section id="main" class="container">
                    <!-- 主体内容 -->
                    <div class="col-sm-10">
                        <div class="" id="crop-avatar">
                            <!-- Current avatar -->

                            <div class="avatar-view" title="Change the avatar" style="padding-left: 220px;">
                                <label style="width: 100px;">点击更换:</label>
                                <img src="${domain}${loginUser.headimgurl}" alt="Avatar" id="current-img" style="width: 200px;height: 200px;">
                            </div><br><br>
                            <!-- Cropping modal -->
                            <div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <form class="avatar-form" action="/upload/avatarupload/" enctype="multipart/form-data" method="post" accept="image/gif, image/jpeg,image/png">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title" id="avatar-modal-label">更改头像</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="avatar-body">

                                                    <!-- avatar_file(源文件),avatar_data(裁剪参数JSON[x,y,w,h]),avatar-src(源文件路径) -->
                                                    <div class="avatar-upload" style="text-align: left">
                                                        <input type="hidden" class="avatar-src" name="avatar_src">
                                                        <input type="hidden" class="avatar-data" name="avatar_data">
                                                        <label for="avatarInput" class="btn btn-primary">选择图片</label>
                                                        <input type="file" class="avatar-input" id="avatarInput" name="avatar_file" style="display: none;" accept="image/*">
                                                    </div>

                                                    <!-- Crop and preview -->
                                                    <div class="row">
                                                        <div class="col-md-9" style="text-align: left">
                                                            <div class="avatar-wrapper"></div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="avatar-preview preview-lg"></div>
                                                            <div class="avatar-preview preview-md"></div>
                                                            <div class="avatar-preview preview-sm"></div>
                                                        </div>
                                                    </div>

                                                    <div class="row avatar-btns">
                                                        <div class="col-md-9">
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-primary" data-method="rotate" data-option="90" title="Rotate 90 degrees">旋转图片</button>
                                                                <button type="button" class="btn btn-primary" data-method="rotate" data-option="15">15deg</button>
                                                                <button type="button" class="btn btn-primary" data-method="rotate" data-option="30">30deg</button>
                                                                <button type="button" class="btn btn-primary" data-method="rotate" data-option="45">45deg</button>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <button type="submit" class="btn btn-primary">提交</button>
                                                            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- <div class="modal-footer">
                                           menutton tymenutton" class="btn btn-default" data-dismiss="modal">menutton>
                                            </div> -->
                                        </form>
                                    </div>
                                </div>
                            </div><!-- /.modal -->

                            <!-- Loading state -->
                            <div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
                        </div>
                    </div>
                </section>
                <label style="width: 100px;">用户姓名:</label><input type="text" name="username" id="username" value="${loginUser.username}"><br><br>
                <label style="width: 100px;">手机号码:</label><input type="text" name="phonenumber" id="phoneNumber" value="${loginUser.phonenumber}"><br><br>
                <label style="width: 100px;margin-left: -80px;">用户性别:</label>
                <c:if test="${loginUser.sex==1}">
                    <input type="radio" name="gender" id="gender_show_input1" value="1" checked> 男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" id="gender_show_input2" value="2"> 女
                </c:if>
                <c:if test="${loginUser.sex==2}">
                    <input type="radio" name="gender" id="gender_show_input1" value="1"> 男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="gender" id="gender_show_input2" value="2" checked> 女
                </c:if>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">放弃修改
                    </button>
                    <button type="button" class="btn btn-primary" id="user_update_btn">
                        提交更改
                    </button>
                </div>
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
<script>
    $("#user_update_btn").click(function(){
        var username = $("#username").val();
        var phoneNumber = $("#phoneNumber").val();
        var id = $("#myId").val();
        var sex = '';
        var e1 = document.getElementById('gender_show_input1');
        var e2 = document.getElementById('gender_show_input2');
        if(e1.checked==true){
            sex = 1;
        }else if(e2.checked==true){
            sex = 2;
        }
        $.ajax({
            type:"POST",
            url:"/user/updatePersonalInfo",
            data:{"username":username,"phoneNumber":phoneNumber,"id":id,"sex":sex},
            success:function (data) {
                var dd = JSON.parse(data);
                if(dd.code==200){
                    alert("更新成功");
                    window.location.href="/user/personalCenter";
                }else if(dd.code==400){
                    alert("更新失败，请重新登陆尝试更改");
                }
            }
        });
    })

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
<script src="/static/upload/bootstrap.min.js"></script>
</body>
</html>
