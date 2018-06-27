package snoob.gdd.service;

import snoob.gdd.model.User;


public interface UserService {
    Object register(User user) throws Exception;

    Object login(User user) throws Exception;

    Object modifyPassword(User user) throws Exception;

    Object checkJWT(String jwt) throws Exception;

    Object home(String userId) throws Exception;

    Object page(User user, Integer pageNumber, Integer pageSize) throws Exception;

    Object delete(String id) throws Exception;

    Object modify(User user) throws Exception;
}
