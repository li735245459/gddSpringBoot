package snoob.gdd.service;

import snoob.gdd.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelService {
    void exportUser(User user, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
