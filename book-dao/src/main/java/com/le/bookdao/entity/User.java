package com.le.bookdao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName(value = "user")
@Component
public class User implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "username")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @TableField(value = "password")
    @NotBlank(message = "密码不能为空")
    private String password;

    @TableField(value = "status")
    private Integer status;


}
