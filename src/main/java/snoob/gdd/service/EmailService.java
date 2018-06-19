package snoob.gdd.service;

public interface EmailService {
    Object sendHtmlEmail(String type, String receiver) throws Exception;

    Object sendEmail(String type, String receiver) throws Exception;

    Object checkEmailCode(String type, String email, String code) throws Exception;
}
