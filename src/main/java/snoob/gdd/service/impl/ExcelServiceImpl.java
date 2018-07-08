package snoob.gdd.service.impl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;
import snoob.gdd.util.ResultUtil;
import snoob.gdd.util.StrUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Resource
    private UserMapper userMapper;
    private HSSFWorkbook workBook = null;
    private HSSFSheet hssfSheet = null;
    private HSSFRow hssfRow = null;
    private HSSFCell hssfCell = null;
    private Sheet sheet = null;
    private Row row = null;
    private Cell cell = null;
    private HSSFCellStyle hssfCellStyle = null;
    private HSSFFont font = null;
    HSSFDataFormat format = null;

    /**
     * 导出用户信息
     *
     * @param response
     * @param user
     * @thhssfRows Exception
     */
    @Override
    public void exportUser(HttpServletResponse response, User user) throws Exception {
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
        /*
         * 创建工作簿
         */
        String[] exportFields = {"姓名", "性别", "手机号码", "邮箱", "爱好", "省", "市", "区/县", "详细地址", "注册时间"}; // 导出字段
        workBook = new HSSFWorkbook();
        /*
         * 创建工作表
         */
        hssfSheet = workBook.createSheet("用户信息");
        /*
         * 创建表头,合并(0,0),(1,9)
         */
        CellRangeAddress cra = new CellRangeAddress(0, 2, 0, 9);
        hssfSheet.addMergedRegion(cra);
        hssfRow = hssfSheet.createRow(0);
        hssfCell = hssfRow.createCell(0);
        hssfCell.setCellValue("用户信息列表");
        hssfCellStyle = workBook.createCellStyle();
        hssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION); // 水平居中
        font = workBook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.WHITE.getIndex());
        hssfCellStyle.setFont(font); // 设置字体
        hssfCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex()); // 设置前景色
        hssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        hssfCell.setCellStyle(hssfCellStyle);
        /*
         * 创建标题
         */
        hssfRow = hssfSheet.createRow(3);
        hssfRow.setHeight((short) (26.25 * 20));
        hssfCellStyle = workBook.createCellStyle();
        hssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        font = workBook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 12);
        hssfCellStyle.setFont(font);
        for (int i = 0; i < exportFields.length; i++) {
            hssfCell = hssfRow.createCell(i, CellType.STRING);
            // 设置列宽
            switch (i) {
                case 0:
                    hssfSheet.setColumnWidth(i, 10 * 256); // 姓名
                    break;
                case 1:
                    hssfSheet.setColumnWidth(i, 8 * 256); // 性别
                    break;
                case 2:
                    hssfSheet.setColumnWidth(i, 20 * 256); // 手机号码
                    break;
                case 3:
                    hssfSheet.setColumnWidth(i, 30 * 256); // 邮箱
                    break;
                case 4:
                    hssfSheet.setColumnWidth(i, 40 * 256); // 爱好
                    break;
                case 5:
                    hssfSheet.setColumnWidth(i, 20 * 256); // 省
                    break;
                case 6:
                    hssfSheet.setColumnWidth(i, 20 * 256); // 市
                    break;
                case 7:
                    hssfSheet.setColumnWidth(i, 30 * 256); // 区
                    break;
                case 8:
                    hssfSheet.setColumnWidth(i, 50 * 256); // 详细地址
                    break;
                case 9:
                    hssfSheet.setColumnWidth(i, 20 * 256); // 注册时间
                    break;
            }
            // 设置列样式
            hssfCell.setCellStyle(hssfCellStyle);
            // 设置值
            hssfCell.setCellValue(exportFields[i]);
        }
        /*
         * 创建数据
         */
        format = workBook.createDataFormat();
        for (int i = 0; i < users.size(); i++) {
            hssfRow = hssfSheet.createRow(i + 4);
            hssfRow.setHeight((short) (26.25 * 15));
            for (int j = 0; j < exportFields.length; j++) {
                hssfCellStyle = workBook.createCellStyle();
                hssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                hssfCellStyle.setAlignment(HorizontalAlignment.LEFT);
                hssfCell = hssfRow.createCell(j, CellType.STRING);
                // 设置cell值
                switch (j) {
                    case 0:
                        hssfCell.setCellValue(users.get(i).getName()); // 姓名
                        break;
                    case 1:
                        hssfCell.setCellValue(users.get(i).getSex()); // 性别
                        break;
                    case 2:
                        hssfCellStyle.setDataFormat(format.getFormat("TEXT"));
                        hssfCell.setCellValue(users.get(i).getPhone()); // 手机号码
                        break;
                    case 3:
                        hssfCell.setCellValue(users.get(i).getEmail()); // 邮箱
                        break;
                    case 4:
                        hssfCell.setCellValue(users.get(i).getHobby()); // 爱好
                        break;
                    case 5:
                        hssfCell.setCellValue(users.get(i).getProvince()); // 省
                        break;
                    case 6:
                        hssfCell.setCellValue(users.get(i).getCity()); // 市
                        break;
                    case 7:
                        hssfCell.setCellValue("-1".equals(users.get(i).getArea()) ? null : users.get(i).getArea()); // 区
                        break;
                    case 8:
                        hssfCell.setCellValue(users.get(i).getAddress()); // 详细地址
                        break;
                    case 9:
                        hssfCellStyle.setDataFormat(format.getFormat("yyyy-MM-dd  hh:mm:ss"));
                        hssfCell.setCellValue(users.get(i).getCreateTime()); // 注册时间
                        break;
                }
                // 设置cell样式
                hssfCell.setCellStyle(hssfCellStyle);
            }
        }
        // 获得输出流,该输出流的输出介质是客户端浏览器（可不可以根据字节流输出到浏览器的进度推算出进度条的值？？？）
        OutputStream os = response.getOutputStream();
        BufferedOutputStream bufferOs = new BufferedOutputStream(os);
        workBook.write(bufferOs);
        bufferOs.flush();
        bufferOs.close();
        workBook.close();
    }

    /**
     * 导入用户信息
     *
     * @param file
     * @return
     */
    @Override
    public Object importUser(MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            List<User> users = new ArrayList();
            workBook = new HSSFWorkbook(file.getInputStream()); // 工作簿
            // 遍历Excel中所有的sheet
            for (int sheetIndex = 0; sheetIndex < workBook.getNumberOfSheets(); sheetIndex++) {
                sheet = workBook.getSheetAt(sheetIndex);
                // 遍历当前sheet中的所有row, 跳过表头(3行)和标题行(1行)
                for (int rowIndex = 4; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    row = sheet.getRow(rowIndex);
                    User user = new User();
                    // 遍历当前row中的所有cell
                    for (int cellIndex = 0; cellIndex <= row.getLastCellNum(); cellIndex++) {
                        cell = row.getCell(cellIndex);
                        switch (cellIndex) {
                            case 0:
                                user.setName(cell.getStringCellValue());
                                break;
                            case 1:
                                user.setSex(cell.getStringCellValue());
                                break;
                            case 2:
                                user.setPhone(cell.getStringCellValue());
                                break;
                            case 3:
                                user.setEmail(cell.getStringCellValue());
                                break;
                            case 4:
                                user.setHobby(cell.getStringCellValue());
                                break;
                            case 5:
                                user.setProvince(cell.getStringCellValue());
                                break;
                            case 6:
                                user.setCity(cell.getStringCellValue());
                                break;
                            case 7:
                                user.setArea(cell.getStringCellValue() == null ? "-1" : cell.getStringCellValue());
                                break;
                            case 8:
                                user.setAddress(cell.getStringCellValue());
                                break;
                            case 9:
//                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(cell.getStringCellValue())
                                user.setCreateTime(cell.getDateCellValue());
                                break;
                        }
                    }
                    user.setId(StrUtil.getUuidStr());
                    users.add(user);
                }
                break;
            }
            userMapper.customInsert(users);
            return ResultUtil.success();
        }
        return ResultUtil.error(ResultEnum.ERROR_UPFILE_NULL);
    }
}
