package com.simple.service.impl;

import com.github.pagehelper.PageHelper;
import com.simple.common.ServerResponse;
import com.simple.dao.CommentMapper;
import com.simple.pojo.Comment;
import com.simple.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Simple4H
 * @Date: 2019/02/27 09:53:05
 */
@Service("iCommentService")
public class CommentServiceImpl implements ICommentService {

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public ServerResponse addNewComment(Comment comment) {
        int result = commentMapper.insert(comment);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("comment success");
        }
        return ServerResponse.createByErrorMessage("comment error");
    }

    public ServerResponse getCommentByArticle(Integer articleId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> lists = commentMapper.getCommentByArticleId(articleId);
        if (lists.isEmpty()) {
            return ServerResponse.createBySuccessMessage("this article not comment");
        }
        return ServerResponse.createBySuccess("get success", lists);

    }

}
