package com.le.bookdao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class Role {

    @TableId
    private Long id;


    private String role;


    private String description;

    private Integer aviliable;
}
