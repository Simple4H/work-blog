package com.simple.dao;

import com.simple.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> getCommentByArticleId(Integer articleId);

    int deleteCommentByIdAndUserId(@Param("id")Integer id,@Param("userId")Integer userId);
}