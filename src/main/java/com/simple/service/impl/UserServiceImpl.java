package com.simple.service.impl;

import com.github.pagehelper.PageInfo;
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
        int checkUsername = userMapper.checkUsername(username);
        if (checkUsername > 0) {
            if (userMapper.checkPassword(password) > 0) {
                User user = userMapper.login(username, password);
                if (user != null) {
                    return ServerResponse.createBySuccess("login success!", user);
                }
                return ServerResponse.createByErrorMessage("login error!");
            }
            return ServerResponse.createByErrorMessage("password is error!");
        }
        return ServerResponse.createByErrorMessage("username is not exist!");

    }

    public ServerResponse register(User user) {
        int result = userMapper.insert(user);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("register Success");
        }
        return ServerResponse.createByErrorMessage("register error");
    }

    public ServerResponse updateUserInfo(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        if (result > 0) {
            if (userMapper.updateUserUpdateTime(user.getId()) > 0) {
                return ServerResponse.createBySuccessMessage("update user info success");
            }
            return ServerResponse.createByErrorMessage("some thing error in update user update time!");
        }
        return ServerResponse.createByErrorMessage("update user info error");
    }

    public ServerResponse<PageInfo> getUserMyLike(Integer userId) {
        return null;
    }

}
