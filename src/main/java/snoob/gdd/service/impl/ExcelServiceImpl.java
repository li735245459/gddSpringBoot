package snoob.gdd.service.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Resource
    private UserMapper userMapper;

    @Override
    public void exportUser(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        // 查询数据
        String excel = user.getId();
        user.setId(null);
        List<User> users = userMapper.select(user);
        //获得输出流,该输出流的输出介质是客户端浏览器
        OutputStream os = response.getOutputStream();
        BufferedOutputStream bufferOs = new BufferedOutputStream(os);
        if ("oxlsx".equals(excel)) {
            //创建工作簿
            XSSFWorkbook workBook = new XSSFWorkbook();
            //创建工作表
            XSSFSheet sheet = workBook.createSheet("helloWorld");
            for(int i = 0 ; i < 50000; i++) {
                //创建行
                XSSFRow row = sheet.createRow(i);
                for (int j = 0 ; j < 10; j++) {
                    //创建单元格
                    XSSFCell cell = row.createCell(j, CellType.STRING);
                    cell.setCellValue("测试");
                }
            }
            bufferOs.flush();
            workBook.write(bufferOs);
            bufferOs.close();
            workBook.close();
        } else {
            //创建工作簿
            HSSFWorkbook workBook = new HSSFWorkbook();
            //创建工作表
            HSSFSheet sheet = workBook.createSheet("helloWorld");
            for(int i = 0 ; i < 50000; i++) {
                //创建行
                HSSFRow row = sheet.createRow(i);
                for (int j = 0 ; j < 10; j++) {
                    //创建单元格
                    HSSFCell cell = row.createCell(j, CellType.STRING);
                    cell.setCellValue("测试");
                }
            }
            bufferOs.flush();
            workBook.write(bufferOs);
            bufferOs.close();
            workBook.close();
        }
    }
}
