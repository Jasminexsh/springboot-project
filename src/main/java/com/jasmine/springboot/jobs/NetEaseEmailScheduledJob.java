package com.jasmine.springboot.jobs;

import com.jasmine.springboot.model.mail.EmailModel;
import com.jasmine.springboot.model.mail.EmailSessionInfo;
import com.jasmine.springboot.service.mail.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 网易邮箱定时邮件Job
 *
 * @author xieshanghan
 * @version NetEaseEmailScheduledJob.java, v 0.1, 2023年01月04日 0:10 xieshanghan
 */
@Component
public class NetEaseEmailScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetEaseEmailScheduledJob.class);

    @Autowired
    private EmailService emailService;

    /**
     * 邮件定时发送
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduledSendEmail() {
        try {
            System.out.println("----开始发送邮件----");
            EmailSessionInfo sender = new EmailSessionInfo("send", "shanghanxie7@163.com", "OLEFWKKADDVZXRPU", "谢尚翰");
            EmailSessionInfo receiver = new EmailSessionInfo("receive", "1148177830@qq.com", "JAsmine");
            EmailModel emailModel = new EmailModel();
            emailModel.setSubject("定时邮件");
            emailModel.setContent("定时发送");
            emailModel.setSendTime(new Date());
            emailService.sendEmail(sender, receiver, emailModel);
            System.out.println("----邮件发送完毕----");
        } catch (Exception ex) {
            LOGGER.error("发送邮件失败：{}", ex.getMessage());
        }
    }

}