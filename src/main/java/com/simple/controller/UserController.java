package com.simple.controller;

import com.simple.common.ServerResponse;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:53:15
 */
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse login(String username, String password) {
        return iUserService.login(username, password);
    }

    @RequestMapping(value = "register.do",method = RequestMethod.POST)
    public ServerResponse register(User user) {
        return iUserService.register(user);
    }
}
