package com.oursnail.wechat.task;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 11:37
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Slf4j
public class QuartzJob {
    public void workForToken() {
        try {
            WeChatTask timer = new WeChatTask();
            timer.getToken_getTicket();
        } catch (Exception e) {
            log.error("未知错误",e);
        }
    }
}
