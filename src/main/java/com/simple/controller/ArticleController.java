package com.simple.controller;

import com.simple.common.ServerResponse;
import com.simple.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:02:52
 */
@RestController
@RequestMapping(value = "/article/")
public class ArticleController {

    @Autowired
    private IArticleService iArticleService;

    @RequestMapping(value = "get_all_article_list.do",method = RequestMethod.POST)
    public ServerResponse getAllArticleList() {
        return iArticleService.getAllArticleList();
    }
}
