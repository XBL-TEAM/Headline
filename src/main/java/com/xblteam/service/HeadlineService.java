package com.xblteam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xblteam.pojo.Headline;
import com.xblteam.pojo.vo.Search;
import com.xblteam.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface HeadlineService extends IService<Headline> {
    Result findNewsPage(Search search);

    Result showHeadlineDetail(int hid);

    Result publish(@RequestBody Headline headline, @RequestHeader String token);

    Result findHeadlineByHid(int hid);

    Result update(Headline headline);

    Result removeByHid(int hid);
}
