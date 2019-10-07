package com.le.bookservice.permission;

import com.le.bookdao.entity.Permission;

public interface IPermissionService {

    Permission createPermission(Permission permission);

    int deletePermission(Long id);
}
