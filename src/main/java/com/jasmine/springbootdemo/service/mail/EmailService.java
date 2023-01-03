package com.jasmine.springbootdemo.service.mail;

import com.jasmine.springbootdemo.model.mail.EmailSessionInfo;
import com.jasmine.springbootdemo.model.mail.EmailModel;

/**
 * 通用邮箱服务
 *
 * @author xieshanghan
 * @version v0.1, 2023/1/4
 */
public interface EmailService {

    /**
     * 发送邮件
     *
     * @param sender     发送方
     * @param receiver   接收方
     * @param emailModel 邮件信息
     */
    void sendEmail(EmailSessionInfo sender, EmailSessionInfo receiver, EmailModel emailModel);

}
