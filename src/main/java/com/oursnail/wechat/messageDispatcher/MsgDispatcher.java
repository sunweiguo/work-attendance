package com.oursnail.wechat.messageDispatcher;

import com.oursnail.wechat.message.req.TextMessage;
import com.oursnail.wechat.message.resp.Article;
import com.oursnail.wechat.message.resp.NewsMessage;
import com.oursnail.wechat.utils.MessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 10:45
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class MsgDispatcher {

    public static String processMessage(Map<String, String> map) {
        String openid=map.get("FromUserName"); //用户openid
        String mpid=map.get("ToUserName");   //公众号原始ID

        //普通文本消息
        TextMessage txtmsg=new TextMessage();
        txtmsg.setToUserName(openid);
        txtmsg.setFromUserName(mpid);
        txtmsg.setCreateTime(new Date().getTime());
        txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            txtmsg.setContent("hello world");
            return MessageUtil.textMessageToXml(txtmsg);
        }

        //对图文消息
        NewsMessage newmsg=new NewsMessage();
        newmsg.setToUserName(openid);
        newmsg.setFromUserName(mpid);
        newmsg.setCreateTime(new Date().getTime());
        newmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
            Article article=new Article();
            article.setDescription("这是图文消息1"); //图文消息的描述
            article.setPicUrl("http://oyii3l15f.bkt.clouddn.com/%E7%99%BB%E9%99%86%E9%A1%B5%E9%9D%A2.png"); //图文消息图片地址
            article.setTitle("点进去是这个项目源码");  //图文消息标题
            article.setUrl("https://gitee.com/_swg/work_attendance");  //图文url链接
            List<Article> list=new ArrayList<Article>();
            list.add(article);     //这里发送的是单图文，如果需要发送多图文则在这里list中加入多个Article即可！
            newmsg.setArticleCount(list.size());
            newmsg.setArticles(list);
            return MessageUtil.newsMessageToXml(newmsg);
        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");
        }


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }

        return null;
    }
}
