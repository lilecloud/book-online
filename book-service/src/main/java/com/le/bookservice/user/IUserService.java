package com.le.bookservice.user;

import com.le.bookdao.entity.User;

import java.util.Set;

public interface IUserService {

    User selectById(Long id);


    User selectByUsername(String username);

    User selectByUsernameAndPassword(String username,String password);


    void changePassword(User user,String newPassword);

    void correlationRoles(Long userId, Long... roleIds); //添加用户-角色关系

    void uncorrelationRoles(Long userId, Long... roleIds);// 移除用户-角色关系

    Set<String> findRoles(String username);// 根据用户名查找其角色

    Set<String> findPermissions(String username); //根据用户名查找其权限
}
