package com.oursnail.wechat.dao;

import com.oursnail.wechat.entity.WxOpenIdUserId;

public interface WxOpenIdUserIdMapper {
    int deleteByPrimaryKey(String wxid);

    int insert(WxOpenIdUserId record);

    int insertSelective(WxOpenIdUserId record);

    WxOpenIdUserId selectByPrimaryKey(String wxid);

    int updateByPrimaryKeySelective(WxOpenIdUserId record);

    int updateByPrimaryKey(WxOpenIdUserId record);

    WxOpenIdUserId getWxOpenIdUserIdByOpenId(String openId);
}