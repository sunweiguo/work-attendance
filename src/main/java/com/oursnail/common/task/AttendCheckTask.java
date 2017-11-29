package com.oursnail.common.task;

import com.oursnail.attend.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author 【swg】.
 * @Date 2017/11/14 14:34
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class AttendCheckTask {
    @Autowired
    private AttendService attendService;

    public  void checkAttend(){
        System.out.println("-----task-----");
        attendService.checkAttend();
    }
}
