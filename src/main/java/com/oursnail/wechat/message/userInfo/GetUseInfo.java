package com.oursnail.wechat.message.userInfo;

import com.oursnail.wechat.init.GlobalConstants;
import com.oursnail.wechat.utils.HttpUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 14:27
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class GetUseInfo {
    /**
     * @Description: 通过openid获取用户微信信息
     * @param @param openid
     * @param @return
     * @param @throws Exception
     */
    public static HashMap<String, String> Openid_userinfo(String openid)
            throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token",
                GlobalConstants.getInterfaceUrl("access_token"));  //定时器中获取到的token
        System.out.println(GlobalConstants.getInterfaceUrl("access_token"));
        params.put("openid", openid);  //需要获取的用户的openid
        params.put("lang", "zh_CN");
        String subscribers = HttpUtils.sendGet(
                GlobalConstants.getInterfaceUrl("OpenidUserinfoUrl"), params);
        System.out.println(subscribers);
        params.clear();
        //这里返回参数只取了昵称、头像、和性别
        params.put("nickname",
                JSONObject.fromObject(subscribers).getString("nickname")); //昵称
        params.put("headimgurl",
                JSONObject.fromObject(subscribers).getString("headimgurl"));  //图像
        params.put("sex", JSONObject.fromObject(subscribers).getString("sex"));  //性别
        return params;
    }
}
