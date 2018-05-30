package snoob.gdd.service;

import snoob.gdd.model.User;


public interface UserService {
    Object register(User user);
    Object login(User user);
    Object sendEmail(String email, String codeType);
}
