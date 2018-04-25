package com.example.demo.controller;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xin on 3/14/2018.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping("/index")
    public String index(){
        return "demo";
    }
}
