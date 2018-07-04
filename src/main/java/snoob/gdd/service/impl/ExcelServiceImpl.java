package snoob.gdd.service.impl;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void exportUser(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = userMapper.selectAll();

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet =wb.createSheet("获取excel测试表格");

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }
}
