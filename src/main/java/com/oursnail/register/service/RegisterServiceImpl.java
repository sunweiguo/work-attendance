package com.oursnail.register.service;

import com.alibaba.fastjson.JSON;
import com.oursnail.common.email.EmailProcessor;
import com.oursnail.common.exception.WxRecordException;
import com.oursnail.common.utils.MD5Util;
import com.oursnail.register.dao.RegisterMapper;
import com.oursnail.register.entity.Register;
import com.oursnail.user.dao.UserMapper;
import com.oursnail.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.Destination;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 12:59
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService{

    private static final String SMS_QUEUE = "email.queue";

    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailProcessor emailProcessor;

    @Override
    public Boolean isUserOld(String email) {
        User user = userMapper.getUserByEmail(email);
        if(user!=null){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void addNewUser(String username, String password, String rePassword, String email, String phoneNumber) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        if(password.equals(rePassword)){
            user.setPassword(MD5Util.encryptPassword(password));
        }
        user.setPhonenumber(phoneNumber);
        /*头像为默认，性别也默认为男*/
        user.setHeadimgurl("snail.jpg");
        user.setSex((byte)1);
        user.setRegisterDate(new Date());
        userMapper.insertSelective(user);
        Register register = new Register();
        register.setUserId(user.getId());
        //用户一点提交时，状态肯定是0，即未认证的情况
        register.setState((byte)0);
        //code也随机生成
        String code = UUID.randomUUID().toString().replace("-", "");
        register.setCode(code);
        registerMapper.insertSelective(register);
        //发送激活邮件
        Destination destination = new ActiveMQQueue(SMS_QUEUE);
        Map<String,String> emailParam = new HashMap<>();
        emailParam.put("email",email);
        emailParam.put("code", code);
        emailParam.put("username",username);
        String message = JSON.toJSONString(emailParam);
        emailProcessor.sendEmaillToQueue(destination,message);
    }

    @Override
    public Register getRegisterByCode(String code) throws WxRecordException {
        Register register = registerMapper.getRegisterByCode(code);
        if(register==null){
            throw new WxRecordException("不存在用户的激活记录");
        }
        return register;
    }

    @Override
    public void updateStatus(Register r) {
        registerMapper.updateByPrimaryKeySelective(r);
    }

    @Override
    public Register getRegisterByUserId(Long userId) {
        return registerMapper.getRegisterByUserId(userId);
    }
}
