package com.oursnail.attend.service;

import com.oursnail.attend.entity.Attend;
import com.oursnail.attend.entity.CountInfo;
import com.oursnail.common.resp.PageQueryBean;
import com.oursnail.common.resp.QueryCondition;

import java.util.List;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 19:33
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public interface AttendService {
    PageQueryBean getAttendPage(QueryCondition condition);

    void signAttend(Attend attend);

    void checkAttend();

    List<CountInfo> getCountInfo();
}
