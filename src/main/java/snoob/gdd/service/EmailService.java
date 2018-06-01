package snoob.gdd.service;

public interface EmailService {
    Object sendEmail(String type, String receiver) throws Exception;
    Object sendHtmlEmail(String type, String receiver) throws Exception;
}
