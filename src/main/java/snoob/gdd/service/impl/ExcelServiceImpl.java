package snoob.gdd.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;
import snoob.gdd.util.ResultUtil;
import tk.mybatis.mapper.entity.Example;

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


    /**
     * 导出用户信息
     *
     * @param response
     * @param user
     * @throws Exception
     */
    @Override
    public void exportUser(HttpServletResponse response, User user) throws Exception {
        response.setHeader("Content-disposition", "attachment; filename=details.xls");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        // 查询数据
        Example example = new Example(User.class);
        example.orderBy("createTime").asc();
        Example.Criteria criteria = example.createCriteria();
        if (user.getName() != null) {
            criteria.andLike("name", user.getName().trim() + "%");
        }
        if (user.getSex() != null && !"0".equals(user.getSex())) {
            criteria.andEqualTo("sex", user.getSex().trim());
        }
        if (user.getEmail() != null) {
            criteria.andEqualTo("email", user.getEmail().trim());
        }
        if (user.getPhone() != null) {
            criteria.andEqualTo("phone", user.getPhone().trim());
        }
        if (user.getCreateTime() != null) {
            criteria.andGreaterThanOrEqualTo("createTime", user.getCreateTime());
        }
        if (user.getLoginTime() != null) {
            criteria.andGreaterThanOrEqualTo("loginTime", user.getLoginTime());
        }
        List<User> users = userMapper.selectByExample(example);
        // 获得输出流,该输出流的输出介质是客户端浏览器
        OutputStream os = response.getOutputStream();
        BufferedOutputStream bufferOs = new BufferedOutputStream(os);
        /*
         * 创建工作簿
         */
        String[] exportFields = {"姓名", "性别", "手机号码", "邮箱", "爱好", "省", "市", "区/县", "详细地址", "注册时间"}; // 导出字段
        HSSFWorkbook workBook = new HSSFWorkbook();
        /*
         * 创建工作表
         */
        HSSFSheet sheet = workBook.createSheet("用户信息");
        /*
         *
         */
        HSSFRow row = null;
        HSSFCell cell = null;
        HSSFCellStyle cellStyle = null;
        HSSFFont font = null;
        /*
         * 创建表头,合并(0,0),(1,9)
         */
        CellRangeAddress cra = new CellRangeAddress(0, 2, 0, 9);
        sheet.addMergedRegion(cra);
        row = sheet.createRow(0);
        cell = row.createCell(0);
        cell.setCellValue("用户信息列表");
        cellStyle = workBook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION); // 水平居中
        font = workBook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFont(font); // 设置字体
        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex()); // 设置前景色
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
        /*
         * 创建标题
         */
        row = sheet.createRow(3);
        row.setHeight((short) (26.25 * 20));
        cellStyle = workBook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        font = workBook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        for (int i = 0; i < exportFields.length; i++) {
            cell = row.createCell(i);
            // 设置列宽
            switch (i) {
                case 0:
                    sheet.setColumnWidth(i, 10 * 256); // 姓名
                    break;
                case 1:
                    sheet.setColumnWidth(i, 8 * 256); // 性别
                    break;
                case 2:
                    sheet.setColumnWidth(i, 20 * 256); // 手机号码
                    break;
                case 3:
                    sheet.setColumnWidth(i, 30 * 256); // 邮箱
                    break;
                case 4:
                    sheet.setColumnWidth(i, 40 * 256); // 爱好
                    break;
                case 5:
                    sheet.setColumnWidth(i, 20 * 256); // 省
                    break;
                case 6:
                    sheet.setColumnWidth(i, 20 * 256); // 市
                    break;
                case 7:
                    sheet.setColumnWidth(i, 30 * 256); // 区
                    break;
                case 8:
                    sheet.setColumnWidth(i, 50 * 256); // 详细地址
                    break;
                case 9:
                    sheet.setColumnWidth(i, 20 * 256); // 注册时间
                    break;
            }
            // 设置列样式
            cell.setCellStyle(cellStyle);
            // 设置值
            cell.setCellValue(exportFields[i]);
        }
        /*
         * 创建数据
         */
        cellStyle = workBook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setDataFormat(workBook.createDataFormat().getFormat("yyyy-MM-dd  hh:mm:ss"));
        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i + 4);
            row.setHeight((short) (26.25 * 15));
            for (int j = 0; j < exportFields.length; j++) {
                cell = row.createCell(j);
                // 设置值
                switch (j) {
                    case 0:
                        cell.setCellValue(users.get(i).getName()); // 姓名
                        break;
                    case 1:
                        cell.setCellValue(users.get(i).getSex()); // 性别
                        break;
                    case 2:
                        cell.setCellValue(users.get(i).getPhone()); // 手机号码
                        break;
                    case 3:
                        cell.setCellValue(users.get(i).getEmail()); // 邮箱
                        break;
                    case 4:
                        cell.setCellValue(users.get(i).getHobby()); // 爱好
                        break;
                    case 5:
                        cell.setCellValue(users.get(i).getProvince()); // 省
                        break;
                    case 6:
                        cell.setCellValue(users.get(i).getCity()); // 市
                        break;
                    case 7:
                        cell.setCellValue("-1".equals(users.get(i).getArea()) ? null : users.get(i).getArea()); // 区
                        break;
                    case 8:
                        cell.setCellValue(users.get(i).getAddress()); // 详细地址
                        break;
                    case 9:
                        cell.setCellValue(users.get(i).getCreateTime()); // 注册时间
                        break;
                }
                // 设置列样式
                cell.setCellStyle(cellStyle);
            }
        }
        workBook.write(bufferOs);
        bufferOs.flush();
        bufferOs.close();
        workBook.close();
    }

    /**
     * 导入用户信息
     *
     * @param request
     * @throws Exception
     */
    @Override
    public Object importUser(HttpServletRequest request) throws Exception {

        return ResultUtil.success();
    }
}
