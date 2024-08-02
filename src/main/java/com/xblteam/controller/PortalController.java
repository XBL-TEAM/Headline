package com.xblteam.controller;

import com.xblteam.pojo.Headline;
import com.xblteam.pojo.vo.Search;
import com.xblteam.service.HeadlineService;
import com.xblteam.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private HeadlineService headlineService;

    //查询新页面信息
    @PostMapping("/findNewsPage")
    public Result findNewsPage(@RequestBody Search search) {
        System.out.println("search = " + search);
        Result result = headlineService.findNewsPage(search);
        System.out.println("result = " + result);
        return result;
    }

    //查询头条详情
    @PostMapping("/showHeadlineDetail")
    public Result showHeadlineDetail(@RequestParam("hid") int hid) {
        Result result = headlineService.showHeadlineDetail(hid);
        System.out.println("result = " + result);
        return result;
    }
}
