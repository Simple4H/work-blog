package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.pojo.Article;
import com.simple.pojo.User;
import com.simple.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:02:52
 */
@Api(value = "博客controller", tags = {"博客操作接口"})
@RestController
@RequestMapping(value = "/article/")
public class ArticleController {

    private final IArticleService iArticleService;

    @Autowired
    public ArticleController(IArticleService iArticleService) {
        this.iArticleService = iArticleService;
    }

    // 获取所有博客
    @ApiOperation(value = "获取所有的博客", httpMethod = "GET")
    @RequestMapping(value = "get_all_article_list.do")
    public ServerResponse getAllArticleList(@ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
                                            @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
        return iArticleService.getAllArticleList(pageNum, pageSize);
    }

    // 通过类型查找博客
    @ApiOperation(value = "通过类型查找博客")
    @RequestMapping(value = "get_article_by_tags.do", method = RequestMethod.POST)
    public ServerResponse getArticleByTags(@ApiParam(name = "tags", value = "分类", required = true) @RequestParam String tags,
                                           @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
                                           @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
        return iArticleService.getArticleByTags(tags, pageNum, pageSize);

    }

    // 通过关键字查找博客
    @ApiOperation(value = "通过关键字查找博客")
    @RequestMapping(value = "search_by_keyword.do", method = RequestMethod.POST)
    public ServerResponse searchByTitle(@ApiParam(name = "keyWord", value = "关键字", required = true) @RequestParam String keyWord,
                                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam int pageNum,
                                        @ApiParam(name = "pageSize", value = "页数", required = true) @RequestParam int pageSize) {
        return iArticleService.searchByKeyWord(keyWord, pageNum, pageSize);

    }

    // 用户新建博客
    @ApiOperation(value = "用户新建博客")
    @RequestMapping(value = "user_create_new_article.do", method = RequestMethod.POST)
    public ServerResponse userCreateNewArticle(HttpSession session,
                                               @ApiParam(name = "title", value = "标题", required = true) @RequestParam String title,
                                               @ApiParam(name = "content", value = "内容", required = true) @RequestParam String content,
                                               @ApiParam(name = "tags", value = "分类", required = true) @RequestParam String tags) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        Article article = new Article();
        article.setUserId(sessionUser.getId());
        article.setTitle(title);
        article.setContent(content);
        article.setTags(tags);
        return iArticleService.userCreateNewArticle(article);
    }

    // 用户修改博客
    @ApiOperation(value = "用户修改博客")
    @RequestMapping(value = "user_update_article.do", method = RequestMethod.POST)
    public ServerResponse userUpdateArticle(HttpSession session,
                                            @ApiParam(name = "articleId", value = "文章id", required = true) @RequestParam int articleId,
                                            @ApiParam(name = "userId", value = "用户id", required = true) @RequestParam int userId,
                                            @ApiParam(name = "title", value = "标题", required = true) @RequestParam String title,
                                            @ApiParam(name = "content", value = "内容", required = true) @RequestParam String content,
                                            @ApiParam(name = "tags", value = "分类", required = true) @RequestParam String tags) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        Article article = new Article();
        article.setId(articleId);
        article.setUserId(userId);
        article.setTitle(title);
        article.setContent(content);
        article.setTags(tags);
        if (sessionUser.getId().equals(article.getUserId())) {
            return iArticleService.userUpdateArticle(article);
        }
        return ServerResponse.createByErrorMessage("you are not author, you can't update this article!!!");
    }

    @ApiOperation(value = "删除博客", httpMethod = "DELETE")
    @RequestMapping(value = "delete_article.do")
    public ServerResponse deleteArticle(HttpSession session,
                                        @ApiParam(name = "articleId", value = "文章Id", required = true) @RequestParam Integer articleId) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        return iArticleService.deleteArticle(sessionUser.getId(), articleId);
    }

}
