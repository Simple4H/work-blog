package com.simple.service;

import com.simple.common.ServerResponse;
import com.simple.pojo.Comment;

/**
 * @Author: Simple4H
 * @Date: 2019/02/27 09:52:50
 */
public interface ICommentService {

    ServerResponse addNewComment(Comment comment);

    ServerResponse getCommentByArticle(Integer articleId, int pageNum, int pageSize);

    ServerResponse deleteComment(Integer commentId, Integer userId);
}
