package com.oursnail.register.service;

import com.oursnail.common.exception.WxRecordException;
import com.oursnail.register.entity.Register;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 12:59
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public interface RegisterService {

    Boolean isUserOld(String email);

    void addNewUser(String username, String password, String rePassword, String email, String phoneNumber) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    Register getRegisterByCode(String code) throws WxRecordException;

    void updateStatus(Register r);

    Register getRegisterByUserId(Long userId);
}
