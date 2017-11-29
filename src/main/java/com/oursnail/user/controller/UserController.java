package com.oursnail.user.controller;

import com.oursnail.common.constants.Constants;
import com.oursnail.common.resp.PageQueryBean;
import com.oursnail.common.resp.QueryCondition;
import com.oursnail.common.resp.Result;
import com.oursnail.common.utils.MD5Util;
import com.oursnail.user.entity.User;
import com.oursnail.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 【swg】.
 * @Date 2017/11/11 11:32
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /*来到用户列表页面*/
    @RequiresRoles("admin")
    @RequestMapping(value = "/userList" , method = RequestMethod.GET)
    public String listUsers(HttpSession session,Model model){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            System.out.println("用户未登陆，请登录");
            return "/login/login";
        }
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("domain", Constants.IMAGES_DOMAIN);
        return "/user/userList";
    }

    /*提供前台用户列表的json信息*/
    @RequiresRoles("admin")
    @RequestMapping("/users")
    @ResponseBody
    public PageQueryBean listUsers(QueryCondition condition, HttpSession session){
        if(condition.getContent()==""){
            condition.setContent(null);
        }
        PageQueryBean result = userService.getAllUsers(condition);
        return result;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "/login/login";
    }


    /*给前台提供用户信息json*/
    @RequiresRoles("admin")
    @RequestMapping("/showUser")
    @ResponseBody
    public Result showUser(HttpServletRequest request){
        Long userId = Long.parseLong(request.getParameter("userId"));
        User user = userService.selectByPrimaryKey(userId);
        Result result = new Result();
        result.setCode(200);
        result.setData(user);
        return result;
    }

    /*更新用户*/
    @RequiresRoles("admin")
    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser(HttpServletRequest request,HttpSession session){
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("phoneNumber");
        Long userId = Long.parseLong(request.getParameter("userId"));
        int sex = Integer.parseInt(request.getParameter("sex"));
        User updateUser = userService.selectByPrimaryKey(userId);
        updateUser.setUsername(username);
        updateUser.setPhonenumber(phoneNumber);
        updateUser.setSex((byte)sex);
        userService.updateUser(updateUser);
        Result result = new Result();
        result.setCode(Constants.RESP_STATUS_OK);
        result.setMessgae("修改成功");
        //session.setAttribute("loginUser",updateUser);
        return result;
    }

    /*展示个人信息页面*/
    @RequestMapping(value="personalCenter",method = RequestMethod.GET)
    public String personalCenter(HttpSession session,Model model){
        User loginUser = (User) session.getAttribute("loginUser");
        if(loginUser==null){
            System.out.println("用户未登陆，请登录");
            return "/login/login";
        }
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("domain", Constants.IMAGES_DOMAIN);
        return "/user/personalCenter";
    }

    /*更新个人信息*/
    @RequestMapping("/updatePersonalInfo")
    @ResponseBody
    public Result updatePersonalInfo(HttpServletRequest request,HttpSession session){
        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");
        String username = request.getParameter("username");
        String phoneNumber = request.getParameter("phoneNumber");
        Long id = Long.parseLong(request.getParameter("id"));
        int sex = Integer.parseInt(request.getParameter("sex"));
        if(loginUser.getId().equals(id)){
            loginUser.setUsername(username);
            loginUser.setPhonenumber(phoneNumber);
            loginUser.setSex((byte)sex);
            userService.updateUser(loginUser);
            session.setAttribute("loginUser",loginUser);
            result.setCode(Constants.RESP_STATUS_OK);
            result.setMessgae("更新成功");
            result.setData(loginUser);
            return result;
        }else {
            result.setCode(Constants.RESP_STATUS_BADREQUEST);
            result.setMessgae("更新失败");
            return result;
        }
    }

    @RequestMapping("/modifyPersonalPassword")
    public String modifyPersonalPasswd(HttpSession session,Model model){
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser",loginUser);
        model.addAttribute("domain", Constants.IMAGES_DOMAIN);
        return "/user/modifyPersonalPassword";
    }

    @RequestMapping(value = "/checkOldPasswd" , method = RequestMethod.POST)
    @ResponseBody
    public Result checkOldPasswd(@RequestParam String oldPassword, HttpSession session, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser",loginUser);
        String password = loginUser.getPassword();
        if(!MD5Util.checkPassword(oldPassword,password)){
            result.setCode(Constants.RESP_STATUS_BADREQUEST);
            return result;
        }else{
            result.setCode(Constants.RESP_STATUS_OK);
            return result;
        }
    }

    @RequestMapping(value = "/checkNewPasswd1" , method = RequestMethod.POST)
    @ResponseBody
    public Result checkNewPasswd1(@RequestParam String newPassword, HttpSession session, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser",loginUser);
        String password = loginUser.getPassword();
        if(MD5Util.checkPassword(newPassword,password)){
            result.setCode(Constants.RESP_STATUS_BADREQUEST);
            return result;
        }else{
            result.setCode(Constants.RESP_STATUS_OK);
            return result;
        }
    }

    @RequestMapping(value = "/checkNewPasswd2" , method = RequestMethod.POST)
    @ResponseBody
    public Result checkNewPasswd2(@RequestParam String newPassword,@RequestParam String newPassword2,HttpSession session,Model model){
        Result result = new Result();
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser",loginUser);
        if(!newPassword2.equals(newPassword)){
            result.setCode(Constants.RESP_STATUS_BADREQUEST);
            return result;
        }else{
            result.setCode(Constants.RESP_STATUS_OK);
            return result;
        }
    }

    @RequestMapping(value = "/modifyPersonalPassword" , method = RequestMethod.POST)
    public String modifyPasswd(String oldPassword,
                               String newPassword,
                               String newPassword2,
                               HttpSession session,
                               Model model) throws Exception{
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("domain", Constants.IMAGES_DOMAIN);
        String dbPassword = loginUser.getPassword();
        try{
            if(MD5Util.checkPassword(oldPassword,dbPassword)){
                if(newPassword.equals(newPassword2)){
                    loginUser.setPassword(MD5Util.encryptPassword(newPassword));
                    this.userService.updateUser(loginUser);
                    return "redirect:/user/success";
                }
            }
        }catch (Exception e){
            throw e;
        }
//      出现问题就停在修改密码页面
        return "/user/modifyPersonalPassword";
    }

    /*修改密码成功之后跳转到成功页面，倒计时之后重新登陆系统*/
    @RequestMapping(value = "/success",method = RequestMethod.GET)
    public String success(HttpSession session,Model model){
        try{
            User loginUser = (User) session.getAttribute("loginUser");
            model.addAttribute("loginUser",loginUser);
            model.addAttribute("domain", Constants.IMAGES_DOMAIN);
            return "/user/success";
        }catch (Exception e){
            log.error("未知错误",e);
            throw e;
        }


    }


    @RequestMapping("/denied")
    public String denied(){
        return "denied";
    }

}
