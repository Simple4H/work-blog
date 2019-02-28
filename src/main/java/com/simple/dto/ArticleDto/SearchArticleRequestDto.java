package com.simple.dto.ArticleDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Simple4H
 * @Date: 2019/02/27 17:24:07
 */
@Data
@ApiModel
public class SearchArticleRequestDto {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "输入")
    private String input;

    @ApiModelProperty(value = "页码")
    private int pageNum;

    @ApiModelProperty(value = "页数")
    private int pageSize;
}
