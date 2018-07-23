package snoob.gdd.service;

import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.model.Cover;
import snoob.gdd.model.User;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传与下载
 */
public interface FileService {
    /**
     * 导出用户信息: 根据条件查询用户信息并以Excel的形式导出到桌面
     *
     * @param response
     * @param user
     * @throws Exception
     */
    void exportUser(HttpServletResponse response, User user) throws Exception;

    /**
     * 导入用户信息: 读取Excel文件并解析导入到数据库
     *
     * @param file
     * @return
     * @throws Exception
     */
    Object importUser(MultipartFile file) throws Exception;

    /**
     * 批量上传图片到七牛云并将返回的图片地址存入数据库
     *
     * @param files
     * @param cover
     * @return
     * @throws Exception
     */
    Object importCover(MultipartFile[] files, Cover cover) throws Exception;
}
