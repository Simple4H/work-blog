package com.simple.service;


import com.simple.common.ServerResponse;
import com.simple.pojo.User;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 10:56:56
 */
public interface IUserService {

    ServerResponse login(String username, String password);

    ServerResponse register(User user);
}
