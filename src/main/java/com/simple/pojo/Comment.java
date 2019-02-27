package com.simple.pojo;

import java.util.Date;

public class Comment {
    private Integer id;

    private Integer userId;

    private Integer articleId;

    private String comment;

    private Date createTime;

    public Comment(Integer id, Integer userId, Integer articleId, String comment, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
        this.comment = comment;
        this.createTime = createTime;
    }

    public Comment() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}