package com.oursnail.user.entity;

import lombok.Data;

/**
 * @Author 【swg】.
 * @Date 2017/11/14 22:07
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Data
public class Permission {
    private int id;
    private String permissionName;
    private int roleId;
}
