package com.oursnail.wechat.service;

import com.oursnail.wechat.entity.WxOpenIdUserId;

/**
 * @Author 【swg】.
 * @Date 2017/11/18 9:59
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public interface WxOpenIdUserIdService {
    WxOpenIdUserId getWxOpenIdUserIdByOpenId(String openId);
    void addNewWxOpenIdUserId(WxOpenIdUserId wxOpenIdUserId);
}
