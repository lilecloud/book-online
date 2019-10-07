package com.le.bookdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.le.bookdao.entity.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {


    List<Permission> selectByRoleIds(List<Long> roleIds);
}
