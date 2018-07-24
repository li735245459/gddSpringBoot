package snoob.gdd.service;

import snoob.gdd.model.EmailCode;

/**
 * 邮箱验证码接口
 */
public interface EmailCodeService {
    /**
     * 发送html格式邮件
     * @param type
     * @param receiver
     * @return
     * @throws Exception
     */
    Object sendHtmlEmail(String type, String receiver) throws Exception;

    /**
     * 检查邮箱验证码是否匹配
     * @param type
     * @param email
     * @param code
     * @return
     * @throws Exception
     */
    Object checkEmailCode(String type, String email, String code) throws Exception;

    /**
     * 分页查询邮件
     * @param emailCode
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    Object page(EmailCode emailCode, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除邮件
     * @param id
     * @return
     * @throws Exception
     */
    Object delete(String id) throws Exception;

}
