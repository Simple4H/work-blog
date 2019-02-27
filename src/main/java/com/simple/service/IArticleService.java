package com.simple.service;

import com.simple.common.ServerResponse;
import com.simple.dto.ArticleDto.CreateArticleRequestDto;
import com.simple.dto.ArticleDto.UpdateArticleRequestDto;
import com.simple.pojo.Article;

/**
 * @Author: Simple4H
 * @Date: 2019/02/26 11:04:34
 */
public interface IArticleService {

    ServerResponse getAllArticleList(int pageNum, int pageSize);

    ServerResponse getArticleByTags(String tags, int pageNum, int pageSize);

    ServerResponse searchByKeyWord(String keyWord, int pageNum, int pageSize);

    ServerResponse userCreateNewArticle(CreateArticleRequestDto requestDto);

    ServerResponse userUpdateArticle(UpdateArticleRequestDto requestDto);

    ServerResponse deleteArticle(Integer userId, Integer articleId);

}
