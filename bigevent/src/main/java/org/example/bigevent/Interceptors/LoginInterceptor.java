package org.example.bigevent.Interceptors;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否登录,验证令牌
        String token = request.getHeader("Authorization");
        // 验证token
        try {
            Map<String,Object> claims = JwtUtil.parseToken(token);
            return true;

        }catch (Exception e){
            response.setStatus(401);
            return false;
        }
    }
}
