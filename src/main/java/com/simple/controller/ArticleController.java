package com.simple.controller;

import com.simple.common.Const;
import com.simple.common.ServerResponse;
import com.simple.pojo.Article;
import com.simple.pojo.User;
import com.simple.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:02:52
 */

@RestController
@RequestMapping(value = "/article/")
public class ArticleController {

    private final IArticleService iArticleService;

    @Autowired
    public ArticleController(IArticleService iArticleService) {
        this.iArticleService = iArticleService;
    }

    // 获取所有博客
    @RequestMapping(value = "get_all_article_list.do", method = RequestMethod.POST)
    public ServerResponse getAllArticleList(int pageNum, int pageSize) {
        return iArticleService.getAllArticleList(pageNum, pageSize);
    }

    // 通过类型查找博客
    @RequestMapping(value = "get_article_by_tags.do", method = RequestMethod.POST)
    public ServerResponse getArticleByTags(String tags, int pageNum, int pageSize) {
        return iArticleService.getArticleByTags(tags, pageNum, pageSize);

    }

    // 通过关键字查找博客
    @RequestMapping(value = "search_by_keyword.do", method = RequestMethod.POST)
    public ServerResponse searchByTitle(String keyWord, int pageNum, int pageSize) {
        return iArticleService.searchByKeyWord(keyWord, pageNum, pageSize);

    }

    // 用户新建博客
    @RequestMapping(value = "user_create_new_article.do", method = RequestMethod.POST)
    public ServerResponse userCreateNewArticle(HttpSession session, Article article) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        article.setUserId(sessionUser.getId());
        return iArticleService.userCreateNewArticle(article);
    }

    // 用户修改博客
    @RequestMapping(value = "user_update_article.do", method = RequestMethod.POST)
    public ServerResponse userUpdateArticle(HttpSession session, Article article) {
        User sessionUser = (User) session.getAttribute(Const.ROLE.CURRENT_USER);
        if (sessionUser == null) {
            return ServerResponse.createErrorByNeedLogin();
        }
        if (sessionUser.getId().equals(article.getUserId())) {
            return iArticleService.userUpdateArticle(article);
        }
        return ServerResponse.createByErrorMessage("you are not author, you can't update this article!!!");
    }
}
