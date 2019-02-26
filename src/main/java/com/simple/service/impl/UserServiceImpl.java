package com.simple.service.impl;

import com.simple.common.ServerResponse;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 10:57:16
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public ServerResponse login(String username, String password) {
        User user = userMapper.login(username,password);
        if (user != null) {
            return ServerResponse.createBySuccess("login success", user);
        }
        return ServerResponse.createByErrorMessage("login error");
    }

    public ServerResponse register(User user) {
        int result = userMapper.insert(user);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("register Success");
        }
        return ServerResponse.createByErrorMessage("register error");
    }
}
