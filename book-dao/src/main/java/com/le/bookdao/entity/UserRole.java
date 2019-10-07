package com.le.bookdao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user_role")
@Data
public class UserRole {


    @TableId
    private Long id;

    private Long userId;

    private Long roleId;
}
