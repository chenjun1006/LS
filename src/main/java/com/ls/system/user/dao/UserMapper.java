package com.ls.system.user.dao;

import com.ls.system.user.entity.User;

import java.util.List;
import java.util.Map;


public interface UserMapper {

    public List<User> selectAllUser();

    public abstract List<User> selectAllUser(Map<Object,Object> map);

    public List<User> selectUserByUserName(String username);

    public  User getUerByUserNameandPwd(String userCode, String userPassword);
}