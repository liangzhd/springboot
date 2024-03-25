package org.example.bigevent.controller;

import jakarta.validation.constraints.Pattern;
import org.example.bigevent.pojo.User;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @PostMapping("/register")
    // 使用Validated框架判断合法性
    public Result register(@Pattern(regexp = "^\\S{6,16}$") String username, @Pattern(regexp = "^\\S{6,16}$") String password) {
//        if (username != null && password != null &&
//                username.length() > 5 && password.length() > 5 &&
//                username.length() <= 16 && password.length() <= 16) {
            // 查询
            User u = userService.findByUserName(username);
            if (u == null) {
                // 注册
                userService.register(username, password);
                return Result.success();
            } else {
                return Result.error("用户名已存在");
            }
//        } else {
//            return Result.error("用户名或密码格式不正确");
//        }
    }
}