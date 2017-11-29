package com.oursnail.user.dao;

import com.oursnail.attend.entity.CountInfo;
import com.oursnail.common.resp.QueryCondition;
import com.oursnail.user.entity.User;
import java.util.List;
import java.util.Set;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByEmail(String email);

    List<User>  getAllUsers(QueryCondition condition);

    int countByCondition(QueryCondition queryCondition);

    void updateUser(User updateUser);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    List<CountInfo> getCountInfo();
}