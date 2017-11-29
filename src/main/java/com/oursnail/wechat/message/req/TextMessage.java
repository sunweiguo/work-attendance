package com.oursnail.wechat.message.req;

import com.oursnail.wechat.message.req.BaseMessage;
import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 10:44
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;
}
