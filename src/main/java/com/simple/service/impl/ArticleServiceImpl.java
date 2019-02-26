package com.simple.service.impl;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:04:47
 */

import com.simple.common.ServerResponse;
import com.simple.dao.ArticleMapper;
import com.simple.pojo.Article;
import com.simple.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iArticleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public ServerResponse getAllArticleList() {
        List<Article> lists = articleMapper.getAllArticleList();
        if (lists.isEmpty()) {
            return ServerResponse.createByErrorMessage("没有文章");
        }
        return ServerResponse.createBySuccess("获取文章列表成功", lists);
    }


}
