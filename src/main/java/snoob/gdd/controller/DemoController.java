package snoob.gdd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snoob.gdd.util.PageUtil;

import javax.annotation.Resource;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RequestMapping("/demo")
@RestController
public class DemoController {
    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Value("${page.currentPage}")
    private Integer currentPage;

    @Resource
    private PageUtil pageUtil;

    @GetMapping("/demo1")
    public Object demo(){
        System.out.println(currentPage);
        System.out.println(pageUtil);
        logger.info(currentPage.toString());
        logger.info(pageUtil.toString());
        return pageUtil;
    }
}
