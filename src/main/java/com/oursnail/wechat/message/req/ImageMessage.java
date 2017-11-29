package com.oursnail.wechat.message.req;

import com.oursnail.wechat.message.req.BaseMessage;
import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 10:42
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;
}
