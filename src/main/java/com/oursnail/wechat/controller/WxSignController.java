package com.oursnail.wechat.controller;

import com.oursnail.common.resp.Result;
import com.oursnail.wechat.entity.WxAttend;
import com.oursnail.wechat.entity.WxOpenIdUserId;
import com.oursnail.wechat.init.GlobalConstants;
import com.oursnail.wechat.oauth.GetUserInfo;
import com.oursnail.wechat.service.WxAttendService;
import com.oursnail.wechat.service.WxOpenIdUserIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Author 【swg】.
 * @Date 2017/11/17 15:59
 * @DESC
 * @CONTACT 317758022@qq.com
 */
@RequestMapping("/sign")
@Controller
@Slf4j
public class WxSignController {
    @Autowired
    private WxOpenIdUserIdService wxOpenIdUserIdService;
    @Autowired
    private WxAttendService wxAttendService;

    @RequestMapping("weixinOauthForSign")
    public String weiXinOauth(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model,
                              @RequestParam(value = "code", required = true) String code,
                              @RequestParam(value = "state", required = true) String state) {
        WxOpenIdUserId wxOpenIdUserId = null;
        try {
            //根据code拿到网页授权的access_token，从而获取用户个人信息
            Map<String, String> wmap = GetUserInfo.getUserInfo(code);
            String openId = wmap.get("openid");
            //根据openId查询数据库中是否存在这样一条记录，不存在就直接插入一条新的
            wxOpenIdUserId = wxOpenIdUserIdService.getWxOpenIdUserIdByOpenId(openId);
            if(wxOpenIdUserId == null){
                wxOpenIdUserId = new WxOpenIdUserId();
                wxOpenIdUserId.setWxid(UUID.randomUUID().toString().replace("-", ""));
                wxOpenIdUserId.setCreatetime(new Date());
                wxOpenIdUserId.setOpenid(openId);
                wxOpenIdUserId.setAccessToken(GlobalConstants.interfaceUrlProperties.getProperty("access_token"));
                wxOpenIdUserId.setUserid(UUID.randomUUID().toString().replace("-", ""));
                wxOpenIdUserIdService.addNewWxOpenIdUserId(wxOpenIdUserId);
            }
            System.out.println("-------wxOpenIdUserId-------"+wxOpenIdUserId);
            session.setAttribute("openid",wmap.get("openid"));
            GlobalConstants.interfaceUrlProperties.put("openid",wmap.get("openid"));
            session.setAttribute("userid",wxOpenIdUserId.getUserid());

        } catch (Exception e) {
            log.warn("发生异常:"+e);
        }
        return "redirect:/sign/signIndex";
    }

    @RequestMapping("/signIndex")
    public String sign(HttpSession session,Model model){
        String userid = (String) session.getAttribute("userid");
        System.out.println("---userid--"+userid);
        model.addAttribute("userid",userid);
        return "/wechatconfig/sign";
    }

    @RequestMapping("/sign")
    @ResponseBody
    public Result sign(@RequestBody String userId){
        Result result = new Result();
        WxAttend wxAttend = new WxAttend();
        wxAttend.setUserId(userId);
        wxAttendService.signAttend(wxAttend);
        result.setMessgae("签到成功");
        return result;
    }
}
