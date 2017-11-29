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
public class LocationMessage extends BaseMessage {
    // 地理位置维度
    private String Location_X;
    // 地理位置经度
    private String Location_Y;
    // 地图缩放大小
    private String Scale;
    // 地理位置信息
    private String Label;
}
