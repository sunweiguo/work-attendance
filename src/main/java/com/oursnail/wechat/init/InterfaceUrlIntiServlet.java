package com.oursnail.wechat.init;

import com.oursnail.wechat.task.WeChatTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 11:32
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class InterfaceUrlIntiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        InterfaceUrlInti.init();
        WeChatTask timer = new WeChatTask();
        try {
            timer.getToken_getTicket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
