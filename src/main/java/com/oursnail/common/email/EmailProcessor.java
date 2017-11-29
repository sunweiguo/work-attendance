package com.oursnail.common.email;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 15:04
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Component
public class EmailProcessor {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendEmaillToQueue(Destination destination, String message) {
        System.out.println("---------------------------------------生产----");
        jmsTemplate.convertAndSend(destination, message);
    }
}
