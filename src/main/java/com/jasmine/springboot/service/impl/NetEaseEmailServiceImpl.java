package com.jasmine.springboot.service.impl;

import com.jasmine.springboot.model.mail.EmailModel;
import com.jasmine.springboot.model.mail.EmailSessionInfo;
import com.jasmine.springboot.service.mail.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 网易邮箱服务实现类
 *
 * @author xieshanghan
 * @version NetEaseEmailServiceImpl.java, v 0.1, 2023年01月04日 15：21 xieshanghan
 */
@Service
public class NetEaseEmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetEaseEmailServiceImpl.class);

    /** Properties */
    private static final String MAIL_TRANSPORT_PROTOCOL_PROPERTY = "mail.transport.protocol";

    private static final String MAIL_TRANSPORT_PROTOCOL_VALUE = "smtp";

    private static final String MAIL_SMTP_HOST_PROPERTY = "mail.smtp.host";

    private static final String MAIL_SMTP_HOST_VALUE = "smtp.163.com";

    private static final String MAIL_SMTP_AUTH_PROPERTY = "mail.smtp.auth";

    private static final String MAIL_SMTP_AUTH_VALUE = "true";

    /** Charset **/

    private static final String CHARSET_UTF_8 = "UTF-8";

    @Override
    public void sendEmail(EmailSessionInfo sender, EmailSessionInfo receiver, EmailModel emailModel) {
        try {
            //参数配置
            Properties properties = new Properties();
            properties.setProperty(MAIL_TRANSPORT_PROTOCOL_PROPERTY, MAIL_TRANSPORT_PROTOCOL_VALUE);
            properties.setProperty(MAIL_SMTP_HOST_PROPERTY, MAIL_SMTP_HOST_VALUE);
            properties.setProperty(MAIL_SMTP_AUTH_PROPERTY, MAIL_SMTP_AUTH_VALUE);
            //创建会话
            Session session = Session.getInstance(properties);
            session.setDebug(true);
            //获取传输对象
            Transport transport = session.getTransport();
            //发送方连接邮件服务器
            String senderEmailAccout = sender.getEmailAccount();
            String senderAccessCode = sender.getAccessCode();
            transport.connect(senderEmailAccout, senderAccessCode);
            //生成邮件
            MimeMessage message = generateMimeMessage(session, sender, receiver, emailModel);
            //发送邮件
            transport.sendMessage(message, message.getAllRecipients());
            //关闭连接
            transport.close();
        } catch (Exception ex) {
            LOGGER.error("发送邮件失败：{}", ex.getMessage());
        }
    }

    private MimeMessage generateMimeMessage(Session session, EmailSessionInfo sender, EmailSessionInfo receiver, EmailModel emailModel) {
        try {
            //创建邮件
            MimeMessage mimeMessage = new MimeMessage(session);
            //发件人
            String senderEmailAccount = sender.getEmailAccount();
            String senderUserName = sender.getUserName();
            mimeMessage.setFrom(new InternetAddress(senderEmailAccount, senderUserName, CHARSET_UTF_8));
            //收件人
            String receiverEmailAccount = receiver.getEmailAccount();
            String receiverUserName = receiver.getUserName();
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiverEmailAccount, receiverUserName, CHARSET_UTF_8));
            //邮件主题
            String emailSubject = emailModel.getSubject();
            mimeMessage.setSubject(emailSubject, CHARSET_UTF_8);
            //邮件正文
            Object emailContent = emailModel.getContent();
            mimeMessage.setContent(emailContent, "text/html;charset=UTF-8");
            //发件时间
            Date emailSendTime = emailModel.getSendTime();
            mimeMessage.setSentDate(emailSendTime);
            //保存设置
            mimeMessage.saveChanges();
            return mimeMessage;
        } catch (Exception ex) {
            LOGGER.error("创建邮件失败：{}", ex.getMessage());
            return null;
        }
    }

}