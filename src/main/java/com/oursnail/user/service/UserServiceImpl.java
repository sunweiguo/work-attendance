package com.oursnail.user.service;

import com.oursnail.common.exception.WxRecordException;
import com.oursnail.common.resp.PageQueryBean;
import com.oursnail.common.resp.QueryCondition;
import com.oursnail.user.dao.UserMapper;
import com.oursnail.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author 【swg】.
 * @Date 2017/11/11 11:44
 * @DESC
 * @CONTACT 317758022@qq.com
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public PageQueryBean getAllUsers(QueryCondition queryCondition) {
        //根据条件查询 count记录数目
        int count = userMapper.countByCondition(queryCondition);
        PageQueryBean pageResult = new PageQueryBean();
        if(count>0){
            pageResult.setTotalRows(count);
            pageResult.setCurrentPage(queryCondition.getCurrentPage());
            pageResult.setPageSize(queryCondition.getPageSize());
            List<User> userList = userMapper.getAllUsers(queryCondition);
            pageResult.setItems(userList);
        }
        //如果有记录 才去查询分页数据 没有相关记录数目 没必要去查分页数据
        return pageResult;
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUser(User updateUser) {
        userMapper.updateUser(updateUser);
    }

    @Override
    public Set<String> findRoles(String username) {
        return userMapper.findRoles(username);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return userMapper.findPermissions(username);
    }

}
