package com.xblteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xblteam.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>{
}
