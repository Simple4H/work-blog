package com.simple.dao;

import com.simple.pojo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> getAllArticleList();

    List<Article> searchByTags(String tags);

    List<Article> searchByKeyWord(String keyWord);

    int updateUserUpdateArticleTime(Integer id);

    int iLikeIt(Integer articleId);

    int iDontLikeIt(Integer articleId);

    int deleteArticleByUserIdAndId(@Param("userId") Integer userId, @Param("articleId") Integer articleId);

    int insertNewArticle(@Param("userId") Integer userId, @Param("title") String title, @Param("content") String content, @Param("tags") String tags);

    int updateArticle(@Param("title") String title, @Param("content") String content, @Param("tags") String tags, @Param("userId") Integer userId, @Param("articleId") Integer articleId);

    int checkIsUser(@Param("userId")Integer userId,@Param("articleId")Integer articleId);
}