package com.oursnail.user.service;

import com.oursnail.common.resp.PageQueryBean;
import com.oursnail.common.resp.QueryCondition;
import com.oursnail.user.entity.User;
import java.util.List;

import java.util.Set;

/**
 * @Author 【swg】.
 * @Date 2017/11/11 11:43
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public interface UserService {
    User getUserById(Long id);

    User getUserByEmail(String email);

    PageQueryBean getAllUsers(QueryCondition condition);

    User selectByPrimaryKey(Long userId);

    void updateUser(User updateUser);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

}
