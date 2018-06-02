package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.service.EmailService;

import javax.annotation.Resource;

/**
 * 邮箱验证码模块
 */
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
//        return emailService.sendEmail(type, receiver);
        return emailService.sendHtmlEmail(type, receiver);
    }

    /**
     * 检查邮箱验证码
     *
     * @param type
     * @param email
     * @param code
     * @return
     * @throws Exception
     */
    @GetMapping("/checkEmailCode/{type}/{email}/{code}")
    public Object checkEmailCode(
            @PathVariable(value = "type") String type,
            @PathVariable(value = "email") String email,
            @PathVariable(value = "code") String code
    ) throws Exception {
        return emailService.checkEmailCode(type, email, code);
    }
}
