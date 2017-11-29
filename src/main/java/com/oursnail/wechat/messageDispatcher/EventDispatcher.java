package com.oursnail.wechat.messageDispatcher;

import com.oursnail.wechat.message.resp.Article;
import com.oursnail.wechat.message.resp.NewsMessage;
import com.oursnail.wechat.message.userInfo.GetUseInfo;
import com.oursnail.wechat.utils.MessageUtil;

import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * @Author 【swg】.
 * @Date 2017/11/16 10:46
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Slf4j
public class EventDispatcher {
    public static String processEvent(Map<String, String> map) {
        String openid=map.get("FromUserName"); //用户openid
        String mpid=map.get("ToUserName");   //公众号原始ID
        //对图文消息
        NewsMessage newmsg=new NewsMessage();
        newmsg.setToUserName(openid);
        newmsg.setFromUserName(mpid);
        newmsg.setCreateTime(new Date().getTime());
        newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            System.out.println("==============这是关注事件！");
            try {
                HashMap<String, String> userinfo= GetUseInfo.Openid_userinfo(openid);
                Article article=new Article();
                article.setDescription("源码链接"); //图文消息的描述
                article.setPicUrl(userinfo.get("headimgurl")); //图文消息图片地址
                article.setTitle("尊敬的："+userinfo.get("nickname")+",你好！");  //图文消息标题
                article.setUrl("https://gitee.com/_swg/work_attendance");  //图文url链接
                List<Article> list=new ArrayList<Article>();
                list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
                newmsg.setArticleCount(list.size());
                newmsg.setArticles(list);
                return MessageUtil.newsMessageToXml(newmsg);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                log.error("未知错误",e);
            }
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
            System.out.println("==============这是扫描二维码事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
            System.out.println("==============这是位置上报事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            System.out.println("==============这是自定义菜单点击事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单View事件
            System.out.println("==============这是自定义菜单View事件！");
        }


        return null;
    }
}
