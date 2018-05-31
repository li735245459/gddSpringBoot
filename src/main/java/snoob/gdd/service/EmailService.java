package snoob.gdd.service;

import snoob.gdd.model.EmailCode;

public interface EmailService {
    Object forgetPassword(EmailCode emailCode);
}
