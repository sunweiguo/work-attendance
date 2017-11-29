package com.oursnail.wechat.controller;

import com.oursnail.common.constants.Constants;
import com.oursnail.common.resp.Result;
import com.oursnail.wechat.jssdk.JSSDK_Config;
import com.oursnail.wechat.oauth.GetUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 15:40
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@Controller
@RequestMapping("/wechatconfig")
@Slf4j
public class WeChatController {
    @RequestMapping("/jssdkconfig")
    public String jssdkConfig(){
        return "/wechatconfig/jssdkconfig";
    }

    @RequestMapping(value = "jssdk",method = RequestMethod.POST)
    @ResponseBody
    public Result JSSDK_config(
            @RequestParam(value = "url", required = true) String url) {
        Result result = new Result();
        try {
            System.out.println(url);
            Map<String, String> configMap = JSSDK_Config.jsSDK_Sign(url);
            result.setCode(Constants.RESP_STATUS_OK);
            result.setData(configMap);
            return result;
        } catch (Exception e) {
            result.setCode(Constants.RESP_STATUS_BADREQUEST);
            return result;
        }
    }

    @RequestMapping("weixinOauth")
    public String weiXinOauth(@RequestParam(value = "code", required = true) String code,
                            @RequestParam(value = "state", required = true) String state,
                            HttpSession session,
                            Model model) {
        System.out.println("Code============="+code+"==========state======="+state);
        try {
            // 用code取得微信用户的基本信息
            GetUserInfo oauth = new GetUserInfo(code);
            HashMap<String, String> userInfo = oauth.getUserInfo(code);
            session.setAttribute("userInfo",userInfo);
            model.addAttribute("userInfo",userInfo);
        } catch (Exception e) {
            log.error(e.toString(), e);
        }
        return "/wechatconfig/userInfo";
    }

    @RequestMapping("/detail")
    public String detail(HttpSession session){
        return "/wechatconfig/detail";
    }




}
