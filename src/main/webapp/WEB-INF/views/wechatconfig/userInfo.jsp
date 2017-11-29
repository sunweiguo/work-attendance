<%--
  Created by IntelliJ IDEA.
  User: swg
  Date: 2017/11/17
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
</head>
<body>
<div style="text-align: center">
    <div id="content">
        <h4>个人微信公开信息</h4>
        <table border="1" cellspacing="0" cellpadding="0" style="max-width: 100%;text-align: center">
            <tr>
                <td> 用户名</td><td>${userInfo.nickname}</td>
            </tr>
            <tr>
                <td> 用户名</td><td>${userInfo.province}</td>
            </tr>
            <tr>
                <td> 城市</td><td>${userInfo.city}</td>
            </tr>
            <tr>
                <td> 头像</td><td><img width="100px;" height="100px;" src="http://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJhCVV0icRNZ6hdr4wxjRG88dTV2aicxpUGGCibbJA1PJiaMD14KkHnzP278ITrYON2Dh3eJT8D8o8mHQ/0"/></td>
            </tr>
            <tr>
                <td> openid</td><td>${userInfo.openid}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
