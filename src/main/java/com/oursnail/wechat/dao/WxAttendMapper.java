package com.oursnail.wechat.dao;

import com.oursnail.wechat.entity.WxAttend;

public interface WxAttendMapper {
    int deleteByPrimaryKey(String id);

    int insert(WxAttend record);

    int insertSelective(WxAttend record);

    WxAttend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WxAttend record);

    int updateByPrimaryKey(WxAttend record);

    WxAttend selectTodaySignRecord(String userId);
}