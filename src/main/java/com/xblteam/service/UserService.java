package com.xblteam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xblteam.pojo.User;
import com.xblteam.util.Result;

public interface UserService extends IService<User> {
    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);

    Result checkLogin(String token);
}
