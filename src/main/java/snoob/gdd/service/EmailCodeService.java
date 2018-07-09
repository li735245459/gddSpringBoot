package snoob.gdd.service;

import snoob.gdd.model.EmailCode;

public interface EmailCodeService {
    Object sendHtmlEmail(String type, String receiver) throws Exception;

    Object sendEmail(String type, String receiver) throws Exception;

    Object checkEmailCode(String type, String email, String code) throws Exception;

    Object page(EmailCode emailCode, Integer pageNumber, Integer pageSize) throws Exception;

    Object delete(String id) throws Exception;

}
