package snoob.gdd.model;

import snoob.gdd.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

/**
 * 邮箱验证码信息
 */
@Table(name = "email_code")
public class EmailCode {
    /**
     * 编号,UUID
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    /**
     * 发送时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 验证类型（0:忘记密码模块）
     */
    private String type;

    /**
     * 发送者邮箱
     */
    private String sender;

    /**
     * 接收者邮箱
     */
    private String receiver;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 验证码
     */
    private String code;

    /**
     * 获取编号,UUID
     *
     * @return id - 编号,UUID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号,UUID
     *
     * @param id 编号,UUID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取发送时间
     *
     * @return create_time - 发送时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置发送时间
     *
     * @param createTime 发送时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取验证类型（0:忘记密码模块）
     *
     * @return type - 验证类型（0:忘记密码模块）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置验证类型（0:忘记密码模块）
     *
     * @param type 验证类型（0:忘记密码模块）
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取发送者邮箱
     *
     * @return sender - 发送者邮箱
     */
    public String getSender() {
        return sender;
    }

    /**
     * 设置发送者邮箱
     *
     * @param sender 发送者邮箱
     */
    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    /**
     * 获取接收者邮箱
     *
     * @return receiver - 接收者邮箱
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 设置接收者邮箱
     *
     * @param receiver 接收者邮箱
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    /**
     * 获取邮件主题
     *
     * @return subject - 邮件主题
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置邮件主题
     *
     * @param subject 邮件主题
     */
    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    /**
     * 获取邮件内容
     *
     * @return content - 邮件内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置邮件内容
     *
     * @param content 邮件内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取验证码
     *
     * @return code - 验证码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置验证码
     *
     * @param code 验证码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}