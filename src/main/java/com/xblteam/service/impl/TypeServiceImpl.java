package com.xblteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xblteam.mapper.TypeMapper;
import com.xblteam.pojo.Type;
import com.xblteam.service.TypeService;
import com.xblteam.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    //查询所有类型
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(new QueryWrapper<>());
        return Result.ok(types);
    }


}
