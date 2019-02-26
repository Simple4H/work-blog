package com.simple.pojo;

public class UserItem {
    private Integer id;

    private Integer userId;

    private Integer articleId;

    public UserItem(Integer id, Integer userId, Integer articleId) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
    }

    public UserItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}