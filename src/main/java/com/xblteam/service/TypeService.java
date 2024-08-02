package com.xblteam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xblteam.pojo.Type;
import com.xblteam.pojo.User;
import com.xblteam.util.Result;

public interface TypeService extends IService<Type> {
    Result findAllTypes();
}
