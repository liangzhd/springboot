package org.example.mybatis.service;

import org.example.mybatis.pojo.User;

public interface UserService {
    public User findById(Integer id);
}
