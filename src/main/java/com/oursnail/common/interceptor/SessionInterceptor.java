package com.oursnail.common.interceptor;

import com.oursnail.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 【swg】.
 * @Date 2017/10/22 12:47
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        if((uri.contains("login"))||(uri.contains("register"))||(uri.contains("error")) || (uri.contains("denied")) || (uri.contains("sign")) || (uri.contains("security")) || (uri.contains("wechatconfig"))){
            return true;
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        if(user!=null){
            return true;
        }
        //转发到登录
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}