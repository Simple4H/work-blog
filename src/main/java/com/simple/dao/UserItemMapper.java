package com.simple.dao;

import com.simple.pojo.UserItem;

public interface UserItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserItem record);

    int insertSelective(UserItem record);

    UserItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserItem record);

    int updateByPrimaryKey(UserItem record);
}