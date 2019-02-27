package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.pojo.Comment;
import com.simple.pojo.User;
import com.simple.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: Simple4H
 * @Date: 2019/02/27 09:52:03
 */

@RestController
@RequestMapping(value = "/comment/")
public class CommentController {

    private final ICommentService iCommentService;

    @Autowired
    public CommentController(ICommentService iCommentService) {
        this.iCommentService = iCommentService;
    }

    @RequestMapping(value = "add_new_comment.do", method = RequestMethod.POST)
    public ServerResponse addNewComment(Integer articleId, String comment, HttpSession session) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        Comment comment1 = new Comment();
        comment1.setUserId(sessionUser.getId());
        comment1.setArticleId(articleId);
        comment1.setComment(comment);
        return iCommentService.addNewComment(comment1);
    }

    @RequestMapping(value = "get_comment_by_articleId.do", method = RequestMethod.POST)
    public ServerResponse getCommentByArticleId(Integer articleId, int pageNum, int pageSize) {
        return iCommentService.getCommentByArticle(articleId, pageNum, pageSize);
    }

    @RequestMapping(value = "delete_comment.do", method = RequestMethod.POST)
    public ServerResponse deleteComment(HttpSession session, Integer commentId) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        return iCommentService.deleteComment(commentId, sessionUser.getId());

    }
}
