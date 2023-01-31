package com.jasmine.springboot.service.mail;

import com.jasmine.springboot.model.mail.EmailSessionInfo;
import com.jasmine.springboot.model.mail.EmailModel;

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
