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
public class VideoMessage extends BaseMessage {
    private String MediaId; // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
    private String ThumbMediaId; // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
}
