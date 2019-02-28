package com.simple.service;


import com.simple.common.ServerResponse;
import com.simple.dto.UserDto.CreateUserRequestDto;
import com.simple.pojo.UserItem;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 10:56:56
 */
public interface IUserService {

    ServerResponse login(String username, String password);

    ServerResponse register(CreateUserRequestDto requestDto);

    ServerResponse updateUserInfo(CreateUserRequestDto requestDto, Integer userId);

    ServerResponse userLikeIt(UserItem userItem);

    ServerResponse getUserMyLike(Integer userId, int pageNum, int pageSize);

    ServerResponse userUnLikeIt(UserItem userItem);
}
