package snoob.gdd.service;

import snoob.gdd.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface UserService {
    Object modify(User user) throws Exception;
    Object modifyPassword(User user) throws Exception;

    Object login(User user) throws Exception;

    Object checkJWT(String jwt) throws Exception;

    Object home(String userId) throws Exception;

    Object page(User user, Integer pageNumber, Integer pageSize) throws Exception;

    Object delete(String id) throws Exception;

    Object export(User user, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
