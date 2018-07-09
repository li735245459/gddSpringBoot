package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@RequestMapping("/excel")
@RestController
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
     * 导入用户信息 , consumes = "multipart/form-data"
     *
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/importUser")
    public Object importUser(@RequestPart("file") MultipartFile file) throws Exception {
        return excelService.importUser(file);
    }

}
