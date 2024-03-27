package org.example.bigevent.service;


import org.example.bigevent.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册
    void register(String username, String password);
    // 更新
    void update(User user);

    // 更新头像
    void updateAvatar(String avatarUrl);
}
