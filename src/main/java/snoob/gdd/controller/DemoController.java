package snoob.gdd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snoob.gdd.properties.Page;

import javax.annotation.Resource;

@RequestMapping("/demo")
@RestController
@CrossOrigin
public class DemoController {
    @Value("${page.currentPage}")
    private Integer currentPage;

    @Resource
    private Page page;

    @GetMapping("/demo1")
    public Object demo(){
        System.out.println(currentPage);
        System.out.println(page);
        return "hello world";
    }
}
