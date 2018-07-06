package snoob.gdd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/excel")
@Controller
public class ExcelController {

    @Resource
    private ExcelService excelService;

    /**
     * 导出用户信息
     *
     * @param response
     * @param user
     * @throws Exception
     */
    @PostMapping("/exportUser")
    public void exportUser(HttpServletResponse response, @RequestBody User user) throws Exception {
        excelService.exportUser(response, user);
    }

    /**
     * 导入用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/importUser")
    public Object importUser(HttpServletRequest request) throws Exception {
        return excelService.importUser(request);
    }
}
