package snoob.gdd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snoob.gdd.model.Page;

import javax.annotation.Resource;

//@CrossOrigin(origins = "http://127.0.0.1:4200")
@RequestMapping("/demo")
@RestController
public class DemoController {

    @Value("${page.currentPage}")
    private Integer currentPage;

    @Resource
    private Page page;

    @GetMapping("/demo1")
    public Object demo1() throws Exception{
        System.out.println(0/0);
        System.out.println(currentPage);
        System.out.println(page);
        return page;
    }
}
