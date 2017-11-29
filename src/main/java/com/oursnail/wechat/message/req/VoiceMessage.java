package com.oursnail.wechat.message.req;

import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 10:45
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class VoiceMessage extends BaseMessage {
    // 媒体ID
    private String MediaId;
    // 语音格式
    private String Format;
}
