package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.service.EmailService;

import javax.annotation.Resource;

/**
 * 邮箱验证码模块
 */
@CrossOrigin(origins = "http://127.0.0.1:4200")
@RequestMapping("/email")
@RestController
public class EmailCodeController {

    @Resource
    private EmailService emailService;

    /**
     * 发送邮件
     *
     * @return
     */
    @GetMapping("/send/{type}/{receiver}")
    public Object send(
            @PathVariable(value = "type") String type,
            @PathVariable(value = "receiver") String receiver) throws Exception {
        return emailService.sendEmail(type, receiver);
    }
}
