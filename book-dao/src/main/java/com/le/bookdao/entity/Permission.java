package com.le.bookdao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("permission")
public class Permission {

    @TableId
    private Long id;

    private String permission;

    private String description;

    private Integer aviliable;

    public Permission(String permission, String description) {
        this.permission = permission;
        this.description = description;
    }

    public Permission() {
    }
}
