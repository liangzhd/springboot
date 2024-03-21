package org.example.mybatis.service.impl;

import org.example.mybatis.mapper.UserMapper;
import org.example.mybatis.pojo.User;
import org.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    ;
}
