package snoob.gdd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/excel")
@Controller
public class ExcelController {

    @Resource
    private ExcelService excelService;

    /**
     * 导出用户信息 , @RequestBody User user
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/exportUser")
    public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws Exception {
        excelService.exportUser(user, request, response);
    }
}
