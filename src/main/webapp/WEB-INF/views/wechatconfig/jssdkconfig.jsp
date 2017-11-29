<%--
  Created by IntelliJ IDEA.
  User: swg
  Date: 2017/11/16
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width" />
    <title>JSSDk配置</title>
    <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        function jssdk() {
            $.ajax({
                url : "http://www.oursnail.xin/wechatconfig/jssdk",
                type : 'post',
                dataType : 'json',
                contentType : "application/x-www-form-urlencoded; charset=utf-8",
                data : {
                    'url' : location.href.split('#')[0]
                },
                success : function(data) {
                    wx.config({
                        debug : false,
                        appId : data.data.appId,
                        timestamp : data.data.timestamp,
                        nonceStr : data.data.nonceStr,
                        signature : data.data.signature,
                        jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
                            'onMenuShareAppMessage', 'onMenuShareQQ',
                            'onMenuShareWeibo', 'hideMenuItems',
                            'showMenuItems', 'hideAllNonBaseMenuItem',
                            'showAllNonBaseMenuItem', 'translateVoice',
                            'startRecord', 'stopRecord', 'onRecordEnd',
                            'playVoice', 'pauseVoice', 'stopVoice',
                            'uploadVoice', 'downloadVoice', 'chooseImage',
                            'previewImage', 'uploadImage', 'downloadImage',
                            'getNetworkType', 'openLocation', 'getLocation',
                            'hideOptionMenu', 'showOptionMenu', 'closeWindow',
                            'scanQRCode', 'chooseWXPay',
                            'openProductSpecificView', 'addCard', 'chooseCard',
                            'openCard' ]
                    });
                    wx.ready(function () {

                        $('#getnetwork').click(function() {
                            wx.getNetworkType({
                                success: function (res) {
                                    alert(res.networkType);
                                    var networkType = res.networkType; // 返回网络类型2g，3g，4g，wifi
                                    if(networkType=='3g'){
                                        alert("您好，您的网络状态是3g网络，这里将播放视频文件会产生大流程!");
                                    }
                                },
                                fail: function (res) {
                                    alert(JSON.stringify(res));
                                }
                            });
                        })

                        $('#getlocation').click(function() {
                            wx.getLocation({
                                success: function (res) {
                                    alert(JSON.stringify(res));
                                },
                                cancel: function (res) {
                                    alert('用户拒绝授权获取地理位置');
                                }
                            });
                        })

                        $('#scan').click(function() {
                            wx.scanQRCode({
                                needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                                scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                                success: function (res) {
                                    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
                                }
                            });
                        })
                        $('#photo').click(function() {
                            wx.chooseImage({
                                count: 1, // 默认9
                                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                                success: function (res) {
                                    var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                                    $("#imageId").attr("src",localIds);
                                }
                            });
                        })

                        $('#preview').click(function() {
                            wx.previewImage({
                                current: 'http://oyii3l15f.bkt.clouddn.com/%E7%94%A8%E6%88%B7%E5%88%97%E8%A1%A8%E9%A1%B5%E9%9D%A2.png', // 当前显示图片的http链接
                                urls: ['http://oyii3l15f.bkt.clouddn.com/%E6%89%93%E5%8D%A1.png','http://oyii3l15f.bkt.clouddn.com/%E6%95%B0%E6%8D%AE%E7%BB%9F%E8%AE%A1.png'] // 需要预览的图片http链接列表

                            });
                        })







                    })
                    //ready结束

                }
            });
        }

        function isWeiXin5() {
            var ua = window.navigator.userAgent.toLowerCase();
            var reg = /MicroMessenger\/[5-9]/i;
            return reg.test(ua);
        }

        window.onload = function() {
            //     if (isWeiXin5() == false) {
            //           alert("您的微信版本低于5.0，无法使用微信支付功能，请先升级！");
            //         }
            jssdk();
        };
    </script>
</head>
<body>
        <input type="button" id="getnetwork" value="得到当前网络情况"><br><br>
        <input type="button" id="getlocation" value="得到当前地理位置"><br><br>
        <input type="button" id="scan" value="扫一扫"><br><br>
        <input type="button" id="photo" value="选择图片或拍照"><br><br>
        显示图片：<img src="" style="width: 100px;height: 100px;" id="imageId"><br><br>
        <input type="button" id="preview" value="预览图片"><br><br>


</body>
</html>