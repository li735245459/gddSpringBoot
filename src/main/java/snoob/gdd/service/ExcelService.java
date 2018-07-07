package snoob.gdd.service;

import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.model.User;

import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    void exportUser(HttpServletResponse response, User user) throws Exception;
    Object importUser(MultipartFile file) throws Exception;
}
