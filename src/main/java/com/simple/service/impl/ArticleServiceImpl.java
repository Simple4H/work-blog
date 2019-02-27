package com.simple.service.impl;


import com.github.pagehelper.PageHelper;
import com.simple.common.ServerResponse;
import com.simple.dao.ArticleMapper;
import com.simple.pojo.Article;
import com.simple.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:04:47
 */

@Service("iArticleService")
public class ArticleServiceImpl implements IArticleService {

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public ServerResponse getAllArticleList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> lists = articleMapper.getAllArticleList();
        if (lists.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有文章");
        }
        return ServerResponse.createBySuccess("获取文章列表成功", lists);
    }

    public ServerResponse getArticleByTags(String tags, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> lists = articleMapper.searchByTags(tags);
        if (!lists.isEmpty()) {
            return ServerResponse.createBySuccess("search success", lists);
        }
        return ServerResponse.createByErrorMessage("nothing!!!!!");
    }

    public ServerResponse searchByKeyWord(String keyWord, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> lists = articleMapper.searchByKeyWord(keyWord);
        if (!lists.isEmpty()) {
            return ServerResponse.createBySuccess("search success", lists);
        }
        return ServerResponse.createByErrorMessage("nothing!!!");
    }

    public ServerResponse userCreateNewArticle(Article article) {
        int result = articleMapper.insert(article);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("create new article success");
        }
        return ServerResponse.createByErrorMessage("create new article fail!!!");
    }

    public ServerResponse userUpdateArticle(Article article) {
        int result = articleMapper.updateByPrimaryKeySelective(article);
        if (result > 0) {
            if (articleMapper.updateUserUpdateArticleTime(article.getId()) > 0) {
                return ServerResponse.createBySuccessMessage("update article success");
            }
            return ServerResponse.createByErrorMessage("some error in update UPDATE_TIME");
        }
        return ServerResponse.createByErrorMessage("update article fail!!!");
    }

    public ServerResponse deleteArticle(Integer userId, Integer articleId) {
        int result = articleMapper.deleteArticleByUserIdAndId(userId, articleId);
        if (result > 0) {
            return ServerResponse.createBySuccessMessage("delete success");
        }
        return ServerResponse.createByErrorMessage("you are not author,so you can not delete this article!!!");
    }
}
