package com.simple.dto.ArticleDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Simple4H
 * @Date: 2019/02/27 15:35:05
 */
@Data
@ApiModel
public class UpdateArticleRequestDto {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "分类")
    private String tags;
}
