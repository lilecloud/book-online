package com.le.bookservice.role;

import com.le.bookdao.entity.Role;

import java.util.List;

public interface IRoleService {


    Role createRole(Role role);

    int deleteRoleById(Long id);


    void correlationPermissions(Long roleId, List<Long> permissionIds);

    void uncorrelationPermissions(Long roleId, List<Long> permissionIds);
}
