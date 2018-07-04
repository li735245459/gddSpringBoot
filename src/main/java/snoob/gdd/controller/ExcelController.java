package snoob.gdd.controller;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.util.List;

@RequestMapping("/excel")
@Controller
public class ExcelController {

    @Resource
    private ExcelService excelService;

    @Resource
    private UserMapper userMapper;

    /**
     * 导出用户信息
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/exportUser")
    public void export(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws Exception {
        List<User> users = userMapper.selectAll();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet =wb.createSheet("获取excel测试表格");

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();

    }
}
