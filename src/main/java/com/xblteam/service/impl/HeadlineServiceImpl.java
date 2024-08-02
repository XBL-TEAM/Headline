package com.xblteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xblteam.mapper.HeadlineMapper;
import com.xblteam.pojo.Headline;
import com.xblteam.pojo.vo.Search;
import com.xblteam.service.HeadlineService;
import com.xblteam.util.JwtHelper;
import com.xblteam.util.Result;
import com.xblteam.util.ResultCodeEnum;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.time.LocalTime.now;

@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline> implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    //查询页面数据
    @Override
    public Result findNewsPage(Search search) {

        IPage<Headline> page = new Page<>(search.getPageNum(), search.getPageSize());

        //头条
        List<Headline> headlines = headlineMapper.selectList(page, search);
        System.out.println("headlines = " + headlines);

        //pageData
        HashMap<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", headlines);
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());

        //pageInfo
        HashMap<String, Object> data = new HashMap<>();
        data.put("pageInfo", pageInfo);

        return Result.ok(data);
    }

    //查询头条详情
    @Override
    public Result showHeadlineDetail(int hid) {
        Headline headline = headlineMapper.selectByHid(hid);

        //浏览量+1
        headline.setPageViews(headline.getPageViews() + 1);
        headlineMapper.updateByHid(headline);

        HashMap<String, Object> data = new HashMap<>();
        data.put("headline", headline);
        return Result.ok(data);
    }

    //头条发布
    @Override
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {

        HashMap<Object, Object> data = new HashMap<>();

        //读取用户
        Integer userId = jwtHelper.getUserId(token);

        //设置头条信息
        headline.setPublisher(userId);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        //插入数据
        int insert = headlineMapper.insert(headline);
        if (insert < 1) {
            return Result.build(data, ResultCodeEnum.FAIL);
        }
        return Result.ok(data);
    }

    //根据hid查询头条
    @Override
    public Result findHeadlineByHid(int hid) {
        Headline headline = headlineMapper.selectByHid(hid);
        HashMap<String, Object> data = new HashMap<>();
        data.put("headline", headline);
        return Result.ok(data);
    }

    //更新头条
    @Override
    public Result update(Headline headline) {

        HashMap<String, Object> data = new HashMap<>();

        //获取该头条版本信息
        Integer version = headlineMapper.selectByHid(headline.getHid()).getVersion();

        //更新数据
        headline.setUpdateTime(new Date());
        headline.setVersion(version+1);

        //修改头条
        int update = headlineMapper.updateByHid(headline);
        if (update < 1) {
            return Result.build(data, ResultCodeEnum.FAIL);
        }
        return Result.ok(data);
    }

    //删除头条
    @Override
    public Result removeByHid(int hid) {

        HashMap<String,String> data = new HashMap<>();
        int i = headlineMapper.deleteByHid(hid);
        if (i < 1) {
            return Result.build(data, ResultCodeEnum.FAIL);
        }
        return Result.ok(data);
    }
}
