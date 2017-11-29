package com.oursnail.wechat.message.req;

import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 10:42
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class BaseMessage {
    // 开发者微信号
    private String ToUserName;
    // 发送方帐号（一个OpenID）
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/image/location/link/video/shortvideo）
    private String MsgType;
    // 消息id，64位整型
    private long MsgId;
}
