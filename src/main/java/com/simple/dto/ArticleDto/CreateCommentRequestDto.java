package com.simple.dto.ArticleDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Simple4H
 * @Date: 2019/02/27 16:56:29
 */
@Data
@ApiModel
public class CreateCommentRequestDto {

    @ApiModelProperty
    private Integer articleId;

    @ApiModelProperty
    private String comment;
}
