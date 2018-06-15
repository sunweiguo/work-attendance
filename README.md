# 一、打卡小系统

1、基础框架是SSM<br>
2、页面仍然是jsp，框架使用bootstarp，数据表格显示用的是jquery datatable配合后台实现分页<br>
3、idea开发 maven管理包 mysql数据库<br>
4、quartz定时调度<br>
5、activemq消息队列结合java mail发送激活邮件<br>
6、cropper实现头像的剪切<br>
7、shiro实现权限的控制<br>
8、oss存储图片<br>

# 二、后台管理效果展示：<br>
注：这里的图片是存在七牛云。。。<br>

* gif效果<br>
![](http://oyii3l15f.bkt.clouddn.com/jdfw.gif) <br>
* 登陆页面<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E7%99%BB%E9%99%86%E9%A1%B5%E9%9D%A2.png)<br>
* 注册页面<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E6%B3%A8%E5%86%8C%E9%A1%B5%E9%9D%A2.png)<br>
* 打卡记录页面<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E7%99%BB%E9%99%86%E8%80%85%E6%89%93%E5%8D%A1%E5%8E%86%E5%8F%B2%E7%BA%AA%E5%BD%95%E9%A1%B5%E9%9D%A2.png)<br>
* 修改个人信息页面<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E6%9B%B4%E6%94%B9%E4%B8%AA%E4%BA%BA%E5%9F%BA%E6%9C%AC%E4%BF%A1%E6%81%AF%E9%A1%B5%E9%9D%A2.png)<br>
* 修改头像页面<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E6%9B%B4%E6%94%B9%E5%A4%B4%E5%83%8F%E9%A1%B5%E9%9D%A2.png)<br>
* 用户列表页面
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E7%94%A8%E6%88%B7%E5%88%97%E8%A1%A8%E9%A1%B5%E9%9D%A2.png)<br>
* 用户信息模态框展示<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF%E6%A8%A1%E6%80%81%E6%A1%86%E5%B1%95%E7%A4%BA.png)<br>
* 更改密码页面<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E6%9B%B4%E6%94%B9%E5%AF%86%E7%A0%81%E9%A1%B5%E9%9D%A2.png)<br>

# 三、微信端<br>
很不好意思，集成了微信端之后，发现页面写不好。。。想想还是将这个打卡功能直接放在后台吧。关于微信，接入微信平台、jssdk的配置和使用、access_token的获取、oauth2.0用户授权都使用了一遍。<br>
打卡的效果图:<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E6%89%93%E5%8D%A1.png)<br>
数据统计：<br>
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E6%95%B0%E6%8D%AE%E7%BB%9F%E8%AE%A1.png)<br>
关注后：
![Alt text](http://oyii3l15f.bkt.clouddn.com/%E5%BE%AE%E4%BF%A1%E5%85%B3%E6%B3%A8%E9%A1%B5%E9%9D%A2.jpg)<br>
关注微信公众号：
![Alt text](http://oyii3l15f.bkt.clouddn.com/0.jpg)<br>

# 四、数据库<br>
详见打卡系统表.txt
