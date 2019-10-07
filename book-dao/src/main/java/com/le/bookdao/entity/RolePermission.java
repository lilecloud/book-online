package com.le.bookdao.entity;

import lombok.Data;

@Data
public class RolePermission {

    private Long id;


    private Long roleId;


    private Long PermissionId;

    public RolePermission() {
    }

    public RolePermission(Long roleId, Long permissionId) {
        this.roleId = roleId;
        PermissionId = permissionId;
    }
}
