package com.simple.dao;

import com.simple.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(@Param("username") String username, @Param("password") String password);

    int checkUsername(String username);

    int checkPassword(String password);

    int updateUserUpdateTime(Integer id);
}