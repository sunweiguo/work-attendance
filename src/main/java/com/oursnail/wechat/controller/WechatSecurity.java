package com.oursnail.wechat.controller;

import com.oursnail.wechat.messageDispatcher.EventDispatcher;
import com.oursnail.wechat.messageDispatcher.MsgDispatcher;
import com.oursnail.wechat.utils.MessageUtil;
import com.oursnail.wechat.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 9:53
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatSecurity {

    @RequestMapping(value = "/security" , method = RequestMethod.GET)
    public void doGet(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(value = "signature", required = true) String signature,
                        @RequestParam(value = "timestamp", required = true) String timestamp,
                        @RequestParam(value = "nonce", required = true) String nonce,
                        @RequestParam(value = "echostr", required = true) String echostr){
        System.out.println("signature=="+signature);
        try {
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                PrintWriter out = response.getWriter();
                out.print(echostr);
                out.close();
            } else {
                log.info("这里存在非法请求！");
            }
        } catch (Exception e) {
            log.error("未知错误",e);
        }
    }

    @RequestMapping(value = "security", method = RequestMethod.POST)
    // post方法用于接收微信服务端消息
    public void DoPost(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("这是post方法！");
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try{
            Map<String, String> map= MessageUtil.parseXml(request);
            String msgtype=map.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)){
                String msgrsp = EventDispatcher.processEvent(map);//进入事件处理
                PrintWriter out = response.getWriter();
                out.print(msgrsp);
                out.close();
            }else{
                String msgrsp = MsgDispatcher.processMessage(map); //进入消息处理
                PrintWriter out = response.getWriter();
                out.print(msgrsp);
                out.close();
            }
        }catch(Exception e){
            log.error("未知错误",e);
        }
    }
}
