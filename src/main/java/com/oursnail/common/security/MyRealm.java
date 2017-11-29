package com.oursnail.common.security;

import com.oursnail.register.entity.Register;
import com.oursnail.register.service.RegisterService;
import com.oursnail.user.entity.User;
import com.oursnail.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @Author 【swg】.
 * @Date 2017/11/14 20:42
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private RegisterService registerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = principalCollection.getPrimaryPrincipal().toString() ;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        Set<String> roleNames = userService.findRoles(username) ;
        Set<String> permissions = userService.findPermissions(username) ;
        info.setRoles(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        String email = usernamePasswordToken.getUsername();
        User user = userService.getUserByEmail(email);
        Register register = registerService.getRegisterByUserId(user.getId());
        if(user==null || register.getState()==0){
            return null;
        }else {
            AuthenticationInfo info = new SimpleAuthenticationInfo(user.getEmail(),user.getPassword(),getName());
            SecurityUtils.getSubject().getSession().setAttribute("loginUser",user);
            return info;
        }
    }
}
