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
                        <li class="active"><a href="/attend/personalAttend"><i class="fa fa-circle-o"></i>我的打卡</a></li>
                        <li><a href="/user/personalCenter"><i class="fa fa-circle-o"></i>个人中心</a></li>
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
                我的打卡记录
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box">
                <div class="box-search">
                    <form onSubmit="return false;" id="userQueryForm">
                        <div class="form-inline">
                            <label>日期范围:</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control pull-left" id="rangeDate" name="rangeDate">
                            </div>
                            <label>状态</label>
                            <select class="form-control" name="status" id="status">
                                <option value="">全部</option>
                                <option value="1">正常</option>
                                <option value="2">异常</option>
                            </select>
                            <button type="submit" class="btn btn-primary" onclick="attendQuery()">查询</button>
                        </div>

                    </form>
                </div>
                <!-- /.box-header -->
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="attend_grid" class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>打卡日期</th>
                            <th>周几</th>
                            <th>早打卡时间</th>
                            <th>晚打卡时间</th>
                            <th>状态</th>
                        </tr>
                        </thead>
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
    //Date range as a menu
    $('#rangeDate').daterangepicker(
        {
            locale: {
                applyLabel: '确定',
                cancelLabel: '取消',
                fromLabel: '起始时间',
                toLabel: '结束时间',
                customRangeLabel: '自定义',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月',
                    '七月', '八月', '九月', '十月', '十一月', '十二月'],
                firstDay: 1,
                format:"YYYY-MM-DD",
                separator:"/"
            },

            ranges: {
                '今天': [moment()],
                '昨天': [moment().subtract(1, 'days')],
                '最近七天': [moment().subtract(6, 'days'), moment()],
                '最近30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
            startDate: moment().startOf('month'),
            endDate: moment().endOf('month')
        }
    );

    Date.prototype.format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "h+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }

    function formToJson(form) {
        var result = {};
        var fieldArray = $('#' + form).serializeArray();
        for (var i = 0; i < fieldArray.length; i++) {
            var field = fieldArray[i];
            if (field.name in result) {
                result[field.name] += ',' + field.value;
            } else {
                result[field.name] = field.value;
            }
        }
        return result;
    }

    //Date picker
    $('#attendDate').datepicker({
        autoclose: true,
        language: 'zh-CN',
        todayHighlight: true
    });
    function del(data) {
        alert("删除操作");
    }
    function update(data) {
        alert("修改操作");
    }

    function detail(data) {
        alert("查看操作");
    }


    var week_CN = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]

    var table;

    function attendQuery() {
        table.ajax.reload();
    }
    $(function () {
        //提示信息
        $.fn.dataTable.ext.errMode = 'none';

        var lang = {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页 _MENU_ 项",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
            "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "查询结果为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页",
                "sJump": "跳转"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        };

        //初始化表格
        table = $("#attend_grid")
            .on('error.dt', function (e, settings, techNote, message) {
                console.warn(message)
            }).dataTable({
                ordering: false, // 禁止排序
                language: lang, //提示信息
                autoWidth: false, //禁用自动调整列宽
                stripeClasses: ["odd", "even"], //为奇偶行加上样式，兼容不支持CSS伪类的场合
                processing: true, //隐藏加载提示,自行处理
                serverSide: true, //启用服务器端分页
                searching: false, //禁用原生搜索
                orderMulti: false, //启用多列排序
                order: [], //取消默认排序查询,否则复选框一列会出现小箭头
                renderer: "bootstrap", //渲染样式：Bootstrap和jquery-ui
                pagingType: "simple_numbers", //分页样式：simple,simple_numbers,full,full_numbers
                columnDefs: [{
                    "targets": 'nosort', //列的样式名
                    "orderable": false //包含上样式名‘nosort'的禁止排序
                }],
                ajax: function (data, callback, settings) {
                    //封装请求参数
                    var param = formToJson("userQueryForm");
                    param.pageSize = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                    param.start = data.start;//开始的记录序号
                    param.currentPage = (data.start / data.length) + 1;//当前页码
                    //console.log(param);
                    //ajax请求数据
                    $.ajax({
                        type: "GET",
                        url: "/attend/myAttends",
                        cache: false, //禁用缓存
                        data: param, //传入组装的参数
                        dataType: "json",
                        success: function (result) {
                            console.log(result);
                            //console.log(result);
                            //setTimeout仅为测试延迟效果
                            setTimeout(function () {
                                //封装返回数据
                                var returnData = {};
                                returnData.draw = data.startRow;//这里直接自行返回了draw计数器,应该由后台返回
                                returnData.recordsTotal = result.totalRows;//返回数据全部记录
                                returnData.recordsFiltered = result.totalRows;//后台不实现过滤功能，每次查询均视作全部结果
                                returnData.data = result.items;//返回的数据列表

                                //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                                callback(returnData);
                            }, 200);
                        }
                    });
                },
                //列表表头字段
                columns: [
                    {   "data": "seq",
                        render: function (data, type, full,row_col) {
                            return row_col.row+1;
                        }
                    },
                    {
                        "data": "attendDate",
                        render: function (data, type, full, row_col) {
                            if(data!=null){
                                return new Date(data).format("yyyy-MM-dd");
                            }else{
                                return "未打卡";
                            }
                        }
                    }
                    ,
                    {
                        "data": "attendWeek",
                        render: function (data, type, full, row_col) {
                            if(data==0){
                                return week_CN[6]
                            }
                            return week_CN[data-1];
                        }
                    }
                    ,
                    {
                        "data": "attendMorning",
                        render: function (data, type, full) {
                            if(data!=null){
                                return new Date(data).format("hh:mm");
                            }else{
                                return "<span style='color: blue'>上午未打卡</span>";
                            }
                        }
                    },
                    {
                        "data": "attendEvening",
                        render: function (data, type, full) {
                            if(data!=null){
                                return new Date(data).format("hh:mm");
                            }else{
                                return "<span style='color: blue'>下午未打卡</span>";
                            }
                        }
                    }
                    ,
                    {
                        "data": "status",
                        render: function (data, type, full) {
                            if(data==1){
                                return "<span style='color: green'>正常</span>";
                            }else{
                                return "<span style='color: red'>异常</span>";
                            }
                        }
                    }
                ]
            })
            .api();
    });

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

