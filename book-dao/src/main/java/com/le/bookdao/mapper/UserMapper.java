package com.le.bookdao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.le.bookdao.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {

    int insert(User user);




}
