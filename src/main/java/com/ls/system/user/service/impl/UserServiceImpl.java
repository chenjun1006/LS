package com.ls.system.user.service.impl;

import com.ls.system.user.dao.UserMapper;
import com.ls.system.user.entity.User;
import com.ls.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: chenjun
 * @Date 2020年01月03日 13:35
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllUser(){
        List<User> userList = userMapper.selectAllUser();
        return userList;
    }

    @Override
    public List<User> selectAllUser(Map<Object, Object> map) {
        List<User> userList = userMapper.selectAllUser();
        return userList;
    }

    @Override
    public User getUerByUserNameandPwd(String userCode, String userPassword) {
        User user = userMapper.getUerByUserNameandPwd(userCode, userPassword);
        if (user != null){
            return user;
        }
        return null;
    }

    @Override
    public List<User> selectUserByUserName( String username) {
        List<User> userList = userMapper.selectUserByUserName(username);
        return userList;
    }


}
