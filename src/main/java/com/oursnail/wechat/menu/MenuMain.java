package com.oursnail.wechat.menu;

import com.alibaba.fastjson.JSONObject;
import com.oursnail.wechat.utils.HttpUtils;
import net.sf.json.JSONArray;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 14:14
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class MenuMain {
    public static void main(String[] args) {

        ViewButton bt1=new ViewButton();
        bt1.setUrl("http://www.oursnail.xin/wechatconfig/jssdkconfig");
        bt1.setName("微信js");
        bt1.setType("view");

        ViewButton bt2=new ViewButton();
        bt2.setUrl("http://www.oursnail.xin/login");
        bt2.setName("登陆");
        bt2.setType("view");

        ViewButton bt3=new ViewButton();
        bt3.setUrl("http://www.oursnail.xin/wechatconfig/detail");
        bt3.setName("说明");
        bt3.setType("view");

        ViewButton sourceBtn=new ViewButton();
        sourceBtn.setUrl("http://blog.csdn.net/sunweiguo1");
        sourceBtn.setName("我的博客");
        sourceBtn.setType("view");

        ViewButton vbt=new ViewButton();
        vbt.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx3e9578488f126713&redirect_uri=http://www.oursnail.xin/wechatconfig/weixinOauth&response_type=code&scope=snsapi_userinfo&state=2#wechat_redirect");
        vbt.setName("oauth");
        vbt.setType("view");

        JSONArray sub_button=new JSONArray();
        sub_button.add(vbt);
        sub_button.add(bt1);
        sub_button.add(bt2);
        sub_button.add(bt3);

        JSONObject buttonOne=new JSONObject();
        buttonOne.put("name", "菜单");
        buttonOne.put("sub_button", sub_button);

        JSONArray button=new JSONArray();
        button.add(buttonOne);
        button.add(sourceBtn);

        JSONObject menujson=new JSONObject();
        menujson.put("button", button);
        System.out.println(menujson);
        //这里为请求接口的url   +号后面的是token，这里就不做过多对token获取的方法解释
        String url="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+"pSBZ6vBMjeChPMqNd3ssfbItDDOHj3U3T91m_D3FiFtQVN11_x1s7sgsgCTt90WHsbhdsK-tpmmw9SY8J1I07FtL1Ui1_jZnrQ4zV3WqKsDL4_5zJDlqMJKQd04ItOq6HYDaAFAVMR";

        try{
            String rs= HttpUtils.sendPostBuffer(url, menujson.toJSONString());
            System.out.println(rs);
        }catch(Exception e){
            System.out.println("请求错误！");
        }

    }

}
