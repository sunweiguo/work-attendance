package com.oursnail.wechat.oauth;

import com.oursnail.wechat.init.GlobalConstants;
import com.oursnail.wechat.utils.HttpUtils;
import net.sf.json.JSONObject;

import java.util.HashMap;

/**
 * @Author 【swg】.
 * @Date 2017/11/17 11:16
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class GetUserInfo {
    private String code = "";
    public static String access_token = "";
    public GetUserInfo(String code){
        this.code  = code;
    }
    public static HashMap<String, String> getUserInfo(String code) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("appid", GlobalConstants.getInterfaceUrl("appid"));
        params.put("secret",GlobalConstants.getInterfaceUrl("AppSecret"));
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        String tokenrs = HttpUtils.sendGet(GlobalConstants.getInterfaceUrl("OauthCodeUrl"), params);
        //1、拿到access_token
        access_token = JSONObject.fromObject(tokenrs).getString("access_token");
        //2、拿到openid
        String openid = JSONObject.fromObject(tokenrs).getString("openid");
        //3、只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
        //String unionid = JSONObject.fromObject(tokenrs).getString("unionid");
        // 4、通过用户openid信息获取用户详细信息
        params.clear();
        params.put("access_token", access_token);
        params.put("openid", openid);
        params.put("lang", "zh_CN");
        String useinfors = HttpUtils.sendGet(GlobalConstants.getInterfaceUrl("OauthInfoUrl"), params);
        //5、将取到的用户信息存到hashMap中返回
        params.clear();
        params.put("openid", openid);
        params.put("nickname",JSONObject.fromObject(useinfors).getString("nickname"));
        params.put("sex", JSONObject.fromObject(useinfors).getString("sex"));
        params.put("province",JSONObject.fromObject(useinfors).getString("province"));
        params.put("city", JSONObject.fromObject(useinfors).getString("city"));
        params.put("headimgurl",JSONObject.fromObject(useinfors).getString("headimgurl"));
        //params.put("unionid",JSONObject.fromObject(useinfors).getString("unionid"));

        return params;
    }
}
