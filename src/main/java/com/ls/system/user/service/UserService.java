package com.ls.system.user.service;

import com.ls.system.user.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Auther: chenjun
 * @Date 2020年01月03日 13:33
 * @Description:
 */

public interface UserService {

    public List<User> selectAllUser();

    public abstract List<User> selectAllUser(Map<Object,Object> map);

    public User getUerByUserNameandPwd(String userCode, String userPassword);

    public List<User> selectUserByUserName(String username);
}
