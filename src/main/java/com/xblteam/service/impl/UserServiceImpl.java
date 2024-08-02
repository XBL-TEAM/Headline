package com.xblteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xblteam.mapper.UserMapper;
import com.xblteam.pojo.User;
import com.xblteam.service.UserService;
import com.xblteam.util.JwtHelper;
import com.xblteam.util.MD5Util;
import com.xblteam.util.Result;
import com.xblteam.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    //用户登录
    @Override
    public Result login(User user) {

        HashMap<String, String> data = new HashMap<>();

        //前端用户名为空
        if (user == null || user.getUsername() == null) {
            return Result.build(data, ResultCodeEnum.USERNAME_ERROR);
        }

        //前端密码为空
        if (user.getUserPwd() == null) {
            return Result.build(data, ResultCodeEnum.PASSWORD_ERROR);
        }

        //查询数据
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User result = userMapper.selectOne(wrapper);

        //用户不存在 或 用户名不正确
        if (result == null || result.getUsername() == null) {
            return Result.build(data, ResultCodeEnum.USERNAME_ERROR);
        }

        //密码不正确
        if (!MD5Util.encrypt(user.getUserPwd()).equals(result.getUserPwd())) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }

        //登录成功
        String token = jwtHelper.createToken(result.getUid());
        data.put("token", token);
        return Result.ok(data);
    }

    //登录获取用户信息
    @Override
    public Result getUserInfo(String token) {

        //已过期
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOT_LOGIN);
        }

        //未过期
        Integer userId = jwtHelper.getUserId(token);
        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        HashMap<String, User> data = new HashMap<>();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    //注册检查用户名
    @Override
    public Result checkUserName(String username) {

        HashMap<String, String> data = new HashMap<>();

        //用户名为空
        if (username == null) {
            return Result.build(data, ResultCodeEnum.USERNAME_NULL);
        }

        //用户名为空格
        String name = username.replace(" ", "");
        if (name.equals("")) {
            return Result.build(data, ResultCodeEnum.USERNAME_NULL);
        }

        //用户名可用
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        System.out.println("user = " + user);
        if (user == null) {
            return Result.ok(data);
        }

        //用户名被占用
        return Result.build(data, ResultCodeEnum.USERNAME_USED);
    }

    //用户注册
    @Override
    public Result regist(User user) {

        HashMap<String, String> data = new HashMap<>();

        //用户名为空
        if (user == null || user.getUsername() == null || user.getUserPwd() == null) {
            return Result.build(data, ResultCodeEnum.USERNAME_NULL);
        }

        //用户名被占用
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername());
        Long row = userMapper.selectCount(wrapper);
        if (row > 0) {
            return Result.build(data, ResultCodeEnum.USERNAME_USED);
        }

        //用户名未占用
        try {
            user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
            userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.build(data, ResultCodeEnum.USERNAME_USED);
        }

        //注册成功
        System.out.println(user);
        return Result.ok(data);
    }

    //登录检查
    @Override
    public Result checkLogin(String token) {
        HashMap<String, String> data = new HashMap<>();
        if (jwtHelper.isExpiration(token)) {
            return Result.build(data, ResultCodeEnum.NOT_LOGIN);
        }
        return Result.ok(data);
    }
}
