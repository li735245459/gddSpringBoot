package snoob.gdd.service;

import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.model.Cover;
import snoob.gdd.model.User;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    /*用户模块*/
    void exportUser(HttpServletResponse response, User user) throws Exception;
    Object importUser(MultipartFile file) throws Exception;
    /*封面模块*/
    Object importCover(MultipartFile[] files, Cover cover) throws Exception;
}
