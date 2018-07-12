package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.EmailCode;
import snoob.gdd.service.EmailCodeService;

import javax.annotation.Resource;

/**
 * 邮箱验证码模块
 */
@RequestMapping("/email")
@RestController
public class EmailCodeController {

    @Resource
    private EmailCodeService emailCodeService;

    /**
     * 发送验证码到邮件
     *
     * @param type
     * @param receiver
     * @return
     * @throws Exception
     */
    @GetMapping("/send/{type}/{receiver}")
    public Object send(
            @PathVariable(value = "type") String type,
            @PathVariable(value = "receiver") String receiver) throws Exception {
        return emailCodeService.sendHtmlEmail(type, receiver);
    }

    /**
     * 检查邮箱、验证码是否匹配
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
        return emailCodeService.checkEmailCode(type, email, code);
    }

    /**
     * 分页查询
     *
     * @param emailCode
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/page/{pageNumber}/{pageSize}")
    public Object page(@RequestBody EmailCode emailCode,
                       @PathVariable(value = "pageNumber") Integer pageNumber,
                       @PathVariable(value = "pageSize") Integer pageSize
    ) throws Exception {
        return emailCodeService.page(emailCode, pageNumber, pageSize);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody String id) throws Exception {
        return emailCodeService.delete(id);
    }


}
