package org.example.bigevent.controller;

import jakarta.validation.constraints.Pattern;
import org.example.bigevent.pojo.User;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.UserService;
import org.example.bigevent.utils.JwtUtil;
import org.example.bigevent.utils.Md5Util;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            // 查询用户是否存在
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
    /**
     *登录
     */
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{6,16}$") String username, @Pattern(regexp = "^\\S{6,16}$") String password) {
        // 查询用户是否存在
        User loginUser = userService.findByUserName(username);
        if (loginUser == null){
            return Result.error("用户名不存在");
        }
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // 登录成功,生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            return Result.success(JwtUtil.getToken(claims));
        }
        return Result.error("密码错误");
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/userInfo")
    public Result<User> getUserInfo(/*@RequestHeader(name = "Authorization") String token*/) {

//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String)map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
}