package com.oursnail.wechat.entity;

import lombok.Data;

import java.util.Date;

@Data
public class WxOpenIdUserId {
    private String wxid;

    private String accessToken;

    private String openid;

    private String userid;

    private Date createtime;

}