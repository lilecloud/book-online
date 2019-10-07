package com.le.bookdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.le.bookdao.entity.Permission;
import com.le.bookdao.entity.Role;
import com.le.bookdao.entity.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {


    @Select("select r.id,r.role,r.aviliable,r.aviliable FROM user_role ur inner join user u on u.id = ur.user_id \n" +
            "inner join role r on r.id = ur.role_id\n" +
            "where u.username = #{username}  and r.aviliable= 1 ")
    List<Role> selectRolesByUsername(String username);

    @Select("")
    List<Permission> selectPermissionsByUsername();
}
