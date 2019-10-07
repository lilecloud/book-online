package com.le.bookservice.user.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.le.bookcommon.GlobalStatic;
import com.le.bookdao.entity.*;
import com.le.bookdao.mapper.RolePermissionMapper;
import com.le.bookdao.mapper.UserMapper;
import com.le.bookdao.mapper.UserRoleMapper;
import com.le.bookservice.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;


    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq(true,"username",username);
        wrapper.last("limit 1");
        User resultUser = userMapper.selectOne(wrapper);
        return resultUser;
    }

    @Override
    public User selectByUsernameAndPassword(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        wrapper.eq(true,"username",username);
        wrapper.eq("password",password);
        wrapper.last("limit 1");
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        userMapper.updateById(user);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        for (Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleMapper.insert(userRole);
        }

    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.in("role_id",roleIds);
        userRoleMapper.delete(queryWrapper);
    }


    @Override
    public Set<String> findRoles(String username) {
        List<Role> roles =  userRoleMapper.selectRolesByUsername(username);
        Set<String> set =  roles.stream().map(role-> role.getDescription() ).collect(Collectors.toSet());
        return set;
    }

    @Override
    public Set<String> findPermissions(String username) {
        List<Role> roles =  userRoleMapper.selectRolesByUsername(username);
        Set<Long> set = roles.stream().map(role-> role.getId() ).collect(Collectors.toSet());

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id",set);
//        rolePermissionMapper.selectList(queryWrapper)

        return null;
    }
}
