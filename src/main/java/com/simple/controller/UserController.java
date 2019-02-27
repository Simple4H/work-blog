package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.dto.UserDto.CreateUserRequestDto;
import com.simple.pojo.User;
import com.simple.pojo.UserItem;
import com.simple.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:53:15
 */
@Api(value = "用户controller", tags = {"用户操作接口"})
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    // 用户注册
    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @PostMapping(value = "")
    public ServerResponse register(@RequestBody CreateUserRequestDto requestDto) {
        return iUserService.register(requestDto);
    }

    // 获取用户信息
    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @GetMapping(value = "/{id}")
    public ServerResponse getUserInfo(HttpSession session, @PathVariable Integer id) {
        User user = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (user != null) {
            if (id.equals(user.getId())) {
                return ServerResponse.createBySuccess("get user info success!", user);
            }
            return ServerResponse.createByErrorMessage("用户信息不匹配");
        }
        return ServerResponse.createErrorByNeedLogin();
    }

    // 用户更新用户信息
    @ApiOperation(value = "用户更新用户信息", httpMethod = "PUT")
    @PutMapping(value = "/{id}")
    public ServerResponse updateUserInfo(@RequestBody CreateUserRequestDto requestDto,
                                         HttpSession session, @PathVariable Integer id) {
//        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
//        if (sessionUser == null) {
//            return ServerResponse.createErrorByNeedLogin();
//        }
//        User user = new User();
//        user.setId(sessionUser.getId());
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);
//        ServerResponse response = iUserService.updateUserInfo(user);
//        if (response.isSuccess()) {
//            session.setAttribute(Const.ROLE.CURRENT_USER, response.getData());
//            return response;
//        }
//        return response;
        return iUserService.updateUserInfo(requestDto,id);
    }



    // 用户获取自己点赞的文章列表
    @ApiOperation(value = "用户获取自己点赞的文章列表",httpMethod = "GET")
    @GetMapping(value = "/likeList")
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
