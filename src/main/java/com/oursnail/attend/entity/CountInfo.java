package com.oursnail.attend.entity;

import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/18 20:09
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class CountInfo {
    private String username;
    private int totalAttends;
    private int normal;
    private int abnormal;
}
