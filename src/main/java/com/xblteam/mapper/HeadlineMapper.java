package com.xblteam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xblteam.pojo.Headline;
import com.xblteam.pojo.vo.Search;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HeadlineMapper extends BaseMapper<Headline> {

    //查询分页信息
    @Select(
            value = {
                    "<script>",
                    "select hid,title,article,type,publisher,page_views,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,update_time,version,is_deleted from news_headline ",
                    "<where>",
                    "<if test='search.type!=0'>",
                    " and type=#{search.type}",
                    "</if>",
                    "<if test='search.keyWords!=\"\"'>",
                    " and article like concat('%',#{search.keyWords} ,'%')",
                    "</if>",
                    "</where>",
                    "</script>"
            }
    )
    List<Headline> selectList(IPage<Headline> page, @Param("search") Search search);

    //根据hid查询头条信息
    @Select("select hid,title,article,type,publisher,page_views,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,update_time,version,is_deleted from news_headline where hid=#{hid};")
    Headline selectByHid(@Param("hid") Integer hid);

    //修改头条
    @Update("update news_headline set title=#{title},article=#{article},page_views=#{pageViews},update_time=now(),version=#{version} where hid=#{hid};")
    int updateByHid(Headline headline);

    //删除头条
    @Delete("delete from news_headline where hid=#{hid}")
    int deleteByHid(int hid);
}
