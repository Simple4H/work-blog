package com.simple.pojo;

import java.util.Date;

public class Article {
    private Integer id;

    private Integer userId;

    private String title;

    private String content;

    private String tags;

    private Integer likeArticle;

    private Date createTime;

    private Date updateTime;

    public Article(Integer id, Integer userId, String title, String content, String tags, Integer likeArticle, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.likeArticle = likeArticle;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Article(Integer userId, String title, String content, String tags) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Article(Integer id, Integer userId, String title, String content, String tags) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Article() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Integer getLikeArticle() {
        return likeArticle;
    }

    public void setLikeArticle(Integer likeArticle) {
        this.likeArticle = likeArticle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}