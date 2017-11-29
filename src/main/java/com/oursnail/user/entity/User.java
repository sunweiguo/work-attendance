package com.oursnail.user.entity;

import lombok.Data;

import java.util.Date;


@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private String headimgurl;

    private String phonenumber;

    private Date registerDate;

    private Byte sex;

    private String email;

    private Integer roleId;

}