package snoob.gdd.service;

import snoob.gdd.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    void exportUser(HttpServletResponse response, User user) throws Exception;
    Object importUser(HttpServletRequest request) throws Exception;
}
