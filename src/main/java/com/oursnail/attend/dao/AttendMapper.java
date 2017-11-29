package com.oursnail.attend.dao;

import com.oursnail.attend.entity.Attend;
import com.oursnail.common.resp.QueryCondition;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(String id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    int countByCondition(QueryCondition queryCondition);

    List<Attend> getAttendPage(QueryCondition queryCondition);

    Attend selectTodaySignRecord(Long userId);

    void batchInsert(List<Attend> attendList);

    List<Attend> selectTodayEveningAbsence();

    List<Long> selectTodayAbsence();
}