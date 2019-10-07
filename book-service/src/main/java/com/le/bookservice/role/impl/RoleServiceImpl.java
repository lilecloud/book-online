package com.le.bookservice.role.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.le.bookcommon.GlobalStatic;
import com.le.bookdao.entity.Permission;
import com.le.bookdao.entity.Role;
import com.le.bookdao.entity.RolePermission;
import com.le.bookdao.mapper.RoleMapper;
import com.le.bookdao.mapper.RolePermissionMapper;
import com.le.bookservice.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElementDecl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Role createRole(Role role) {
        int count = roleMapper.insert(role);
        return role;
    }

    @Override
    public int deleteRoleById(Long id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public void correlationPermissions(@NotNull Long roleId, @NotNull List<Long> permissionIds) {
        if(!CollectionUtils.isEmpty(permissionIds) && !Objects.isNull(roleId)){
            permissionIds.forEach( permissionId ->{
                    rolePermissionMapper.insert(new RolePermission(roleId,permissionId));
            });
        }

    }

    @Override
    public void uncorrelationPermissions(@NotNull Long roleId, @NotNull List<Long> permissionIds) {
        if(!CollectionUtils.isEmpty(permissionIds) && !Objects.isNull(roleId)){
            QueryWrapper<RolePermission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id",roleId);
            queryWrapper.in("permission_id",permissionIds);
            rolePermissionMapper.delete(queryWrapper);
        }

    }
}
