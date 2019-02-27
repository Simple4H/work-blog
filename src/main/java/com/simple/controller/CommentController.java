//package com.simple.controller;
//
//import com.simple.common.Const;
//import com.simple.common.ServerResponse;
//import com.simple.pojo.Comment;
//import com.simple.pojo.User;
//import com.simple.service.ICommentService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//
///**
// * @Author: Simple4H
// * @Date: 2019/02/27 09:52:03
// */
//@Api(value = "评论controller", tags = {"评论操作接口"})
//@RestController
//@RequestMapping(value = "/comment/")
//public class CommentController {
//
//    private final ICommentService iCommentService;
//
//    @Autowired
//    public CommentController(ICommentService iCommentService) {
//        this.iCommentService = iCommentService;
//    }
//
//    @ApiOperation(value = "添加评论")
//    @RequestMapping(value = "/comment/add_new_comment.do", method = RequestMethod.POST)
//    public ServerResponse addNewComment(@ApiParam(name = "articleId", value = "文章id", required = true) @RequestParam Integer articleId,
//                                        @ApiParam(name = "comment", value = "评论", required = true) @RequestParam String comment,
//                                        HttpSession session) {
//        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
//        if (sessionUser == null) {
//            return ServerResponse.createErrorByNeedLogin();
//        }
//        Comment comment1 = new Comment();
//        comment1.setUserId(sessionUser.getId());
//        comment1.setArticleId(articleId);
//        comment1.setComment(comment);
//        return iCommentService.addNewComment(comment1);
//    }
//
//    @ApiOperation(value = "通过文章id获取评论")
//    @RequestMapping(value = "/comment/get_comment_by_articleId.do", method = RequestMethod.POST)
//    public ServerResponse getCommentByArticleId(@ApiParam(name = "articleId", value = "文章id", required = true) @RequestParam Integer articleId,
//                                                @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
//                                                @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
//        return iCommentService.getCommentByArticle(articleId, pageNum, pageSize);
//    }
//
//    @ApiOperation(value = "删除评论", httpMethod = "DELETE")
//    @RequestMapping(value = "/comment/delete_comment.do")
//    public ServerResponse deleteComment(HttpSession session,
//                                        @ApiParam(name = "commentId", value = "评论id", required = true) @RequestParam Integer commentId) {
//        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
//        if (sessionUser == null) {
//            return ServerResponse.createErrorByNeedLogin();
//        }
//        return iCommentService.deleteComment(commentId, sessionUser.getId());
//    }
//}
