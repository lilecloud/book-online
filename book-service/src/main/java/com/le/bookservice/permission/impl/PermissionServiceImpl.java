package com.le.bookservice.permission.impl;

import com.le.bookdao.entity.Permission;
import com.le.bookdao.mapper.PermissionMapper;
import com.le.bookservice.permission.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionServiceImpl implements IPermissionService {


    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission createPermission(Permission permission) {
        int count = permissionMapper.insert(permission);
        return permission;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePermission(Long id) {
        return permissionMapper.deleteById(id);
    }
}
