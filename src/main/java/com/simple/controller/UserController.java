package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.pojo.User;
import com.simple.pojo.UserItem;
import com.simple.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:53:15
 */
@Api(value = "用户controller", tags = {"用户操作接口"})
@RestController
@RequestMapping(value = "/user/")
public class UserController {

    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    // 用户登录
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse login(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
                                @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password,
                                HttpSession session) {
        ServerResponse response = iUserService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.ROLE.CURRENT_USER, response.getData());
            return response;
        }
        return response;
    }

    // 获取用户信息
    @ApiOperation(value = "获取用户信息",httpMethod = "GET")
    @RequestMapping(value = "get_user_info.do")
    public ServerResponse getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess("get user info success!", user);
        }
        return ServerResponse.createErrorByNeedLogin();
    }

    // 用户注册
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse register(@ApiParam(name = "username", value = "用户名", required = true) @RequestParam String username,
                                   @ApiParam(name = "password", value = "密码", required = true) @RequestParam String password,
                                   @ApiParam(name = "email", value = "email", required = true) @RequestParam String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return iUserService.register(user);
    }

    // 用户更新用户信息
    // TODO: 2019-02-27 修改单个字段
    @ApiOperation(value = "用户更新用户信息")
    @RequestMapping(value = "update_user_info.do", method = RequestMethod.POST)
    public ServerResponse updateUserInfo(@ApiParam(name = "username", value = "用户名") @RequestParam String username,
                                         @ApiParam(name = "password", value = "密码") @RequestParam String password,
                                         @ApiParam(name = "email", value = "email") @RequestParam    String email,
                                         HttpSession session) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        User user = new User();
        user.setId(sessionUser.getId());
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        ServerResponse response = iUserService.updateUserInfo(user);
        if (response.isSuccess()) {
            session.setAttribute(Const.ROLE.CURRENT_USER, response.getData());
            return response;
        }
        return response;
    }

    // 用户退出登录
    @ApiOperation(value = "用户退出登录")
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    public ServerResponse logout(HttpSession session) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        session.removeAttribute(Const.ROLE.CURRENT_USER);
        return ServerResponse.createBySuccessMessage("logout success");
    }

    // 点赞博客
    @ApiOperation(value = "点赞博客")
    @RequestMapping(value = "user_like_it.do", method = RequestMethod.POST)
    public ServerResponse userLikeIt(HttpSession session,
                                     @ApiParam(name = "articleId", value = "文章ID", required = true) @RequestParam Integer articleId) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        UserItem userItem = new UserItem();
        userItem.setUserId(sessionUser.getId());
        userItem.setArticleId(articleId);
        return iUserService.userLikeIt(userItem);
    }

    // 用户获取自己点赞的文章列表
    @ApiOperation(value = "用户获取自己点赞的文章列表")
    @RequestMapping(value = "get_user_my_like.do", method = RequestMethod.POST)
    public ServerResponse getUserMyLike(HttpSession session,
                                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
                                        @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        return iUserService.getUserMyLike(sessionUser.getId(), pageNum, pageSize);
    }
}
