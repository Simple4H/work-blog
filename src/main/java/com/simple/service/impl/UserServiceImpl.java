package com.simple.service.impl;

import com.github.pagehelper.PageHelper;
import com.simple.common.ServerResponse;
import com.simple.dao.ArticleMapper;
import com.simple.dao.UserItemMapper;
import com.simple.dao.UserMapper;
import com.simple.pojo.User;
import com.simple.pojo.UserItem;
import com.simple.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 10:57:16
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    private final ArticleMapper articleMapper;

    private final UserMapper userMapper;

    private final UserItemMapper userItemMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserItemMapper userItemMapper, ArticleMapper articleMapper) {
        this.userMapper = userMapper;
        this.userItemMapper = userItemMapper;
        this.articleMapper = articleMapper;
    }

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

    // TODO: 2019-02-26 判断博客是否存在 
    public ServerResponse userLikeIt(UserItem userItem) {
        // 判断是否已经点赞过该博客
        Integer checkResult = userItemMapper.checkUserIsLikeItBefore(userItem.getUserId(), userItem.getArticleId());
        if (checkResult != null) {
            // 如果已经点赞过则直接取消喜欢
            int result = userItemMapper.deleteByUserIdAndArticleId(userItem.getUserId(), userItem.getArticleId());
            if (result > 0) {
                // 博客点赞数-1
                articleMapper.idontLikeIt(userItem.getArticleId());
                return ServerResponse.createBySuccessMessage("unlike success");
            }
            return ServerResponse.createByErrorMessage("have a error!!!!");
        }
        // 没有点赞过
        if (userItemMapper.insert(userItem) > 0) {
            //点赞数+1
            articleMapper.iLikeIt(userItem.getArticleId());
            return ServerResponse.createBySuccessMessage("you like it success");
        }
        return ServerResponse.createByErrorMessage("some thing error!!!!");
    }

    public ServerResponse getUserMyLike(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserItem> lists = userItemMapper.getUserItemByUserId(userId);
        if (lists.isEmpty()) {
            return ServerResponse.createByErrorMessage("You don't have any likes!");
        }
        return ServerResponse.createBySuccess("success", lists);
    }

}
