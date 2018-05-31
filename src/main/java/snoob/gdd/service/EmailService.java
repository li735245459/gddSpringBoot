package snoob.gdd.service;

public interface EmailService {
    Object sendSimpleEmail(String receiver, String type);
    Object sendHtmlEmail(String receiver, String type);
}
