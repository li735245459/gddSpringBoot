package snoob.gdd.properties;

import org.springframework.stereotype.Component;

@Component
public class Email {
    private String sender;
    private String receiver;
    private String subject;
    private String text;
}
