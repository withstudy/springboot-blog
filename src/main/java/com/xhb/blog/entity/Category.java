package com.xhb.blog.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Category",description="分类实体")
public class Category {
    @ApiModelProperty(value="id",required=false)
    private Integer id;
    @ApiModelProperty(value="分类名",required=true)
    private String name;
    @ApiModelProperty(value="分类简介",required=true)
    private String content;

    public Category(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
