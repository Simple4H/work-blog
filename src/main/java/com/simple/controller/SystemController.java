package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.pojo.User;
import com.simple.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Simple4H
 * @Date: 2019/02/27 16:15:28
 */
@RestController
@RequestMapping(value = "/system")
@Api(value = "系统controller", tags = {"系统操作接口"})
public class SystemController {

    private final IUserService iUserService;

    @Autowired
    public SystemController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    // 用户登录
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    @PostMapping(value = "")
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

    // 用户退出登录
    @ApiOperation(value = "用户退出登录", httpMethod = "POST")
    @PostMapping(value = "/{id}")
    public ServerResponse logout(HttpSession session, @PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        if (sessionUser.getId().equals(id)) {
            session.removeAttribute(Const.ROLE.CURRENT_USER);
            return ServerResponse.createBySuccessMessage("logout success");
        }
        return ServerResponse.createByErrorMessage("不是相同用户，无法操作");
    }
}
