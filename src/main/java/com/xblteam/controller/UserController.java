package com.xblteam.controller;

import com.xblteam.pojo.User;
import com.xblteam.service.UserService;
import com.xblteam.util.JwtHelper;
import com.xblteam.util.Result;
import com.xblteam.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        System.out.println("result = " + result);
        return result;
    }

    //获取登录信息
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        System.out.println("result = " + result);
        return result;
    }

    //注册用户名检查
    @PostMapping("/checkUserName")
    public Result checkUserName( String username) {
        Result result = userService.checkUserName(username);
        System.out.println("result = " + result);
        return result;
    }

    //用户注册
    @PostMapping("/regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        System.out.println("result = " + result);
        return result;
    }

    //登录检查
    @GetMapping("/checkLogin")
    public Result checkLogin(@RequestHeader String token) {
        if(token == null || token.isEmpty()){
            return Result.build(new HashMap<>(), ResultCodeEnum.NOT_LOGIN);
        }
        Result result = userService.checkLogin(token);
        System.out.println("result = " + result);
        return result;
    }
}
