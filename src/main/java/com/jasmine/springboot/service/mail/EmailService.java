package com.jasmine.springboot.service.mail;

import com.jasmine.springboot.model.mail.EmailSessionInfo;
import com.jasmine.springboot.model.mail.EmailModel;

/**
 * 邮箱服务
 *
 * @author xieshanghan
 * @version EmailService.java, v 0.1, 2023年01月04日 11：17 xieshanghan
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
