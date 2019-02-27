package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.dto.ArticleDto.CreateArticleRequestDto;
import com.simple.dto.ArticleDto.SearchArticleRequestDto;
import com.simple.dto.ArticleDto.UpdateArticleRequestDto;
import com.simple.pojo.Comment;
import com.simple.pojo.User;
import com.simple.pojo.UserItem;
import com.simple.service.IArticleService;
import com.simple.service.ICommentService;
import com.simple.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:02:52
 */
@Api(value = "博客controller", tags = {"博客操作接口"})
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    private final IUserService iUserService;

    private final ICommentService iCommentService;

    private final IArticleService iArticleService;

    @Autowired
    public ArticleController(IArticleService iArticleService, ICommentService iCommentService, IUserService iUserService) {
        this.iArticleService = iArticleService;
        this.iCommentService = iCommentService;
        this.iUserService = iUserService;
    }

    // 获取所有博客
    @ApiOperation(value = "获取所有的博客", httpMethod = "GET")
    @GetMapping(value = "")
    public ServerResponse getAllArticleList(@ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
                                            @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
        return iArticleService.getAllArticleList(pageNum, pageSize);
    }

//    // 通过类型查找博客
//    @ApiOperation(value = "通过类型查找博客")
//    @RequestMapping(value = "/get_article_by_tags.do", method = RequestMethod.POST)
//    public ServerResponse getArticleByTags(@ApiParam(name = "tags", value = "分类", required = true) @RequestParam String tags,
//                                           @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
//                                           @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
//        return iArticleService.getArticleByTags(tags, pageNum, pageSize);
//
//    }
//
//    // 通过关键字查找博客
//    @ApiOperation(value = "通过关键字查找博客")
//    @RequestMapping(value = "/search_by_keyword.do", method = RequestMethod.POST)
//    public ServerResponse searchByTitle(@ApiParam(name = "keyWord", value = "关键字", required = true) @RequestParam String keyWord,
//                                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
//                                        @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
//        return iArticleService.searchByKeyWord(keyWord, pageNum, pageSize);
//
//    }


    // 博客查找
    @ApiOperation(value = "博客查找", httpMethod = "POST")
    @PostMapping(value = "/search")
    public ServerResponse searchArticle(@RequestBody SearchArticleRequestDto requestDto) {
        String type = requestDto.getType();
        if (type.equals("tags")) {
            return iArticleService.getArticleByTags(requestDto.getInput(),requestDto.getPageNum(), requestDto.getPageSize());
        }else if (type.equals("keyword")){
            return iArticleService.searchByKeyWord(requestDto.getInput(), requestDto.getPageNum(), requestDto.getPageSize());
        }
        return ServerResponse.createByErrorMessage("类型没有选择正确");
    }

    // 用户新建博客
    @ApiOperation(value = "用户新建博客", httpMethod = "POST")
    @PostMapping(value = "")
    public ServerResponse userCreateNewArticle(HttpSession session,
                                               @RequestBody CreateArticleRequestDto requestDto) {
//        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
//        if (sessionUser == null) {
//            return ServerResponse.createErrorByNeedLogin();
//        }
//        requestDto.setUserId(sessionUser.getId());
        return iArticleService.userCreateNewArticle(requestDto);
    }

    // 用户修改博客
    @ApiOperation(value = "用户修改博客", httpMethod = "PUT")
    @PutMapping(value = "{articleId}")
    public ServerResponse userUpdateArticle(HttpSession session,
                                            @RequestBody UpdateArticleRequestDto requestDto,
                                            @PathVariable Integer articleId) {
//        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
//        if (sessionUser == null) {
//            return ServerResponse.createErrorByNeedLogin();
//        }
//        if (sessionUser.getId().equals(requestDto.getUserId())) {
//            return iArticleService.userUpdateArticle(article);
//        }
//        return ServerResponse.createByErrorMessage("you are not author, you can't update this article!!!");

        return iArticleService.userUpdateArticle(requestDto, articleId);
    }

    @ApiOperation(value = "删除博客", httpMethod = "DELETE")
    @DeleteMapping(value = "{articleId}")
    public ServerResponse deleteArticle(HttpSession session,
                                        @PathVariable Integer articleId) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        return iArticleService.deleteArticle(sessionUser.getId(), articleId);
    }


//    ----------------------------------------------------------------------------------------------


    @ApiOperation(value = "添加评论")
    @RequestMapping(value = "/comment/{articleId}", method = RequestMethod.POST)
    public ServerResponse addNewComment(@PathVariable Integer articleId,
                                        @ApiParam(name = "comment", value = "评论", required = true) @RequestParam String comment,
                                        HttpSession session) {
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

    @ApiOperation(value = "通过文章id获取评论", httpMethod = "GET")
    @GetMapping(value = "/comment/{articleId}")
    public ServerResponse getCommentByArticleId(@PathVariable Integer articleId,
                                                @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
                                                @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
        return iCommentService.getCommentByArticle(articleId, pageNum, pageSize);
    }

    @ApiOperation(value = "删除评论", httpMethod = "DELETE")
    @DeleteMapping(value = "/comment/{commentId}")
    public ServerResponse deleteComment(HttpSession session,
                                        @PathVariable Integer commentId) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        return iCommentService.deleteComment(commentId, sessionUser.getId());
    }

    //    ----------------------------------------------------------------------------------------
// 点赞博客
    @ApiOperation(value = "点赞博客", httpMethod = "GET")
    @GetMapping(value = "/like/{articleId}")
    public ServerResponse userLikeIt(HttpSession session,
                                     @PathVariable Integer articleId) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        UserItem userItem = new UserItem();
        userItem.setUserId(sessionUser.getId());
        userItem.setArticleId(articleId);
        return iUserService.userLikeIt(userItem);
    }

}
