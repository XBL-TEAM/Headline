package com.xblteam.controller;

import com.xblteam.service.TypeService;
import com.xblteam.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portal")
public class TypeController {

    @Autowired
    private TypeService typeService;

    //查询所有分类
    @GetMapping("/findAllTypes")
    public Result findAllTypes() {
        Result result = typeService.findAllTypes();
        System.out.println("result = " + result);
        return result;
    }
}
