package com.oursnail.wechat.message.req;

import com.oursnail.wechat.message.req.BaseMessage;
import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 10:43
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class LinkMessage extends BaseMessage {
    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;
}
