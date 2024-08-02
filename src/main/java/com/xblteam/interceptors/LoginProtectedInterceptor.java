package com.xblteam.interceptors;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.support.json.JSONWriter;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xblteam.util.JwtHelper;
import com.xblteam.util.Result;
import com.xblteam.util.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;

@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录未过期
        String token = request.getHeader("token");
        if (!jwtHelper.isExpiration(token)) {
            return true;
        }

        //登录过期
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(Result.build(new HashMap<>(), ResultCodeEnum.NOT_LOGIN));     //json转换
        System.out.println("result = " + jsonString);
        response.getWriter().print(jsonString);
        return false;
    }
}
