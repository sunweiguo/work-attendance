package com.oursnail.wechat.service;

import com.oursnail.wechat.dao.WxOpenIdUserIdMapper;
import com.oursnail.wechat.entity.WxOpenIdUserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 【swg】.
 * @Date 2017/11/18 10:02
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Service
@Slf4j
public class WxOpenIdUserIdServiceImpl implements WxOpenIdUserIdService{
    @Autowired
    private WxOpenIdUserIdMapper wxOpenIdUserIdMapper;

    @Override
    public WxOpenIdUserId getWxOpenIdUserIdByOpenId(String openId) {
        return wxOpenIdUserIdMapper.getWxOpenIdUserIdByOpenId(openId);
    }

    @Override
    public void addNewWxOpenIdUserId(WxOpenIdUserId wxOpenIdUserId) {
        wxOpenIdUserIdMapper.insertSelective(wxOpenIdUserId);
    }
}
