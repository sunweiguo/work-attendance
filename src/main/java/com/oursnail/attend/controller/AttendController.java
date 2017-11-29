package com.oursnail.attend.controller;

import com.oursnail.attend.entity.Attend;
import com.oursnail.attend.entity.CountInfo;
import com.oursnail.attend.service.AttendService;
import com.oursnail.common.constants.Constants;
import com.oursnail.common.resp.PageQueryBean;
import com.oursnail.common.resp.QueryCondition;
import com.oursnail.common.resp.Result;
import com.oursnail.user.entity.User;
import java.util.List;

import com.oursnail.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author 【swg】.
 * @Date 2017/11/13 19:32
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Controller
@RequestMapping("/attend")
@Slf4j
public class AttendController {
    @Autowired
    private AttendService attendService;
    @Autowired
    private UserService userService;

    @RequestMapping("/personalAttend")
    public String personalAttend(HttpSession session,Model model){
        try{
            User loginUser = (User) session.getAttribute("loginUser");
            model.addAttribute("loginUser",loginUser);
            model.addAttribute("domain", Constants.IMAGES_DOMAIN);
            return "/attend/personalAttend";
        }catch (Exception e) {
            log.error("未知错误",e);
            throw e;
        }
    }

    @RequestMapping("/myAttends")
    @ResponseBody
    public PageQueryBean myAttends(QueryCondition condition, HttpSession session){
        try{
            User loginUser = (User) session.getAttribute("loginUser");
            String [] rangeDate = condition.getRangeDate().split("/");
            condition.setStartDate(rangeDate[0]);
            condition.setEndDate(rangeDate[1]);
            condition.setUserId(loginUser.getId());
            PageQueryBean result = attendService.getAttendPage(condition);
            return result;
        }catch (Exception e){
            log.error("展示个人签到失败",e);
            throw e;
        }
    }

    @RequestMapping("/sign")
    @ResponseBody
    public Result signAttend(HttpSession session) throws Exception {
        //System.out.println("userid----"+userId);
        Result result = new Result();
        Attend attend = new Attend();
        User loginUser = (User) session.getAttribute("loginUser");
        attend.setUserId(loginUser.getId());
        attendService.signAttend(attend);
        result.setCode(Constants.RESP_STATUS_OK);
        result.setMessgae("签到成功");
        return result;
    }

    @RequestMapping("/ShowAllAttendsByUserId")
    public String getAttendByUserId(@RequestParam String userId,Model model,HttpSession session){
        try{
            User loginUser = (User) session.getAttribute("loginUser");
            model.addAttribute("loginUser",loginUser);
            model.addAttribute("domain", Constants.IMAGES_DOMAIN);
            model.addAttribute("userId",userId);
            Long id = Long.parseLong(userId);
            User user = userService.selectByPrimaryKey(id);
            model.addAttribute("user",user);
            return "/attend/ShowAllAttendsByUserId";
        }catch (Exception e) {
            log.error("未知错误",e);
            throw e;
        }
    }

    @RequestMapping("/showAttends/{userId}")
    @ResponseBody
    public PageQueryBean showAttends(QueryCondition condition, HttpSession session,@PathVariable Long userId){
        try{
            String [] rangeDate = condition.getRangeDate().split("/");
            condition.setStartDate(rangeDate[0]);
            condition.setEndDate(rangeDate[1]);
            User user = userService.selectByPrimaryKey(userId);
            condition.setUserId(user.getId());
            PageQueryBean result = attendService.getAttendPage(condition);
            return result;
        }catch (Exception e){
            log.error("展示个人签到失败",e);
            throw e;
        }
    }

    @RequestMapping("/count")
    public String count(HttpSession session,Model model){
        try{
            User loginUser = (User) session.getAttribute("loginUser");
            model.addAttribute("loginUser",loginUser);
            model.addAttribute("domain", Constants.IMAGES_DOMAIN);
            List<CountInfo> countInfos = attendService.getCountInfo();
            model.addAttribute("countInfos",countInfos);
            return "/attend/count";
        }catch (Exception e) {
            log.error("未知错误",e);
            throw e;
        }
    }






}
