package com.simple.dao;

import com.simple.pojo.UserItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserItem record);

    int insertSelective(UserItem record);

    UserItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserItem record);

    int updateByPrimaryKey(UserItem record);

    Integer checkUserIsLikeItBefore(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    int deleteByUserIdAndArticleId(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    List<UserItem> getUserItemByUserId(Integer userId);
}