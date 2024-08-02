package com.xblteam.controller;

import com.xblteam.pojo.Headline;
import com.xblteam.pojo.vo.Search;
import com.xblteam.service.HeadlineService;
import com.xblteam.service.UserService;
import com.xblteam.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/headline")
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    //头条发布
    @PostMapping("/publish")
    public Result publish(@RequestBody Headline headline,@RequestHeader String token) {
        Result result = headlineService.publish(headline,token);
        System.out.println("result = " + result);
        return result;
    }

    //查询头条
    @PostMapping("/findHeadlineByHid")
    public Result findHeadlineByHid(@RequestParam int hid) {
        Result result = headlineService.findHeadlineByHid(hid);
        System.out.println("result = " + result);
        return result;
    }

    //修改头条
    @PostMapping("/update")
    public Result update(@RequestBody Headline headline) {
        Result result = headlineService.update(headline);
        System.out.println("result = " + result);
        return result;
    }

    //删除头条
    @PostMapping("/removeByHid")
    public Result removeByHid(@RequestParam int hid) {
        Result result = headlineService.removeByHid(hid);
        System.out.println("result = " + result);
        return result;
    }
}
