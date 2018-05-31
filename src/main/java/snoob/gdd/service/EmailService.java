package snoob.gdd.service;

public interface EmailService {
    Object sendSimpleEmail(String type, String sender, String receiver, String subject, String content);
    Object sendHtmlEmail(String type, String sender, String receiver, String subject, String content);
}
