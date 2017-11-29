package com.oursnail.login.controller;

import com.oursnail.common.constants.Constants;
import com.oursnail.common.resp.Result;
import com.oursnail.common.utils.MD5Util;
import com.oursnail.register.service.RegisterService;
import com.oursnail.user.entity.User;
import com.oursnail.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 9:20
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterService registerService;

    @RequestMapping("")
    public String login(){
        return "login/login";
    }

/*    @RequestMapping("/check")
    @ResponseBody
    public Result check(HttpServletRequest request, HttpSession session){
        Result result = new Result();
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if(StringUtils.isBlank(email) || StringUtils.isBlank(password)){
                result.setMessgae("输入的用户名或者密码为空");
                result.setCode(Constants.RESP_STATUS_BADREQUEST);
                return result;
            }
            User loginUser = userService.getUserByEmail(email);
            if(null==loginUser){
                result.setCode(Constants.RESP_STATUS_BADREQUEST);
                result.setMessgae("用户名不正确");
            }
            else if(!MD5Util.checkPassword(password,loginUser.getPassword())){
                result.setCode(Constants.RESP_STATUS_BADREQUEST);
                result.setMessgae("密码不正确");
            }else if((registerService.getRegisterByUserId(loginUser.getId()).getState()==0)){
                result.setCode(Constants.RESP_STATUS_BADREQUEST);
                result.setMessgae("还没有激活");
            }else {
                session.setAttribute("loginUser",loginUser);
                return result;
            }

        }catch (Exception e){
            log.error("未知错误",e);
        }
        result.setCode(Constants.RESP_STATUS_BADREQUEST);
        return result;
    }*/


    @RequestMapping("/check")
    @ResponseBody
    public Result check(HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Result result = new Result();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email, password);
        token.setRememberMe(true);
        try {
            subject.login(token);
            SecurityUtils.getSubject().getSession().setTimeout(1800000);
        } catch (Exception e) {
            result.setCode(Constants.RESP_STATUS_BADREQUEST);
            return result;
        }
        return result;
    }

}
